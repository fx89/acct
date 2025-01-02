package com.desolatetimelines.acct.workspace.springrepository;

import com.desolatetimelines.acct.workspace.model.JpaAcctAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaAcctAccountsRepository extends CrudRepository<JpaAcctAccount, Long> {

    Optional<JpaAcctAccount> findFirstByAccountUUID(String accountUUID);

}
