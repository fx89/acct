package com.desolatetimelines.acct.common.ws.exception;

/**
 * Thrown whenever ACCT has a problem working with JWTs
 */
public class AcctJwtException extends RuntimeException {

    public AcctJwtException(String message) {
        super(message);
    }

    public AcctJwtException(String message, Throwable cause) {
        super(message, cause);
    }

}
