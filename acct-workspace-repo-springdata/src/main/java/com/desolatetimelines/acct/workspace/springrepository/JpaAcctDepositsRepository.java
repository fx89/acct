package com.desolatetimelines.acct.workspace.springrepository;

import com.desolatetimelines.acct.workspace.model.JpaAcctDeposit;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaAcctDepositsRepository extends CrudRepository<JpaAcctDeposit, Long> {

    Optional<JpaAcctDeposit> findFirstByDepositUUID(String depositUUID);

}
