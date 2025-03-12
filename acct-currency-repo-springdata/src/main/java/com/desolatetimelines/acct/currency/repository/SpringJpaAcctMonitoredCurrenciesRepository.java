package com.desolatetimelines.acct.currency.repository;

import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.model.JpaAcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.springrepository.JpaAcctMonitoredCurrenciesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.desolatetimelines.acct.currency.util.AcctCurrencyRepoSpringDataUtils.doWithJpaAcctMonitoredCurrencyReturning;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctMonitoredCurrenciesRepository} using Spring Data JPA
 */
@Service
public class SpringJpaAcctMonitoredCurrenciesRepository implements AcctMonitoredCurrenciesRepository {

    private final JpaAcctMonitoredCurrenciesRepository jpaAcctMonitoredCurrenciesRepository;

    public SpringJpaAcctMonitoredCurrenciesRepository(
        JpaAcctMonitoredCurrenciesRepository jpaAcctMonitoredCurrenciesRepository
    ) {
        this.jpaAcctMonitoredCurrenciesRepository = jpaAcctMonitoredCurrenciesRepository;
    }

    @Override
    public AcctMonitoredCurrency createNew() {
        return new JpaAcctMonitoredCurrency();
    }

    @Override
    public Optional<AcctMonitoredCurrency> findFirstByMonitoredCurrencyUUID(String monitoredCurrencyUUID) {
        return
            jpaAcctMonitoredCurrenciesRepository
                .findFirstByMonitoredCurrencyUUID(monitoredCurrencyUUID)
                .map(identity());
    }

    @Override
    public AcctMonitoredCurrency save(AcctMonitoredCurrency monitoredCurrency) {
        return doWithJpaAcctMonitoredCurrencyReturning(monitoredCurrency, jpaAcctMonitoredCurrenciesRepository::save);
    }

    @Override
    public Collection<AcctMonitoredCurrency> findAll() {
        return
            StreamSupport.stream(jpaAcctMonitoredCurrenciesRepository.findAll().spliterator(), false)
                .map(jpaAcctMonitoredCurrency -> (AcctMonitoredCurrency) jpaAcctMonitoredCurrency)
                .toList();
    }

    @Override
    public void delete(AcctMonitoredCurrency monitoredCurrency) {
        jpaAcctMonitoredCurrenciesRepository.delete(
            doWithJpaAcctMonitoredCurrencyReturning(monitoredCurrency, identity())
        );
    }

    @Override
    public Collection<AcctMonitoredCurrency> findAllByBankUUIDIn(Collection<String> bankUUIDs) {
        return
            mapJpaEntitiesToSpecEntities(
                jpaAcctMonitoredCurrenciesRepository.findAllByBankUUIDIn(bankUUIDs)
            );
    }

    @Override
    public Collection<AcctMonitoredCurrency> findAllByCurrencyUUIDIn(Collection<String> currencyUUIDs) {
        return
            mapJpaEntitiesToSpecEntities(
                jpaAcctMonitoredCurrenciesRepository.findAllByCurrencyUUIDIn(currencyUUIDs)
            );
    }

    @Override
    public Collection<AcctMonitoredCurrency> findAllByQuotedCurrencyUUIDIn(
        Collection<String> quotedCurrencyUUIDs
    ) {
        return
            mapJpaEntitiesToSpecEntities(
                jpaAcctMonitoredCurrenciesRepository.findAllByQuotedCurrencyUUIDIn(quotedCurrencyUUIDs)
            );
    }

    private static Collection<AcctMonitoredCurrency> mapJpaEntitiesToSpecEntities(
        Collection<JpaAcctMonitoredCurrency> jpaEntities
    ) {
        return
            jpaEntities.stream()
                .map(jpaAcctMonitoredCurrency -> (AcctMonitoredCurrency) jpaAcctMonitoredCurrency)
                .toList();
    }

}
