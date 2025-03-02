package com.desolatetimelines.acct.currency.collector.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public record ExchangeRates(
    @JsonProperty("ExchangeRates")
    Collection<ExchangeRate> exchangeRates
) {
}
