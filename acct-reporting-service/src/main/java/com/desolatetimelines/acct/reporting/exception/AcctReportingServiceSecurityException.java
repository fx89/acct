package com.desolatetimelines.acct.reporting.exception;

import com.desolatetimelines.acct.common.model.ObjectTypes;
import com.desolatetimelines.acct.reporting.service.AcctReportingErrorCodesRegistryService;

import java.util.HashMap;
import java.util.Map;

/**
 * Thrown by the {@link com.desolatetimelines.acct.reporting.service.AcctReportingService reporting service}
 * when users attempt to execute operations that are not allowed
 */
public class AcctReportingServiceSecurityException extends AcctReportingServiceException {

    public AcctReportingServiceSecurityException(
        AcctReportingErrorCodesRegistryService errors,
        ObjectTypes resourceType,
        String resourceUUID
    ) {
        super(
            getSecurityErrorCodeForResourceType(errors, resourceType),
            computeErrorParameters(resourceType, resourceUUID)
        );
    }

    private static String getSecurityErrorCodeForResourceType(
        AcctReportingErrorCodesRegistryService errors,
        ObjectTypes resourceType
    ) {
        if (ObjectTypes.DASHBOARD == resourceType) {
            return errors.SECURITY_INACCESSIBLE_DASHBOARD;
        }

        if (ObjectTypes.REPORT == resourceType) {
            return errors.SECURITY_INACCESSIBLE_REPORT;
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
