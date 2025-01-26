package com.desolatetimelines.acct.catalog.exception;

import java.util.Map;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when an icon validation error occurs
 */
public class AcctCatalogServiceIconValidationException extends AcctCatalogServiceValidationException {

    public AcctCatalogServiceIconValidationException(String errorCode, Map<String, String> parameters) {
        super(errorCode, parameters);
    }

    public AcctCatalogServiceIconValidationException(String errorCode, Map<String, String> parameters, Throwable cause) {
        super(errorCode, parameters, cause);
    }

}
