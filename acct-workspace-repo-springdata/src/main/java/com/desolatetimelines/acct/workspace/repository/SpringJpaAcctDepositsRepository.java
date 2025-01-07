package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.workspace.model.AcctDeposit;
import com.desolatetimelines.acct.workspace.model.JpaAcctDeposit;
import com.desolatetimelines.acct.workspace.springrepository.JpaAcctDepositsRepository;
import org.springframework.stereotype.Service;

import static com.desolatetimelines.acct.workspace.util.AcctWorkspaceRepoSpringDataUtils.doWithJpaAcctDepositReturning;

/**
 * Implementation of the {@link AcctDepositsRepository} using Spring Data JPA
 */
@Service
public class SpringJpaAcctDepositsRepository implements AcctDepositsRepository {

    private final JpaAcctDepositsRepository jpaAcctDepositsRepository;

    public SpringJpaAcctDepositsRepository(JpaAcctDepositsRepository jpaAcctDepositsRepository) {
        this.jpaAcctDepositsRepository = jpaAcctDepositsRepository;
    }

    @Override
    public AcctDeposit createNew() {
        return new JpaAcctDeposit();
    }

    @Override
    public AcctDeposit save(AcctDeposit deposit) {
        return doWithJpaAcctDepositReturning(deposit, jpaAcctDepositsRepository::save);
    }

}
