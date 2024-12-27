package com.desolatetimelines.acct.job.privilegesprovider.service;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;
import com.desolatetimelines.acct.privilegesprovider.service.AcctServicePrivilegesProvider;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.desolatetimelines.acct.job.privilegesprovider.model.JobPrivilege.*;

/**
 * {@link AcctServicePrivilegesProvider privileges provider} for the Jobs Registry service
 */
@Service
public class AcctJobServicePrivilegesProvider implements AcctServicePrivilegesProvider {

    @Override
    public Set<AcctPrivilege> getPrivileges() {
        return Set.of(
            JOBS_REGISTER.getAcctPrivilege(),
            JOBS_UPDATE.getAcctPrivilege(),
            JOBS_LIST_ALL.getAcctPrivilege(),
            JOBS_STATES_GET.getAcctPrivilege(),
            JOBS_STATES_SET.getAcctPrivilege(),
            JOBS_STATES_HISTORY_LIST.getAcctPrivilege()
        );
    }

}
