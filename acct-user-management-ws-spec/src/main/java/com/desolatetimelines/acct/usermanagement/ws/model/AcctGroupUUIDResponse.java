package com.desolatetimelines.acct.usermanagement.ws.model;

/**
 * Response from the user creation operation
 *
 * @param groupUUID the UUID of the newly created group
 */
public record AcctGroupUUIDResponse(

    String groupUUID

) {
}
