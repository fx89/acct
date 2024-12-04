package com.desolatetimelines.acct.usermanagement.ws.endpoint;

import com.desolatetimelines.acct.usermanagement.ws.model.*;

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

    /**
     * Retrieves the details of the current user
     */
    AcctUserDetails getCurrentUser();

    /**
     * Sets the {@code softDeleted} flag to {@code true} for the user
     * identified by the given user UUID
     *
     * @param userUUID the given user UUID
     */
    void deleteUser(String userUUID);

    /**
     * Sets the {@code softDeleted} flag to {@code false} for the user
     * identified by the given user UUID
     *
     * @param userUUID the given user UUID
     */
    void undelete(String userUUID);

    /**
     * Returns a page of {@link AcctUserInfo user information records} having the given size and number,
     * for the users for which the {@link AcctUserInfo#userLoginName() login name} or the
     * {@link AcctUserInfo#userName() human-readable name} matches the given pattern
     *
     * @param pattern    the given pattern
     * @param pageNumber the given number
     * @param pageSize   the given size
     */
    AcctPage<AcctUserInfo> findSortedPageOfUsersByLoginNameOrNamePattern(String pattern, int pageNumber, int pageSize);

}
