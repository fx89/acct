package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.catalog.service.AcctCatalogErrorCodesRegistryService;

import java.util.Map;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when an icon-related constraint violation occurs
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
