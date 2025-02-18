package com.desolatetimelines.acct.currency.ws.model;

/**
 * Container for the modifiable properties of a monitored currency
 */
public record MonitoredCurrencySaveRequest(
    String bankUUID,
    String currencyUUID,
    String quoteCurrencyUUID,
    String collectorName,
    String scheduledTimeHhMm
) {
}
