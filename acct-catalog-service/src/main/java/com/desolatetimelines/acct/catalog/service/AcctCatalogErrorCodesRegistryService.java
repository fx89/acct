package com.desolatetimelines.acct.catalog.service;

import com.desolatetimelines.acct.common.service.AbstractErrorCodesRegistryService;
import org.springframework.stereotype.Service;

@Service
public class AcctCatalogErrorCodesRegistryService extends AbstractErrorCodesRegistryService {

    public String ICON_ALREADY_EXISTS;

    protected AcctCatalogErrorCodesRegistryService() {
        super(Integer.parseInt(System.getenv("CATALOG_SERVICE_NUMBER")));
    }

    @Override
    protected void initializeServiceSpecificErrorCodes() {
        final String CAT_NAME_CONSTRAINT_VIOLATIONS = "Constraint violations";

        ICON_ALREADY_EXISTS = resolveErrorCode(
            CAT_NAME_CONSTRAINT_VIOLATIONS,
            "Icon already exists",
            "An icon with the same name as the one that is being created already exists within " +
                "the referenced icons category"
        );
    }

}
