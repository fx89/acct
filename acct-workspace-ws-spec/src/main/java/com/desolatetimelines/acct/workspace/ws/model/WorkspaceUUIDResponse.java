package com.desolatetimelines.acct.workspace.ws.model;

/**
 * This is an object that wraps a workspace UUID so that it may be returned
 * as an object by a REST API
 *
 * @param workspaceUUID the wrapped workspace UUID
 */
public record WorkspaceUUIDResponse(
    String workspaceUUID
) {
}
