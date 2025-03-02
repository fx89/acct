package com.desolatetimelines.acct.currency.collector.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record GetExchangeRatesResponse(
    @JsonProperty("ValidityDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    Date validityDate,

    @JsonProperty("ExchangeRates")
    ExchangeRates exchangeRates
) {
}
