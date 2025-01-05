package com.desolatetimelines.acct.workspace.privilegesprovider.model;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;

/**
 * Defines the privileges required by the Workspace service
 */
public enum WorkspacePrivilege {

    WORKSPACES_SAVE_OWN(
        AcctPrivilege.builder()
            .withPrivilegeId(WorkspacePrivilegeIds.WORKSPACES_SAVE_OWN)
            .withPrivilegeName("Create or update a workspace")
            .withPrivilegeDescription("Allows creating new workspaces or updating workspaces that a user owns directly")
            .build()
    ),

    WORKSPACES_SAVE_GROUP(
        AcctPrivilege.builder()
            .withPrivilegeId(WorkspacePrivilegeIds.WORKSPACES_SAVE_GROUP)
            .withPrivilegeName("Update a group workspace")
            .withPrivilegeDescription("Allows updating workspaces owned by groups that the user is part of")
            .build()
    ),

    WORKSPACES_SAVE_ANY(
        AcctPrivilege.builder()
            .withPrivilegeId(WorkspacePrivilegeIds.WORKSPACES_SAVE_ANY)
            .withPrivilegeName("Update any workspace")
            .withPrivilegeDescription("Allows updating workspaces owned by anyone")
            .build()
    ),

    WORKSPACES_READ_OWN(
        AcctPrivilege.builder()
            .withPrivilegeId(WorkspacePrivilegeIds.WORKSPACES_READ_OWN)
            .withPrivilegeName("Get the details of a directly-owned workspace")
            .withPrivilegeDescription("Allows reading the details of workspaces that a user owns directly")
            .build()
    ),

    WORKSPACES_READ_GROUP(
        AcctPrivilege.builder()
            .withPrivilegeId(WorkspacePrivilegeIds.WORKSPACES_READ_GROUP)
            .withPrivilegeName("Read the details of a group workspace")
            .withPrivilegeDescription("Allows reading the details of workspaces owned by groups that the user is part of")
            .build()
    ),

    WORKSPACES_READ_ANY(
        AcctPrivilege.builder()
            .withPrivilegeId(WorkspacePrivilegeIds.WORKSPACES_READ_ANY)
            .withPrivilegeName("Read the details of any workspace")
            .withPrivilegeDescription("Allows reading the details of workspaces owned by anyone")
            .build()
    ),

    WORKSPACES_DELETE_OWN(
        AcctPrivilege.builder()
            .withPrivilegeId(WorkspacePrivilegeIds.WORKSPACES_DELETE_OWN)
            .withPrivilegeName("Delete own workspaces")
            .withPrivilegeDescription("Allows deleting workspaces that a user owns directly")
            .build()
    ),

    WORKSPACES_DELETE_GROUP(
        AcctPrivilege.builder()
            .withPrivilegeId(WorkspacePrivilegeIds.WORKSPACES_DELETE_GROUP)
            .withPrivilegeName("Delete group workspaces")
            .withPrivilegeDescription("Allows deleting workspaces that are owned by a group that the user is part of")
            .build()
    ),

    WORKSPACES_DELETE_ANY(
        AcctPrivilege.builder()
            .withPrivilegeId(WorkspacePrivilegeIds.WORKSPACES_DELETE_ANY)
            .withPrivilegeName("Delete any workspaces")
            .withPrivilegeDescription("Allows deleting any workspace, no matter the ownership")
            .build()
    ),

    ACCOUNT_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(WorkspacePrivilegeIds.ACCOUNTS_SAVE)
            .withPrivilegeName("Create or update an account")
            .withPrivilegeDescription("Allows creating or updating accounts, " +
                "provided that the user has the right to update the account's parent workspace")
            .build()
    ),
    ACCOUNT_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(WorkspacePrivilegeIds.ACCOUNTS_READ)
            .withPrivilegeName("Read an account's properties")
            .withPrivilegeDescription("Allows reading an account's properties, " +
                "provided that the user has the right to read the account's parent workspace")
            .build()
    ),
    ACCOUNT_DELETE(
        AcctPrivilege.builder()
            .withPrivilegeId(WorkspacePrivilegeIds.ACCOUNTS_DELETE)
            .withPrivilegeName("Delete an account")
            .withPrivilegeDescription("Allows deleting an account, " +
                "provided that the user has the right to update the account's parent workspace")
            .build()
    ),

    ACCOUNT_RECORDS_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(WorkspacePrivilegeIds.ACCOUNT_RECORDS_SAVE)
            .withPrivilegeName("Create or update account records")
            .withPrivilegeDescription("Allows adding or modifying account records, " +
                "provided that the user has the right to update the account's parent workspace")
            .build()
    ),

    ACCOUNT_RECORDS_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(WorkspacePrivilegeIds.ACCOUNT_RECORDS_READ)
            .withPrivilegeName("Read account records")
            .withPrivilegeDescription("Allows listing and reading the details of account records, " +
                "provided that the user has the right to update the account's parent workspace")
            .build()
    ),

    ACCOUNT_RECORDS_DELETE(
        AcctPrivilege.builder()
            .withPrivilegeId(WorkspacePrivilegeIds.ACCOUNT_RECORDS_DELETE)
            .withPrivilegeName("Delete account records")
            .withPrivilegeDescription("Allows deleting account records, " +
                "provided that the user has the right to update the account's parent workspace")
            .build()
    ),

    ACCOUNT_RECORDS_TRANSFER(
        AcctPrivilege.builder()
            .withPrivilegeId(WorkspacePrivilegeIds.ACCOUNT_RECORDS_TRANSFER)
            .withPrivilegeName("Transfer amounts between accounts")
            .withPrivilegeDescription("Allows transferring various amounts between different accounts, " +
                "provided that the user has the right to the parent workspace of the aforementioned accounts")
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
