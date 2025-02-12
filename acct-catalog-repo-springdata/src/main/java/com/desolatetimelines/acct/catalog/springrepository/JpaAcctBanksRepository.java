package com.desolatetimelines.acct.catalog.springrepository;

import com.desolatetimelines.acct.catalog.model.JpaAcctBank;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface JpaAcctBanksRepository extends CrudRepository<JpaAcctBank, Long> {

    Optional<JpaAcctBank> findFirstByBankUUID(String bankUUID);

    Collection<JpaAcctBank> findAllByBankUUIDIn(Collection<String> bankUUIDs);

}
