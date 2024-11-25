package com.desolatetimelines.acct.usermanagement.repository;

import com.desolatetimelines.acct.usermanagement.model.AcctUser;
import com.desolatetimelines.acct.usermanagement.model.AcctUsersGroup;

import java.util.Optional;
import java.util.Set;

/**
 * Repository for loading and persisting {@link AcctUsersGroup user groups}
 */
public interface AcctUserGroupsRepository {

    /**
     * Returns a new instance of {@link AcctUsersGroup}
     */
    AcctUsersGroup createNew();

    /**
     * Retrieves a set of the {@link AcctUsersGroup user groups} to which the
     * {@link AcctUser user account}
     * with the given user UUID is assigned. If there are no such user groups,
     * an empty set is returned.
     *
     * @param userUUID the given user UUID
     */
    Set<AcctUsersGroup> findUserGroupByUserUUID(String userUUID);

    /**
     * Retrieves the group with the given group UUID or returns an empty optional.
     *
     * @param groupUUID the given group UUID
     */
    Optional<AcctUsersGroup> findFirstByGroupUUID(String groupUUID);

    /**
     * Saves the given users group
     *
     * @param usersGroup the given users group
     * @return a reference to the created entity
     */
    AcctUsersGroup save(AcctUsersGroup usersGroup);

}
