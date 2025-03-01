package com.desolatetimelines.acct.currency.service;

import com.desolatetimelines.acct.catalog.ws.client.InMemoryBanksEndpoint;
import com.desolatetimelines.acct.catalog.ws.client.InMemoryCurrenciesEndpoint;
import com.desolatetimelines.acct.catalog.ws.endpoint.BanksEndpoint;
import com.desolatetimelines.acct.catalog.ws.endpoint.CurrenciesEndpoint;
import com.desolatetimelines.acct.catalog.ws.model.BankSaveRequest;
import com.desolatetimelines.acct.catalog.ws.model.CurrencySaveRequest;
import com.desolatetimelines.acct.currency.collector.service.CurrencyCollectorService;
import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrencyRecord;
import com.desolatetimelines.acct.currency.model.InMemoryAcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.model.InMemoryAcctMonitoredCurrencyRecord;
import com.desolatetimelines.acct.currency.repository.InMemoryAcctMonitoredCurrenciesRepository;
import com.desolatetimelines.acct.currency.repository.InMemoryAcctMonitoredCurrencyRecordsRepository;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AcctCurrencyCollectionServiceTest {

    /**
     * Mock bank code for use in the unit test
     */
    private static final String MOCK_BANK_CODE = "MBK";

    /**
     * Mock bank code for a fictional bank that's not supported by any collector
     */
    private static final String MOCK_UNSUPPORTED_BANK_CODE = "MUB";

    /**
     * Mock currency code for use in the unit test
     */
    private static final String MOCK_CURRENCY_CODE = "MOK";

    /**
     * Date of the record that already exists in the data store and does not overlap with any collected record
     */
    private static final Instant DAY_BEFORE_OVERLAP = Instant.now().minus(Duration.ofDays(2));

    /**
     * Date of the record that already exists in the data store and overlaps with the collected record with the same date
     */
    private static final Instant OVERLAPPING_DAY = Instant.now().minus(Duration.ofDays(1));

    /**
     * Date of the collected record that does not overlap with any of the records that already exist in the data store
     */
    private static final Instant DAY_AFTER_OVERLAP = Instant.now();

    /**
     * Sale value initially set for monitored currency records that exist in the data store
     * before the collection process runs
     */
    private static final Double DATA_STORE_SALE_VALUE = 1.0d;

    /**
     * Purchase value initially set for monitored currency records that exist in the data store
     * before the collection process runs
     */
    private static final Double DATA_STORE_PURCHASE_VALUE = 2.0d;

    /**
     * Sale value set for the collected currency exchange records
     */
    private static final Double COLLECTED_SALE_VALUE = 3.0d;

    /**
     * Purchase value set for the collected currency exchange records
     */
    private static final Double COLLECTED_PURCHASE_VALUE = 4.0d;

    @Test
    void testHandleCurrencyExchangeRatesCollection_currencyCollectorsAreReturnedProperly() {
        // Create a new in-memory data store for the test data
        final InMemoryDataStore dataStore = InMemoryDataStore.createNew();

        // Initialize the data store
        initializeDataStore(dataStore);

        // Create the currency collection service
        final AcctCurrencyCollectionService currencyCollectionService = createCurrencyCollectionService(dataStore);

        // Test that the currency collector is returned correctly
        final Map<String, CurrencyCollectorService> collectors = currencyCollectionService.getCurrencyCollectorsByName();
        assertEquals(1, collectors.size());
        final CurrencyCollectorService collector = collectors.get(dataStore.currencyCollectorService.getClass().getCanonicalName());
        assertNotNull(collector);
    }

    @Test
    void testHandleCurrencyExchangeRatesCollection_collectionWorksProperly() {
        // Create a new in-memory data store for the test data
        final InMemoryDataStore dataStore = InMemoryDataStore.createNew();

        // Initialize the data store
        initializeDataStore(dataStore);

        // Create the currency collection service
        final AcctCurrencyCollectionService currencyCollectionService = createCurrencyCollectionService(dataStore);

        // Perform the currency exchange rates collection
        currencyCollectionService.handleCurrencyExchangeRatesCollection();

        // Verify the outcome of the currency exchange rates collection process in the data store
        verifyUnsupportedCurrencyNotCollected(dataStore);
        verifyCurrencyCollectionOutcome(dataStore);
    }

    private static void initializeDataStore(InMemoryDataStore dataStore) {
        // Add the currency
        final String currencyUUID =
            dataStore.currenciesEndpoint().saveCurrency(
                null,
                new CurrencySaveRequest(MOCK_CURRENCY_CODE, "Mock Currency", null)
            ).currencyUUID();

        // Add the bank
        final String supportedBankUUID =
            dataStore.banksEndpoint().saveBank(
                null,
                new BankSaveRequest(MOCK_BANK_CODE, "Mock Bank", "mockBankURL", null)
            ).bankUUID();

        // Add the unsupported bank
        final String unsupportedBankUUID =
            dataStore.banksEndpoint().saveBank(
                null,
                new BankSaveRequest(MOCK_UNSUPPORTED_BANK_CODE, "Mock Unsupported Bank", "mockUnsupportedBankURL", null)
            ).bankUUID();

        // Add a monitored currency for the supported bank
        final AcctMonitoredCurrency monitoredCurrency =
            dataStore.dataService().saveMonitoredCurrency(
                InMemoryAcctMonitoredCurrency.builder()
                    .withMonitoredCurrencyUUID(UUID.randomUUID().toString())
                    .withBankUUID(supportedBankUUID)
                    .withCurrencyUUID(currencyUUID)
                    .withCollectorName(MockCurrencyCollectorService.class.getCanonicalName())
                    .withScheduledTimeHHMM("00:00")
                    .build()
            );

        // Add another monitored currency for the unsupported bank
        dataStore.dataService().saveMonitoredCurrency(
            InMemoryAcctMonitoredCurrency.builder()
                .withMonitoredCurrencyUUID(UUID.randomUUID().toString())
                .withBankUUID(unsupportedBankUUID)
                .withCurrencyUUID(currencyUUID)
                .withCollectorName(MockCurrencyCollectorService.class.getCanonicalName())
                .withScheduledTimeHHMM("00:00")
                .build()
        );

        // Add some monitored currency records that already exist before running the collection process...

        // One record that should not be modified by the collection process
        dataStore.dataService().saveMonitoredCurrencyRecord(
            InMemoryAcctMonitoredCurrencyRecord.builder()
                .withMonitoredCurrency(monitoredCurrency)
                .withMonitoredCurrencyRecordDate(DAY_BEFORE_OVERLAP)
                .withMonitoredCurrencyRecordPurchaseValue(DATA_STORE_PURCHASE_VALUE)
                .withMonitoredCurrencyRecordSaleValue(DATA_STORE_SALE_VALUE)
                .build()
        );

        // One record that should be modified by the collection process
        dataStore.dataService().saveMonitoredCurrencyRecord(
            InMemoryAcctMonitoredCurrencyRecord.builder()
                .withMonitoredCurrency(monitoredCurrency)
                .withMonitoredCurrencyRecordDate(OVERLAPPING_DAY)
                .withMonitoredCurrencyRecordPurchaseValue(DATA_STORE_PURCHASE_VALUE)
                .withMonitoredCurrencyRecordSaleValue(DATA_STORE_SALE_VALUE)
                .build()
        );

        // Add the records that should be returned by the currency collector...

        // One record that should overwrite the existing record to be overwritten
        dataStore.currencyCollectorService().addRecordToBeCollected(
            OVERLAPPING_DAY,
            COLLECTED_PURCHASE_VALUE,
            COLLECTED_SALE_VALUE
        );

        // One record that should not overwrite any existing records
        dataStore.currencyCollectorService().addRecordToBeCollected(
            DAY_AFTER_OVERLAP,
            COLLECTED_PURCHASE_VALUE,
            COLLECTED_SALE_VALUE
        );
    }

    private static AcctCurrencyCollectionService createCurrencyCollectionService(InMemoryDataStore dataStore) {
        return
            new AcctCurrencyCollectionService(
                dataStore.dataService(),
                new AcctCurrencyCollectionService.AcctCurrencyExchangeRecordsPersistenceService(dataStore.dataService()),
                dataStore.banksEndpoint(),
                dataStore.currenciesEndpoint(),
                List.of(dataStore.currencyCollectorService())
            );
    }

    private static void verifyUnsupportedCurrencyNotCollected(InMemoryDataStore dataStore) {
        // Get the UUID of the unsupported bank code
        final String unsupportedBankUUID =
            dataStore.banksEndpoint.getBanks()
                .stream()
                .filter(b -> Objects.equals(MOCK_UNSUPPORTED_BANK_CODE, b.bankCode()))
                .findFirst()
                .orElseThrow()
                .bankUUID();

        // Get a reference to the monitored currency with the unsupported bank
        final AcctMonitoredCurrency monitoredCurrency =
            dataStore.monitoredCurrenciesRepository().findAll()
                .stream()
                .filter(c -> Objects.equals(unsupportedBankUUID, c.getBankUUID()))
                .findFirst().orElseThrow();

        // Check that no records exist for the monitored currency with the unsupported bank
        assertNull(monitoredCurrency.getLastMonitoredCurrencyRecordDate());
        assertNull(monitoredCurrency.getLastMonitoredCurrencyRecordSaleValue());
        assertNull(monitoredCurrency.getLastMonitoredCurrencyRecordPurchaseValue());
        assertNotNull(monitoredCurrency.getLastCollectionDate());
        assertNotNull(monitoredCurrency.getCollectionErrorMessage());
    }

    private static void verifyCurrencyCollectionOutcome(InMemoryDataStore dataStore) {
        // Get the UUID of the supported bank code
        final String supportedBankUUID =
            dataStore.banksEndpoint.getBanks()
                .stream()
                .filter(b -> Objects.equals(MOCK_BANK_CODE, b.bankCode()))
                .findFirst()
                .orElseThrow()
                .bankUUID();

        // Get a reference to the monitored currency with the supported bank
        final AcctMonitoredCurrency monitoredCurrency =
            dataStore.monitoredCurrenciesRepository().findAll()
                .stream()
                .filter(c -> Objects.equals(supportedBankUUID, c.getBankUUID()))
                .findFirst().orElseThrow();

        // Check that the monitored currency has been properly updated
        assertNotNull(monitoredCurrency.getLastCollectionDate());
        assertEquals(DAY_AFTER_OVERLAP, monitoredCurrency.getLastMonitoredCurrencyRecordDate());
        assertEquals(COLLECTED_PURCHASE_VALUE, monitoredCurrency.getLastMonitoredCurrencyRecordPurchaseValue());
        assertEquals(COLLECTED_SALE_VALUE, monitoredCurrency.getLastMonitoredCurrencyRecordSaleValue());
        assertNull(monitoredCurrency.getCollectionErrorMessage());

        // Get the records sorted by record date
        final List<AcctMonitoredCurrencyRecord> records =
            dataStore.monitoredCurrencyRecordsRepository().findAllByMonitoredCurrency(monitoredCurrency)
                .stream()
                .sorted(Comparator.comparing(AcctMonitoredCurrencyRecord::getMonitoredCurrencyRecordDate))
                .toList();

        // Check that there are exactly 3 records
        assertEquals(3, records.size());

        // Check that the records have the proper dates
        assertEquals(DAY_BEFORE_OVERLAP, records.get(0).getMonitoredCurrencyRecordDate());
        assertEquals(OVERLAPPING_DAY, records.get(1).getMonitoredCurrencyRecordDate());
        assertEquals(DAY_AFTER_OVERLAP, records.get(2).getMonitoredCurrencyRecordDate());

        // Check that the record that was supposed to remain unchanged was not changed
        assertEquals(DATA_STORE_PURCHASE_VALUE, records.get(0).getMonitoredCurrencyRecordPurchaseValue());
        assertEquals(DATA_STORE_SALE_VALUE, records.get(0).getMonitoredCurrencyRecordSaleValue());

        // Check that the record that was supposed to be changed was actually changed
        assertEquals(COLLECTED_PURCHASE_VALUE, records.get(1).getMonitoredCurrencyRecordPurchaseValue());
        assertEquals(COLLECTED_SALE_VALUE, records.get(1).getMonitoredCurrencyRecordSaleValue());

        // Check that the newly added record was properly added
        assertEquals(COLLECTED_PURCHASE_VALUE, records.get(2).getMonitoredCurrencyRecordPurchaseValue());
        assertEquals(COLLECTED_SALE_VALUE, records.get(2).getMonitoredCurrencyRecordSaleValue());
    }

    /**
     * Use the {@link InMemoryDataStore#createNew createNew} method to create an in-memory data store
     * that contains in-memory implementations of the repositories and external endpoint clients that
     * are required to build a {@link AcctCurrencyCollectionService currency collection service}
     *
     * @param monitoredCurrenciesRepository      In-memory implementation of the monitored currencies repository
     * @param monitoredCurrencyRecordsRepository In-memory implementation of the monitored currency records repository
     * @param dataService                        Currency data service that makes use of in-memory implementation of the data repositories
     * @param banksEndpoint                      Implementation of the Banks endpoint that uses an in-memory repository rather than a REST client
     * @param currenciesEndpoint                 Implementation of the Currencies endpoint that uses an in-memory repository rather than a REST client
     * @param currencyCollectorService           A mockup currency collector that returns whatever records it is set up to return
     */
    private record InMemoryDataStore(
        InMemoryAcctMonitoredCurrenciesRepository monitoredCurrenciesRepository,
        InMemoryAcctMonitoredCurrencyRecordsRepository monitoredCurrencyRecordsRepository,
        AcctCurrencyDataService dataService,
        BanksEndpoint banksEndpoint,
        CurrenciesEndpoint currenciesEndpoint,
        MockCurrencyCollectorService currencyCollectorService
    ) {
        public static InMemoryDataStore createNew() {
            final InMemoryAcctMonitoredCurrenciesRepository monitoredCurrenciesRepository =
                new InMemoryAcctMonitoredCurrenciesRepository();

            final InMemoryAcctMonitoredCurrencyRecordsRepository monitoredCurrencyRecordsRepository =
                new InMemoryAcctMonitoredCurrencyRecordsRepository();

            return
                new InMemoryDataStore(
                    monitoredCurrenciesRepository,
                    monitoredCurrencyRecordsRepository,
                    new AcctCurrencyDataService(
                        monitoredCurrenciesRepository,
                        monitoredCurrencyRecordsRepository
                    ),
                    new InMemoryBanksEndpoint(),
                    new InMemoryCurrenciesEndpoint(),
                    new MockCurrencyCollectorService(List.of(MOCK_BANK_CODE))
                );
        }
    }

}
