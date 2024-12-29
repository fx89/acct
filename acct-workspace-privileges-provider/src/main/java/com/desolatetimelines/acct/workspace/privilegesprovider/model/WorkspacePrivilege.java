package com.desolatetimelines.acct.workspace.privilegesprovider.model;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;

/**
 * Defines the privileges required by the Workspace service
 */
public enum WorkspacePrivilege {

    WORKSPACES_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(WorkspacePrivilegeIds.WORKSPACES_SAVE)
            .withPrivilegeName("Create or update a workspace")
            .withPrivilegeDescription("Allows creating new workspaces or updating owned workspaces")
            .build()
    );

    private final AcctPrivilege acctPrivilege;

    WorkspacePrivilege(AcctPrivilege acctPrivilege) {
        this.acctPrivilege = acctPrivilege;
    }

    public AcctPrivilege getAcctPrivilege() {
        return acctPrivilege;
    }

}
