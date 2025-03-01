package com.desolatetimelines.acct.currency.collector.service;

import com.desolatetimelines.acct.currency.collector.model.CollectedCurrencyExchangeRecord;

import java.util.Collection;

public interface CurrencyCollectorService {

    /**
     * Returns a collection of bank codes for all the banks that are supported by this collector
     */
    Collection<String> getSupportedBankCodes();

    /**
     * Collects the latest currency exchange records for the currency with the given currency code
     * from the bank with the given bank code.
     *
     * @param bankCode     the given bank code
     * @param currencyCode the given currency code
     * @return a collection of all the collected records
     */
    Collection<CollectedCurrencyExchangeRecord> collectRecords(String bankCode, String currencyCode);

}
