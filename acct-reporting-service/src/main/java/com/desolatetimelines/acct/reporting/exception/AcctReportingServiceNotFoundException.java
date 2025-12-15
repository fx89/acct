package com.desolatetimelines.acct.reporting.exception;

import com.desolatetimelines.acct.common.model.ObjectTypes;
import com.desolatetimelines.acct.reporting.service.AcctReportingErrorCodesRegistryService;

import java.util.HashMap;
import java.util.Map;

/**
 * Thrown by the {@link com.desolatetimelines.acct.reporting.service.AcctReportingService reporting service}
 * when a required entity cannot be found
 */
public class AcctReportingServiceNotFoundException extends AcctReportingServiceException {

    public AcctReportingServiceNotFoundException(
        AcctReportingErrorCodesRegistryService errors,
        ObjectTypes resourceType,
        String resourceUUID
    ) {
        super(
            getNotFoundErrorCodeForResourceType(errors, resourceType),
            computeErrorParameters(resourceType, resourceUUID)
        );
    }

    private static String getNotFoundErrorCodeForResourceType(
        AcctReportingErrorCodesRegistryService errors,
        ObjectTypes resourceType
    ) {
        if (ObjectTypes.DASHBOARD == resourceType) {
            return errors.RESOURCE_NOT_FOUND_DASHBOARD;
        }

        if (ObjectTypes.DATA_PROVIDER == resourceType) {
            return errors.RESOURCE_NOT_FOUND_DATA_PROVIDER;
        }

        if (ObjectTypes.DATA_PROVIDER_INSTANCE == resourceType) {
            return errors.RESOURCE_NOT_FOUND_DATA_PROVIDER_INSTANCE;
        }

        if (ObjectTypes.REPORT == resourceType) {
            return errors.RESOURCE_NOT_FOUND_REPORT;
        }


        throw new IllegalStateException(
            "Developer forgot to add the code that handles the resource type " +
                (resourceType == null ? "null" : resourceType.name())
        );
    }

    private static Map<String, String> computeErrorParameters(ObjectTypes resourceType, String resourceUUID) {
        // Create the parameters map
        final Map<String, String> parameters = new HashMap<>(2);

        // Put the resource type in the parameters map
        parameters.put("resourceType", resourceType.name());

        // If a resource UUID was provided, put that in the parameters map
        if (resourceUUID != null) {
            parameters.put("resourceUUID", resourceUUID);
        }

        // Return a reference to the parameters map
        return parameters;
    }

}
