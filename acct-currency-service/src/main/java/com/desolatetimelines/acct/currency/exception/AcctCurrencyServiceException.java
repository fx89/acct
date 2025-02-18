package com.desolatetimelines.acct.currency.exception;

import com.desolatetimelines.acct.common.exception.AcctException;

import java.util.Map;

/**
 * Generic exception thrown by the {@link com.desolatetimelines.acct.currency.service.AcctCurrencyService currency service}
 */
public class AcctCurrencyServiceException extends AcctException {

    public AcctCurrencyServiceException(String errorCode, Map<String, String> parameters) {
        super(errorCode, parameters);
    }

    public AcctCurrencyServiceException(String errorCode, Map<String, String> parameters, Throwable cause) {
        super(errorCode, parameters, cause);
    }

}
