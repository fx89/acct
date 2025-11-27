package com.desolatetimelines.acct.currency.repository;

import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrencyRecord;
import com.desolatetimelines.acct.currency.model.JpaAcctMonitoredCurrencyRecord;
import com.desolatetimelines.acct.currency.springrepository.JpaAcctMonitoredCurrencyRecordsRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;

import static com.desolatetimelines.acct.currency.util.AcctCurrencyRepoSpringDataUtils.doWithJpaAcctMonitoredCurrencyRecordReturning;
import static com.desolatetimelines.acct.currency.util.AcctCurrencyRepoSpringDataUtils.doWithJpaAcctMonitoredCurrencyReturning;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctMonitoredCurrencyRecordsRepository} using Spring Data JPA
 */
@Service
public class SpringJpaAcctMonitoredCurrencyRecordsRepository implements AcctMonitoredCurrencyRecordsRepository {

    final JpaAcctMonitoredCurrencyRecordsRepository jpaAcctMonitoredCurrencyRecordsRepository;

    public SpringJpaAcctMonitoredCurrencyRecordsRepository(
        JpaAcctMonitoredCurrencyRecordsRepository jpaAcctMonitoredCurrencyRecordsRepository
    ) {
        this.jpaAcctMonitoredCurrencyRecordsRepository = jpaAcctMonitoredCurrencyRecordsRepository;
    }

    @Override
    public AcctMonitoredCurrencyRecord createNew() {
        return new JpaAcctMonitoredCurrencyRecord();
    }

    @Override
    public AcctMonitoredCurrencyRecord save(AcctMonitoredCurrencyRecord monitoredCurrencyRecord) {
        return
            doWithJpaAcctMonitoredCurrencyRecordReturning(
                monitoredCurrencyRecord,
                jpaAcctMonitoredCurrencyRecordsRepository::save
            );
    }

    @Override
    public Collection<AcctMonitoredCurrencyRecord> findAllByMonitoredCurrencyAndMonitoredCurrencyRecordDateIn(
        AcctMonitoredCurrency monitoredCurrency, Collection<Instant> monitoredCurrencyRecordDates
    ) {
        return
            jpaAcctMonitoredCurrencyRecordsRepository.findAllByMonitoredCurrencyAndMonitoredCurrencyRecordDateIn(
                    doWithJpaAcctMonitoredCurrencyReturning(monitoredCurrency, identity()),
                    monitoredCurrencyRecordDates
                )
                .stream()
                .map(jpaAcctMonitoredCurrencyRecord -> (AcctMonitoredCurrencyRecord) jpaAcctMonitoredCurrencyRecord)
                .toList();
    }

    @Override
    public void deleteAll(Collection<AcctMonitoredCurrencyRecord> monitoredCurrencyRecords) {
        jpaAcctMonitoredCurrencyRecordsRepository.deleteAll(
            monitoredCurrencyRecords.stream()
                .map(monitoredCurrencyRecord -> (JpaAcctMonitoredCurrencyRecord) monitoredCurrencyRecord)
                .toList()
        );
    }

    @Override
    public Collection<AcctMonitoredCurrencyRecord> findAllByMonitoredCurrency(
        AcctMonitoredCurrency monitoredCurrency
    ) {
        return
            jpaAcctMonitoredCurrencyRecordsRepository.findAllByMonitoredCurrency(
                    doWithJpaAcctMonitoredCurrencyReturning(monitoredCurrency, identity())
                )
                .stream()
                .map(jpaAcctMonitoredCurrencyRecord -> (AcctMonitoredCurrencyRecord) jpaAcctMonitoredCurrencyRecord)
                .toList();

    }

    @Override
    public Collection<AcctMonitoredCurrencyRecord> findAllByMonitoredCurrencyAndMonitoredCurrencyRecordDateBetween(
        AcctMonitoredCurrency monitoredCurrency,
        Instant startDate,
        Instant endDate
    ) {
        return
            jpaAcctMonitoredCurrencyRecordsRepository.findAllByMonitoredCurrencyAndMonitoredCurrencyRecordDateBetween(
                    doWithJpaAcctMonitoredCurrencyReturning(monitoredCurrency, identity()),
                    startDate,
                    endDate
                )
                .stream()
                .map(jpaAcctMonitoredCurrencyRecord -> (AcctMonitoredCurrencyRecord) jpaAcctMonitoredCurrencyRecord)
                .toList();
    }
}
