package com.desolatetimelines.acct.reporting.model;


public interface AcctDashboard {

    String getDashboardUUID();

    void setDashboardUUID(String dashboardUUID);

    String getWorkspaceUUID();

    void setWorkspaceUUID(String workspaceUUID);

    String getDashboardName();

    void setDashboardName(String dashboardName);

    String getDashboardDescription();

    void setDashboardDescription(String dashboardDescription);

    String getDashboardIconUUID();

    void setDashboardIconUUID(String dashboardIconUUID);

}
