package com.desolatetimelines.acct.currency.ws.model;

import java.util.Collection;

/**
 * Defines a currency collector that's available for use for the purpose of
 * gathering records for monitored currencies
 *
 * @param currencyCollectorName the name that uniquely identifies the currency exchange rates collector
 * @param supportedBankCodes    a collection of the codes of the banks that are supported by the collector
 */
public record MonitoredCurrencyCollector(
    String currencyCollectorName,
    Collection<String> supportedBankCodes
) {
}
