package com.desolatetimelines.acct.workspace.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "\"workspace\"")
public class JpaAcctWorkspace implements AcctWorkspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workspaceId;

    @Column(name = "workspace_uuid")
    private String workspaceUUID;

    @Column(name = "workspace_name")
    private String workspaceName;

    @Column(name = "workspace_description")
    private String workspaceDescription;

    @Column(name = "workspace_icon_uuid")
    private String workspaceIconUUID;

    @Column(name = "default_currency_uuid")
    private String defaultCurrencyUUID;

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
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
    public String getWorkspaceName() {
        return workspaceName;
    }

    @Override
    public void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
    }

    @Override
    public String getWorkspaceDescription() {
        return workspaceDescription;
    }

    @Override
    public void setWorkspaceDescription(String workspaceDescription) {
        this.workspaceDescription = workspaceDescription;
    }

    @Override
    public String getWorkspaceIconUUID() {
        return workspaceIconUUID;
    }

    @Override
    public void setWorkspaceIconUUID(String workspaceIconUUID) {
        this.workspaceIconUUID = workspaceIconUUID;
    }

    @Override
    public String getDefaultCurrencyUUID() {
        return defaultCurrencyUUID;
    }

    @Override
    public void setDefaultCurrencyUUID(String defaultCurrencyUUID) {
        this.defaultCurrencyUUID = defaultCurrencyUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctWorkspace that = (JpaAcctWorkspace) o;
        return Objects.equals(workspaceUUID, that.workspaceUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workspaceUUID);
    }
}
