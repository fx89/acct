package com.desolatetimelines.acct.currency.service;

import com.desolatetimelines.acct.currency.collector.model.CollectedCurrencyExchangeRecord;
import com.desolatetimelines.acct.currency.collector.model.SessionParameters;
import com.desolatetimelines.acct.currency.collector.service.CurrencyCollectorService;
import com.desolatetimelines.acct.currency.model.MockCurrencyCollectionSession;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MockCurrencyCollectorService implements CurrencyCollectorService<MockCurrencyCollectionSession> {

    private final Collection<String> supportedBankCodes;

    private final List<CollectedCurrencyExchangeRecord> recordsToBeCollected = new ArrayList<>();

    public MockCurrencyCollectorService(Collection<String> supportedBankCodes) {
        this.supportedBankCodes = supportedBankCodes;
    }

    @Override
    public Collection<String> getSupportedBankCodes() {
        return supportedBankCodes;
    }

    @Override
    public MockCurrencyCollectionSession startSession(SessionParameters sessionParameters) {
        return new MockCurrencyCollectionSession(recordsToBeCollected);
    }

    @Override
    public Collection<CollectedCurrencyExchangeRecord> collectRecords(
        MockCurrencyCollectionSession collectionSession,
        String bankCode,
        String currencyCode
    ) {
        return collectionSession.collectedRecords();
    }

    @Override
    public void endSession(MockCurrencyCollectionSession session) {

    }

    public void addRecordToBeCollected(Instant date, double buyPrice, double sellPrice) {
        recordsToBeCollected.add(new CollectedCurrencyExchangeRecord(date, buyPrice, sellPrice));
    }

}
