package com.desolatetimelines.acct.security.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "report_owner")
public class JpaAcctReportOwner implements AcctReportOwner {

    @Id
    @Column(name = "report_owner_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportOwnerId;

    @Column(name = "report_uuid")
    private String reportUUID;

    @Enumerated(EnumType.STRING)
    @Column(name = "owner_type")
    private OwnerType ownerType;

    @Column(name = "owner_uuid")
    private String ownerUUID;

    public JpaAcctReportOwner() {
    }

    public JpaAcctReportOwner(Long reportOwnerId, String reportUUID, OwnerType ownerType, String ownerUUID) {
        this.reportOwnerId = reportOwnerId;
        this.reportUUID = reportUUID;
        this.ownerType = ownerType;
        this.ownerUUID = ownerUUID;
    }

    public Long getReportOwnerId() {
        return reportOwnerId;
    }

    public void setReportOwnerId(Long reportOwnerId) {
        this.reportOwnerId = reportOwnerId;
    }

    public String getReportUUID() {
        return reportUUID;
    }

    public void setReportUUID(String reportUUID) {
        this.reportUUID = reportUUID;
    }

    @Override
    public String getResourceUUID() {
        return reportUUID;
    }

    @Override
    public void setResourceUUID(String reportUUID) {
        this.reportUUID = reportUUID;
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
        JpaAcctReportOwner that = (JpaAcctReportOwner) o;
        return Objects.equals(reportOwnerId, that.reportOwnerId) && Objects.equals(reportUUID, that.reportUUID) && ownerType == that.ownerType && Objects.equals(ownerUUID, that.ownerUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportOwnerId, reportUUID, ownerType, ownerUUID);
    }
}
