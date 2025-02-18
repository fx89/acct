package com.desolatetimelines.acct.currency.exception;

import com.desolatetimelines.acct.currency.service.AcctCurrencyErrorCodesRegistryService;

import java.util.Map;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.currency.service.AcctCurrencyService currency service}
 * when a constraint violation related to a monitored currency occurs
 */
public class AcctCurrencyServiceMonitoredCurrencyConstraintViolationException extends AcctCurrencyServiceConstraintViolationException {

    public AcctCurrencyServiceMonitoredCurrencyConstraintViolationException(
        AcctCurrencyErrorCodesRegistryService errors,
        String bankUUID,
        String currencyUUID,
        String quoteCurrencyUUID,
        Throwable cause
    ) {
        super(
            errors.MONITORED_CURRENCY_ALREADY_EXISTS,
            Map.of(
                "bankUUID", bankUUID,
                "currencyUUID", currencyUUID,
                "quoteCurrencyUUID", quoteCurrencyUUID
            ),
            cause
        );
    }

}
