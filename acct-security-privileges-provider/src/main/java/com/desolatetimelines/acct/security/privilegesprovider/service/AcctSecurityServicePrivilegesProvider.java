package com.desolatetimelines.acct.security.privilegesprovider.service;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;
import com.desolatetimelines.acct.privilegesprovider.service.AcctServicePrivilegesProvider;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.desolatetimelines.acct.security.privilegesprovider.model.SecurityPrivilege.*;

/**
 * {@link AcctServicePrivilegesProvider privileges provider} for the User Management service
 */
@Service
public class AcctSecurityServicePrivilegesProvider implements AcctServicePrivilegesProvider {
    @Override
    public Set<AcctPrivilege> getPrivileges() {
        return
            Set.of(
                WORKSPACE_OWNERS_READ.getAcctPrivilege(),
                WORKSPACE_OWNERS_SAVE.getAcctPrivilege(),
                WORKSPACE_OWNERS_DELETE.getAcctPrivilege(),
                DASHBOARD_OWNERS_READ.getAcctPrivilege(),
                DASHBOARD_OWNERS_SAVE.getAcctPrivilege(),
                DASHBOARD_OWNERS_DELETE.getAcctPrivilege(),
                REPORT_OWNERS_READ.getAcctPrivilege(),
                REPORT_OWNERS_SAVE.getAcctPrivilege(),
                REPORT_OWNERS_DELETE.getAcctPrivilege(),
                PRIVILEGES_READ.getAcctPrivilege(),
                OWN_PRIVILEGES_READ.getAcctPrivilege(),
                PRIVILEGES_SAVE.getAcctPrivilege(),
                PRIVILEGES_DELETE.getAcctPrivilege()
            );
    }
}
