package com.desolatetimelines.acct.catalog.exception;

import java.util.Map;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when a constraint violation occurs
 */
public class AcctCatalogServiceConstraintViolationException extends AcctCatalogServiceException {



    public AcctCatalogServiceConstraintViolationException(String errorCode, Map<String, String> parameters) {
        super(errorCode, parameters);
    }

    public AcctCatalogServiceConstraintViolationException(String errorCode, Map<String, String> parameters, Throwable cause) {
        super(errorCode, parameters, cause);
    }

}
