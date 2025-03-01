package com.desolatetimelines.acct.currency.repository;

import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.model.InMemoryAcctMonitoredCurrency;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of the {@link AcctMonitoredCurrenciesRepository} using in-memory storage
 */
public class InMemoryAcctMonitoredCurrenciesRepository implements AcctMonitoredCurrenciesRepository {

    private final Map<String, AcctMonitoredCurrency> monitoredCurrencyRecordsByUUID = new ConcurrentHashMap<>();

    @Override
    public AcctMonitoredCurrency createNew() {
        return new InMemoryAcctMonitoredCurrency();
    }

    @Override
    public Optional<AcctMonitoredCurrency> findFirstByMonitoredCurrencyUUID(String monitoredCurrencyUUID) {
        return Optional.ofNullable(monitoredCurrencyRecordsByUUID.get(monitoredCurrencyUUID));
    }

    @Override
    public AcctMonitoredCurrency save(AcctMonitoredCurrency monitoredCurrency) {
        monitoredCurrencyRecordsByUUID.put(
            monitoredCurrency.getMonitoredCurrencyUUID(),
            monitoredCurrency
        );

        return monitoredCurrency;
    }

    @Override
    public Collection<AcctMonitoredCurrency> findAll() {
        return monitoredCurrencyRecordsByUUID.values();
    }
}
