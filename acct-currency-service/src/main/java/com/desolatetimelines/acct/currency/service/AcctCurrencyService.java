package com.desolatetimelines.acct.currency.service;

import com.desolatetimelines.acct.common.model.ObjectTypes;
import com.desolatetimelines.acct.currency.collector.service.CurrencyCollectorService;
import com.desolatetimelines.acct.currency.exception.AcctCurrencyServiceMonitoredCurrencyConstraintViolationException;
import com.desolatetimelines.acct.currency.exception.AcctCurrencyServiceMonitoredCurrencyNotFoundException;
import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrencyRecord;
import com.desolatetimelines.acct.currency.model.MonitoredCurrencyRecord;
import com.desolatetimelines.acct.usage.ws.client.RESTUsageEndpointClient;
import com.desolatetimelines.acct.usage.ws.model.ServiceItemTypesList;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Main class of the services layer of the ACCT currency service
 */
@Service
public class AcctCurrencyService {

    private final AcctCurrencyDataService dataService;

    private final AcctCurrencyErrorCodesRegistryService errors;

    private final AcctCurrencyCollectionService currencyCollectionService;

    private final RESTUsageEndpointClient usageEndpointClient;

    private final String applicationName;

    private final String contextPath;

    public AcctCurrencyService(
        AcctCurrencyDataService dataService,
        AcctCurrencyErrorCodesRegistryService errors,
        AcctCurrencyCollectionService currencyCollectionService,
        RESTUsageEndpointClient usageEndpointClient,
        @Value("${CURRENCY_APPLICATION_NAME}") String applicationName,
        @Value("${CURRENCY_SERVER_CONTEXT_PATH}") String contextPath
    ) {
        this.dataService = dataService;
        this.errors = errors;
        this.currencyCollectionService = currencyCollectionService;
        this.usageEndpointClient = usageEndpointClient;
        this.applicationName = applicationName;
        this.contextPath = contextPath;
    }

    /**
     * Registers in-use item types with the usage service upon startup
     */
    @SuppressWarnings("unused")
    @EventListener(ApplicationReadyEvent.class)
    protected void registerInUseObjectTypes() {
        usageEndpointClient.registerItemTypesForService(
            ServiceItemTypesList.builder()
                .withServiceName(applicationName)
                .withServiceContextPath(contextPath)
                .withItemType(List.of(
                    ObjectTypes.BANK.name(),
                    ObjectTypes.CURRENCY.name()
                ))
                .build()
        );
    }

    /**
     * Returns the UUIDs of any used items of the given type and that can be found in the given list
     *
     * @param objectType the given type
     * @param itemUUIDs  the given list
     */
    public Collection<String> getInUseItemUUIDs(String objectType, Collection<String> itemUUIDs) {
        // If the object type is BANK then search monitored currencies for banks
        if (Objects.equals(objectType, ObjectTypes.BANK.name())) {
            return
                dataService.findMonitoredCurrenciesByBankUUIDIn(itemUUIDs)
                    .stream()
                    .map(AcctMonitoredCurrency::getBankUUID)
                    .distinct()
                    .toList();
        }

        // If the object type is CURRENCY then search monitored currencies for currencies and quoted currencies
        if (Objects.equals(objectType, ObjectTypes.CURRENCY.name())) {
            return
                Stream.concat(
                        dataService.findMonitoredCurrenciesByCurrencyUUIDIn(itemUUIDs)
                            .stream()
                            .map(AcctMonitoredCurrency::getCurrencyUUID),
                        dataService.findMonitoredCurrenciesByQuotedCurrencyUUIDIn(itemUUIDs)
                            .stream()
                            .map(AcctMonitoredCurrency::getQuotedCurrencyUUID)
                    ).distinct()
                    .toList();
        }

        // If this point has been reached, it means that either the item type is not supported
        // or the code for handling the object type is missing from above
        throw new IllegalArgumentException("Object type [" + objectType + "] not supported");
    }

