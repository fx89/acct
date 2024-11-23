package com.desolatetimelines.acct.usermanagement.privilegesprovider.service;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;
import com.desolatetimelines.acct.privilegesprovider.service.AcctServicePrivilegesProvider;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.desolatetimelines.acct.usermanagement.privilegesprovider.model.UserManagementPrivilege.*;

/**
 * {@link AcctServicePrivilegesProvider privileges provider} for the User Management service
 */
@Service
public class AcctUserManagementServicePrivilegesProvider implements AcctServicePrivilegesProvider {
    @Override
    public Set<AcctPrivilege> getPrivileges() {
        return
            Set.of(
                USERS_READ_CURRENT.getAcctPrivilege(),
                USERS_READ.getAcctPrivilege(),
                USERS_SAVE_CURRENT.getAcctPrivilege(),
                USERS_SAVE.getAcctPrivilege(),
                USERS_SOFT_DELETE.getAcctPrivilege(),
                USERS_UNDELETE.getAcctPrivilege(),
                USERS_RESET_PASSWORD.getAcctPrivilege(),
                GROUPS_READ_OWN.getAcctPrivilege(),
                GROUPS_READ.getAcctPrivilege(),
                GROUPS_SAVE.getAcctPrivilege(),
                GROUPS_DELETE.getAcctPrivilege()
            );
    }
}
