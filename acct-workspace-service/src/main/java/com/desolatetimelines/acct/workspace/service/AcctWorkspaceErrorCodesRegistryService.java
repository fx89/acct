package com.desolatetimelines.acct.workspace.service;

import com.desolatetimelines.acct.common.service.AbstractErrorCodesRegistryService;
import org.springframework.stereotype.Service;

@Service
public class AcctWorkspaceErrorCodesRegistryService extends AbstractErrorCodesRegistryService {

    public String RESOURCE_NOT_ACCESSIBLE;

    public String RESOURCE_NOT_FOUND_WORKSPACE;
    public String RESOURCE_NOT_FOUND_ACCOUNT;
    public String RESOURCE_NOT_FOUND_ACCOUNT_RECORD;
    public String RESOURCE_NOT_FOUND_DEPOSIT;
    public String INSUFFICIENT_FUNDS;
    public String MISMATCHED_CURRENCIES;
    public String SAME_CURRENCY;
    public String ALREADY_CAPITALIZED;

    protected AcctWorkspaceErrorCodesRegistryService() {
        super(Integer.parseInt(System.getenv("WORKSPACE_SERVICE_NUMBER")));
    }

    @Override
    protected void initializeServiceSpecificErrorCodes() {
        final String CAT_NAME_SECURITY = "Service-specific security exceptions";

        final String CAT_NAME_DATA_ACCESS = "Data access exceptions";

        final String CAT_NAME_BUSINESS_RULES_VALIDATION = "Business rules validation exceptions";

        RESOURCE_NOT_ACCESSIBLE =
            resolveErrorCode(
                CAT_NAME_SECURITY,
                "Resource not accessible",
                "A user or service requires access to a resource that is not owned or accessible in " +
                    "any way by the said user or service"
            );

        RESOURCE_NOT_FOUND_WORKSPACE =
            resolveErrorCode(
                CAT_NAME_DATA_ACCESS,
                "Workspace not found",
                "The service was requested to run operations invoking a workspace that does not exist"
            );

        RESOURCE_NOT_FOUND_ACCOUNT =
            resolveErrorCode(
                CAT_NAME_DATA_ACCESS,
                "Account not found",
                "The service was requested to run operations invoking an account that does not exist"
            );

        RESOURCE_NOT_FOUND_ACCOUNT_RECORD =
            resolveErrorCode(
                CAT_NAME_DATA_ACCESS,
                "Account record not found",
                "The service was requested to run operations invoking an account record that does not exist"
            );

        RESOURCE_NOT_FOUND_DEPOSIT =
            resolveErrorCode(
                CAT_NAME_DATA_ACCESS,
                "Deposit not found",
                "The service was requested to run operations invoking a deposit that does not exist"
            );

        MISMATCHED_CURRENCIES =
            resolveErrorCode(
                CAT_NAME_BUSINESS_RULES_VALIDATION,
                "Mismatched currencies",
                "An illegal operation was attempted on two accounts with different currencies"
            );

        SAME_CURRENCY =
            resolveErrorCode(
                CAT_NAME_BUSINESS_RULES_VALIDATION,
                "Same currency",
                "An operation was attempted on two accounts which were expected to have different " +
                    "currencies and yet they have been found to have the same currency"
            );

        INSUFFICIENT_FUNDS =
            resolveErrorCode(
                CAT_NAME_BUSINESS_RULES_VALIDATION,
                "Insufficient funds",
                "An operation cannot be performed because an account does not sufficient funds"
            );

        ALREADY_CAPITALIZED =
            resolveErrorCode(
                CAT_NAME_BUSINESS_RULES_VALIDATION,
                "Deposit already capitalized",
                "An attempt was made to capitalize a deposit that has already been capitalized"
            );
    }

}
