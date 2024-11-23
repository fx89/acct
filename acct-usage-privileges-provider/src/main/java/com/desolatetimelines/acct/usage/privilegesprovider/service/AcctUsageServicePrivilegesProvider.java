package com.desolatetimelines.acct.usage.privilegesprovider.service;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;
import com.desolatetimelines.acct.privilegesprovider.service.AcctServicePrivilegesProvider;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

/**
 * {@link AcctServicePrivilegesProvider privileges provider} for the User Management service
 */
@Service
public class AcctUsageServicePrivilegesProvider implements AcctServicePrivilegesProvider {
    @Override
    public Set<AcctPrivilege> getPrivileges() {
        return Collections.emptySet(); // No privileges required for the ACCT Usage service yet (other than the generic ones)
    }
}
