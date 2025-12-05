package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstance;
import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstanceProperty;
import com.desolatetimelines.acct.reporting.model.JpaAcctDataProviderInstanceProperty;
import com.desolatetimelines.acct.reporting.springrepository.JpaAcctDataProviderInstancePropertiesRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.desolatetimelines.acct.reporting.util.AcctReportingRepoSpringDataUtils.*;
import static java.util.function.Function.identity;

/**
 * Implementation of the
 * {@link SpringJpaAcctDataProviderInstancePropertiesRepository data provider instance properties repository}
 * that uses Spring Data JPA to persist data provider instance properties to the database
 */
@Service
public class SpringJpaAcctDataProviderInstancePropertiesRepository
    implements AcctDataProviderInstancePropertiesRepository {

    private final JpaAcctDataProviderInstancePropertiesRepository jpaAcctDataProviderInstancePropertiesRepository;

    public SpringJpaAcctDataProviderInstancePropertiesRepository(
        JpaAcctDataProviderInstancePropertiesRepository jpaAcctDataProviderInstancePropertiesRepository
    ) {
        this.jpaAcctDataProviderInstancePropertiesRepository = jpaAcctDataProviderInstancePropertiesRepository;
    }

    @Override
    public AcctDataProviderInstanceProperty createNew() {
        return new JpaAcctDataProviderInstanceProperty();
    }

    @Override
    public AcctDataProviderInstanceProperty save(AcctDataProviderInstanceProperty property) {
        return
            doWithJpaAcctDataProviderInstancePropertyReturning(
                property,
                jpaAcctDataProviderInstancePropertiesRepository::save
            );
    }

    @Override
    public void deleteByDataProviderInstance(AcctDataProviderInstance dataProviderInstance) {
        doWithJpaAcctDataProviderInstance(
            dataProviderInstance,
            jpaAcctDataProviderInstancePropertiesRepository::deleteByDataProviderInstance
        );
    }

    @Override
    public Set<AcctDataProviderInstanceProperty> findAllByDataProviderInstance(
        AcctDataProviderInstance dataProviderInstance
    ) {
        return
            new HashSet<>(
                jpaAcctDataProviderInstancePropertiesRepository
                    .findAllByDataProviderInstance(
                        doWithJpaAcctDataProviderInstanceReturning(dataProviderInstance, identity())
                    )
            );
    }
}
