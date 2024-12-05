package com.desolatetimelines.acct.usermanagement.ws.endpoint;

import com.desolatetimelines.acct.usermanagement.ws.model.AcctGroupDetails;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctGroupUUIDResponse;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctPage;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUsersGroupCreationRequest;

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

    /**
     * Returns a page of {@link AcctGroupDetails group records} having the given size and number,
     * for the groups for which the {@link AcctGroupDetails#groupName()}  group name} matches
     * the given pattern
     *
     * @param pattern    the given pattern
     * @param pageNumber the given number
     * @param pageSize   the given size
     */
    AcctPage<AcctGroupDetails> findSortedPageOfGroupsByNamePattern(String pattern, int pageNumber, int pageSize);

    /**
     * Updates the group with the given UUID, setting the properties provided in the given creation request.
     * If a UUID is not given, a new group is created with the given details.
     *
     * @param groupUUID                 the given UUID
     * @param usersGroupCreationRequest the given creation request
     * @return the UUID of the created or updated group
     */
    AcctGroupUUIDResponse saveUsersGroup(
        String groupUUID,
        AcctUsersGroupCreationRequest usersGroupCreationRequest
    );

}
