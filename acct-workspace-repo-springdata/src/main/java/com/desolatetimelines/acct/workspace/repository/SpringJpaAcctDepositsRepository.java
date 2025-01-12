package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.workspace.model.AcctDeposit;
import com.desolatetimelines.acct.workspace.model.JpaAcctDeposit;
import com.desolatetimelines.acct.workspace.springrepository.JpaAcctDepositsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

    @Override
    public Page<AcctDeposit> findDepositsByWorkspaceUUID(String workspaceUUID, int pageNumber, int pageSize) {
        // Get the page
        final org.springframework.data.domain.Page<JpaAcctDeposit> page =
            jpaAcctDepositsRepository
                .findByDepositCreationAccountRecordAccountWorkspaceWorkspaceUUID(
                    workspaceUUID,
                    PageRequest.of(
                        pageNumber,
                        pageSize,
                        Sort.by("depositProjectedEndDate")
                    )
                );

        // Map the page
        return
            new Page<>(
                page.get().map(rec -> (AcctDeposit) rec).toList(),
                page.getNumberOfElements(),
                page.getTotalElements()
            );
    }

    @Override
    public Page<AcctDeposit> findDepositsByWorkspaceUUIDAndBankUUID(
        String workspaceUUID, String bankUUID, int pageNumber, int pageSize
    ) {
        // Get the page
        final org.springframework.data.domain.Page<JpaAcctDeposit> page =
            jpaAcctDepositsRepository
                .findByDepositCreationAccountRecordAccountWorkspaceWorkspaceUUIDAndBankUUID(
                    workspaceUUID,
                    bankUUID,
                    PageRequest.of(
                        pageNumber,
                        pageSize,
                        Sort.by("depositProjectedEndDate")
                    )
                );

        // Map the page
        return
            new Page<>(
                page.get().map(rec -> (AcctDeposit) rec).toList(),
                page.getNumberOfElements(),
                page.getTotalElements()
            );
    }

    @Override
    public Page<AcctDeposit> findDepositsByWorkspaceUUIDAndDepositInterestAccountRecordNullAndDepositProjectedEndDateLessThan(
        String workspaceUUID,
        Instant projectedEndDate,
        int pageNumber,
        int pageSize
    ) {
        // Get the page
        final org.springframework.data.domain.Page<JpaAcctDeposit> page =
            jpaAcctDepositsRepository
                .findByDepositCreationAccountRecordAccountWorkspaceWorkspaceUUIDAndDepositInterestAccountRecordNullAndDepositProjectedEndDateBefore(
                    workspaceUUID,
                    projectedEndDate,
                    PageRequest.of(
                        pageNumber,
                        pageSize,
                        Sort.by("depositProjectedEndDate")
                    )
                );

        // Map the page
        return
            new Page<>(
                page.get().map(rec -> (AcctDeposit) rec).toList(),
                page.getNumberOfElements(),
                page.getTotalElements()
            );
    }

}
