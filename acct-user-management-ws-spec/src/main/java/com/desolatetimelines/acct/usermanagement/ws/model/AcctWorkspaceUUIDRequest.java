package com.desolatetimelines.acct.usermanagement.ws.model;

/**
 * Request for setting or updating a user's default workspace
 *
 * @param workspaceUUID the UUID of the new default workspace for the user
 */
public record AcctWorkspaceUUIDRequest(
    String workspaceUUID
) {
}
