package com.desolatetimelines.acct.usermanagement.repository;

import com.desolatetimelines.acct.usermanagement.model.AcctUser;

import java.util.Optional;

/**
 * Repository for loading and persisting {@link AcctUser user accounts}
 */
public interface UserAccountsRepository {

    /**
     * Returns a new instance of {@link AcctUser}
     */
    AcctUser createNew();

    /**
     * Retrieves the {@link AcctUser user account} having the given {@link AcctUser#userUUID() user UUID},
     * if such a user account exists, or an empty optional if such a user account does not exist.
     *
     * @param userUUID the given user UUID
     */
    Optional<AcctUser> findUserAccountByUserUUID(String userUUID);

    /**
     * Retrieves the {@link AcctUser user account} having the given
     * {@link AcctUser#getUserLoginName()}  user login name}, if such
     * a user account exists, or an empty optional if such a user account
     * does not exist.
     *
     * @param userLoginName the given user login name
     */
    Optional<AcctUser> findUserAccountByUserLoginName(String userLoginName);

}
