package com.desolatetimelines.acct.currency.service;

import com.desolatetimelines.acct.catalog.ws.endpoint.BanksEndpoint;
import com.desolatetimelines.acct.catalog.ws.endpoint.CurrenciesEndpoint;
import com.desolatetimelines.acct.catalog.ws.model.BankProperties;
import com.desolatetimelines.acct.catalog.ws.model.CurrencyProperties;
import com.desolatetimelines.acct.currency.collector.model.CollectedCurrencyExchangeRecord;
import com.desolatetimelines.acct.currency.collector.service.CurrencyCollectorService;
import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrencyRecord;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.ZoneOffset.UTC;
import static java.util.Collections.emptyList;

/**
 * Handles the collection of currency exchange records for various currencies
 * and from various banks, according to the definitions accessible through the
 * data service.
 */
@Service
public class AcctCurrencyCollectionService {

    private final AcctCurrencyDataService dataService;

    private final AcctCurrencyExchangeRecordsPersistenceService persistenceService;

    private final BanksEndpoint banksEndpoint;

    private final CurrenciesEndpoint currenciesEndpoint;

    private final Map<String, CurrencyCollectorService> currencyCollectorsByName;

    public AcctCurrencyCollectionService(
        AcctCurrencyDataService dataService,
        AcctCurrencyExchangeRecordsPersistenceService persistenceService,
        BanksEndpoint banksEndpoint,
        CurrenciesEndpoint currenciesEndpoint,
        Collection<CurrencyCollectorService> currencyCollectors
    ) {
        this.dataService = dataService;
        this.persistenceService = persistenceService;
        this.banksEndpoint = banksEndpoint;
        this.currenciesEndpoint = currenciesEndpoint;
        this.currencyCollectorsByName = mapCurrencyCollectorsByName(currencyCollectors);
    }

    private static Map<String, CurrencyCollectorService> mapCurrencyCollectorsByName(
        Collection<CurrencyCollectorService> currencyCollectors
    ) {
        // Create a map of the proper size
        final Map<String, CurrencyCollectorService> currencyCollectorsByName =
            new HashMap<>(currencyCollectors.size());

        // Populate the map
        currencyCollectors.forEach(currencyCollector ->
            currencyCollectorsByName.put(
                currencyCollector.getClass().getCanonicalName(),
                currencyCollector
            )
        );

        // Return a reference
        return currencyCollectorsByName;
    }

    /**
     * Returns the available {@link CurrencyCollectorService currency collectors} mapped by their names
     */
    public Map<String, CurrencyCollectorService> getCurrencyCollectorsByName() {
        return currencyCollectorsByName;
    }

    /**
     * Collects the exchange records for all the {@link AcctMonitoredCurrency monitored currencies}
     * that have a {@link AcctMonitoredCurrency#getCollectorName() curency collector} set and for
     * which it is time to collect this data. The collected data is persisted to the data store.
     */
    public void handleCurrencyExchangeRatesCollection() {
        // find all the monitored currencies
        final Collection<AcctMonitoredCurrency> allMonitoredCurrencies =
            dataService.findAllMonitoredCurrencies();

        // Get all the bank UUIDs configured for the monitored currencies
        final Collection<String> bankUUIDs =
            allMonitoredCurrencies.stream()
                .map(AcctMonitoredCurrency::getBankUUID)
                .distinct()
                .toList();

        // Get the banks with the UUIDs extracted earlier and create a map
        // of bank codes by bank UUIDs
        final Map<String, String> bankCodesByBankUUID =
            banksEndpoint.getBanks()
                .stream()
                .filter(bank -> bankUUIDs.contains(bank.bankUUID()))
                .collect(Collectors.toMap(
                    BankProperties::bankUUID,
                    BankProperties::bankCode
                ));

        // Get all the currency UUIDs for the monitored currencies
        final Collection<String> currencyUUIDs =
            allMonitoredCurrencies.stream()
                .map(AcctMonitoredCurrency::getCurrencyUUID)
                .distinct()
                .toList();

        // Get the currencies with the UUIDs extracted earlier and create a map
        // of currency codes by currency UUIDs
        final Map<String, String> currencyCodesByCurrencyUUID =
            currenciesEndpoint.getCurrencies()
                .stream()
                .filter(currency -> currencyUUIDs.contains(currency.currencyUUID()))
                .collect(Collectors.toMap(
                    CurrencyProperties::currencyUUID,
                    CurrencyProperties::currencyCode
                ));

        // For each monitored currency, handle currency exchange rates collection
        allMonitoredCurrencies.forEach(monitoredCurrency ->
            handleCurrencyExchangeRatesCollection(
                monitoredCurrency,
                bankCodesByBankUUID.get(monitoredCurrency.getBankUUID()),
                currencyCodesByCurrencyUUID.get(monitoredCurrency.getCurrencyUUID())
            )
        );
    }

