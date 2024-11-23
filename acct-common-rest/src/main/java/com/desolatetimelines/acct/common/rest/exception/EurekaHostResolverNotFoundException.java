package com.desolatetimelines.acct.common.rest.exception;

/**
 * Thrown from the {@link com.desolatetimelines.acct.common.rest.service.EurekaHostResolver EurekaHostResolver}
 * when the sought application is not found or when there are no instances registered with the Eureka service
 * that are up and running
 */
public class EurekaHostResolverNotFoundException extends EurekaHostResolverException {

    public EurekaHostResolverNotFoundException(String message) {
        super(message);
    }

}
