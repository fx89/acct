package com.desolatetimelines.acct.currency.collector.model;

public record BCRCurrencyCollectionSession(
    GetExchangeRatesResponse exchangeRatesResponse
) implements CollectionSession {
}
