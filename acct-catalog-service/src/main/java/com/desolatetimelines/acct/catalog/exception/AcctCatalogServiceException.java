package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.common.exception.AcctException;

import java.util.Map;

/**
 * Generic exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 */
public class AcctCatalogServiceException extends AcctException {

    public AcctCatalogServiceException(String errorCode, Map<String, String> parameters) {
        super(errorCode, parameters);
    }

    public AcctCatalogServiceException(String errorCode, Map<String, String> parameters, Throwable cause) {
        super(errorCode, parameters, cause);
    }

}
