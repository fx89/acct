package com.desolatetimelines.acct.workspace.ws.endpoint;

import com.desolatetimelines.acct.workspace.ws.model.WorkspaceCollectionsResponse;
import com.desolatetimelines.acct.workspace.ws.model.WorkspaceDetails;
import com.desolatetimelines.acct.workspace.ws.model.WorkspaceProperties;
import com.desolatetimelines.acct.workspace.ws.model.WorkspaceUUIDResponse;

import java.util.Collection;

/**
 * Specifies workspaces endpoint functionality that can be accessed by both clients and other ACCT services.
 * Defines both client and server functionality.
 */
public interface WorkspacesEndpoint {

    /**
     * Persists the given workspace properties to either a new workspace entity, if the workspace UUID parameter
     * is not given, or to an existing workspace with the UUID matching the given workspace UUID parameter.
     *
     * @param workspaceUUID       the workspace UUID parameter
     * @param workspaceProperties the given workspace properties
     */
    WorkspaceUUIDResponse saveWorkspace(String workspaceUUID, WorkspaceProperties workspaceProperties);

    /**
     * Deletes the workspace with the given workspace UUID
     *
     * @param workspaceUUID the given workspace UUID
     */
    void deleteWorkspace(String workspaceUUID);

    /**
     * Retrieves the details of all the workspaces accessible to the current user.
     */
    WorkspaceCollectionsResponse getUserAccessibleWorkspaces();

    /**
     * Retrieves the details of all workspaces that are directly accessible to the user with the given user UUID
     *
     * @param userUUID the given user UUID
     */
    Collection<WorkspaceDetails> getUserWorkspaces(String userUUID);

}
