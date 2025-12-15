package com.desolatetimelines.acct.reporting.model;

import jakarta.persistence.*;

import java.util.Objects;

import static com.desolatetimelines.acct.reporting.util.AcctReportingRepoSpringDataUtils.doWithJpaAcctReport;

@Entity
@Table(name = "\"report_series\"")
public class JpaAcctReportSeries implements AcctReportSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportSeriesId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "report_id", nullable = false)
    private JpaAcctReport report;

    @Column(name = "report_column_name", nullable = false)
    private String reportColumnName;

    @Column(name = "report_series_name", nullable = false)
    private String reportSeriesName;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_series_type", nullable = false)
    private AcctReportSeriesType reportSeriesType;

    public Long getReportSeriesId() {
        return reportSeriesId;
    }

    public void setReportSeriesId(Long reportSeriesId) {
        this.reportSeriesId = reportSeriesId;
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
    public String getReportColumnName() {
        return reportColumnName;
    }

    @Override
    public void setReportColumnName(String reportColumnName) {
        this.reportColumnName = reportColumnName;
    }

    @Override
    public String getReportSeriesName() {
        return reportSeriesName;
    }

    @Override
    public void setReportSeriesName(String reportSeriesName) {
        this.reportSeriesName = reportSeriesName;
    }

    @Override
    public AcctReportSeriesType getReportSeriesType() {
        return reportSeriesType;
    }

    @Override
    public void setReportSeriesType(AcctReportSeriesType reportSeriesType) {
        this.reportSeriesType = reportSeriesType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctReportSeries that = (JpaAcctReportSeries) o;
        return Objects.equals(report, that.report) && Objects.equals(reportSeriesName, that.reportSeriesName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(report, reportSeriesName);
    }
}
