package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.workspace.model.AcctAccount;

import java.util.Optional;

/**
 * Repository for loading and persisting {@link AcctAccount accounts}
 */
public interface AccountsRepository {

    /**
     * Creates a new instance of {@link AcctAccount}
     */
    AcctAccount createNew();

    /**
     * Retrieves the {@link AcctAccount account} having the given account UUID
     * or an empty optional if such an account does not exist
     *
     * @param accountUUID the given account UUID
     */
    Optional<AcctAccount> findFirstByAccountUUID(String accountUUID);

    /**
     * Persists the referenced {@link AcctAccount account}
     *
     * @param account the referenced account
     * @return a reference to the persisted entity
     */
    AcctAccount saveAccount(AcctAccount account);

}