    /**
     * Creates or updates the {@link AcctMonitoredCurrency monitored currency}. The decision to
     * create or update is based on the existence of the given monitored currency UUID. If the
     * monitored currency UUID is not given, a new monitored currency entity is created. Otherwise,
     * an existing monitored currency is updated. Whatever the case, the given properties are
     * set for the monitored currency entity.<br />
     * <br />
     * If the monitored currency UUID is given and there is no currency for the given UUID, an
     * exception is thrown.<br />
     * <br />
     * If a monitored currency already exists for the given bank UUID, currency UUID and quoted
     * currency UUID, and it is not the monitored currency being updated, an exception is thrown.
     *
     * @param monitoredCurrencyUUID the given monitored currency UUID
     * @param bankUUID              the UUID of the bank that offers the exchange rate
     * @param currencyUUID          the UUID of the currency being monitored
     * @param quoteCurrencyUUID     the UUID of the currency against which the monitored currency is measured
     * @param collectorName         the name of the collector service that handles the data collection
     * @param scheduledTimeHhMm     the strTime of day, formatted as "HH:MM", when the exchange rate is expected to be updated
     * @return a reference to the persisted {@link AcctMonitoredCurrency monitored currency}
     */
    @Transactional
    public AcctMonitoredCurrency saveMonitoredCurrency(
        String monitoredCurrencyUUID,
        String bankUUID,
        String currencyUUID,
        String quoteCurrencyUUID,
        String collectorName,
        String scheduledTimeHhMm
    ) {
        // Get or create the monitored currency based on the existence of the UUID
        final Optional<AcctMonitoredCurrency> optionalMonitoredCurrency =
            getOrCreateOptionalEntity(
                monitoredCurrencyUUID,
                dataService::findMonitoredCurrencyByMonitoredCurrencyUUID,
                dataService::createNewAcctMonitoredCurrency,
                AcctMonitoredCurrency::setMonitoredCurrencyUUID
            );

        // If the monitored currency does not exist, throw an exception
        final AcctMonitoredCurrency monitoredCurrency =
            optionalMonitoredCurrency.orElseThrow(
                () -> new AcctCurrencyServiceMonitoredCurrencyNotFoundException(errors, monitoredCurrencyUUID)
            );

        // Set the monitored currency properties
        monitoredCurrency.setBankUUID(bankUUID);
        monitoredCurrency.setCurrencyUUID(currencyUUID);
        monitoredCurrency.setQuotedCurrencyUUID(quoteCurrencyUUID);
        monitoredCurrency.setCollectorName(collectorName);
        monitoredCurrency.setScheduledTimeHHMM(scheduledTimeHhMm);

        // Persist the monitored currency and return a reference
        try {
            return dataService.saveMonitoredCurrency(monitoredCurrency);
        }
        // Throw a service layer exception if a constraint violation exception occurs
        catch (DataIntegrityViolationException e) {
            throw
                new AcctCurrencyServiceMonitoredCurrencyConstraintViolationException(
                    errors,
                    bankUUID,
                    currencyUUID,
                    quoteCurrencyUUID,
                    e
                );
        }
    }

    /**
     * Returns a collection of all the {@link AcctMonitoredCurrency monitored currencies}
     */
    public Collection<AcctMonitoredCurrency> getMonitoredCurrencies() {
        return dataService.findAllMonitoredCurrencies();
    }

    /**
     * Returns all the available currency collectors mapped by name
     */
    public Map<String, CurrencyCollectorService<?>> getCurrencyCollectorsByName() {
        return currencyCollectionService.getCurrencyCollectorsByName();
    }

    /**
     * Returns a collection of all the available {@link AcctMonitoredCurrencyRecord monitored currency records}
     * for the currency with the given monitored currency UUID, sorted by
     * {@link AcctMonitoredCurrencyRecord#getMonitoredCurrencyRecordDate() record date}
     *
     * @param monitoredCurrencyUUID the given monitored currency UUID
     */
    public Collection<AcctMonitoredCurrencyRecord> getMonitoredCurrencyRecordsSortedByDate(
        String monitoredCurrencyUUID
    ) {
        // Attempt to find the monitored currency or throw an exception if not found
        final AcctMonitoredCurrency monitoredCurrency = findMonitoredCurrencyOrFail(monitoredCurrencyUUID);

        // Get the records, sort and return
        return
            dataService.findMonitoredCurrencyRecordsByMonitoredCurrency(monitoredCurrency)
                .stream()
                .sorted(Comparator.comparing(AcctMonitoredCurrencyRecord::getMonitoredCurrencyRecordDate))
                .toList();
    }

    /**
     * Deletes the {@link AcctMonitoredCurrency monitored currency} with the given monitored currency UUID.
     * Throws an exception if the referenced monitored currency does not exist.
     *
     * @param monitoredCurrencyUUID the given monitored currency UUID
     */
    @Transactional
    public void deleteMonitoredCurrencyByMonitoredCurrencyUUID(String monitoredCurrencyUUID) {
        // Attempt to find the monitored currency or throw an exception if not found
        final AcctMonitoredCurrency monitoredCurrency = findMonitoredCurrencyOrFail(monitoredCurrencyUUID);

        // Delete the monitored currency
        dataService.deleteMonitoredCurrency(monitoredCurrency);
    }

