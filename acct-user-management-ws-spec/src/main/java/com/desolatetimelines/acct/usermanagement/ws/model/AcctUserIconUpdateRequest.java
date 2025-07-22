package com.desolatetimelines.acct.usermanagement.ws.model;

/**
 * Container for the userIconUUID property, to be transferred in via REST API calls
 */
public record AcctUserIconUpdateRequest(
    String userIconUUID
) {
}
