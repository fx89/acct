package com.desolatetimelines.acct.reporting.model;

import jakarta.persistence.*;

import static com.desolatetimelines.acct.reporting.util.AcctReportingRepoSpringDataUtils.doWithJpaAcctDashboardReport;

@Entity
@Table(name = "\"dashboard_report_filter\"")
public class JpaAcctDashboardReportFilter implements AcctDashboardReportFilter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dashboardReportFilterId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dashboard_report_id", nullable = false)
    private JpaAcctDashboardReport dashboardReport;

    @Column(name = "filter_name")
    private String filterName;

    @Column(name = "report_column_name")
    private String reportColumnName;

    public Long getDashboardReportFilterId() {
        return dashboardReportFilterId;
    }

    public void setDashboardReportFilterId(Long dashboardReportFilterId) {
        this.dashboardReportFilterId = dashboardReportFilterId;
    }

    @Override
    public AcctDashboardReport getDashboardReport() {
        return dashboardReport;
    }

    @Override
    public void setDashboardReport(AcctDashboardReport dashboardReport) {
        doWithJpaAcctDashboardReport(dashboardReport, this::setDashboardReport);
    }

    public void setDashboardReport(JpaAcctDashboardReport dashboardReport) {
        this.dashboardReport = dashboardReport;
    }

    @Override
    public String getFilterName() {
        return filterName;
    }

    @Override
    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    @Override
    public String getReportColumnName() {
        return reportColumnName;
    }

    @Override
    public void setReportColumnName(String reportColumnName) {
        this.reportColumnName = reportColumnName;
    }
}
