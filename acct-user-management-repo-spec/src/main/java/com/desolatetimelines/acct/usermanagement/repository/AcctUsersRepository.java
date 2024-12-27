package com.desolatetimelines.acct.usermanagement.repository;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.usermanagement.model.AcctUser;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;

/**
 * Repository for loading and persisting {@link AcctUser user accounts}
 */
public interface AcctUsersRepository {

    /**
     * Returns a new instance of {@link AcctUser}
     */
    AcctUser createNew();

    /**
     * Persists the referenced user and returns a reference to the persisted entity
     *
     * @param acctUser the referenced user
     */
    AcctUser save(AcctUser acctUser);

    /**
     * Retrieves the {@link AcctUser user account} having the given {@link AcctUser#getUserUUID()} user UUID},
     * if such a user account exists, or an empty optional if such a user account does not exist.
     *
     * @param userUUID the given user UUID
     */
    Optional<AcctUser> findUserAccountByUserUUID(String userUUID);

    /**
     * Retrieves the {@link AcctUser user account} having the given
     * {@link AcctUser#getUserLoginName()}  user login name, if such
     * a user account exists, or an empty optional if such a user account
     * does not exist.
     *
     * @param userLoginName the given user login name
     */
    Optional<AcctUser> findUserAccountByUserLoginName(String userLoginName);

    /**
     * Returns a page of user with the given number and of the given size,
     * containing users for which either the login name or human-readable
     * name contains the given pattern
     *
     * @param pattern    the given pattern
     * @param pageNumber the given number
     * @param pageSize   the given size
     */
    Page<AcctUser> findUsersByUserLoginNameLikeOrUserNameLike(String pattern, int pageNumber, int pageSize);

    /**
     * Returns a collection of {@link AcctUser users} that have the
     * {@link AcctUser#getSoftDeleted() softDeleted flag} set to true and the
     * {@link AcctUser#getSoftDeletedDate() softDeletedDate} older than the given
     * reference date
     *
     * @param referenceDate the given reference date
     */
    Collection<AcctUser> findUsersBySoftDeletedTrueAndSoftDeletedDateLessThan(Instant referenceDate);

    /**
     * Deletes all the users in the referenced list
     *
     * @param users the referenced list
     */
    void deleteUsers(Collection<AcctUser> users);

    /**
     * Returns the list of {@link AcctUser users} that are using one of the icon UUIDs from the given list
     *
     * @param userIconUUIDs the given list
     */
    Collection<AcctUser> findUsersByUserIconUUIDIn(Collection<String> userIconUUIDs);

    /**
     * Returns a list of {@link AcctUser users} that are using one of the workspace UUIDs from the given list
     *
     * @param workspaceUUID the given list
     */
    Collection<AcctUser> findUsersByWorkspaceUUIDIn(Collection<String> workspaceUUID);
}
