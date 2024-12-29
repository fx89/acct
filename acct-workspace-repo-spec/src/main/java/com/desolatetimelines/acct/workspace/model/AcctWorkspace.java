package com.desolatetimelines.acct.workspace.model;

public interface AcctWorkspace {

    String getWorkspaceUUID();

    void setWorkspaceUUID(String workspaceUUID);

    String getWorkspaceName();

    void setWorkspaceName(String workspaceName);

    String getWorkspaceDescription();

    void setWorkspaceDescription(String workspaceDescription);

    String getWorkspaceIconUUID();

    void setWorkspaceIconUUID(String workspaceIconUUID);

    String getDefaultCurrencyUUID();

    void setDefaultCurrencyUUID(String defaultCurrencyUUID);

}
