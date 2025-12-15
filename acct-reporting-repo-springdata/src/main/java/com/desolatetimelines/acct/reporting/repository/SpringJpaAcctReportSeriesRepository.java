package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctReport;
import com.desolatetimelines.acct.reporting.model.AcctReportSeries;
import com.desolatetimelines.acct.reporting.model.JpaAcctReportSeries;
import com.desolatetimelines.acct.reporting.springrepository.JpaAcctReportSeriesRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.desolatetimelines.acct.reporting.util.AcctReportingRepoSpringDataUtils.*;
import static java.util.function.Function.identity;

/**
 * Implementation of the
 * {@link AcctReportSeriesRepository report series repository}
 * that uses Spring Data JPA to persist data provider instances to the database
 */
@Service
public class SpringJpaAcctReportSeriesRepository implements AcctReportSeriesRepository {

    private final JpaAcctReportSeriesRepository jpaAcctReportSeriesRepository;

    public SpringJpaAcctReportSeriesRepository(JpaAcctReportSeriesRepository jpaAcctReportSeriesRepository) {
        this.jpaAcctReportSeriesRepository = jpaAcctReportSeriesRepository;
    }

    @Override
    public AcctReportSeries createNew() {
        return new JpaAcctReportSeries();
    }

    @Override
    public AcctReportSeries save(AcctReportSeries reportSeries) {
        return
            doWithJpaAcctReportSeriesReturning(
                reportSeries,
                jpaAcctReportSeriesRepository::save
            );
    }

    @Override
    public Set<AcctReportSeries> findAllByReport(AcctReport report) {
        return
            new HashSet<>(
                jpaAcctReportSeriesRepository.findAllByReport(
                    doWithJpaAcctReportReturning(report, identity())
                )
            );
    }

    @Override
    public void delete(AcctReportSeries reportSeries) {
        doWithJpaAcctReportSeries(
            reportSeries,
            jpaAcctReportSeriesRepository::delete
        );
    }

}
