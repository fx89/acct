package com.desolatetimelines.acct.catalog.exception;

import java.util.Map;

/**
 * Generic exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when a given item is not found
 */
public class AcctCatalogServiceNotFoundException extends AcctCatalogServiceException {

    public AcctCatalogServiceNotFoundException(String errorCode, Map<String, String> parameters) {
        super(errorCode, parameters);
    }

    public AcctCatalogServiceNotFoundException(String errorCode, Map<String, String> parameters, Throwable cause) {
        super(errorCode, parameters, cause);
    }

}