    private void handleCurrencyExchangeRatesCollection(
        AcctMonitoredCurrency monitoredCurrency,
        String bankCode,
        String currencyCode
    ) {
        // Get a reference to the currency collector defined for this currency
        final Optional<CurrencyCollectorService> optionalCurrencyCollector =
            identifyCurrencyCollectorServiceForMonitoredCurrency(monitoredCurrency);

        // If there's no currency collector defined then exit
        // because no collection can be done
        if (optionalCurrencyCollector.isEmpty()) {
            return;
        }

        // If the collector does not support the bank code then exit
        // because the collector cannot collect data from the bank
        if (!optionalCurrencyCollector.get().getSupportedBankCodes().contains(bankCode)) {
            persistenceService.saveCurrencyErrorMessage(
                monitoredCurrency,
                "Bank not supported by the currency exchange records collector"
            );
            return;
        }

        // If the monitored currency exchange records have been collected today then exit
        // because the data was already collected for the day
        if (monitoredCurrency.getLastMonitoredCurrencyRecordDate() != null &&
            monitoredCurrency.getLastMonitoredCurrencyRecordDate().isAfter(
                LocalDate.now().atStartOfDay().toInstant(UTC)
            )
        ) {
            return;
        }

        // Get the monitored currency's scheduled time (HH:MM)
        final Instant todayAtScheduledTime = todayAtTimeStrHHMM(monitoredCurrency.getScheduledTimeHHMM());

        // If the current time is before the scheduled time then exit
        // because it's too early to collect the data
        if (Instant.now().isBefore(todayAtScheduledTime)) {
            return;
        }

        // Collect the monitored currency exchange records
        Collection<CollectedCurrencyExchangeRecord> collectedCurrencyExchangeRecords =
            optionalCurrencyCollector.get().collectRecords(bankCode, currencyCode);

        // Persists the records and update the monitored currency
        try {
            persistenceService.persistMonitoredCurrencyExchangeRecords(
                monitoredCurrency,
                collectedCurrencyExchangeRecords
            );
        }
        // Record any exception that might occur
        catch (Exception e) {
            persistenceService.saveCurrencyErrorMessage(
                monitoredCurrency,
                "Exception occurred while trying to collect exchange rates: " + e.getMessage()
            );
        }
    }

    /**
     * Attempts to identify the {@link CurrencyCollectorService currency collector} set for the referenced
     * monitored currency, if it is set.
     *
     * @param monitoredCurrency the given monitored currency
     * @return either an empty optional or one containing the reference to the currency collector
     */
    private Optional<CurrencyCollectorService> identifyCurrencyCollectorServiceForMonitoredCurrency(
        AcctMonitoredCurrency monitoredCurrency
    ) {
        return
            Optional
                .ofNullable(monitoredCurrency.getCollectorName())
                .map(currencyCollectorsByName::get);
    }

    /**
     * Returns an instant of the current date at the given time
     *
     * @param timeStrHHMM the given time, formatted as "HH:MM", expressed in UTC
     */
    private static Instant todayAtTimeStrHHMM(String timeStrHHMM) {
        // Parse the time string
        final LocalTime time;
        try {
            time = LocalTime.parse(timeStrHHMM);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("The given time string [" + timeStrHHMM + "] is badly formatted");
        }

        // Return the current date at the given time
        return LocalDate.now().atTime(time).toInstant(UTC);
    }

    /**
     * Persists collected currency exchange records in a dedicated transaction
     * that can be rolled back if anything goes wrong
     */
    @Service
    public static class AcctCurrencyExchangeRecordsPersistenceService {

        private final AcctCurrencyDataService dataService;

        public AcctCurrencyExchangeRecordsPersistenceService(AcctCurrencyDataService dataService) {
            this.dataService = dataService;
        }

