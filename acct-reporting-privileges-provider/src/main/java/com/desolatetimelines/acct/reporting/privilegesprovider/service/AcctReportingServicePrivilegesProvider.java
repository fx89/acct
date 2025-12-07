package com.desolatetimelines.acct.reporting.privilegesprovider.service;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;
import com.desolatetimelines.acct.privilegesprovider.service.AcctServicePrivilegesProvider;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.desolatetimelines.acct.reporting.privilegesprovider.model.ReportingPrivilege.*;

/**
 * {@link AcctServicePrivilegesProvider privileges provider} for the Reporting service
 */
@Service
public class AcctReportingServicePrivilegesProvider implements AcctServicePrivilegesProvider {
    @Override
    public Set<AcctPrivilege> getPrivileges() {
        return
            Set.of(
                DASHBOARDS_SAVE.getAcctPrivilege(),
                DASHBOARDS_READ.getAcctPrivilege(),
                DASHBOARDS_DELETE.getAcctPrivilege(),
                DASHBOARDS_DELETE_GROUP.getAcctPrivilege(),
                DATA_PROVIDERS_READ.getAcctPrivilege(),
                DATA_PROVIDER_INSTANCES_SAVE.getAcctPrivilege(),
                DATA_PROVIDER_INSTANCES_READ.getAcctPrivilege()
            );
    }
}
