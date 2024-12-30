package com.desolatetimelines.acct.workspace.privilegesprovider.service;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;
import com.desolatetimelines.acct.privilegesprovider.service.AcctServicePrivilegesProvider;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.desolatetimelines.acct.workspace.privilegesprovider.model.WorkspacePrivilege.*;

/**
 * {@link AcctServicePrivilegesProvider privileges provider} for the Workspace service
 */
@Service
public class AcctWorkspaceServicePrivilegesProvider implements AcctServicePrivilegesProvider {
    @Override
    public Set<AcctPrivilege> getPrivileges() {
        return
            Set.of(
                WORKSPACES_SAVE_OWN.getAcctPrivilege(),
                WORKSPACES_SAVE_GROUP.getAcctPrivilege(),
                WORKSPACES_SAVE_ANY.getAcctPrivilege(),

                WORKSPACES_READ_OWN.getAcctPrivilege(),
                WORKSPACES_READ_GROUP.getAcctPrivilege(),
                WORKSPACES_READ_ANY.getAcctPrivilege(),

                WORKSPACES_DELETE_OWN.getAcctPrivilege(),
                WORKSPACES_DELETE_GROUP.getAcctPrivilege(),
                WORKSPACES_DELETE_ANY.getAcctPrivilege()
            );
    }
}
