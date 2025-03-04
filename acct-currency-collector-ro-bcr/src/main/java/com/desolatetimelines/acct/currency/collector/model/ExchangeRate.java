package com.desolatetimelines.acct.currency.collector.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record ExchangeRate(
    @JsonProperty("Currency")
    String currency,

    @JsonProperty("BuyRate")
    Double buyRate,

    @JsonProperty("SellRate")
    Double sellRate,

    @JsonProperty("DenominationUnits")
    Double denominationUnit,

    @JsonProperty("RateDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    Date rateDate,

    @JsonProperty("RateTime")
    String strTime
) {
}
