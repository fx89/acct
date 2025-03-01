package com.desolatetimelines.acct.currency.collector.model;

import java.time.Instant;

/**
 * Represents a currency exchange record collected by a
 * {@link com.desolatetimelines.acct.currency.collector.service.CurrencyCollectorService currency collector service}
 * for a given currency from a given bank, provided that the bank is supported.
 *
 * @param date      the date when the exchange rate applies
 * @param buyPrice  the price at which the bank sells the currency to the buyer
 * @param sellPrice the price at which the bank buys the currency from the seller
 */
public record CollectedCurrencyExchangeRecord(
    Instant date,
    double buyPrice,
    double sellPrice
) {
}
