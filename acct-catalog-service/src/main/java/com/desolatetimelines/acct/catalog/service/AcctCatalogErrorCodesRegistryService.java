package com.desolatetimelines.acct.catalog.service;

import com.desolatetimelines.acct.common.service.AbstractErrorCodesRegistryService;
import org.springframework.stereotype.Service;

@Service
public class AcctCatalogErrorCodesRegistryService extends AbstractErrorCodesRegistryService {

    public String ICON_ALREADY_EXISTS;

    public String ICON_VALIDATION_NAME_PATTERN;

    protected AcctCatalogErrorCodesRegistryService() {
        super(Integer.parseInt(System.getenv("CATALOG_SERVICE_NUMBER")));
    }

    @Override
    protected void initializeServiceSpecificErrorCodes() {
        final String CAT_NAME_CONSTRAINT_VIOLATIONS = "Constraint violations";
        final String CAT_NAME_BUSINESS_RULES_VALIDATION = "Business rules Validation";

        ICON_ALREADY_EXISTS = resolveErrorCode(
            CAT_NAME_CONSTRAINT_VIOLATIONS,
            "Icon already exists",
            "An icon with the same name as the one that is being created already exists within " +
                "the referenced icons category"
        );

        ICON_VALIDATION_NAME_PATTERN = resolveErrorCode(
            CAT_NAME_BUSINESS_RULES_VALIDATION,
            "The icon name pattern is not correct",
            "The provided icon name pattern is either too short or otherwise incorrect"
        );
    }

}
