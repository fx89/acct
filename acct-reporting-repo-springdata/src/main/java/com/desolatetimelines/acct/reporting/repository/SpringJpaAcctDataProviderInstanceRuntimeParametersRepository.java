package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstance;
import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstanceRuntimeParameter;
import com.desolatetimelines.acct.reporting.model.JpaAcctDataProviderInstanceRuntimeParameter;
import com.desolatetimelines.acct.reporting.springrepository.JpaAcctDataProviderInstanceRuntimeParametersRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.desolatetimelines.acct.reporting.util.AcctReportingRepoSpringDataUtils.*;
import static java.util.function.Function.identity;

/**
 * Implementation of the
 * {@link AcctDataProviderInstanceRuntimeParametersRepository data provider instance runtime paramters repository}
 * that uses Spring Data JPA to persist data provider instance runtime parameters to the database
 */
@Service
public class SpringJpaAcctDataProviderInstanceRuntimeParametersRepository
    implements AcctDataProviderInstanceRuntimeParametersRepository {

    private final JpaAcctDataProviderInstanceRuntimeParametersRepository jpaAcctDataProviderInstanceRuntimeParametersRepository;

    public SpringJpaAcctDataProviderInstanceRuntimeParametersRepository(
        JpaAcctDataProviderInstanceRuntimeParametersRepository jpaAcctDataProviderInstanceRuntimeParametersRepository
    ) {
        this.jpaAcctDataProviderInstanceRuntimeParametersRepository = jpaAcctDataProviderInstanceRuntimeParametersRepository;
    }

    @Override
    public AcctDataProviderInstanceRuntimeParameter createNew() {
        return new JpaAcctDataProviderInstanceRuntimeParameter();
    }

    @Override
    public AcctDataProviderInstanceRuntimeParameter save(AcctDataProviderInstanceRuntimeParameter parameter) {
        return
            doWithAcctDataProviderInstanceRuntimeParameterReturning(
                parameter,
                jpaAcctDataProviderInstanceRuntimeParametersRepository::save
            );
    }

    @Override
    public void delete(AcctDataProviderInstanceRuntimeParameter runtimeParameter) {
        doWithJpaAcctDataProviderInstanceRuntimeParameter(
            runtimeParameter,
            jpaAcctDataProviderInstanceRuntimeParametersRepository::delete
        );
    }

    @Override
    public void deleteByDataProviderInstance(AcctDataProviderInstance dataProviderInstance) {
        doWithJpaAcctDataProviderInstance(
            dataProviderInstance,
            jpaAcctDataProviderInstanceRuntimeParametersRepository::deleteByDataProviderInstance
        );
    }

    @Override
    public Set<AcctDataProviderInstanceRuntimeParameter> findAllByDataProviderInstanceIn(
        Set<AcctDataProviderInstance> dataProviderInstances
    ) {
        return
            new HashSet<>(
                jpaAcctDataProviderInstanceRuntimeParametersRepository
                    .findAllByDataProviderInstanceIn(
                        dataProviderInstances.stream()
                            .map(dataProviderInstance -> doWithJpaAcctDataProviderInstanceReturning(dataProviderInstance, identity()))
                            .collect(Collectors.toSet())
                    )
            );
    }

}
