package com.desolatetimelines.acct.job.privilegesprovider.model;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;

/**
 * Defines the privileges required by the Jobs Registry service
 */
public enum JobPrivilege {
    JOBS_REGISTER(
        AcctPrivilege.builder()
            .withPrivilegeId(JobPrivilegeIds.JOBS_REGISTER)
            .withPrivilegeName("Register a job")
            .withPrivilegeDescription("Allows registering jobs")
            .build()
    ),

    JOBS_LIST_ALL(
        AcctPrivilege.builder()
            .withPrivilegeId(JobPrivilegeIds.JOBS_LIST_ALL)
            .withPrivilegeName("List all jobs")
            .withPrivilegeDescription("Allows listing of al the jobs registered in the jobs registry")
            .build()
    ),

    JOBS_STATES_GET(
        AcctPrivilege.builder()
            .withPrivilegeId(JobPrivilegeIds.JOBS_STATES_GET)
            .withPrivilegeName("Get the state of a job")
            .withPrivilegeDescription("Allows the retrieval of the state of a given job")
            .build()
    ),

    JOBS_STATES_SET(
        AcctPrivilege.builder()
            .withPrivilegeId(JobPrivilegeIds.JOBS_STATES_SET)
            .withPrivilegeName("Set the state of a job")
            .withPrivilegeDescription("Allows setting the state of a given job")
            .build()
    );

    private final AcctPrivilege acctPrivilege;

    JobPrivilege(AcctPrivilege acctPrivilege) {
        this.acctPrivilege = acctPrivilege;
    }

    public AcctPrivilege getAcctPrivilege() {
        return acctPrivilege;
    }
}
