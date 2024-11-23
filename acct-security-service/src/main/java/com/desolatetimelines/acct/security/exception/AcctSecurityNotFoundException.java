package com.desolatetimelines.acct.security.exception;

/**
 * Thrown when data is not found
 */
public class AcctSecurityNotFoundException extends RuntimeException {

    public AcctSecurityNotFoundException(String message) {
        super(message);
    }

}
