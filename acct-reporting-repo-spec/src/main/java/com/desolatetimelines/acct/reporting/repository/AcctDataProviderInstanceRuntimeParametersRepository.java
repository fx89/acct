package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstance;
import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstanceRuntimeParameter;

import java.util.Set;

/**
 * Repository for loading and persisting
 * {@link AcctDataProviderInstanceRuntimeParameter data provider instnace runtime parameters}
 */
public interface AcctDataProviderInstanceRuntimeParametersRepository {

    AcctDataProviderInstanceRuntimeParameter createNew();

    AcctDataProviderInstanceRuntimeParameter save(AcctDataProviderInstanceRuntimeParameter parameter);

    void delete(AcctDataProviderInstanceRuntimeParameter runtimeParameter);

    void deleteByDataProviderInstance(AcctDataProviderInstance dataProviderInstance);

    Set<AcctDataProviderInstanceRuntimeParameter> findAllByDataProviderInstanceIn(Set<AcctDataProviderInstance> dataProviderInstances);

}
