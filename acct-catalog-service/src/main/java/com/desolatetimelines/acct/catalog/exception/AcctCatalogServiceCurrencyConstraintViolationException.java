package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.catalog.service.AcctCatalogErrorCodesRegistryService;

import java.util.Map;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when a constraint violation related to a currency occurs
 */
public class AcctCatalogServiceCurrencyConstraintViolationException extends AcctCatalogServiceConstraintViolationException {

    public AcctCatalogServiceCurrencyConstraintViolationException(
        AcctCatalogErrorCodesRegistryService errors,
        String currencyCode,
        Throwable cause
    ) {
        super(
            errors.CURRENCY_ALREADY_EXISTS,
            Map.of(
                "currencyCode", currencyCode
            ),
            cause
        );
    }

}
