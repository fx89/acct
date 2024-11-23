package com.desolatetimelines.acct.common.rest.exception;

/**
 * Thrown from the {@link com.desolatetimelines.acct.common.rest.service.EurekaHostResolver EurekaHostResolver}
 * when the host definitions returned by the Eureka service do not contain the preferred return properties
 */
public class EurekaHostResolverBadHostDefinitionException extends EurekaHostResolverException {

    public EurekaHostResolverBadHostDefinitionException(String message) {
        super(message);
    }

}
