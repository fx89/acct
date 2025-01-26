package com.desolatetimelines.acct.catalog.exception;

import java.util.Map;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when a validation error occurs
 */
public class AcctCatalogServiceValidationException extends AcctCatalogServiceException {



    public AcctCatalogServiceValidationException(String errorCode, Map<String, String> parameters) {
        super(errorCode, parameters);
    }

    public AcctCatalogServiceValidationException(String errorCode, Map<String, String> parameters, Throwable cause) {
        super(errorCode, parameters, cause);
    }

}
