package com.desolatetimelines.acct.usermanagement.ws.model;

/**
 * Request for setting the current user's password.
 * Use <a href="https://www.browserling.com/tools/bcrypt">this site</a> to bcrypt with the default parameters.
 *
 * @param userEncryptedPassword a bcrypt-encrypted password
 */
public record AcctCurrentUserPasswordSettingRequest(

    String userEncryptedPassword

) {
}
