package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.workspace.model.AcctAccountRecord;
import com.desolatetimelines.acct.workspace.model.AcctCurrencyExchange;
import com.desolatetimelines.acct.workspace.model.JpaAcctCurrencyExchange;
import com.desolatetimelines.acct.workspace.springrepository.JpaAcctCurrencyExchangesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static com.desolatetimelines.acct.workspace.util.AcctWorkspaceRepoSpringDataUtils.doWithJpaAcctAccountRecordReturning;
import static com.desolatetimelines.acct.workspace.util.AcctWorkspaceRepoSpringDataUtils.doWithJpaAcctCurrencyExchangeReturning;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctCurrencyExchangesRepository} using Spring Data JPA
 */
@Service
public class SpringJpaAcctCurrencyExchangesRepository implements AcctCurrencyExchangesRepository {

    private final JpaAcctCurrencyExchangesRepository jpaAcctCurrencyExchangesRepository;

    public SpringJpaAcctCurrencyExchangesRepository(JpaAcctCurrencyExchangesRepository jpaAcctCurrencyExchangesRepository) {
        this.jpaAcctCurrencyExchangesRepository = jpaAcctCurrencyExchangesRepository;
    }

    @Override
    public AcctCurrencyExchange createNew() {
        return new JpaAcctCurrencyExchange();
    }

    @Override
    public AcctCurrencyExchange save(AcctCurrencyExchange currencyExchange) {
        return doWithJpaAcctCurrencyExchangeReturning(currencyExchange, jpaAcctCurrencyExchangesRepository::save);
    }

    @Override
    public Collection<AcctCurrencyExchange> findAllByTargetAccountRecordIn(
        Collection<AcctAccountRecord> accountRecords
    ) {
        return
            jpaAcctCurrencyExchangesRepository.findAllByCurrencyExchangeTargetAccountRecordIn(
                    accountRecords
                        .stream()
                        .map(acctAccountRecord -> doWithJpaAcctAccountRecordReturning(acctAccountRecord, identity()))
                        .toList()
                )
                .stream()
                .map(jpaAcctCurrencyExchange -> (AcctCurrencyExchange) jpaAcctCurrencyExchange)
                .toList();
    }

    @Override
    public Optional<AcctCurrencyExchange> findFirstByTargetAccountRecordId(Long accountRecordId) {
        return
            jpaAcctCurrencyExchangesRepository
                .findFirstByCurrencyExchangeTargetAccountRecordAccountRecordId(accountRecordId)
                .map(identity());

    }
}
