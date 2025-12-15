package com.desolatetimelines.acct.reporting.service;

import com.desolatetimelines.acct.common.service.AbstractErrorCodesRegistryService;
import org.springframework.stereotype.Service;

@Service
public class AcctReportingErrorCodesRegistryService extends AbstractErrorCodesRegistryService {

    public String RESOURCE_NOT_FOUND_DASHBOARD;
    public String RESOURCE_NOT_FOUND_DATA_PROVIDER;
    public String RESOURCE_NOT_FOUND_DATA_PROVIDER_INSTANCE;
    public String RESOURCE_NOT_FOUND_REPORT;
    public String SECURITY_INACCESSIBLE_DASHBOARD;
    public String DATA_PROVIDER_INSTANCE_PROPERTY_NOT_SUPPLIED;

    protected AcctReportingErrorCodesRegistryService() {
        super(Integer.parseInt(System.getenv("REPORTING_SERVICE_NUMBER")));
    }

    @Override
    protected void initializeServiceSpecificErrorCodes() {
        final String CAT_NAME_DATA_ACCESS = "Data access exceptions";
        final String CAT_NAME_SECURITY = "Security exceptions";
        final String CAT_NAME_INPUT_VALIDATION = "Input validation exceptions";

        RESOURCE_NOT_FOUND_DASHBOARD =
            resolveErrorCode(
                CAT_NAME_DATA_ACCESS,
                "Dashboard not found",
                "The service was requested to run operations invoking a dashboard that does not exist"
            );

        RESOURCE_NOT_FOUND_DATA_PROVIDER =
            resolveErrorCode(
                CAT_NAME_DATA_ACCESS,
                "Data provider not found",
                "The service was requested to run operations invoking a data provider that does not exist"
            );

        RESOURCE_NOT_FOUND_DATA_PROVIDER_INSTANCE =
            resolveErrorCode(
                CAT_NAME_DATA_ACCESS,
                "Data provider instance not found",
                "The service was requested to run operations invoking a data provider instance that does not exist"
            );

        RESOURCE_NOT_FOUND_REPORT =
            resolveErrorCode(
                CAT_NAME_DATA_ACCESS,
                "Report not found",
                "The service was requested to run operations invoking a report that does not exist"
            );

        SECURITY_INACCESSIBLE_DASHBOARD =
            resolveErrorCode(
                CAT_NAME_SECURITY,
                "Dashboard-related operation not allowed",
                "A user is attempting to run a dashboard-related operation that is not permitted"
            );

        DATA_PROVIDER_INSTANCE_PROPERTY_NOT_SUPPLIED =
            resolveErrorCode(
                CAT_NAME_INPUT_VALIDATION,
                "Data provider instance property not supplied",
                "A data provider instance property, which was defined by the data provider, " +
                    "was not supplied when registering the data provider instance"
            );
    }

}
