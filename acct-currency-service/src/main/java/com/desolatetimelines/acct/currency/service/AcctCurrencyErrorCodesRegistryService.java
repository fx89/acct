package com.desolatetimelines.acct.currency.service;

import com.desolatetimelines.acct.common.service.AbstractErrorCodesRegistryService;
import org.springframework.stereotype.Service;

@Service
public class AcctCurrencyErrorCodesRegistryService extends AbstractErrorCodesRegistryService {

    public String MONITORED_CURRENCY_ALREADY_EXISTS;

    public String MONITORED_CURRENCY_NOT_FOUND;

    protected AcctCurrencyErrorCodesRegistryService() {
        super(Integer.parseInt(System.getenv("CURRENCY_SERVICE_NUMBER")));
    }

    @Override
    protected void initializeServiceSpecificErrorCodes() {
        final String CUR_NAME_CONSTRAINT_VIOLATIONS = "Constraint violations";
        final String CUR_NAME_NOT_FOUND = "Not found exceptions";

        MONITORED_CURRENCY_ALREADY_EXISTS = resolveErrorCode(
            CUR_NAME_CONSTRAINT_VIOLATIONS,
            "Monitored currency already exists",
            "A monitored currency with the same name as the one that is being created already exists"
        );

        MONITORED_CURRENCY_NOT_FOUND = resolveErrorCode(
            CUR_NAME_NOT_FOUND,
            "The monitored currency was not found",
            "An operation was requested for a monitored currency that cannot be found"
        );
    }

}
