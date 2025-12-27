package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctReport;
import com.desolatetimelines.acct.reporting.model.AcctReportDataProviderInstance;
import com.desolatetimelines.acct.reporting.model.JpaAcctReportDataProviderInstance;
import com.desolatetimelines.acct.reporting.springrepository.JpaAcctReportDataProviderInstancesRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.desolatetimelines.acct.reporting.util.AcctReportingRepoSpringDataUtils.*;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toSet;

/**
 * Implementation of the
 * {@link AcctReportDataProviderInstancesRepository report data provider instances repository}
 * that uses Spring Data JPA to persist data provider instances to the database
 */
@Service
public class SpringJpaAcctReportDataProviderInstancesRepository implements AcctReportDataProviderInstancesRepository {

    private final JpaAcctReportDataProviderInstancesRepository jpaAcctReportDataProviderInstancesRepository;

    public SpringJpaAcctReportDataProviderInstancesRepository(
        JpaAcctReportDataProviderInstancesRepository jpaAcctReportDataProviderInstancesRepository
    ) {
        this.jpaAcctReportDataProviderInstancesRepository = jpaAcctReportDataProviderInstancesRepository;
    }

    @Override
    public AcctReportDataProviderInstance createNew() {
        return new JpaAcctReportDataProviderInstance();
    }

    @Override
    public AcctReportDataProviderInstance save(AcctReportDataProviderInstance reportDataProviderInstance) {
        return
            doWithJpaAcctReportDataProviderInstanceReturning(
                reportDataProviderInstance,
                jpaAcctReportDataProviderInstancesRepository::save
            );
    }

    @Override
    public Set<AcctReportDataProviderInstance> findAllByReportIn(Set<AcctReport> reports) {
        return
            new HashSet<>(
                jpaAcctReportDataProviderInstancesRepository.findAllByReportIn(
                    reports.stream()
                        .map(report -> doWithJpaAcctReportReturning(report, identity()))
                        .collect(toSet())
                )
            );
    }

    @Override
    public void delete(AcctReportDataProviderInstance reportDataProviderInstance) {
        doWithJpaAcctReportDataProviderInstance(
            reportDataProviderInstance,
            jpaAcctReportDataProviderInstancesRepository::delete
        );
    }

}
