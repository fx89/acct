package com.desolatetimelines.acct.reporting.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "\"dashboard\"")
public class JpaAcctDashboard implements AcctDashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dashboardId;

    @Column(name = "dashboard_uuid")
    private String dashboardUUID;

    @Column(name = "workspace_uuid")
    private String workspaceUUID;

    @Column(name = "dashboard_name")
    private String dashboardName;

    @Column(name = "dashboard_description")
    private String dashboardDescription;

    @Column(name = "dashboard_icon_uuid")
    private String dashboardIconUUID;

    public Long getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(Long dashboardId) {
        this.dashboardId = dashboardId;
    }

    @Override
    public String getDashboardUUID() {
        return dashboardUUID;
    }

    @Override
    public void setDashboardUUID(String dashboardUUID) {
        this.dashboardUUID = dashboardUUID;
    }

    @Override
    public String getWorkspaceUUID() {
        return workspaceUUID;
    }

    @Override
    public void setWorkspaceUUID(String workspaceUUID) {
        this.workspaceUUID = workspaceUUID;
    }

    @Override
    public String getDashboardName() {
        return dashboardName;
    }

    @Override
    public void setDashboardName(String dashboardName) {
        this.dashboardName = dashboardName;
    }

    @Override
    public String getDashboardDescription() {
        return dashboardDescription;
    }

    @Override
    public void setDashboardDescription(String dashboardDescription) {
        this.dashboardDescription = dashboardDescription;
    }

    @Override
    public String getDashboardIconUUID() {
        return dashboardIconUUID;
    }

    @Override
    public void setDashboardIconUUID(String dashboardIconUUID) {
        this.dashboardIconUUID = dashboardIconUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctDashboard that = (JpaAcctDashboard) o;
        return Objects.equals(dashboardUUID, that.dashboardUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dashboardUUID);
    }
}
