package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.catalog.service.AcctCatalogErrorCodesRegistryService;

import java.util.Map;

/**
 * Generic exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when a bank is not found
 */
public class AcctCatalogServiceBankNotFoundException extends AcctCatalogServiceNotFoundException {

    public AcctCatalogServiceBankNotFoundException(
        AcctCatalogErrorCodesRegistryService errors,
        String bankUUID
    ) {
        super(
            errors.BANK_NOT_FOUND,
            Map.of("bankUUID", bankUUID)
        );
    }

}
