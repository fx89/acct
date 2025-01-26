package com.desolatetimelines.acct.catalog.exception;

import java.util.Map;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when one or more items are in use
 */
public class AcctCatalogServiceInUseException extends AcctCatalogServiceException {

    public AcctCatalogServiceInUseException(String errorCode, Map<String, String> parameters) {
        super(errorCode, parameters);
    }

    public AcctCatalogServiceInUseException(String errorCode, Map<String, String> parameters, Throwable cause) {
        super(errorCode, parameters, cause);
    }

}
