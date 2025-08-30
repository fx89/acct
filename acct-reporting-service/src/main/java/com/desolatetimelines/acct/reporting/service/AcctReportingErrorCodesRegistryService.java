package com.desolatetimelines.acct.reporting.service;

import com.desolatetimelines.acct.common.service.AbstractErrorCodesRegistryService;
import org.springframework.stereotype.Service;

@Service
public class AcctReportingErrorCodesRegistryService extends AbstractErrorCodesRegistryService {

    public String RESOURCE_NOT_FOUND_DASHBOARD;

    protected AcctReportingErrorCodesRegistryService() {
        super(Integer.parseInt(System.getenv("WORKSPACE_SERVICE_NUMBER")));
    }

    @Override
    protected void initializeServiceSpecificErrorCodes() {
        final String CAT_NAME_DATA_ACCESS = "Data access exceptions";

        RESOURCE_NOT_FOUND_DASHBOARD =
            resolveErrorCode(
                CAT_NAME_DATA_ACCESS,
                "Dashboard not found",
                "The service was requested to run operations invoking a dashboard that does not exist"
            );
    }

}
