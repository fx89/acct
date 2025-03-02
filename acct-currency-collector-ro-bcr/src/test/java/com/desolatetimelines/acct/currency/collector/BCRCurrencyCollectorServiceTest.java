package com.desolatetimelines.acct.currency.collector;

import com.desolatetimelines.acct.currency.collector.model.BCRCurrencyCollectionSession;
import com.desolatetimelines.acct.currency.collector.model.BankParameters;
import com.desolatetimelines.acct.currency.collector.model.CollectedCurrencyExchangeRecord;
import com.desolatetimelines.acct.currency.collector.model.SessionParameters;
import com.desolatetimelines.acct.currency.collector.service.BCRCurrencyCollectorService;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BCRCurrencyCollectorServiceTest {

    @Test
    void TestBCRCurrencyCollectorService() {
        // Create the collector
        final BCRCurrencyCollectorService collectorService = new BCRCurrencyCollectorService();

        // Start the session
        final BCRCurrencyCollectionSession session =
            collectorService.startSession(
                new SessionParameters(
                    List.of(
                        new BankParameters(
                            "BCR",
                            List.of("EUR")
                        )
                    )
                )
            );

        // Collect the data
        Collection<CollectedCurrencyExchangeRecord> collectedRecords =
            collectorService.collectRecords(session, "BCR", "EUR");

        // End the session
        collectorService.endSession(session);

        // Test that the data has been collected
        assertEquals(1, collectedRecords.size());
        assertNotNull(collectedRecords.stream().findFirst().orElseThrow().date());
        assertTrue(collectedRecords.stream().findFirst().orElseThrow().sellPrice() > 0);
        assertTrue(collectedRecords.stream().findFirst().orElseThrow().buyPrice() > 0);
    }

}
