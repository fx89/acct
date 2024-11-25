package com.desolatetimelines.acct.usermanagement.ws.model;

/**
 * Response from the user creation operation
 *
 * @param userUUID the UUID of the newly created user
 */
public record AcctUserUUIDResponse(
    String userUUID
) {
}
