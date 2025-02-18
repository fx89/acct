package com.desolatetimelines.acct.currency.exception;

import java.util.Map;

/**
 * Generic exception thrown by the {@link com.desolatetimelines.acct.currency.service.AcctCurrencyService currency service}
 * when a given item is not found
 */
public class AcctCurrencyServiceNotFoundException extends AcctCurrencyServiceException {

    public AcctCurrencyServiceNotFoundException(String errorCode, Map<String, String> parameters) {
        super(errorCode, parameters);
    }

    public AcctCurrencyServiceNotFoundException(String errorCode, Map<String, String> parameters, Throwable cause) {
        super(errorCode, parameters, cause);
    }

}
