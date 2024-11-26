package com.desolatetimelines.acct.usermanagement.ws.endpoint;

import com.desolatetimelines.acct.usermanagement.ws.model.AcctCurrentUserPasswordSettingRequest;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserCreationRequest;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserDetails;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserUUIDResponse;

/**
 * Specifies users endpoint functionality that can be accessed by both clients and other ACCT services.
 * Defines both client and server functionality.
 */
public interface UsersEndpoint {

    /**
     * Retrieves the details of the user with the give user UUID
     *
     * @param userUUID the given user UUID
     */
    AcctUserDetails getUserByUserUUID(String userUUID);

    /**
     * Creates a new user with the details stated in the given user creation request
     *
     * @param request the given user creation request
     * @return the user UUID
     */
    AcctUserUUIDResponse saveUser(AcctUserCreationRequest request);

    /**
     * Sets the password of the current user to the given value
     *
     * @param passwordSettingRequest container for the given value
     */
    void setCurrentUserPassword(AcctCurrentUserPasswordSettingRequest passwordSettingRequest);

}
