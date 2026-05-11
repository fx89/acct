package com.desolatetimelines.acct.currency.collector.model;

import java.util.List;

public record RatesListItem(
    List<CurrenciesListItem> currencyList,
    String type
) {
}
