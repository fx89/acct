package com.desolatetimelines.acct.workspace.springrepository;

import com.desolatetimelines.acct.workspace.model.JpaAcctDeposit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaAcctDepositsRepository extends CrudRepository<JpaAcctDeposit, Long> {

    Optional<JpaAcctDeposit> findFirstByDepositUUID(String depositUUID);

    Page<JpaAcctDeposit> findByDepositCreationAccountRecordAccountWorkspaceWorkspaceUUID(
        String workspaceUUID, Pageable pageable
    );

    Page<JpaAcctDeposit> findByDepositCreationAccountRecordAccountWorkspaceWorkspaceUUIDAndBankUUID(
        String workspaceUUID, String bankUUID, Pageable pageable
    );

}
