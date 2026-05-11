package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.catalog.service.AcctCatalogErrorCodesRegistryService;

import java.util.Collection;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.join;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when one or more banks are in use
 */
public class AcctCatalogServiceBankInUseException extends AcctCatalogServiceInUseException {

    public AcctCatalogServiceBankInUseException(
        AcctCatalogErrorCodesRegistryService errors,
        Collection<String> bankUUIDs
    ) {
        super(errors.BANK_IN_USE, Map.of("bankUUIDs", join(bankUUIDs, ",")));
    }

}
