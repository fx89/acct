package com.desolatetimelines.acct.currency.collector.service;

import com.desolatetimelines.acct.currency.collector.model.BNRCurrencyCollectionSession;
import com.desolatetimelines.acct.currency.collector.model.BankParameters;
import com.desolatetimelines.acct.currency.collector.model.CollectedCurrencyExchangeRecord;
import com.desolatetimelines.acct.currency.collector.model.SessionParameters;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BNRCurrencyCollectorServiceTest {

    @Test
    void testCollectRecords_returnsCorrectly() {
        // Initialize the currency collector
        final BNRCurrencyCollectorService currencyCollector = new BNRCurrencyCollectorService();

        // Start the session
        final BNRCurrencyCollectionSession session =
            currencyCollector.startSession(
                new SessionParameters(
                    List.of(
                        new BankParameters(
                            "BNR",
                            List.of("EUR")
                        )
                    )
                )
            );

        // Run the currency collector
        final Collection<CollectedCurrencyExchangeRecord> collectedRecords =
            currencyCollector.collectRecords(session, "BNR", "EUR");

        // End the session
        currencyCollector.endSession(session);

        // Verify that data has been collected
        assertEquals(1, collectedRecords.size());
        assertNotNull(collectedRecords.stream().findFirst().orElseThrow().date());
        assertTrue(collectedRecords.stream().findFirst().orElseThrow().sellPrice() > 0);
        assertTrue(collectedRecords.stream().findFirst().orElseThrow().buyPrice() > 0);
    }

}
