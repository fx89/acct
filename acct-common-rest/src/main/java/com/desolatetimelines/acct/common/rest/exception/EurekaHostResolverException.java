package com.desolatetimelines.acct.common.rest.exception;

/**
 * Thrown from the {@link com.desolatetimelines.acct.common.rest.service.EurekaHostResolver EurekaHostResolver}
 */
public class EurekaHostResolverException extends RuntimeException {

    public EurekaHostResolverException(String message) {
        super(message);
    }

}
