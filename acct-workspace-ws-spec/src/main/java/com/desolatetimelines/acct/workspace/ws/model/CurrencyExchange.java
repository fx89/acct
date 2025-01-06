package com.desolatetimelines.acct.workspace.ws.model;

/**
 * Describes a currency exchange request, which contains a {@link CurrencyTransfer currency transfer request}
 * and an {@link CurrencyExchange#exchangeRate() exchange rate}. If this currency transfer is supposed to be
 * a buy-back related to a previously recorded exchange (i.e. previously bought EUR with USD and now the EUR
 * is sold for USD to make a profit in USD) then an original account record ID may be provided.
 *
 * @param currencyTransfer        the currency transfer request
 * @param exchangeRate            the exchange rate
 * @param originalAccountRecordId the original account record ID
 */
public record CurrencyExchange(
    CurrencyTransfer currencyTransfer,
    Double exchangeRate,
    Long originalAccountRecordId
) {
}
