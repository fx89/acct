package com.desolatetimelines.acct.reporting.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "\"report\"")
public class JpaAcctReport implements AcctReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @Column(name = "report_uuid", nullable = false)
    private String reportUUID;

    @Column(name = "report_name", nullable = false)
    private String reportName;

    @Column(name = "report_description")
    private String reportDescription;

    @Column(name = "report_sql_statement", nullable = false)
    private String reportSQLStatement;

    @Column(name = "report_category_column_name")
    private String reportCategoryColumnName;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_type", nullable = false)
    private AcctReportType reportType;

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    @Override
    public String getReportUUID() {
        return reportUUID;
    }

    @Override
    public void setReportUUID(String reportUUID) {
        this.reportUUID = reportUUID;
    }

    @Override
    public String getReportName() {
        return reportName;
    }

    @Override
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String getReportDescription() {
        return reportDescription;
    }

    @Override
    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    @Override
    public String getReportSQLStatement() {
        return reportSQLStatement;
    }

    @Override
    public void setReportSQLStatement(String reportSQLStatement) {
        this.reportSQLStatement = reportSQLStatement;
    }

    @Override
    public String getReportCategoryColumnName() {
        return reportCategoryColumnName;
    }

    @Override
    public void setReportCategoryColumnName(String reportCategoryColumnName) {
        this.reportCategoryColumnName = reportCategoryColumnName;
    }

    @Override
    public AcctReportType getReportType() {
        return reportType;
    }

    @Override
    public void setReportType(AcctReportType reportType) {
        this.reportType = reportType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctReport that = (JpaAcctReport) o;
        return Objects.equals(reportUUID, that.reportUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(reportUUID);
    }
}
