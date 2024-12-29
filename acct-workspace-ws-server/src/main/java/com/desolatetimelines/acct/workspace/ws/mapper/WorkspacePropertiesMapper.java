package com.desolatetimelines.acct.workspace.ws.mapper;

import com.desolatetimelines.acct.workspace.model.WorkspaceDetails;
import com.desolatetimelines.acct.workspace.ws.model.WorkspaceProperties;

/**
 * Provides mapping methods for the {@link WorkspaceProperties} type
 */
public abstract class WorkspacePropertiesMapper {

    public static WorkspaceDetails toWorkspaceDetails(String workspaceUUID, WorkspaceProperties workspaceProperties) {
        return
            WorkspaceDetails.builder()
                .withWorkspaceUUID(workspaceUUID)
                .withWorkspaceName(workspaceProperties.workspaceName())
                .withWorkspaceDescription(workspaceProperties.workspaceDescription())
                .withWorkspaceIconUUID(workspaceProperties.workspaceIconUUID())
                .withDefaultCurrencyUUID(workspaceProperties.defaultCurrencyUUID())
                .build();
    }

}
