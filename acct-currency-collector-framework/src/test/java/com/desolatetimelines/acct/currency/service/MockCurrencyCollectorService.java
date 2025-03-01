package com.desolatetimelines.acct.currency.service;

import com.desolatetimelines.acct.currency.collector.model.CollectedCurrencyExchangeRecord;
import com.desolatetimelines.acct.currency.collector.service.CurrencyCollectorService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MockCurrencyCollectorService implements CurrencyCollectorService {

    private final Collection<String> supportedBankCodes;

    private final List<CollectedCurrencyExchangeRecord> collectedRecords = new ArrayList<>();

    public MockCurrencyCollectorService(Collection<String> supportedBankCodes) {
        this.supportedBankCodes = supportedBankCodes;
    }

    @Override
    public Collection<String> getSupportedBankCodes() {
        return supportedBankCodes;
    }

    @Override
    public Collection<CollectedCurrencyExchangeRecord> collectRecords(String bankCode, String currencyCode) {
        return collectedRecords;
    }

    public void addRecordToBeCollected(Instant date, double buyPrice, double sellPrice) {
        collectedRecords.add(new CollectedCurrencyExchangeRecord(date, buyPrice, sellPrice));
    }

}
