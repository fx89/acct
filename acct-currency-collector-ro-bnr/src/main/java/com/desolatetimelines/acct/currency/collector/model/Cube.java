package com.desolatetimelines.acct.currency.collector.model;

import java.time.Instant;
import java.util.Collection;

public record Cube(
    Instant recordDate,
    Collection<CubeRate> rates
) {
}
