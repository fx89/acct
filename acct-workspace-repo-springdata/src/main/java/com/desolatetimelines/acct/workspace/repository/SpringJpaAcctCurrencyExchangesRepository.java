package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.workspace.model.AcctAccountRecord;
import com.desolatetimelines.acct.workspace.model.AcctCurrencyExchange;
import com.desolatetimelines.acct.workspace.springrepository.JpaAcctCurrencyExchangesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.desolatetimelines.acct.workspace.util.AcctWorkspaceRepoSpringDataUtils.doWithJpaAcctAccountRecordReturning;
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
}
