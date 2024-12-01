package com.desolatetimelines.acct.security.ws.endpoint.model;

/**
 * Defines a workspace ownership record
 *
 * @param ownerType     the type of owner
 * @param ownerUUID     the UUID of the owner
 * @param workspaceUUID the UUID of the workspace
 */
public record WorkspaceOwner(
    OwnerType ownerType,
    String ownerUUID,
    String workspaceUUID
) {

}
