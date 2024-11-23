package com.desolatetimelines.acct.usermanagement.ws.endpoint;

import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserDetails;

/**
 * Specifies users endpoint functionality that can be accessed by both clients and other ACCT services.
 * Defines both client and server functionality.
 */
public interface UsersEndpoint {

    /**
     * Retrieves the details of the user with the give userUUID
     */
    AcctUserDetails getUserByUserUUID(String userUUID);

}
