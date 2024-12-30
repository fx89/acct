package com.desolatetimelines.acct.workspace.ws.endpoint;

import com.desolatetimelines.acct.workspace.ws.model.WorkspaceProperties;
import com.desolatetimelines.acct.workspace.ws.model.WorkspaceUUIDResponse;

/**
 * Specifies workspaces endpoint functionality that can be accessed by both clients and other ACCT services.
 * Defines both client and server functionality.
 */
public interface WorkspacesEndpoint {

    WorkspaceUUIDResponse saveWorkspace(String workspaceUUID, WorkspaceProperties workspaceProperties);

    void deleteWorkspace(String workspaceUUID);

}
