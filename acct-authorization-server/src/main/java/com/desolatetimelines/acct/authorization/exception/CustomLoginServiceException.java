package com.desolatetimelines.acct.authorization.exception;

/**
 * Exceptions thrown by the
 * {@link com.desolatetimelines.acct.authorization.service.CustomLoginService custom login service}
 */
public class CustomLoginServiceException extends RuntimeException {

    public CustomLoginServiceException(String message) {
        super(message);
    }

}
