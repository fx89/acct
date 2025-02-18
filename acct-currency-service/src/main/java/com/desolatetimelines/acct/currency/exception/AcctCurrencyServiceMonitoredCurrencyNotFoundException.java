package com.desolatetimelines.acct.currency.exception;

import com.desolatetimelines.acct.currency.service.AcctCurrencyErrorCodesRegistryService;

import java.util.Map;

/**
 * Generic exception thrown by the {@link com.desolatetimelines.acct.currency.service.AcctCurrencyService currency service}
 * when a monitored currency is not found
 */
public class AcctCurrencyServiceMonitoredCurrencyNotFoundException extends AcctCurrencyServiceNotFoundException {

    public AcctCurrencyServiceMonitoredCurrencyNotFoundException(
        AcctCurrencyErrorCodesRegistryService errors,
        String monitoredCurrencyUUID
    ) {
        super(
            errors.MONITORED_CURRENCY_NOT_FOUND,
            Map.of("monitoredCurrencyUUID", monitoredCurrencyUUID)
        );
    }

}
