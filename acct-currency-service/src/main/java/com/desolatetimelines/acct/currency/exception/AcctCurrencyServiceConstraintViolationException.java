package com.desolatetimelines.acct.currency.exception;

import java.util.Map;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.currency.service.AcctCurrencyService currency service}
 * when a constraint violation occurs
 */
public class AcctCurrencyServiceConstraintViolationException extends AcctCurrencyServiceException {


    public AcctCurrencyServiceConstraintViolationException(
        String errorCode,
        Map<String, String> parameters
    ) {
        super(errorCode, parameters);
    }

    public AcctCurrencyServiceConstraintViolationException(
        String errorCode,
        Map<String, String> parameters,
        Throwable cause
    ) {
        super(errorCode, parameters, cause);
    }

}
