package com.desolatetimelines.acct.security.ws.endpoint;

import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;

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

}
