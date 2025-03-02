package com.desolatetimelines.acct.currency.collector.model;

import java.util.List;

public record SessionParameters(
    List<BankParameters> banks
) {
}
