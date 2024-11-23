package com.desolatetimelines.acct.privilegesprovider.service;

import com.desolatetimelines.acct.privilegesprovider.model.AcctGlobalPrivilege;
import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Provides privileges that are used by multiple modules
 */
@Service
public class AcctGlobalServicePrivilegesProvider implements AcctServicePrivilegesProvider {
    @Override
    public Set<AcctPrivilege> getPrivileges() {
        return
            Set.of(
                AcctGlobalPrivilege.ADMIN_OPERATIONS.getAcctPrivilege()
            );
    }
}
