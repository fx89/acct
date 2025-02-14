package com.desolatetimelines.acct.catalog.ws.model;

/**
 * Contains the editable properties of a currency
 *
 * @param currencyCode     Unique 3-letter code that identifies the currency
 * @param currencyName     Human-readable name of the currency
 * @param currencyIconUUID UUID that identifies the currency in the GUI
 */
public record CurrencySaveRequest(
    String currencyCode,
    String currencyName,
    String currencyIconUUID
) {
}
