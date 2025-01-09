package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.workspace.model.AcctDeposit;
import com.desolatetimelines.acct.workspace.model.JpaAcctDeposit;
import com.desolatetimelines.acct.workspace.springrepository.JpaAcctDepositsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.desolatetimelines.acct.workspace.util.AcctWorkspaceRepoSpringDataUtils.doWithJpaAcctDepositReturning;
import static java.util.function.Function.identity;

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

    @Override
    public Optional<AcctDeposit> findFirstByDepositUUID(String depositUUID) {
        return jpaAcctDepositsRepository.findFirstByDepositUUID(depositUUID).map(identity());
    }

}
