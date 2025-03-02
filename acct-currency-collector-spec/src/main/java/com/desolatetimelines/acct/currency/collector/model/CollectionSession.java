package com.desolatetimelines.acct.currency.collector.model;

/**
 * Object that holds data which has to be persisted between multiple subsequent runs of the
 * {@link com.desolatetimelines.acct.currency.collector.service.CurrencyCollectorService#collectRecords(CollectionSession, String, String) collectRecords}
 * method of a given instance of
 * {@link com.desolatetimelines.acct.currency.collector.service.CurrencyCollectorService CurrencyCollectionService}
 * within one single collection session
 */
public interface CollectionSession {
}
