package com.desolatetimelines.acct.usermanagement.privilegesprovider.model;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;

/**
 * Defines the privileges required by the User Management service
 */
public enum UserManagementPrivilege {
    USERS_READ_CURRENT(
        AcctPrivilege.builder()
            .withPrivilegeId(UserManagementPrivilegeIds.USERS_READ_CURRENT)
            .withPrivilegeName("Read current user properties")
            .withPrivilegeDescription("Allows reading properties of the current user, such as the icon UUID and the default workspace UUID")
            .build()
    ),

    USERS_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(UserManagementPrivilegeIds.USERS_READ)
            .withPrivilegeName("Read any user's properties")
            .withPrivilegeDescription("Allows reading the properties of any registered user")
            .build()
    ),

    USERS_SAVE_CURRENT(
        AcctPrivilege.builder()
            .withPrivilegeId(UserManagementPrivilegeIds.USERS_SAVE_CURRENT)
            .withPrivilegeName("Save current user's properties")
            .withPrivilegeDescription("Allows saving / updating the properties of the current user")
            .build()
    ),

    USERS_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(UserManagementPrivilegeIds.USERS_SAVE)
            .withPrivilegeName("Save any user's properties")
            .withPrivilegeDescription("Allows saving / updating the properties of any registered user, as well as creating new users")
            .build()
    ),

    USERS_SOFT_DELETE(
        AcctPrivilege.builder()
            .withPrivilegeId(UserManagementPrivilegeIds.USERS_SOFT_DELETE)
            .withPrivilegeName("Soft-delete users")
            .withPrivilegeDescription("Allows Marking any registered user as deleted")
            .build()
    ),

    USERS_UNDELETE(
        AcctPrivilege.builder()
            .withPrivilegeId(UserManagementPrivilegeIds.USERS_UNDELETE)
            .withPrivilegeName("Undelete users")
            .withPrivilegeDescription("Allows the clearing of the soft-deleted flag of any registered user")
            .build()
    ),

    USERS_RESET_PASSWORD(
        AcctPrivilege.builder()
            .withPrivilegeId(UserManagementPrivilegeIds.USERS_RESET_PASSWORD)
            .withPrivilegeName("Reset user password")
            .withPrivilegeDescription("Allows resetting the password of any registered user")
            .build()
    ),

    GROUPS_READ_OWN(
        AcctPrivilege.builder()
            .withPrivilegeId(UserManagementPrivilegeIds.GROUPS_READ_OWN)
            .withPrivilegeName("Read own group properties")
            .withPrivilegeDescription("Allows reading the properties of the groups to which the current user is assigned")
            .build()
    ),

    GROUPS_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(UserManagementPrivilegeIds.GROUPS_READ)
            .withPrivilegeName("Read any group's properties")
            .withPrivilegeDescription("Allows reading the properties of any group, including the ones to which the current user is not assigned")
            .build()
    ),

    GROUPS_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(UserManagementPrivilegeIds.GROUPS_SAVE)
            .withPrivilegeName("Save any group's properties")
            .withPrivilegeDescription("Allows updating the properties of existing groups, as well as creating new groups")
            .build()
    ),

    GROUPS_DELETE(
        AcctPrivilege.builder()
            .withPrivilegeId(UserManagementPrivilegeIds.GROUPS_DELETE)
            .withPrivilegeName("Delete any group")
            .withPrivilegeDescription("Allows deleting any registered group, consequently unmapping the group from any existing users")
            .build()
    );

    private final AcctPrivilege acctPrivilege;

    UserManagementPrivilege(AcctPrivilege acctPrivilege) {
        this.acctPrivilege = acctPrivilege;
    }

    public AcctPrivilege getAcctPrivilege() {
        return acctPrivilege;
    }
}
