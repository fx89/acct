package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctBank;

import java.util.Collection;
import java.util.Optional;

/**
 * Repository for loading and persisting {@link AcctBank banks}
 */
public interface AcctBanksRepository {

    /**
     * Creates a new instance of {@link AcctBank}
     *
     * @return a reference to the newly created instance
     */
    AcctBank createNew();

    /**
     * Persists the referenced bank
     *
     * @param bank the referenced bank
     * @return a reference to the persisted entity
     */
    AcctBank save(AcctBank bank);

    /**
     * Returns a collection of all the {@link AcctBank banks} registered in the catalog
     */
    Collection<AcctBank> findAll();

    /**
     * Retrieves the {@link AcctBank bank} with the given bank UUID or an empty optional
     * if such an entity does not exist.
     *
     * @param bankUUID the given bank UUID
     */
    Optional<AcctBank> findFirstByBankUUID(String bankUUID);

    /**
     * Returns a collection of {@link AcctBank banks} identified by the UUIDs in the
     * given collection of bank UUIDs
     *
     * @param bankUUIDs the given collection of bank UUIDs
     */
    Collection<AcctBank> findAllByBankUUIDIn(Collection<String> bankUUIDs);

    /**
     * Deletes the {@link AcctBank banks} in the referenced collection of banks
     *
     * @param banks the referenced collection of banks
     */
    void deleteAll(Collection<AcctBank> banks);

}
