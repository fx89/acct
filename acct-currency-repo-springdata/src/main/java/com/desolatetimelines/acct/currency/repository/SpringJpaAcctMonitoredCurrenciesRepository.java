package com.desolatetimelines.acct.currency.repository;

import com.desolatetimelines.acct.currency.model.AcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.model.JpaAcctMonitoredCurrency;
import com.desolatetimelines.acct.currency.springrepository.JpaAcctMonitoredCurrenciesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

}
