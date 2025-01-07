package com.desolatetimelines.acct.workspace.springrepository;

import com.desolatetimelines.acct.workspace.model.JpaAcctDeposit;
import org.springframework.data.repository.CrudRepository;

public interface JpaAcctDepositsRepository extends CrudRepository<JpaAcctDeposit, Long> {
}
