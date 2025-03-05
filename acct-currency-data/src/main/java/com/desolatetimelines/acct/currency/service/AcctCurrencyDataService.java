package com.desolatetimelines.acct.currency.service;

import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrencyRecord;
import com.desolatetimelines.acct.currency.repository.AcctMonitoredCurrenciesRepository;
import com.desolatetimelines.acct.currency.repository.AcctMonitoredCurrencyRecordsRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;

/**
 * Facade for the data layer of the currency service, allowing for the
 * loading and persisting of currency data objects
 */
@Service
public class AcctCurrencyDataService {

    private final AcctMonitoredCurrenciesRepository monitoredCurrenciesRepository;

    private final AcctMonitoredCurrencyRecordsRepository monitoredCurrencyRecordsRepository;

    public AcctCurrencyDataService(
        AcctMonitoredCurrenciesRepository monitoredCurrenciesRepository,
        AcctMonitoredCurrencyRecordsRepository monitoredCurrencyRecordsRepository
    ) {
        this.monitoredCurrenciesRepository = monitoredCurrenciesRepository;
        this.monitoredCurrencyRecordsRepository = monitoredCurrencyRecordsRepository;
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
     * Returns a collection of all the {@link AcctMonitoredCurrency monitored currencies}
     */
    public Collection<AcctMonitoredCurrency> findAllMonitoredCurrencies() {
        return monitoredCurrenciesRepository.findAll();
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

    /**
     * Creates a new instance of {@link AcctMonitoredCurrencyRecord}
     *
     * @return a reference to the newly created instance
     */
    public AcctMonitoredCurrencyRecord createNewAcctMonitoredCurrencyRecord() {
        return monitoredCurrencyRecordsRepository.createNew();
    }

    /**
     * Persists the referenced {@link AcctMonitoredCurrencyRecord monitored currency record}
     *
     * @param monitoredCurrencyRecord the referenced monitored currency record
     * @return a reference to the persisted entity
     */
    public AcctMonitoredCurrencyRecord saveMonitoredCurrencyRecord(AcctMonitoredCurrencyRecord monitoredCurrencyRecord) {
        return monitoredCurrencyRecordsRepository.save(monitoredCurrencyRecord);
    }

    /**
     * Returns a collection of all {@link AcctMonitoredCurrencyRecord monitored currency records}
     * belonging to the referenced {@link AcctMonitoredCurrency monitored currency} and that have
     * the {@link AcctMonitoredCurrencyRecord#getMonitoredCurrencyRecordDate() record date} in the
     * given collection of record dates
     *
     * @param monitoredCurrency            the referenced monitored currency
     * @param monitoredCurrencyRecordDates the given collection of record dates
     */
    public Collection<AcctMonitoredCurrencyRecord>
    findAllMonitoredCurrencyRecordsByMonitoredCurrencyAndMonitoredCurrencyRecordDateIn(
        AcctMonitoredCurrency monitoredCurrency,
        Collection<Instant> monitoredCurrencyRecordDates
    ) {
        return
            monitoredCurrencyRecordsRepository.findAllByMonitoredCurrencyAndMonitoredCurrencyRecordDateIn(
                monitoredCurrency,
                monitoredCurrencyRecordDates
            );
    }

    /**
     * Removes all of the {@link AcctMonitoredCurrencyRecord monitored currency records} in the
     * given collection of monitored currency records
     *
     * @param monitoredCurrencyRecords the given collection of monitored currency records
     */
    public void deleteAllMonitoredCurrencyRecords(Collection<AcctMonitoredCurrencyRecord> monitoredCurrencyRecords) {
        monitoredCurrencyRecordsRepository.deleteAll(monitoredCurrencyRecords);
    }

    /**
     * Returns a collection of all the {@link AcctMonitoredCurrencyRecord records}
     * of the referenced monitored currency
     *
     * @param monitoredCurrency the referenced monitored currency
     */
    public Collection<AcctMonitoredCurrencyRecord> findMonitoredCurrencyRecordsByMonitoredCurrency(
        AcctMonitoredCurrency monitoredCurrency
    ) {
        return monitoredCurrencyRecordsRepository.findAllByMonitoredCurrency(monitoredCurrency);
    }

    /**
     * Deletes the referenced monitored currency
     *
     * @param monitoredCurrency the referenced monitored currency
     */
    void deleteMonitoredCurrency(AcctMonitoredCurrency monitoredCurrency) {
        monitoredCurrenciesRepository.delete(monitoredCurrency);
    }

}
