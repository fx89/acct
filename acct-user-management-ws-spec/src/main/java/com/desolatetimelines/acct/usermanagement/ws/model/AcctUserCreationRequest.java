package com.desolatetimelines.acct.usermanagement.ws.model;

/**
 * Container for the parameters of a request to create a user
 *
 * @param userLoginName         the login ID of the user
 * @param userEncryptedPassword the encrypted password chosen for the user
 * @param userName              the human-readable name of the user
 * @param userIconUUID          the UUID of the icon chosen for the user
 * @param defaultWorkspaceUUID  the UUID of the workspace on which the user lands after logging in
 */
public record AcctUserCreationRequest(

    String userLoginName,
    String userEncryptedPassword,
    String userName,
    String userIconUUID,
    String defaultWorkspaceUUID

) {
}
