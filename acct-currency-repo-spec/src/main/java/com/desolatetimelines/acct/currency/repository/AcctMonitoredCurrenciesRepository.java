package com.desolatetimelines.acct.currency.repository;

import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrency;

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

}
