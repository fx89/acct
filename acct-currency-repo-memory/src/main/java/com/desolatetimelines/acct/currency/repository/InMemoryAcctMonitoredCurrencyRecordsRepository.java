package com.desolatetimelines.acct.currency.repository;

import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrencyRecord;
import com.desolatetimelines.acct.currency.model.InMemoryAcctMonitoredCurrencyRecord;

import java.time.Instant;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Implementation of the {@link AcctMonitoredCurrencyRecordsRepository} using in-memory storage
 */
public class InMemoryAcctMonitoredCurrencyRecordsRepository implements AcctMonitoredCurrencyRecordsRepository {

    private final Collection<AcctMonitoredCurrencyRecord> records = new CopyOnWriteArrayList<>();

    @Override
    public AcctMonitoredCurrencyRecord createNew() {
        return new InMemoryAcctMonitoredCurrencyRecord();
    }

    @Override
    public AcctMonitoredCurrencyRecord save(AcctMonitoredCurrencyRecord monitoredCurrencyRecord) {
        // Find any already existing record
        final Optional<AcctMonitoredCurrencyRecord> optionalExistingRecord =
            records.stream()
                .filter(record -> Objects.equals(record, monitoredCurrencyRecord))
                .findFirst();

        // If found, delete the already existing record
        optionalExistingRecord.ifPresent(records::remove);

        // Add the new record
        records.add(monitoredCurrencyRecord);

        // Return a reference
        return monitoredCurrencyRecord;
    }

    @Override
    public Collection<AcctMonitoredCurrencyRecord> findAllByMonitoredCurrencyAndMonitoredCurrencyRecordDateIn(
        AcctMonitoredCurrency monitoredCurrency,
        Collection<Instant> monitoredCurrencyRecordDates
    ) {
        return
            records.stream()
                .filter(record -> Objects.equals(monitoredCurrency, record.getMonitoredCurrency()))
                .filter(record -> monitoredCurrencyRecordDates.contains(record.getMonitoredCurrencyRecordDate()))
                .toList();
    }

    @Override
    public void deleteAll(Collection<AcctMonitoredCurrencyRecord> monitoredCurrencyRecords) {
        records.removeAll(monitoredCurrencyRecords);
    }

    public Collection<AcctMonitoredCurrencyRecord> findAllByMonitoredCurrency(AcctMonitoredCurrency monitoredCurrency) {
        return
            records.stream()
                .filter(r -> Objects.equals(monitoredCurrency, r.getMonitoredCurrency()))
                .toList();
    }

}
