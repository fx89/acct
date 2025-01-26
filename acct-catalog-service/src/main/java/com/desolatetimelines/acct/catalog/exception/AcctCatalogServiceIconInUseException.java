package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.catalog.service.AcctCatalogErrorCodesRegistryService;

import java.util.Collection;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.join;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when one or more icons are in use
 */
public class AcctCatalogServiceIconInUseException extends AcctCatalogServiceInUseException {

    public AcctCatalogServiceIconInUseException(
        AcctCatalogErrorCodesRegistryService errors,
        Collection<String> iconUUIDs
    ) {
        super(errors.ICON_IN_USE, Map.of("iconUUIDs", join(iconUUIDs, ",")));
    }
}
