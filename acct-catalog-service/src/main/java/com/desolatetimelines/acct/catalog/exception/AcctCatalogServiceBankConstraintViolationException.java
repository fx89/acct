package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.catalog.service.AcctCatalogErrorCodesRegistryService;

import java.util.Map;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when a constraint violation related to an income or expense item occurs
 */
public class AcctCatalogServiceBankConstraintViolationException extends AcctCatalogServiceConstraintViolationException {

    public AcctCatalogServiceBankConstraintViolationException(
        AcctCatalogErrorCodesRegistryService errors,
        String bankCode,
        Throwable cause
    ) {
        super(
            errors.BANK_ALREADY_EXISTS,
            Map.of(
                "bankCode", bankCode
            ),
            cause
        );
    }

}
