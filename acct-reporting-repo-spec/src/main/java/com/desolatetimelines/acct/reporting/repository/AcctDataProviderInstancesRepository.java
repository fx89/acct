package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstance;

import java.util.Optional;

/**
 * Repository for loading and persisting {@link AcctDataProviderInstance data provider instances}
 */
public interface AcctDataProviderInstancesRepository {

    AcctDataProviderInstance createNew();

    Optional<AcctDataProviderInstance> findFirstByDataProviderInstanceUUID(String dataProviderInstanceUUID);

    AcctDataProviderInstance save(AcctDataProviderInstance dataProviderInstance);

}
