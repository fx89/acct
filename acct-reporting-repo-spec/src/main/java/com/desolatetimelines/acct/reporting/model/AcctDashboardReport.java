package com.desolatetimelines.acct.reporting.model;

public interface AcctDashboardReport {

    AcctDashboard getDashboard();

    void setDashboard(AcctDashboard dashboard);

    AcctReport getReport();

    void setReport(AcctReport report);

    Integer getRowNumber();

    void setRowNumber(Integer rowNumber);

    Integer getColumnNumber();

    void setColumnNumber(Integer columnNumber);

    String getContainerName();

    void setContainerName(String containerName);

    Integer getContainerHeightPx();

    void setContainerHeightPx(Integer containerHeightPx);

}
