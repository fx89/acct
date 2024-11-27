package com.desolatetimelines.acct.security.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "dashboard_owner")
public class JpaAcctDashboardOwner implements AcctDashboardOwner {

    @Id
    @Column(name = "dashboard_owner_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dashboardOwnerId;

    @Column(name = "dashboard_uuid")
    private String dashboardUUID;

    @Enumerated(EnumType.STRING)
    @Column(name = "owner_type")
    private OwnerType ownerType;

    @Column(name = "owner_uuid")
    private String ownerUUID;

    public JpaAcctDashboardOwner() {
    }

    public JpaAcctDashboardOwner(Long dashboardOwnerId, String dashboardUUID, OwnerType ownerType, String ownerUUID) {
        this.dashboardOwnerId = dashboardOwnerId;
        this.dashboardUUID = dashboardUUID;
        this.ownerType = ownerType;
        this.ownerUUID = ownerUUID;
    }

    public Long getDashboardOwnerId() {
        return dashboardOwnerId;
    }

    public void setDashboardOwnerId(Long dashboardOwnerId) {
        this.dashboardOwnerId = dashboardOwnerId;
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
    public OwnerType getOwnerType() {
        return ownerType;
    }

    @Override
    public void setOwnerType(OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    @Override
    public String getOwnerUUID() {
        return ownerUUID;
    }

    @Override
    public void setOwnerUUID(String ownerUUID) {
        this.ownerUUID = ownerUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctDashboardOwner that = (JpaAcctDashboardOwner) o;
        return Objects.equals(dashboardOwnerId, that.dashboardOwnerId) && Objects.equals(dashboardUUID, that.dashboardUUID) && ownerType == that.ownerType && Objects.equals(ownerUUID, that.ownerUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dashboardOwnerId, dashboardUUID, ownerType, ownerUUID);
    }
}
