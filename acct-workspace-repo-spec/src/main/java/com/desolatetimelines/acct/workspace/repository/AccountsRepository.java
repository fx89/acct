package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.workspace.model.AcctAccount;
import com.desolatetimelines.acct.workspace.model.AcctWorkspace;

import java.util.Collection;
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
     * Retrieves a collection of {@link AcctAccount accounts} for which the
     * {@link AcctAccount#getAccountIconUUID() account icon UUID} is one of
     * the UUIDs in the given collection. If there is no match, an empty
     * collection is returned.
     *
     * @param accountIconUUIDs the given collection
     */
    Collection<AcctAccount> findAllByAccountIconUUIDIn(Collection<String> accountIconUUIDs);

    /**
     * Retrieves a collection of {@link AcctAccount accounts} for which the
     * {@link AcctAccount#getBankUUID() bank UUID} is one of the UUIDs in
     * the given collection. If there is no match, an empty collection is
     * returned.
     *
     * @param bankUUIDs the given collection
     */
    Collection<AcctAccount> findAllByBankUUIDIn(Collection<String> bankUUIDs);

    /**
     * Retrieves a collection of {@link AcctAccount accounts} for which the
     * {@link AcctAccount#getCurrencyUUID() currencyUUID} is one of the UUIDs
     * in the given collection. If there is no match, an empty collection is
     * returned.
     *
     * @param currencyUUIDs the given collection
     */
    Collection<AcctAccount> findAllByCurrencyUUIDIn(Collection<String> currencyUUIDs);

    /**
     * Persists the referenced {@link AcctAccount account}
     *
     * @param account the referenced account
     * @return a reference to the persisted entity
     */
    AcctAccount saveAccount(AcctAccount account);

    /**
     * Retrieves a collection of {@link AcctAccount accounts} that are
     * contained by the referenced {@link AcctWorkspace workspace}
     *
     * @param workspace the referenced workspace
     */
    Collection<AcctAccount> findAllByWorkspace(AcctWorkspace workspace);

    /**
     * Deletes the referenced {@link AcctAccount account}
     */
    void deleteAccount(AcctAccount account);

}
