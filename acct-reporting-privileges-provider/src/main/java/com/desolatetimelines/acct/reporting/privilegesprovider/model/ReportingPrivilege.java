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
    ),

    DASHBOARDS_DELETE(
        AcctPrivilege.builder()
            .withPrivilegeId(ReportingPrivilegeIds.DASHBOARDS_DELETE)
            .withPrivilegeName("Deletes user-owned dashboards")
            .withPrivilegeDescription("Allows deletion of existing dashboards that are owned by the user")
            .build()
    ),

    DASHBOARDS_DELETE_GROUP(
        AcctPrivilege.builder()
            .withPrivilegeId(ReportingPrivilegeIds.DASHBOARDS_DELETE)
            .withPrivilegeName("Deletes user-accessible dashboards")
            .withPrivilegeDescription("Allows deletion of existing dashboards that are accessible to the user via a users group")
            .build()
    ),

    DATA_PROVIDERS_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(ReportingPrivilegeIds.DATA_PROVIDERS_READ)
            .withPrivilegeName("Read data provider meta-data")
            .withPrivilegeDescription("Allows reading of the meta-data of reporting data providers")
            .build()
    ),

    DATA_PROVIDER_INSTANCES_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(ReportingPrivilegeIds.DATA_PROVIDER_INSTANCES_SAVE)
            .withPrivilegeName("Save data provider instance definitions")
            .withPrivilegeDescription("Allows creating or updating data provider instance definitions")
            .build()
    ),

    DATA_PROVIDER_INSTANCES_READ(
        AcctPrivilege.builder()
            .withPrivilegeId(ReportingPrivilegeIds.DATA_PROVIDER_INSTANCES_READ)
            .withPrivilegeName("Read data provider instance definitions")
            .withPrivilegeDescription("Allows reading data provider instance definitions")
            .build()
    ),

    DATA_PROVIDER_INSTANCES_DELETE(
        AcctPrivilege.builder()
            .withPrivilegeId(ReportingPrivilegeIds.DATA_PROVIDER_INSTANCES_DELETE)
            .withPrivilegeName("Delete data provider instances")
            .withPrivilegeDescription("Allows deleting data provider instances")
            .build()
    ),

    REPORT_SAVE(
        AcctPrivilege.builder()
            .withPrivilegeId(ReportingPrivilegeIds.REPORT_SAVE)
            .withPrivilegeName("Save reports")
            .withPrivilegeDescription("Allows creating new reports or updating existing reports that are accessible to the current user either directly or via groups")
            .build()
    ),

    REPORT_RUN(
        AcctPrivilege.builder()
            .withPrivilegeId(ReportingPrivilegeIds.REPORT_RUN)
            .withPrivilegeName("Run reports")
            .withPrivilegeDescription("Allows running reports and individual data provider instances and retrieving data sets resulted from these runs")
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