    /**
     * Runs the collection job for the {@link AcctMonitoredCurrency monitored currency} with the
     * given monitored currency UUID. Throw an exception if the referenced monitored currency does
     * not exist.
     *
     * @param monitoredCurrencyUUID the given monitored currency UUID
     */
    public void manuallyCollectMonitoredCurrencyRecords(String monitoredCurrencyUUID) {
        // Attempt to find the monitored currency or throw an exception if not found
        final AcctMonitoredCurrency monitoredCurrency = findMonitoredCurrencyOrFail(monitoredCurrencyUUID);

        // Run the collection job
        currencyCollectionService.handleCurrencyExchangeRatesCollection(monitoredCurrency);
    }

    /**
     * Creates or updates the {@link AcctMonitoredCurrencyRecord monitored currency records} identified
     * by the {@link MonitoredCurrencyRecord#monitoredCurrencyRecordDate() record dates} of the given
     * records collection for the {@link AcctMonitoredCurrency monitored currency} identified by the given
     * monitored currency UUID
     *
     * @param monitoredCurrencyUUID the given monitored currency UUID
     * @param records               the given records collection
     */
    @Transactional
    public void createOrUpdateMonitoredCurrencyRecords(
        String monitoredCurrencyUUID,
        Collection<MonitoredCurrencyRecord> records
    ) {
        // Attempt to find the monitored currency or throw an exception if not found
        final AcctMonitoredCurrency monitoredCurrency = findMonitoredCurrencyOrFail(monitoredCurrencyUUID);

        // Attempt to find any already-existing records for the given date
        final Collection<AcctMonitoredCurrencyRecord> existingRecords =
            dataService.findAllMonitoredCurrencyRecordsByMonitoredCurrencyAndMonitoredCurrencyRecordDateIn(
                monitoredCurrency,
                records.stream()
                    .map(MonitoredCurrencyRecord::monitoredCurrencyRecordDate)
                    .toList()
            );

        // For each input record...
        records.forEach(record -> {
            // Get the already existing record or create a new one
            final AcctMonitoredCurrencyRecord dbRec =
                existingRecords.stream()
                    .filter(rec ->
                        Objects.equals(
                            rec.getMonitoredCurrencyRecordDate(),
                            record.monitoredCurrencyRecordDate()
                        )
                    )
                    .findFirst()
                    .orElseGet(() -> {
                        final AcctMonitoredCurrencyRecord newRec = dataService.createNewAcctMonitoredCurrencyRecord();
                        newRec.setMonitoredCurrency(monitoredCurrency);
                        newRec.setMonitoredCurrencyRecordDate(record.monitoredCurrencyRecordDate());
                        return newRec;
                    });

            // Update the values
            dbRec.setMonitoredCurrencyRecordPurchaseValue(record.monitoredCurrencyRecordPurchaseValue());
            dbRec.setMonitoredCurrencyRecordSaleValue(record.monitoredCurrencyRecordSaleValue());

            // Save the record
            dataService.saveMonitoredCurrencyRecord(dbRec);
        });
    }

    /**
     * Retrieves the monitored currency with the given monitored currency UUID or raises an exception
     * if the referenced monitored currency does not exist
     *
     * @param monitoredCurrencyUUID the given monitored currency UUID
     */
    private AcctMonitoredCurrency findMonitoredCurrencyOrFail(String monitoredCurrencyUUID) {
        // Attempt to find the monitored currency or throw an exception if not found
        return
            dataService.findMonitoredCurrencyByMonitoredCurrencyUUID(monitoredCurrencyUUID)
                .orElseThrow(
                    () -> new AcctCurrencyServiceMonitoredCurrencyNotFoundException(errors, monitoredCurrencyUUID)
                );
    }

    // TODO: Put this in a common place, where it can be accessed by both the Catalog and Currency services
    private <T> Optional<T> getOrCreateOptionalEntity(
        String entityUUID,
        Function<String, Optional<T>> findFunction,
        Supplier<T> newEntitySupplier,
        BiConsumer<T, String> uuidSettingConsumer
    ) {
        return
            Optional
                .ofNullable(entityUUID)
                .map(findFunction)
                .orElseGet(() -> {
                    T newEntity = newEntitySupplier.get();
                    uuidSettingConsumer.accept(newEntity, UUID.randomUUID().toString());
                    return Optional.of(newEntity);
                });
    }

}
