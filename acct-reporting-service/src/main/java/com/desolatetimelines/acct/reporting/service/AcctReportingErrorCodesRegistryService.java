package com.desolatetimelines.acct.reporting.service;

import com.desolatetimelines.acct.common.service.AbstractErrorCodesRegistryService;
import org.springframework.stereotype.Service;

@Service
public class AcctReportingErrorCodesRegistryService extends AbstractErrorCodesRegistryService {

    public String RESOURCE_NOT_FOUND_DASHBOARD;
    public String SECURITY_INACCESSIBLE_DASHBOARD;

    protected AcctReportingErrorCodesRegistryService() {
        super(Integer.parseInt(System.getenv("REPORTING_SERVICE_NUMBER")));
    }

    @Override
    protected void initializeServiceSpecificErrorCodes() {
        final String CAT_NAME_DATA_ACCESS = "Data access exceptions";
        final String CAT_NAME_SECURITY = "Security exceptions";

        RESOURCE_NOT_FOUND_DASHBOARD =
            resolveErrorCode(
                CAT_NAME_DATA_ACCESS,
                "Dashboard not found",
                "The service was requested to run operations invoking a dashboard that does not exist"
            );

        SECURITY_INACCESSIBLE_DASHBOARD =
            resolveErrorCode(
                CAT_NAME_SECURITY,
                "Dashboard-related operation not allowed",
                "A user is attempting to run a dashboard-related operation that is not permitted"
            );
    }

}
