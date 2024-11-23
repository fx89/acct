package com.desolatetimelines.acct.usermanagement.ws.privateendpoint;

import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserDetails;

/**
 * Specifies users endpoint functionality that can be accessed by other ACCT services, but not by clients.
 * The idea is to avoid polluting the list of publicly-available functions with those that are for private
 * use only.
 * Defines both client and server functionality.
 */
public interface UsersPrivateEndpoint {

    /**
     * Retrieves the details of the user with the given username
     */
    AcctUserDetails getUserByUsername(String username);

}
