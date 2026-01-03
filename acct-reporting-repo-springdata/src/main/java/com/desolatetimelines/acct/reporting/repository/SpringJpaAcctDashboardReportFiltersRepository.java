package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctDashboard;
import com.desolatetimelines.acct.reporting.model.AcctDashboardReport;
import com.desolatetimelines.acct.reporting.model.AcctDashboardReportFilter;
import com.desolatetimelines.acct.reporting.model.JpaAcctDashboardReportFilter;
import com.desolatetimelines.acct.reporting.springrepository.JpaAcctDashboardReportFiltersRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.desolatetimelines.acct.reporting.util.AcctReportingRepoSpringDataUtils.*;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctDashboardReportFiltersRepository dashboard report filters repository}
 * that uses Spring Data JPA to persist dashboard report filters to the database
 */
@Service
public class SpringJpaAcctDashboardReportFiltersRepository implements AcctDashboardReportFiltersRepository {

    private final JpaAcctDashboardReportFiltersRepository jpaAcctDashboardReportFiltersRepository;

    public SpringJpaAcctDashboardReportFiltersRepository(
        JpaAcctDashboardReportFiltersRepository jpaAcctDashboardReportFiltersRepository
    ) {
        this.jpaAcctDashboardReportFiltersRepository = jpaAcctDashboardReportFiltersRepository;
    }

    @Override
    public AcctDashboardReportFilter createNew() {
        return new JpaAcctDashboardReportFilter();
    }

    @Override
    public AcctDashboardReportFilter save(AcctDashboardReportFilter dashboardReportFilter) {
        return
            doWithJpaAcctDashboardReportFilterReturning(
                dashboardReportFilter,
                jpaAcctDashboardReportFiltersRepository::save
            );
    }

    @Override
    public Set<AcctDashboardReportFilter> findAllByDashboardReportIn(Set<AcctDashboardReport> dashboardReports) {
        return
            new HashSet<>(
                jpaAcctDashboardReportFiltersRepository
                    .findAllByDashboardReportIn(
                        dashboardReports.stream()
                            .map(dashboardReport ->
                                doWithJpaAcctDashboardReportReturning(
                                    dashboardReport,
                                    identity()
                                )
                            )
                            .collect(Collectors.toSet())

                    )
            );
    }

    @Override
    public void delete(AcctDashboardReportFilter dashboardReportFilter) {
        doWithJpaAcctDashboardReportFilter(
            dashboardReportFilter,
            jpaAcctDashboardReportFiltersRepository::delete
        );
    }

    @Override
    public void deleteByDashboardReportDashboard(AcctDashboard dashboard) {
        doWithJpaAcctDashboard(
            dashboard,
            jpaAcctDashboardReportFiltersRepository::deleteByDashboardReportDashboard
        );
    }

}
