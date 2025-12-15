package com.desolatetimelines.acct.reporting.model;

public interface AcctReportSeries {

    AcctReport getReport();

    void setReport(AcctReport report);

    String getReportColumnName();

    void setReportColumnName(String reportColumnName);

    String getReportSeriesName();

    void setReportSeriesName(String reportSeriesName);

    AcctReportSeriesType getReportSeriesType();

    void setReportSeriesType(AcctReportSeriesType reportSeriesType);

}
