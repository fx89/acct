package com.desolatetimelines.acct.job.privilegesprovider.service;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;
import com.desolatetimelines.acct.privilegesprovider.service.AcctServicePrivilegesProvider;

import java.util.Set;

import static com.desolatetimelines.acct.job.privilegesprovider.model.JobPrivilege.JOBS_REGISTER;

/**
 * {@link AcctServicePrivilegesProvider privileges provider} for the Jobs Registry service
 */
public class AcctJobServicePrivilegesProvider implements AcctServicePrivilegesProvider {

    @Override
    public Set<AcctPrivilege> getPrivileges() {
        return Set.of(
            JOBS_REGISTER.getAcctPrivilege()
        );
    }

}
