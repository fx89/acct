package com.desolatetimelines.acct.security.privilegesprovider.model;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;

/**
 * Defines the privileges required by the Security service
 */
public enum SecurityPrivilege {

    WORKSPACE_OWNERS_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(SecurityPrivilegeIds.WORKSPACE_OWNERS_READ)
            .withPrivilegeName("")
            .withPrivilegeDescription("")
            .build()
    ),
    WORKSPACE_OWNERS_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(SecurityPrivilegeIds.WORKSPACE_OWNERS_SAVE)
            .withPrivilegeName("")
            .withPrivilegeDescription("")
            .build()
    ),
    WORKSPACE_OWNERS_DELETE(
        AcctPrivilege.builder()
            .withPrivilegeId(SecurityPrivilegeIds.WORKSPACE_OWNERS_DELETE)
            .withPrivilegeName("")
            .withPrivilegeDescription("")
            .build()
    ),
    DASHBOARD_OWNERS_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(SecurityPrivilegeIds.DASHBOARD_OWNERS_READ)
            .withPrivilegeName("")
            .withPrivilegeDescription("")
            .build()
    ),
    DASHBOARD_OWNERS_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(SecurityPrivilegeIds.DASHBOARD_OWNERS_SAVE)
            .withPrivilegeName("")
            .withPrivilegeDescription("")
            .build()
    ),
    DASHBOARD_OWNERS_DELETE(
        AcctPrivilege.builder()
            .withPrivilegeId(SecurityPrivilegeIds.DASHBOARD_OWNERS_DELETE)
            .withPrivilegeName("")
            .withPrivilegeDescription("")
            .build()
    ),
    REPORT_OWNERS_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(SecurityPrivilegeIds.REPORT_OWNERS_READ)
            .withPrivilegeName("")
            .withPrivilegeDescription("")
            .build()
    ),
    REPORT_OWNERS_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(SecurityPrivilegeIds.REPORT_OWNERS_SAVE)
            .withPrivilegeName("")
            .withPrivilegeDescription("")
            .build()
    ),
    REPORT_OWNERS_DELETE(
        AcctPrivilege.builder()
            .withPrivilegeId(SecurityPrivilegeIds.REPORT_OWNERS_DELETE)
            .withPrivilegeName("")
            .withPrivilegeDescription("")
            .build()
    ),
    PRIVILEGES_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(SecurityPrivilegeIds.PRIVILEGES_READ)
            .withPrivilegeName("")
            .withPrivilegeDescription("")
            .build()
    ),
    OWN_PRIVILEGES_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(SecurityPrivilegeIds.OWN_PRIVILEGES_READ)
            .withPrivilegeName("")
            .withPrivilegeDescription("")
            .build()
    ),
    PRIVILEGES_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(SecurityPrivilegeIds.PRIVILEGES_SAVE)
            .withPrivilegeName("")
            .withPrivilegeDescription("")
            .build()
    ),
    PRIVILEGES_DELETE(
        AcctPrivilege.builder()
            .withPrivilegeId(SecurityPrivilegeIds.PRIVILEGES_DELETE)
            .withPrivilegeName("")
            .withPrivilegeDescription("")
            .build()
    );

    private final AcctPrivilege acctPrivilege;

    SecurityPrivilege(AcctPrivilege acctPrivilege) {
        this.acctPrivilege = acctPrivilege;
    }

    public AcctPrivilege getAcctPrivilege() {
        return acctPrivilege;
    }
}
