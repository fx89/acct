package com.desolatetimelines.acct.currency.collector.model;

import java.util.List;

public record BankParameters(
    String bankCode,
    List<String> currencyCodes
) {
}
