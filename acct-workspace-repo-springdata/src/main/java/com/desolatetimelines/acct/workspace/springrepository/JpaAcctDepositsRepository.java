package com.desolatetimelines.acct.workspace.springrepository;

import com.desolatetimelines.acct.workspace.model.JpaAcctDeposit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;

public interface JpaAcctDepositsRepository extends CrudRepository<JpaAcctDeposit, Long> {

    Optional<JpaAcctDeposit> findFirstByDepositUUID(String depositUUID);

    Page<JpaAcctDeposit> findByDepositCreationAccountRecordAccountWorkspaceWorkspaceUUIDAndDepositProjectedEndDateGreaterThanEqual(
        String workspaceUUID, Instant projectedEndDate, Pageable pageable
    );

    Page<JpaAcctDeposit> findByDepositCreationAccountRecordAccountWorkspaceWorkspaceUUIDAndBankUUIDAndDepositProjectedEndDateGreaterThanEqual(
        String workspaceUUID, String bankUUID, Instant projectedEndDate, Pageable pageable
    );

    Page<JpaAcctDeposit> findByDepositCreationAccountRecordAccountWorkspaceWorkspaceUUIDAndBankUUIDAndDepositInterestAccountRecordNullAndDepositProjectedEndDateBefore(
        String workspaceUUID, String bankUUID, Instant depositProjectedEndDate, Pageable pageable
    );

    Page<JpaAcctDeposit> findByDepositCreationAccountRecordAccountWorkspaceWorkspaceUUIDAndDepositInterestAccountRecordNullAndDepositProjectedEndDateBefore(
        String workspaceUUID, Instant depositProjectedEndDate, Pageable pageable
    );

    Collection<JpaAcctDeposit> findAllByBankUUIDIn(Collection<String> bankUUIDs);

    Collection<JpaAcctDeposit> findAllByCurrencyUUIDIn(Collection<String> currencyUUIDs);

}
