package com.desolatetimelines.acct.currency.service;

import com.desolatetimelines.acct.catalog.ws.endpoint.BanksEndpoint;
import com.desolatetimelines.acct.catalog.ws.endpoint.CurrenciesEndpoint;
import com.desolatetimelines.acct.catalog.ws.model.BankProperties;
import com.desolatetimelines.acct.catalog.ws.model.CurrencyProperties;
import com.desolatetimelines.acct.currency.collector.model.BankParameters;
import com.desolatetimelines.acct.currency.collector.model.CollectedCurrencyExchangeRecord;
import com.desolatetimelines.acct.currency.collector.model.CollectionSession;
import com.desolatetimelines.acct.currency.collector.model.SessionParameters;
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
import static java.util.stream.Collectors.groupingBy;

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

    private final Map<String, CurrencyCollectorService<? extends CollectionSession>> currencyCollectorsByName;

    public AcctCurrencyCollectionService(
        AcctCurrencyDataService dataService,
        AcctCurrencyExchangeRecordsPersistenceService persistenceService,
        BanksEndpoint banksEndpoint,
        CurrenciesEndpoint currenciesEndpoint,
        Collection<CurrencyCollectorService<? extends CollectionSession>> currencyCollectors
    ) {
        this.dataService = dataService;
        this.persistenceService = persistenceService;
        this.banksEndpoint = banksEndpoint;
        this.currenciesEndpoint = currenciesEndpoint;
        this.currencyCollectorsByName = mapCurrencyCollectorsByName(currencyCollectors);
    }

    private static Map<String, CurrencyCollectorService<? extends CollectionSession>> mapCurrencyCollectorsByName(
        Collection<CurrencyCollectorService<? extends CollectionSession>> currencyCollectors
    ) {
        // Create a map of the proper size
        final Map<String, CurrencyCollectorService<? extends CollectionSession>> currencyCollectorsByName =
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
    public Map<String, CurrencyCollectorService<? extends CollectionSession>> getCurrencyCollectorsByName() {
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

        // Group monitored currencies by collector name (exclude the ones that do not specify a collector name)
        final Map<String, List<AcctMonitoredCurrency>> monitoredCurrenciesByCollectorName =
            allMonitoredCurrencies.stream()
                .filter(monitoredCurrency -> monitoredCurrency.getCollectorName() != null)
                .collect(groupingBy(AcctMonitoredCurrency::getCollectorName));

        // For each collector name that's defined
        monitoredCurrenciesByCollectorName.forEach((collectorName, monitoredCurrencies) -> {
            // Get a reference to the currency collector defined for this currency
            final CurrencyCollectorService<? extends CollectionSession> currencyCollector = currencyCollectorsByName.get(collectorName);

            // Run the collector
            handleCurrencyExchangeRatesCollectionByCollector(
                collectorName,
                currencyCollector,
                monitoredCurrencies,
                bankCodesByBankUUID,
                currencyCodesByCurrencyUUID
            );
        });
    }

    private <T extends CollectionSession> void handleCurrencyExchangeRatesCollectionByCollector(
        String collectorName,
        CurrencyCollectorService<T> currencyCollector,
        List<AcctMonitoredCurrency> monitoredCurrencies,
        final Map<String, String> bankCodesByBankUUID,
        final Map<String, String> currencyCodesByCurrencyUUID
    ) {
        // If the currency collector does not exist, record this as an error message for all related currencies
        // and then exit
        if (currencyCollector == null) {
            persistenceService.saveCurrenciesErrorMessage(
                monitoredCurrencies,
                "Collector not found: " + collectorName
            );
            return;
        }

        // Group monitored currencies with the same collector by bank code
        final Map<String, List<AcctMonitoredCurrency>> monitoredCurrenciesByBankCode =
            monitoredCurrencies.stream()
                .collect(groupingBy(monitoredCurrency ->
                    bankCodesByBankUUID.get(monitoredCurrency.getBankUUID())
                ));

        // Exclude any bank codes that are not supported by the collector
        final Map<String, List<AcctMonitoredCurrency>> monitoredCurrenciesBySupportedBankCode =
            new HashMap<>(monitoredCurrenciesByBankCode.size());

        monitoredCurrenciesByBankCode.forEach((bankCode, currencies) -> {
            if (currencyCollector.getSupportedBankCodes() != null &&
                currencyCollector.getSupportedBankCodes().contains(bankCode)
            ) {
                monitoredCurrenciesBySupportedBankCode.put(bankCode, currencies);
            } else {
                persistenceService.saveCurrenciesErrorMessage(
                    currencies,
                    "Bank not supported by the collector: " + bankCode
                );
            }
        });

        // Map monitored currency codes by bank code and create the session parameters
        final SessionParameters sessionParameters =
            new SessionParameters(
                monitoredCurrenciesBySupportedBankCode.entrySet().stream()
                    .map(entry ->
                        new BankParameters(
                            entry.getKey(),
                            entry.getValue().stream()
                                .map(curr -> currencyCodesByCurrencyUUID.get(curr.getCurrencyUUID()))
                                .toList()
                        )
                    )
                    .toList()
            );

        // Get monitored currencies for supported bank codes
        final Collection<AcctMonitoredCurrency> currenciesWithSupportedBankCodes =
            monitoredCurrenciesByBankCode.values().stream().flatMap(List::stream).toList();

        // Try to initialize the session for the collector
        T session;
        try {
            session = currencyCollector.startSession(sessionParameters);
        }
        // If not possible then record this for all related monitored currencies and exit
        catch (Exception e) {
            persistenceService.saveCurrenciesErrorMessage(
                currenciesWithSupportedBankCodes,
                "Collection session init failed: " + e.getMessage()
            );
            return;
        }

        // For each bank code...
        monitoredCurrenciesBySupportedBankCode.forEach((bankCode, currencies) ->
            // For each currency
            currencies.forEach(currency -> {
                // Try to collect currency exchange records
                try {
                    // If the monitored currency exchange records have been collected today then exit
                    // because the data was already collected for the day
                    if (currency.getLastMonitoredCurrencyRecordDate() != null &&
                        currency.getLastMonitoredCurrencyRecordDate().isAfter(
                            LocalDate.now().atStartOfDay().toInstant(UTC)
                        )
                    ) {
                        return;
                    }

                    // Get the monitored currency's scheduled time (HH:MM)
                    final Instant todayAtScheduledTime = todayAtTimeStrHHMM(currency.getScheduledTimeHHMM());

                    // If the current time is before the scheduled time then exit
                    // because it's too early to collect the data
                    if (Instant.now().isBefore(todayAtScheduledTime)) {
                        return;
                    }

                    // Collect the monitored currency exchange records
                    Collection<CollectedCurrencyExchangeRecord> collectedCurrencyExchangeRecords =
                        currencyCollector.collectRecords(
                            session,
                            bankCode,
                            currencyCodesByCurrencyUUID.get(currency.getCurrencyUUID())
                        );

                    // Persist the currency collected currency exchange records
                    persistenceService.persistMonitoredCurrencyExchangeRecords(
                        currency,
                        collectedCurrencyExchangeRecords
                    );
                }
                // If failed then record the error for the currency
                catch (Exception e) {
                    persistenceService.saveCurrencyErrorMessage(
                        currency,
                        "Unable to collect exchange rates: " + e.getMessage()
                    );
                }

            })
        );

        // End the session
        currencyCollector.endSession(session);
    }

    /**
     * Attempts to identify the {@link CurrencyCollectorService currency collector} set for the referenced
     * monitored currency, if it is set.
     *
     * @param monitoredCurrency the given monitored currency
     * @return either an empty optional or one containing the reference to the currency collector
     */
    private Optional<CurrencyCollectorService<? extends CollectionSession>> identifyCurrencyCollectorServiceForMonitoredCurrency
    (
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
                        groupingBy(matchedRecord ->
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
        public void saveCurrenciesErrorMessage(
            Collection<AcctMonitoredCurrency> monitoredCurrencies,
            String errorMessage
        ) {
            monitoredCurrencies.forEach(monitoredCurrency ->
                saveCurrencyErrorMessage(monitoredCurrency, errorMessage)
            );
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
