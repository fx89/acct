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
    );

    private final AcctPrivilege acctPrivilege;

    JobPrivilege(AcctPrivilege acctPrivilege) {
        this.acctPrivilege = acctPrivilege;
    }

    public AcctPrivilege getAcctPrivilege() {
        return acctPrivilege;
    }
}
