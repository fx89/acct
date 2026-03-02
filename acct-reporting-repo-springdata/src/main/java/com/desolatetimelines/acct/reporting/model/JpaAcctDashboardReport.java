package com.desolatetimelines.acct.reporting.model;

import jakarta.persistence.*;

import java.util.Objects;

import static com.desolatetimelines.acct.reporting.util.AcctReportingRepoSpringDataUtils.doWithJpaAcctDashboard;
import static com.desolatetimelines.acct.reporting.util.AcctReportingRepoSpringDataUtils.doWithJpaAcctReport;

@Entity
@Table(name = "\"dashboard_report\"")
public class JpaAcctDashboardReport implements AcctDashboardReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dashboardReportId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dashboard_id", nullable = false)
    private JpaAcctDashboard dashboard;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "report_id", nullable = false)
    private JpaAcctReport report;

    @Column(name = "row_number")
    private Integer rowNumber;

    @Column(name = "column_number")
    private Integer columnNumber;

    @Column(name = "container_name")
    private String containerName;

    @Column(name = "container_width_px")
    private Integer containerWidthPx;

    @Column(name = "container_height_px")
    private Integer containerHeightPx;

    public Long getDashboardReportId() {
        return dashboardReportId;
    }

    public void setDashboardReportId(Long dashboardReportId) {
        this.dashboardReportId = dashboardReportId;
    }

    @Override
    public AcctDashboard getDashboard() {
        return dashboard;
    }

    @Override
    public void setDashboard(AcctDashboard dashboard) {
        doWithJpaAcctDashboard(dashboard, this::setDashboard);
    }

    public void setDashboard(JpaAcctDashboard dashboard) {
        this.dashboard = dashboard;
    }

    @Override
    public AcctReport getReport() {
        return report;
    }

    @Override
    public void setReport(AcctReport report) {
        doWithJpaAcctReport(report, this::setReport);
    }

    public void setReport(JpaAcctReport report) {
        this.report = report;
    }

    @Override
    public Integer getRowNumber() {
        return rowNumber;
    }

    @Override
    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    @Override
    public Integer getColumnNumber() {
        return columnNumber;
    }

    @Override
    public void setColumnNumber(Integer columnNumber) {
        this.columnNumber = columnNumber;
    }

    @Override
    public String getContainerName() {
        return containerName;
    }

    @Override
    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    @Override
    public Integer getContainerWidthPx() {
        return containerWidthPx;
    }

    @Override
    public Integer getContainerHeightPx() {
        return containerHeightPx;
    }

    @Override
    public void setContainerWidthPx(Integer containerWidthPx) {
        this.containerWidthPx = containerWidthPx;
    }

    @Override
    public void setContainerHeightPx(Integer containerHeightPx) {
        this.containerHeightPx = containerHeightPx;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctDashboardReport that = (JpaAcctDashboardReport) o;
        return Objects.equals(dashboard, that.dashboard) && Objects.equals(report, that.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dashboard, report);
    }
}
