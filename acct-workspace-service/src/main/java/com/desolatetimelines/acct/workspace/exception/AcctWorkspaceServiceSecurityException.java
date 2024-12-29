package com.desolatetimelines.acct.workspace.exception;

import com.desolatetimelines.acct.common.exception.ForbiddenException;
import com.desolatetimelines.acct.common.model.ObjectTypes;
import com.desolatetimelines.acct.workspace.service.AcctWorkspaceErrorCodesRegistryService;

import java.util.Map;

/**
 * Thrown by the {@link com.desolatetimelines.acct.workspace.service.AcctWorkspaceService}
 * when a resource of a given {@link ObjectTypes type} is not accessible to a user.
 */
@ForbiddenException
public class AcctWorkspaceServiceSecurityException extends AcctWorkspaceServiceException {

    /**
     * @param errors       a reference to the error codes registry service defined in the workspace module
     * @param resourceType the resource type
     * @param resourceUUID the resource UUID
     */
    public AcctWorkspaceServiceSecurityException(
        AcctWorkspaceErrorCodesRegistryService errors,
        ObjectTypes resourceType,
        String resourceUUID
    ) {
        super(
            errors.RESOURCE_NOT_ACCESSIBLE,
            Map.of(
                "resourceType", resourceType.name(),
                "resourceUUID", resourceUUID
            )
        );
    }
}
