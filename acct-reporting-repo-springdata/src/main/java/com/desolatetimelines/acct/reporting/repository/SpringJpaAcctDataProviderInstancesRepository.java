package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstance;
import com.desolatetimelines.acct.reporting.model.JpaAcctDataProviderInstance;
import com.desolatetimelines.acct.reporting.springrepository.JpaAcctDataProviderInstancesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.desolatetimelines.acct.reporting.util.AcctReportingRepoSpringDataUtils.doWithJpaAcctDataProviderInstance;
import static com.desolatetimelines.acct.reporting.util.AcctReportingRepoSpringDataUtils.doWithJpaAcctDataProviderInstanceReturning;
import static java.util.function.Function.identity;

/**
 * Implementation of the
 * {@link AcctDataProviderInstancesRepository data provider instances repository}
 * that uses Spring Data JPA to persist data provider instances to the database
 */
@Service
public class SpringJpaAcctDataProviderInstancesRepository implements AcctDataProviderInstancesRepository {

    private final JpaAcctDataProviderInstancesRepository jpaAcctDataProviderInstancesRepository;

    public SpringJpaAcctDataProviderInstancesRepository(
        JpaAcctDataProviderInstancesRepository jpaAcctDataProviderInstancesRepository
    ) {
        this.jpaAcctDataProviderInstancesRepository = jpaAcctDataProviderInstancesRepository;
    }

    @Override
    public AcctDataProviderInstance createNew() {
        return new JpaAcctDataProviderInstance();
    }

    @Override
    public Optional<AcctDataProviderInstance> findFirstByDataProviderInstanceUUID(String dataProviderInstanceUUID) {
        return
            jpaAcctDataProviderInstancesRepository
                .findFirstByDataProviderInstanceUUID(dataProviderInstanceUUID)
                .map(identity())
            ;
    }

    @Override
    public Set<AcctDataProviderInstance> findAll() {
        return
            StreamSupport
                .stream(jpaAcctDataProviderInstancesRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(AcctDataProviderInstance dataProviderInstance) {
        doWithJpaAcctDataProviderInstance(
            dataProviderInstance,
            jpaAcctDataProviderInstancesRepository::delete
        );
    }

    @Override
    public AcctDataProviderInstance save(AcctDataProviderInstance dataProviderInstance) {
        return
            doWithJpaAcctDataProviderInstanceReturning(
                dataProviderInstance,
                jpaAcctDataProviderInstancesRepository::save
            );
    }

}
