package com.desolatetimelines.acct.workspace.exception;

import com.desolatetimelines.acct.common.exception.AcctException;
import com.desolatetimelines.acct.common.exception.NotFoundException;
import com.desolatetimelines.acct.common.model.ObjectTypes;
import com.desolatetimelines.acct.workspace.service.AcctWorkspaceErrorCodesRegistryService;

import java.util.HashMap;
import java.util.Map;

/**
 * Thrown by the {@link com.desolatetimelines.acct.workspace.service.AcctWorkspaceService}
 * when an entity of a given type is not found
 */
@NotFoundException
public class AcctWorkspaceServiceNotFoundException extends AcctException {

    /**
     * @param errors       a reference to the error codes registry service defined in the workspace module
     * @param resourceType the resource type
     * @param resourceUUID the resource UUID
     */
    public AcctWorkspaceServiceNotFoundException(
        AcctWorkspaceErrorCodesRegistryService errors,
        ObjectTypes resourceType,
        String resourceUUID
    ) {
        super(
            getNotFoundErrorCodeForResourceType(errors, resourceType),
            computeErrorParameters(resourceType, resourceUUID)
        );
    }

    private static String getNotFoundErrorCodeForResourceType(
        AcctWorkspaceErrorCodesRegistryService errors,
        ObjectTypes resourceType
    ) {
        if (ObjectTypes.WORKSPACE == resourceType) {
            return errors.RESOURCE_NOT_FOUND_WORKSPACE;
        }

        if (ObjectTypes.ACCOUNT == resourceType) {
            return errors.RESOURCE_NOT_FOUND_ACCOUNT;
        }

        if (ObjectTypes.ACCOUNT_RECORD == resourceType) {
            return errors.RESOURCE_NOT_FOUND_ACCOUNT_RECORD;
        }

        if (ObjectTypes.DEPOSIT == resourceType) {
            return errors.RESOURCE_NOT_FOUND_DEPOSIT;
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
