package com.desolatetimelines.acct.currency.model;

import com.desolatetimelines.acct.currency.collector.model.CollectedCurrencyExchangeRecord;
import com.desolatetimelines.acct.currency.collector.model.CollectionSession;

import java.util.List;

public record MockCurrencyCollectionSession(
    List<CollectedCurrencyExchangeRecord> collectedRecords
) implements CollectionSession {
}
