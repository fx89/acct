package com.desolatetimelines.acct.reporting.springrepository;

import com.desolatetimelines.acct.reporting.model.JpaAcctDataProviderInstance;
import com.desolatetimelines.acct.reporting.model.JpaAcctDataProviderInstanceProperty;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface JpaAcctDataProviderInstancePropertiesRepository extends CrudRepository<JpaAcctDataProviderInstanceProperty, Long> {

    void deleteByDataProviderInstance(JpaAcctDataProviderInstance dataProviderInstance);

    Set<JpaAcctDataProviderInstanceProperty> findAllByDataProviderInstance(
        JpaAcctDataProviderInstance dataProviderInstance
    );

}
