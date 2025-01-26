package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.catalog.service.AcctCatalogErrorCodesRegistryService;

import java.util.Map;

/**
 * Generic exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when an icon is not found
 */
public class AcctCatalogServiceIconNotFoundException extends AcctCatalogServiceException {

    public AcctCatalogServiceIconNotFoundException(
        AcctCatalogErrorCodesRegistryService errors,
        String iconUUID
    ) {
        super(
            errors.ICON_NOT_FOUND,
            Map.of("iconUUID", iconUUID)
        );
    }

}
