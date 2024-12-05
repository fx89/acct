package com.desolatetimelines.acct.usermanagement.ws.model;

/**
 * Container for the parameters of a request to create a users group
 *
 * @param groupName        The name of the group
 * @param groupDescription The description of the group
 * @param groupIconUUID    the UUID of the group's icon
 */
public record AcctUsersGroupCreationRequest(

    String groupName,
    String groupDescription,
    String groupIconUUID

) {
}
