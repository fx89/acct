package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.catalog.service.AcctCatalogErrorCodesRegistryService;

import java.util.Map;

/**
 * Generic exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when a currency is not found
 */
public class AcctCatalogServiceCurrencyNotFoundException extends AcctCatalogServiceNotFoundException {

    public AcctCatalogServiceCurrencyNotFoundException(
        AcctCatalogErrorCodesRegistryService errors,
        String currencyUUID
    ) {
        super(
            errors.CURRENCY_NOT_FOUND,
            Map.of("currencyUUID", currencyUUID)
        );
    }

}
