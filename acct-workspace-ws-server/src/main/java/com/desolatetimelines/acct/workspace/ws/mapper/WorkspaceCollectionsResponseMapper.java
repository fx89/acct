package com.desolatetimelines.acct.workspace.ws.mapper;

import com.desolatetimelines.acct.workspace.model.AcctWorkspacesByOwnership;
import com.desolatetimelines.acct.workspace.ws.model.WorkspaceCollectionsResponse;

/**
 * Provides mapping methods for the {@link WorkspaceCollectionsResponse} type
 */
public abstract class WorkspaceCollectionsResponseMapper {

    public static WorkspaceCollectionsResponse fromAcctWorkspacesByOwnership(
        AcctWorkspacesByOwnership acctWorkspacesByOwnership
    ) {
        return
            WorkspaceCollectionsResponse.builder()
                .withUserWorkspaces(
                    WorkspaceDetailsMapper.fromAcctWorkspacesCollection(acctWorkspacesByOwnership.userWorkspaces())
                )
                .withGroupWorkspaces(
                    WorkspaceDetailsMapper.fromAcctWorkspacesCollection(acctWorkspacesByOwnership.groupWorkspaces())
                )
                .withPublicWorkspaces(
                    WorkspaceDetailsMapper.fromAcctWorkspacesCollection(acctWorkspacesByOwnership.publicWorkspaces())
                )
                .build();
    }

}
