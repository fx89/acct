package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctDashboard;
import com.desolatetimelines.acct.reporting.model.AcctDashboardReport;
import com.desolatetimelines.acct.reporting.model.JpaAcctDashboardReport;
import com.desolatetimelines.acct.reporting.springrepository.JpaAcctDashboardReportsRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.desolatetimelines.acct.reporting.util.AcctReportingRepoSpringDataUtils.*;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctDashboardReportsRepository dashboard reports repository}
 * that uses Spring Data JPA to persist dashboard reports to the database
 */
@Service
public class SpringJpaAcctDashboardReportsRepository implements AcctDashboardReportsRepository {

    private final JpaAcctDashboardReportsRepository jpaAcctDashboardReportsRepository;

    public SpringJpaAcctDashboardReportsRepository(
        JpaAcctDashboardReportsRepository jpaAcctDashboardReportsRepository
    ) {
        this.jpaAcctDashboardReportsRepository = jpaAcctDashboardReportsRepository;
    }

    @Override
    public AcctDashboardReport createNew() {
        return new JpaAcctDashboardReport();
    }

    @Override
    public AcctDashboardReport save(AcctDashboardReport dashboardReport) {
        return
            doWithJpaAcctDashboardReportReturning(
                dashboardReport,
                jpaAcctDashboardReportsRepository::save
            );
    }

    @Override
    public Optional<AcctDashboardReport> findFirstByDashboardAndReportUUID(
        AcctDashboard dashboard,
        String reportUUID
    ) {
        return
            jpaAcctDashboardReportsRepository
                .findFirstByDashboardAndReportReportUUID(
                    doWithJpaAcctDashboardReturning(dashboard, identity()),
                    reportUUID
                )
                .map(identity());
    }

    @Override
    public Set<AcctDashboardReport> findAllByDashboardDashboardUUID(String dashboardUUID) {
        return
            new HashSet<>(
                jpaAcctDashboardReportsRepository.findAllByDashboardDashboardUUID(
                    dashboardUUID
                )
            );
    }

    @Override
    public void delete(AcctDashboardReport dashboardReport) {
        doWithJpaAcctDashboardReport(
            dashboardReport,
            jpaAcctDashboardReportsRepository::delete
        );
    }

    @Override
    public void deleteByDashboard(AcctDashboard dashboard) {
        doWithJpaAcctDashboard(
            dashboard,
            jpaAcctDashboardReportsRepository::deleteByDashboard
        );
    }

}
