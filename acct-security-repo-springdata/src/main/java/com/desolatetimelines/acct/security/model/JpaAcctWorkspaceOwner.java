package com.desolatetimelines.acct.security.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "workspace_owner")
public class JpaAcctWorkspaceOwner implements AcctWorkspaceOwner {

    @Id
    @Column(name = "workspace_owner_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workspaceOwnerId;

    @Column(name = "workspace_uuid")
    private String workspaceUUID;

    @Enumerated(EnumType.STRING)
    @Column(name = "owner_type")
    private OwnerType ownerType;

    @Column(name = "owner_uuid")
    private String ownerUUID;

    public JpaAcctWorkspaceOwner() {
    }

    public JpaAcctWorkspaceOwner(Long workspaceOwnerId, String workspaceUUID, OwnerType ownerType, String ownerUUID) {
        this.workspaceOwnerId = workspaceOwnerId;
        this.workspaceUUID = workspaceUUID;
        this.ownerType = ownerType;
        this.ownerUUID = ownerUUID;
    }

    public Long getWorkspaceOwnerId() {
        return workspaceOwnerId;
    }

    public void setWorkspaceOwnerId(Long workspaceOwnerId) {
        this.workspaceOwnerId = workspaceOwnerId;
    }

    public String getWorkspaceUUID() {
        return workspaceUUID;
    }

    public void setWorkspaceUUID(String workspaceUUID) {
        this.workspaceUUID = workspaceUUID;
    }

    @Override
    public String getResourceUUID() {
        return workspaceUUID;
    }

    @Override
    public void setResourceUUID(String workspaceUUID) {
        this.workspaceUUID = workspaceUUID;
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
        JpaAcctWorkspaceOwner that = (JpaAcctWorkspaceOwner) o;
        return Objects.equals(workspaceOwnerId, that.workspaceOwnerId) && Objects.equals(workspaceUUID, that.workspaceUUID) && ownerType == that.ownerType && Objects.equals(ownerUUID, that.ownerUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workspaceOwnerId, workspaceUUID, ownerType, ownerUUID);
    }
}
