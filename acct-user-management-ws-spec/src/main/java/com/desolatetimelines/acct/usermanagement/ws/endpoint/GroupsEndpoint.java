package com.desolatetimelines.acct.usermanagement.ws.endpoint;

import com.desolatetimelines.acct.usermanagement.ws.model.AcctGroupDetails;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctPage;

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

}
