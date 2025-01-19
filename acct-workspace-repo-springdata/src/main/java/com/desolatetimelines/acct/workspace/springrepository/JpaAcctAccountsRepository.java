package com.desolatetimelines.acct.workspace.springrepository;

import com.desolatetimelines.acct.workspace.model.JpaAcctAccount;
import com.desolatetimelines.acct.workspace.model.JpaAcctWorkspace;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface JpaAcctAccountsRepository extends CrudRepository<JpaAcctAccount, Long> {

    Optional<JpaAcctAccount> findFirstByAccountUUID(String accountUUID);

    Collection<JpaAcctAccount> findAllByWorkspace(JpaAcctWorkspace workspace);

    Collection<JpaAcctAccount> findAllByAccountIconUUIDIn(Collection<String> accountIconUUIDs);

    Collection<JpaAcctAccount> findAllByBankUUIDIn(Collection<String> bankUUIDs);

    Collection<JpaAcctAccount> findAllByCurrencyUUIDIn(Collection<String> currencyUUIDs);

}
