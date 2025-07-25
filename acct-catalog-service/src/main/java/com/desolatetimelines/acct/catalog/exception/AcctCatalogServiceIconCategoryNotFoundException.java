package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.catalog.service.AcctCatalogErrorCodesRegistryService;

import java.util.Map;

/**
 * Generic exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when an icon is not found
 */
public class AcctCatalogServiceIconCategoryNotFoundException extends AcctCatalogServiceNotFoundException {

    public AcctCatalogServiceIconCategoryNotFoundException(
        AcctCatalogErrorCodesRegistryService errors,
        String iconCategoryName
    ) {
        super(
            errors.ICON_CATEGORY_NOT_FOUND,
            Map.of("iconCategoryName", iconCategoryName)
        );
    }

}
