package com.desolatetimelines.acct.workspace.ws.mapper;

import com.desolatetimelines.acct.workspace.model.AcctWorkspace;
import com.desolatetimelines.acct.workspace.ws.model.WorkspaceDetails;

import java.util.Collection;

/**
 * Provides mapping methods for the {@link WorkspaceDetails} type
 */
public abstract class WorkspaceDetailsMapper {

    public static WorkspaceDetails fromAcctWorkspace(AcctWorkspace acctWorkspace) {
        return
            WorkspaceDetails.builder()
                .withWorkspaceUUID(acctWorkspace.getWorkspaceUUID())
                .withWorkspaceName(acctWorkspace.getWorkspaceName())
                .withWorkspaceDescription(acctWorkspace.getWorkspaceDescription())
                .withWorkspaceIconUUID(acctWorkspace.getWorkspaceIconUUID())
                .withDefaultCurrencyUUID(acctWorkspace.getDefaultCurrencyUUID())
                .build();
    }

    public static Collection<WorkspaceDetails> fromAcctWorkspacesCollection(Collection<AcctWorkspace> acctWorkspaces) {
        return
            acctWorkspaces
                .stream()
                .map(WorkspaceDetailsMapper::fromAcctWorkspace)
                .toList();
    }
}
