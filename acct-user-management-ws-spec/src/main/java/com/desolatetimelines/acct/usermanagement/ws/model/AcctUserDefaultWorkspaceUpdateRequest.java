package com.desolatetimelines.acct.usermanagement.ws.model;

/**
 * Container for the defaultWorkspaceUUID property, to be transferred in via REST API calls
 */
public record AcctUserDefaultWorkspaceUpdateRequest(
    String defaultWorkspaceUUID
) {
}
