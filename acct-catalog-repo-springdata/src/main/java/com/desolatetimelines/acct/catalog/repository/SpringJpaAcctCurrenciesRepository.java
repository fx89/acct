package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctCurrency;
import com.desolatetimelines.acct.catalog.model.JpaAcctCurrency;
import com.desolatetimelines.acct.catalog.springrepository.JpaAcctCurrenciesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.desolatetimelines.acct.catalog.util.AcctCatalogRepoSpringDataUtils.doWithJpaAcctCurrencyReturning;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctCurrenciesRepository} that uses Spring Data
 */
@Service
public class SpringJpaAcctCurrenciesRepository implements AcctCurrenciesRepository {

    private final JpaAcctCurrenciesRepository jpaAcctCurrenciesRepository;

    public SpringJpaAcctCurrenciesRepository(JpaAcctCurrenciesRepository jpaAcctCurrenciesRepository) {
        this.jpaAcctCurrenciesRepository = jpaAcctCurrenciesRepository;
    }

    @Override
    public AcctCurrency createNew() {
        return new JpaAcctCurrency();
    }

    @Override
    public AcctCurrency save(AcctCurrency currency) {
        return doWithJpaAcctCurrencyReturning(currency, jpaAcctCurrenciesRepository::save);
    }

    @Override
    public Optional<AcctCurrency> findByCurrencyUUID(String currencyUUID) {
        return jpaAcctCurrenciesRepository.findFirstByCurrencyUUID(currencyUUID).map(identity());
    }

    @Override
    public Collection<AcctCurrency> findAllByCurrencyUUIDIn(Collection<String> currencyUUIDs) {
        return
            jpaAcctCurrenciesRepository.findAllByCurrencyUUIDIn(currencyUUIDs)
                .stream()
                .map(jpaAcctCurrency -> (AcctCurrency) jpaAcctCurrency)
                .toList();
    }

    @Override
    public Collection<AcctCurrency> findAll() {
        return
            StreamSupport.stream(jpaAcctCurrenciesRepository.findAll().spliterator(), false)
                .map(jpaAcctCurrency -> (AcctCurrency) jpaAcctCurrency)
                .toList();
    }

    @Override
    public void deleteAll(Collection<AcctCurrency> currencies) {
        jpaAcctCurrenciesRepository.deleteAll(
            currencies.stream()
                .map(currency -> doWithJpaAcctCurrencyReturning(currency, identity()))
                .toList()
        );
    }

}
