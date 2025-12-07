package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstance;

import java.util.Optional;
import java.util.Set;

/**
 * Repository for loading and persisting {@link AcctDataProviderInstance data provider instances}
 */
public interface AcctDataProviderInstancesRepository {

    AcctDataProviderInstance createNew();

    AcctDataProviderInstance save(AcctDataProviderInstance dataProviderInstance);

    Optional<AcctDataProviderInstance> findFirstByDataProviderInstanceUUID(String dataProviderInstanceUUID);

    Set<AcctDataProviderInstance> findAll();

}
