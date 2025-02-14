package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.catalog.service.AcctCatalogErrorCodesRegistryService;

import java.util.Collection;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.join;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when one or more banks are in use
 */
public class AcctCatalogServiceCurrencyInUseException extends AcctCatalogServiceInUseException {

    public AcctCatalogServiceCurrencyInUseException(
        AcctCatalogErrorCodesRegistryService errors,
        Collection<String> currencyUUIDs
    ) {
        super(errors.CURRENCY_IN_USE, Map.of("currencyUUIDs", join(currencyUUIDs, ",")));
    }

}
