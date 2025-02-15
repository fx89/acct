package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctCurrency;
import com.desolatetimelines.acct.catalog.model.AcctIcon;

import java.util.Collection;
import java.util.Optional;

/**
 * Repository for loading and persisting {@link AcctCurrency currencies}
 */
public interface AcctCurrenciesRepository {

    /**
     * Creates a new instance of {@link AcctCurrency}
     *
     * @return a reference to the newly created entity
     */
    AcctCurrency createNew();

    /**
     * Persists the referenced {@link AcctCurrency currency}
     *
     * @param currency the referenced currency
     * @return a reference to the persisted entity
     */
    AcctCurrency save(AcctCurrency currency);

    /**
     * Returns the {@link AcctCurrency currency} identified by the given currency UUID
     * or an empty optional if such a currency does not exist
     *
     * @param currencyUUID the given currency UUID
     */
    Optional<AcctCurrency> findByCurrencyUUID(String currencyUUID);

    /**
     * Returns the {@link AcctCurrency currencies} referenced by the UUIDs in the given
     * collection of currency UUIDs.
     *
     * @param currencyUUIDs the given collection of currency UUIDs
     */
    Collection<AcctCurrency> findAllByCurrencyUUIDIn(Collection<String> currencyUUIDs);

    /**
     * Returns a collection of all {@link AcctCurrency currencies} in the catalog
     */
    Collection<AcctCurrency> findAll();

    /**
     * Returns the {@link AcctCurrency currencies} linked to the {@link AcctIcon icons} referenced by
     * the UUIDs in the given collection of icon UUIDs.
     *
     * @param iconUUIDs the given collection of icon UUIDs
     */
    Collection<AcctCurrency> findAllByCurrencyIconUUIDIn(Collection<String> iconUUIDs);

    /**
     * Deletes the {@link AcctCurrency currencies} in the given collection of currencies
     *
     * @param currencies the given collection of currencies
     */
    void deleteAll(Collection<AcctCurrency> currencies);

}
