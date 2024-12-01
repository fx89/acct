package com.desolatetimelines.acct.security.ws.endpoint;

import com.desolatetimelines.acct.security.ws.endpoint.model.OwnedWorkspacesGroup;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;
import com.desolatetimelines.acct.security.ws.endpoint.model.WorkspaceAccessibilityReport;
import com.desolatetimelines.acct.security.ws.endpoint.model.WorkspaceOwner;

import java.util.Collection;

/**
 * Specification for the workspace ownership endpoint. Defines both client and server functionality.
 */
public interface WorkspaceOwnershipEndpoint {

    /**
     * Returns a set of UUIDs of the workspaces owned by the owner of the given owner type
     * having the given owner UUID
     *
     * @param ownerType the given owner type
     * @param ownerUUID the given owner UUID
     */
    Collection<String> getWorkspacesOwnedByOwnerOfType(OwnerType ownerType, String ownerUUID);

    /**
     * Returns a group of collections containing the UUIDs of the workspaces accessible to the user
     * via each possible ownership type: <ul>
     * <li>{@link OwnerType#USER accessible to the user}</li>
     * <li>{@link OwnerType#GROUP accessible to the user's group}</li>
     * <li>{@link OwnerType#PUBLIC accessible to everyone}</li>
     * </ul>
     *
     * @param userUUID the UUID of the user whose resources are being queried
     */
    OwnedWorkspacesGroup getUserAccessibleWorkspaces(String userUUID);

    /**
     * Creates a new workspace ownership record with the provider details
     *
     * @param workspaceOwner container for the provided details
     */
    void addWorkspaceOwner(WorkspaceOwner workspaceOwner);

    /**
     * Deletes the workspace owner of the given owner type having the given owner UUID
     * and the given workspaceUUID
     *
     * @param ownerType     the given owner type
     * @param ownerUUID     the given owner UUID
     * @param workspaceUUID the given workspace UUID
     */
    void deleteWorkspaceOwner(OwnerType ownerType, String ownerUUID, String workspaceUUID);

    /**
     * Checks the accessibility of the workspace identified by the given workspace UUID
     * to the user identified by the given user UUID
     *
     * @param userUUID      the given user UUID
     * @param workspaceUUID the given workspace UUID
     * @return the accessibility report
     */
    WorkspaceAccessibilityReport isUserAccessibleWorkspace(String userUUID, String workspaceUUID);

}
