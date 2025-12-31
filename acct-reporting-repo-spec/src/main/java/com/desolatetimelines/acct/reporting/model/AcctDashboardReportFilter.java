package com.desolatetimelines.acct.reporting.model;

public interface AcctDashboardReportFilter {

    AcctDashboardReport getDashboardReport();

    void setDashboardReport(AcctDashboardReport dashboardReport);

    String getFilterName();

    void setFilterName(String filterName);

    String getReportColumnName();

    void setReportColumnName(String reportColumnName);

}
