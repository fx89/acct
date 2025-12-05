package com.desolatetimelines.acct.reporting.springrepository;

import com.desolatetimelines.acct.reporting.model.JpaAcctDataProviderInstance;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaAcctDataProviderInstancesRepository
    extends CrudRepository<JpaAcctDataProviderInstance, Long> {

    Optional<JpaAcctDataProviderInstance> findFirstByDataProviderInstanceUUID(String dataProviderInstanceUUID);

}
