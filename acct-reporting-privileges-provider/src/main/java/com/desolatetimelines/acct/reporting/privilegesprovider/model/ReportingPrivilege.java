package com.desolatetimelines.acct.reporting.privilegesprovider.model;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;

/**
 * Defines the privileges required by the Reporting service
 */
public enum ReportingPrivilege {

    DASHBOARDS_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(ReportingPrivilegeIds.DASHBOARDS_SAVE)
            .withPrivilegeName("Create or update a dashboard")
            .withPrivilegeDescription("Allows creating new dashboards or updating dashboards")
            .build()
    ),

    DASHBOARDS_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(ReportingPrivilegeIds.DASHBOARDS_READ)
            .withPrivilegeName("Reads user-accessible dashboards")
            .withPrivilegeDescription("Allows reading the properties of existing dashboards that are accessible to the user")
            .build()
    );


    private final AcctPrivilege acctPrivilege;

    ReportingPrivilege(AcctPrivilege acctPrivilege) {
        this.acctPrivilege = acctPrivilege;
    }

    public AcctPrivilege getAcctPrivilege() {
        return acctPrivilege;
    }

}
