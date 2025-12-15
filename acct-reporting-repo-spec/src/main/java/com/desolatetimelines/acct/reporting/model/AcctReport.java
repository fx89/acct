package com.desolatetimelines.acct.reporting.model;

public interface AcctReport {

    String getReportUUID();

    void setReportUUID(String reportUUID);

    String getReportName();

    void setReportName(String reportName);

    String getReportDescription();

    void setReportDescription(String reportDescription);

    String getReportSQLStatement();

    void setReportSQLStatement(String reportSQLStatement);

    String getReportCategoryColumnName();

    void setReportCategoryColumnName(String reportCategoryColumnName);

    AcctReportType getReportType();

    void setReportType(AcctReportType reportType);

}
