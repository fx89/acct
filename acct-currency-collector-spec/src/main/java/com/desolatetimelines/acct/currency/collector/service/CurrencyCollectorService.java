package com.desolatetimelines.acct.currency.collector.service;

import com.desolatetimelines.acct.currency.collector.model.CollectedCurrencyExchangeRecord;
import com.desolatetimelines.acct.currency.collector.model.CollectionSession;
import com.desolatetimelines.acct.currency.collector.model.SessionParameters;

import java.util.Collection;

public interface CurrencyCollectorService<T extends CollectionSession> {

    /**
     * Returns a collection of bank codes for all the banks that are supported by this collector
     */
    Collection<String> getSupportedBankCodes();

    /**
     * Performs any pre-collection actions that might be required to be performed before the
     * {@link CurrencyCollectorService#collectRecords(CollectionSession, String, String) collectRecords}
     * method is called multiple times (once for each combination of bank code and currency
     * code for which collection is required) and stores the result into a session object.
     *
     * @param sessionParameters parameters with which the session runs
     * @return a reference to the session object
     */
    T startSession(SessionParameters sessionParameters);

    /**
     * Collects the latest currency exchange records for the currency with the given currency code
     * from the bank with the given bank code.
     *
     * @param bankCode     the given bank code
     * @param currencyCode the given currency code
     * @return a collection of all the collected records
     */
    Collection<CollectedCurrencyExchangeRecord> collectRecords(T session, String bankCode, String currencyCode);

    /**
     * Perform any post-collection actions that might be required to be performed at the end
     * of the current collection session.
     *
     * @param session reference to the session object used for the current collection session
     */
    void endSession(T session);
}
