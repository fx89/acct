package com.desolatetimelines.acct.usermanagement.ws.endpoint;

import com.desolatetimelines.acct.usermanagement.ws.model.AcctGroupDetails;

import java.util.Collection;

/**
 * Specifies groups endpoint functionality that can be accessed by both clients and other ACCT services.
 * Defines both client and server functionality.
 */
public interface GroupsEndpoint {

    /**
     * Retrieves a list of groups mapped to the user with the given UUID
     *
     * @param userUUID the given UUID
     */
    Collection<AcctGroupDetails> getUserGroups(String userUUID);

}
