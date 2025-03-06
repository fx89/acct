package com.desolatetimelines.acct.currency.service;

import com.desolatetimelines.acct.currency.collector.service.CurrencyCollectorService;
import com.desolatetimelines.acct.currency.exception.AcctCurrencyServiceMonitoredCurrencyConstraintViolationException;
import com.desolatetimelines.acct.currency.exception.AcctCurrencyServiceMonitoredCurrencyNotFoundException;
import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrencyRecord;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Main class of the services layer of the ACCT currency service
 */
@Service
public class AcctCurrencyService {

    private final AcctCurrencyDataService dataService;

    private final AcctCurrencyErrorCodesRegistryService errors;

    private final AcctCurrencyCollectionService currencyCollectionService;

    public AcctCurrencyService(
        AcctCurrencyDataService dataService,
        AcctCurrencyErrorCodesRegistryService errors,
        AcctCurrencyCollectionService currencyCollectionService
    ) {
        this.dataService = dataService;
        this.errors = errors;
        this.currencyCollectionService = currencyCollectionService;
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
