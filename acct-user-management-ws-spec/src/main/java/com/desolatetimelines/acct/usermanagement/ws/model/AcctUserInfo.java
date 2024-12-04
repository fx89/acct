package com.desolatetimelines.acct.usermanagement.ws.model;

/**
 * Brief representation of a user in the ACCT ecosystem
 *
 * @param userUUID      a V4 UUID that uniquely identifies the user in the ACCT ecosystem
 * @param userName      the human-readable name of the user
 * @param userLoginName the login ID of the user
 */
public record AcctUserInfo(

    String userUUID,
    String userName,
    String userLoginName

) {
}
