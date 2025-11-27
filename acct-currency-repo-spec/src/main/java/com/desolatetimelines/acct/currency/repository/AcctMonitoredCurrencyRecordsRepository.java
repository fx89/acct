package com.desolatetimelines.acct.currency.repository;

import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrencyRecord;

import java.time.Instant;
import java.util.Collection;

/**
 * Repository for loading and persisting {@link AcctMonitoredCurrencyRecord monitored currency records}
 */
public interface AcctMonitoredCurrencyRecordsRepository {

    /**
     * Creates a new instance of {@link AcctMonitoredCurrencyRecord}
     *
     * @return a reference to the newly created instance
     */
    AcctMonitoredCurrencyRecord createNew();

    /**
     * Persists the referenced {@link AcctMonitoredCurrencyRecord monitored currency record}
     *
     * @param monitoredCurrencyRecord the referenced monitored currency record
     * @return a reference to the persisted entity
     */
    AcctMonitoredCurrencyRecord save(AcctMonitoredCurrencyRecord monitoredCurrencyRecord);

    /**
     * Returns a collection of all {@link AcctMonitoredCurrencyRecord monitored currency records}
     * belonging to the referenced {@link AcctMonitoredCurrency monitored currency} and that have
     * the {@link AcctMonitoredCurrencyRecord#getMonitoredCurrencyRecordDate() record date} in the
     * given collection of record dates
     *
     * @param monitoredCurrency            the referenced monitored currency
     * @param monitoredCurrencyRecordDates the given collection of record dates
     */
    Collection<AcctMonitoredCurrencyRecord> findAllByMonitoredCurrencyAndMonitoredCurrencyRecordDateIn(
        AcctMonitoredCurrency monitoredCurrency,
        Collection<Instant> monitoredCurrencyRecordDates
    );

    /**
     * Removes all of the {@link AcctMonitoredCurrencyRecord monitored currency records} in the
     * given collection of monitored currency records
     *
     * @param monitoredCurrencyRecords the given collection of monitored currency records
     */
    void deleteAll(Collection<AcctMonitoredCurrencyRecord> monitoredCurrencyRecords);

    /**
     * Returns a collection of all the {@link AcctMonitoredCurrencyRecord records}
     * of the referenced monitored currency
     *
     * @param monitoredCurrency the referenced monitored currency
     */
    Collection<AcctMonitoredCurrencyRecord> findAllByMonitoredCurrency(
        AcctMonitoredCurrency monitoredCurrency
    );

    /**
     * Returns a collection of all the {@link AcctMonitoredCurrencyRecord records}
     * of the referenced monitored currency that lie within the time interval defined
     * by the given start date and the given end date
     *
     * @param monitoredCurrency the referenced monitored currency
     * @param startDate         the given start date
     * @param endDate           the given end date
     */
    Collection<AcctMonitoredCurrencyRecord> findAllByMonitoredCurrencyAndMonitoredCurrencyRecordDateBetween(
        AcctMonitoredCurrency monitoredCurrency,
        Instant startDate,
        Instant endDate
    );

}
