package com.desolatetimelines.acct.currency.repository;

import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrency;

import java.util.Collection;
import java.util.Optional;

/**
 * Repository for loading and persisting {@link AcctMonitoredCurrency monitored currencies}
 */
public interface AcctMonitoredCurrenciesRepository {

    /**
     * Creates a new instance of {@link AcctMonitoredCurrency}
     *
     * @return a reference to the newly created instance
     */
    AcctMonitoredCurrency createNew();

    /**
     * Returns the {@link AcctMonitoredCurrency monitored currency} with the given monitored
     * currency UUID or an empty optional if such an entity does not exist
     *
     * @param monitoredCurrencyUUID the given monitored currency UUID
     */
    Optional<AcctMonitoredCurrency> findFirstByMonitoredCurrencyUUID(String monitoredCurrencyUUID);

    /**
     * Persists the referenced {@link AcctMonitoredCurrency monitored currency}
     *
     * @param monitoredCurrency the referenced monitored currency
     * @return a reference to the persisted entity
     */
    AcctMonitoredCurrency save(AcctMonitoredCurrency monitoredCurrency);

    /**
     * Returns a collection of all the {@link AcctMonitoredCurrency monitored currencies}
     */
    Collection<AcctMonitoredCurrency> findAll();

    /**
     * Deletes the referenced monitored currency
     *
     * @param monitoredCurrency the referenced monitored currency
     */
    void delete(AcctMonitoredCurrency monitoredCurrency);

    /**
     * Returns a collection of {@link AcctMonitoredCurrency monitored currencies} for which the
     * {@link AcctMonitoredCurrency#getBankUUID() bank UUID} can be found in the given collection
     * of bank UUIDs
     *
     * @param bankUUIDs the given collection of bank UUIDs
     */
    Collection<AcctMonitoredCurrency> findAllByBankUUIDIn(Collection<String> bankUUIDs);

    /**
     * Returns a collection of {@link AcctMonitoredCurrency monitored currencies} for which the
     * {@link AcctMonitoredCurrency#getCurrencyUUID() currenncy UUID} can be found in the given
     * collection of currency UUIDs
     *
     * @param currencyUUIDs the given collection of currency UUIDs
     */
    Collection<AcctMonitoredCurrency> findAllByCurrencyUUIDIn(
        Collection<String> currencyUUIDs
    );

    /**
     * Returns a collection of {@link AcctMonitoredCurrency monitored currencies} for which the
     * {@link AcctMonitoredCurrency#getQuotedCurrencyUUID() quoted currenncy UUID} can be found
     * in the given collection of quoted currency UUIDs
     *
     * @param quotedCurrencyUUIDs the given collection of quoted currency UUIDs
     */
    Collection<AcctMonitoredCurrency> findAllByQuotedCurrencyUUIDIn(
        Collection<String> quotedCurrencyUUIDs
    );

}
