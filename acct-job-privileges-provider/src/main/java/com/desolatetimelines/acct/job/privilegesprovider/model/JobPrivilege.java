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
    );

    private final AcctPrivilege acctPrivilege;

    JobPrivilege(AcctPrivilege acctPrivilege) {
        this.acctPrivilege = acctPrivilege;
    }

    public AcctPrivilege getAcctPrivilege() {
        return acctPrivilege;
    }
}