        /**
         * Persists collected currency exchange records in a dedicated transaction
         * that can be rolled back if anything goes wrong
         */
        @Transactional
        public void persistMonitoredCurrencyExchangeRecords(
            AcctMonitoredCurrency monitoredCurrency,
            Collection<CollectedCurrencyExchangeRecord> currencyExchangeRecords
        ) {
            // Identify any exchange rates history that might have already been recorded for the currency
            // on the dates of the collected currency exchange records
            final Collection<AcctMonitoredCurrencyRecord> historyRecords =
                dataService.findAllMonitoredCurrencyRecordsByMonitoredCurrencyAndMonitoredCurrencyRecordDateIn(
                    monitoredCurrency,
                    currencyExchangeRecords.stream().map(CollectedCurrencyExchangeRecord::date).toList()
                );

            // Split the collected records into "to be updated" and "to be created"
            final Map<String, List<MatchedCurrencyExchangeRecord>> recordsByRequiredOperation =
                currencyExchangeRecords.stream()
                    .map(collectedRecord ->
                        new MatchedCurrencyExchangeRecord(
                            collectedRecord,
                            historyRecords.stream()
                                .filter(registeredRecord ->
                                    Objects.equals(
                                        registeredRecord.getMonitoredCurrencyRecordDate(),
                                        collectedRecord.date()
                                    )
                                )
                                .findFirst().orElse(null)
                        )
                    )
                    .collect(
                        Collectors.groupingBy(matchedRecord ->
                            matchedRecord.registeredRecord == null
                                ? "TO_BE_CREATED"
                                : "TO_BE_UPDATED"
                        )
                    );

            // Update the records to be updated
            Optional.ofNullable(recordsByRequiredOperation.get("TO_BE_UPDATED")).orElse(emptyList())
                .forEach(record -> {
                    // Update the registered record
                    record.registeredRecord().setMonitoredCurrencyRecordPurchaseValue(record.collectedRecord().buyPrice());
                    record.registeredRecord().setMonitoredCurrencyRecordSaleValue(record.collectedRecord().sellPrice());

                    // Persist the registered record
                    dataService.saveMonitoredCurrencyRecord(record.registeredRecord());
                });

            // Add the records to be added
            Optional.ofNullable(recordsByRequiredOperation.get("TO_BE_CREATED")).orElse(emptyList())
                .forEach(record -> {
                    // Create the record
                    final AcctMonitoredCurrencyRecord monitoredCurrencyRecord =
                        dataService.createNewAcctMonitoredCurrencyRecord();

                    // Populate the record
                    monitoredCurrencyRecord.setMonitoredCurrency(monitoredCurrency);
                    monitoredCurrencyRecord.setMonitoredCurrencyRecordDate(record.collectedRecord().date());
                    monitoredCurrencyRecord.setMonitoredCurrencyRecordSaleValue(record.collectedRecord().sellPrice());
                    monitoredCurrencyRecord.setMonitoredCurrencyRecordPurchaseValue(record.collectedRecord().buyPrice());

                    // Save the record
                    dataService.saveMonitoredCurrencyRecord(monitoredCurrencyRecord);
                });

            // Find the last collected record
            final CollectedCurrencyExchangeRecord lastCollectedRecord =
                currencyExchangeRecords.stream()
                    .max(Comparator.comparing(CollectedCurrencyExchangeRecord::date))
                    .orElseThrow();

            // Update the monitored currency
            monitoredCurrency.setLastCollectionDate(Instant.now());
            monitoredCurrency.setLastMonitoredCurrencyRecordDate(lastCollectedRecord.date());
            monitoredCurrency.setLastMonitoredCurrencyRecordPurchaseValue(lastCollectedRecord.buyPrice());
            monitoredCurrency.setLastMonitoredCurrencyRecordSaleValue(lastCollectedRecord.sellPrice());

            // Persist the monitored currency
            dataService.saveMonitoredCurrency(monitoredCurrency);
        }

        @Transactional
        public void saveCurrencyErrorMessage(AcctMonitoredCurrency monitoredCurrency, String errorMessage) {
            monitoredCurrency.setLastCollectionDate(Instant.now());
            monitoredCurrency.setCollectionErrorMessage(errorMessage);
            dataService.saveMonitoredCurrency(monitoredCurrency);
        }

        private record MatchedCurrencyExchangeRecord(
            CollectedCurrencyExchangeRecord collectedRecord,
            AcctMonitoredCurrencyRecord registeredRecord
        ) {
        }
    }

}
