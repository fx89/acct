package com.desolatetimelines.acct.currency.collector.model;

public record CurrenciesListItem(
    CurrencyPair currencyPair,
    Rate buyRate,
    Rate sellRate,
    String validityDate
) {
}
