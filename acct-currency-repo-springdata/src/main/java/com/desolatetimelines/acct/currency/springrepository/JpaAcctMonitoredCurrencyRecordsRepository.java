package com.desolatetimelines.acct.currency.springrepository;

import com.desolatetimelines.acct.currency.model.JpaAcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.model.JpaAcctMonitoredCurrencyRecord;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.Collection;

public interface JpaAcctMonitoredCurrencyRecordsRepository extends CrudRepository<JpaAcctMonitoredCurrencyRecord, Long> {

    Collection<JpaAcctMonitoredCurrencyRecord> findAllByMonitoredCurrencyAndMonitoredCurrencyRecordDateIn(
        JpaAcctMonitoredCurrency monitoredCurrency, Collection<Instant> monitoredCurrencyRecordDates
    );

    Collection<JpaAcctMonitoredCurrencyRecord> findAllByMonitoredCurrency(
        JpaAcctMonitoredCurrency monitoredCurrency
    );

    Collection<JpaAcctMonitoredCurrencyRecord> findAllByMonitoredCurrencyAndMonitoredCurrencyRecordDateBetween(
        JpaAcctMonitoredCurrency monitoredCurrency,
        Instant startDate,
        Instant endDate
    );

}
