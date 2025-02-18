package com.desolatetimelines.acct.currency.service;

import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.repository.AcctMonitoredCurrenciesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Facade for the data layer of the currency service, allowing for the
 * loading and persisting of currency data objects
 */
@Service
public class AcctCurrencyDataService {

    private final AcctMonitoredCurrenciesRepository monitoredCurrenciesRepository;

    public AcctCurrencyDataService(AcctMonitoredCurrenciesRepository monitoredCurrenciesRepository) {
        this.monitoredCurrenciesRepository = monitoredCurrenciesRepository;
    }

    /**
     * Creates a new instance of {@link AcctMonitoredCurrency}
     *
     * @return a reference to the newly created instance
     */
    public AcctMonitoredCurrency createNewAcctMonitoredCurrency() {
        return monitoredCurrenciesRepository.createNew();
    }

    /**
     * Returns the {@link AcctMonitoredCurrency monitored currency} with the given monitored
     * currency UUID or an empty optional if such an entity does not exist
     *
     * @param monitoredCurrencyUUID the given monitored currency UUID
     */
    public Optional<AcctMonitoredCurrency> findMonitoredCurrencyByMonitoredCurrencyUUID(String monitoredCurrencyUUID) {
        return monitoredCurrenciesRepository.findFirstByMonitoredCurrencyUUID(monitoredCurrencyUUID);
    }

    /**
     * Persists the referenced {@link AcctMonitoredCurrency monitored currency}
     *
     * @param monitoredCurrency the referenced monitored currency
     * @return a reference to the persisted entity
     */
    public AcctMonitoredCurrency saveMonitoredCurrency(AcctMonitoredCurrency monitoredCurrency) {
        return monitoredCurrenciesRepository.save(monitoredCurrency);
    }

}
