package com.desolatetimelines.acct.reporting.springrepository;

import com.desolatetimelines.acct.reporting.model.JpaAcctDataProviderInstance;
import com.desolatetimelines.acct.reporting.model.JpaAcctDataProviderInstanceRuntimeParameter;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface JpaAcctDataProviderInstanceRuntimeParametersRepository
    extends CrudRepository<JpaAcctDataProviderInstanceRuntimeParameter, Long> {

    void deleteByDataProviderInstance(JpaAcctDataProviderInstance dataProviderInstance);

    Set<JpaAcctDataProviderInstanceRuntimeParameter> findAllByDataProviderInstanceIn(
        Set<JpaAcctDataProviderInstance> dataProviderInstance
    );

}
