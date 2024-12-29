package com.desolatetimelines.acct.workspace.privilegesprovider.service;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;
import com.desolatetimelines.acct.privilegesprovider.service.AcctServicePrivilegesProvider;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.desolatetimelines.acct.workspace.privilegesprovider.model.WorkspacePrivilege.WORKSPACES_SAVE;

/**
 * {@link AcctServicePrivilegesProvider privileges provider} for the Workspace service
 */
@Service
public class AcctWorkspaceServicePrivilegesProvider implements AcctServicePrivilegesProvider {
    @Override
    public Set<AcctPrivilege> getPrivileges() {
        return
            Set.of(
                WORKSPACES_SAVE.getAcctPrivilege()
            );
    }
}
