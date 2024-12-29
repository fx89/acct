package com.desolatetimelines.acct.workspace.ws.mapper;

import com.desolatetimelines.acct.workspace.model.AcctWorkspace;
import com.desolatetimelines.acct.workspace.ws.model.WorkspaceUUIDResponse;

/**
 * Provides mapping methods for the {@link WorkspaceUUIDResponse} type
 */
public abstract class WorkspaceUUIDResponseMapper {

    public static WorkspaceUUIDResponse fromAcctWorkspace(AcctWorkspace acctWorkspace) {
        return new WorkspaceUUIDResponse(acctWorkspace.getWorkspaceUUID());
    }

}
