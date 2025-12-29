package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstance;
import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstanceProperty;

import java.util.Set;

/**
 * Repository for loading and persisting {@link AcctDataProviderInstanceProperty data provider instance properties}
 */
public interface AcctDataProviderInstancePropertiesRepository {

    AcctDataProviderInstanceProperty createNew();

    AcctDataProviderInstanceProperty save(AcctDataProviderInstanceProperty property);

    void deleteByDataProviderInstance(AcctDataProviderInstance dataProviderInstance);

    Set<AcctDataProviderInstanceProperty> findAllByDataProviderInstanceIn(
        Set<AcctDataProviderInstance> dataProviderInstances
    );

}
