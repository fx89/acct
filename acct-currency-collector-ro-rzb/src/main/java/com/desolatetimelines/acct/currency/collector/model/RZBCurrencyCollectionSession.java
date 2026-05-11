package com.desolatetimelines.acct.currency.collector.model;

public record RZBCurrencyCollectionSession(
    RZBRatesListResponse ratesListResponse
) implements CollectionSession {
}
