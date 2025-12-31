package com.desolatetimelines.acct.reporting.springrepository;

import com.desolatetimelines.acct.reporting.model.JpaAcctDashboard;
import com.desolatetimelines.acct.reporting.model.JpaAcctDashboardReport;
import com.desolatetimelines.acct.reporting.model.JpaAcctDashboardReportFilter;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface JpaAcctDashboardReportFiltersRepository extends CrudRepository<JpaAcctDashboardReportFilter, Long> {

    Set<JpaAcctDashboardReportFilter> findAllByDashboardReport(JpaAcctDashboardReport dashboardReport);

    void deleteByDashboardReportDashboard(JpaAcctDashboard dashboard);

}
