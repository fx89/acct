package com.desolatetimelines.acct.currency.collector.service;

import com.desolatetimelines.acct.currency.collector.model.CollectedCurrencyExchangeRecord;
import com.desolatetimelines.acct.currency.collector.model.RZBCurrencyCollectionSession;
import com.desolatetimelines.acct.currency.collector.model.SessionParameters;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

public class RZBCurrencyCollectorServiceTest {

    @Test
    public void testCollectRecords_returnsCorrectly() {
        // Initialize the currency collector
        final RZBCurrencyCollectorService currencyCollector = new RZBCurrencyCollectorService();

        // Create the session
        final RZBCurrencyCollectionSession session =
            currencyCollector.startSession(new SessionParameters(emptyList()));

        // Run the currency collector
        final Collection<CollectedCurrencyExchangeRecord> collectedRecords =
            currencyCollector.collectRecords(session, "RZB", "EUR");

        // Verify that there is exactly one collected record
        assertEquals(1, collectedRecords.size());

        // Get a reference to the one collected record
        final CollectedCurrencyExchangeRecord collectedRecord = collectedRecords.iterator().next();

        // Verify that the collected record's date property is populated
        assertNotNull(collectedRecord.date());

        // Verify that the buy price is lower than the sell price
        assertTrue(collectedRecord.buyPrice() < collectedRecord.sellPrice());
    }

}
