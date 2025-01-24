package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.catalog.service.AcctCatalogErrorCodesRegistryService;

import java.util.Map;

/**
 * Generic exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 */
public class AcctCatalogServiceIconConstraintViolationException extends AcctCatalogServiceConstraintViolationException {

    public AcctCatalogServiceIconConstraintViolationException(
        AcctCatalogErrorCodesRegistryService errors,
        String iconName,
        String iconCategoryName,
        Throwable cause
    ) {
        super(
            errors.ICON_ALREADY_EXISTS,
            Map.of(
                "iconCategoryName", iconCategoryName,
                "iconName", iconName
            ),
            cause
        );
    }

}
