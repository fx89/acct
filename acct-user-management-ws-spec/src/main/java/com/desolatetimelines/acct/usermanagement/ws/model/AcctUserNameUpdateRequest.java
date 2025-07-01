package com.desolatetimelines.acct.usermanagement.ws.model;

/**
 * Container for the userName property, to be transferred in via REST API calls
 */
public record AcctUserNameUpdateRequest(
    String userName
) {
}
