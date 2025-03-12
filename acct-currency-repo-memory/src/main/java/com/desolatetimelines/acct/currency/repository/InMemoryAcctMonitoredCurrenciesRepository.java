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

    @Override
    public void delete(AcctMonitoredCurrency monitoredCurrency) {
        monitoredCurrencyRecordsByUUID.remove(monitoredCurrency.getMonitoredCurrencyUUID());
    }

    @Override
    public Collection<AcctMonitoredCurrency> findAllByBankUUIDIn(Collection<String> bankUUIDs) {
        return
            monitoredCurrencyRecordsByUUID.values().stream()
                .filter(mc -> bankUUIDs.contains(mc.getBankUUID()))
                .toList();
    }

    @Override
    public Collection<AcctMonitoredCurrency> findAllByCurrencyUUIDIn(Collection<String> currencyUUIDs) {
        return
            monitoredCurrencyRecordsByUUID.values().stream()
                .filter(mc -> currencyUUIDs.contains(mc.getCurrencyUUID()))
                .toList();
    }

    @Override
    public Collection<AcctMonitoredCurrency> findAllByQuotedCurrencyUUIDIn(
        Collection<String> quotedCurrencyUUIDs
    ) {
        return
            monitoredCurrencyRecordsByUUID.values().stream()
                .filter(mc -> quotedCurrencyUUIDs.contains(mc.getQuotedCurrencyUUID()))
                .toList();
    }
}
