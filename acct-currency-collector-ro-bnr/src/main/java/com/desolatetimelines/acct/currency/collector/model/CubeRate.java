package com.desolatetimelines.acct.currency.collector.model;

public record CubeRate(
    String currency,
    Double rate,
    Double multiplier
) {
}
