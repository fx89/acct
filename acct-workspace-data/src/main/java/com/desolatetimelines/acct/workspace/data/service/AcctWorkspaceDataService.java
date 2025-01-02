package com.desolatetimelines.acct.workspace.data.service;

import com.desolatetimelines.acct.workspace.model.AcctAccount;
import com.desolatetimelines.acct.workspace.model.AcctWorkspace;
import com.desolatetimelines.acct.workspace.repository.AccountsRepository;
import com.desolatetimelines.acct.workspace.repository.AcctWorkspacesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Facade for the ACCT Workspace data layer
 */
@Service
public class AcctWorkspaceDataService {

    private final AcctWorkspacesRepository workspacesRepository;

    private final AccountsRepository accountsRepository;

    public AcctWorkspaceDataService(
        AcctWorkspacesRepository workspacesRepository,
        AccountsRepository accountsRepository
    ) {
        this.workspacesRepository = workspacesRepository;
        this.accountsRepository = accountsRepository;
    }

    /**
     * Creates a new {@link AcctWorkspace workspace} instance
     *
     * @return a reference to the newly created entity
     */
    public AcctWorkspace createNewWorkspace() {
        return workspacesRepository.createNew();
    }

    /**
     * Persists the referenced {@link AcctWorkspace workspace}
     *
     * @param workspace the referenced workspace
     * @return a reference to the persisted entity
     */
    public AcctWorkspace saveWorkspace(AcctWorkspace workspace) {
        return workspacesRepository.save(workspace);
    }

    /**
     * Returns a reference to the {@link AcctWorkspace workspace} entity with the given
     * workspace UUID or an empty optional if such an entity does not exist
     *
     * @param workspaceUUID the given workspace UUID
     */
    public Optional<AcctWorkspace> findWorkspaceByWorkspaceUUID(String workspaceUUID) {
        return workspacesRepository.findFirstByWorkspaceUUID(workspaceUUID);
    }

    /**
     * Retrieves a collection of {@link AcctWorkspace workspaces} for the UUIDs
     * in the given collection of workspace UUIDs
     *
     * @param workspaceUUIDs the given collection of workspace UUIDs
     */
    public Collection<AcctWorkspace> findWorkspacesByWorkspaceUUIDIn(Collection<String> workspaceUUIDs) {
        return workspacesRepository.findAllByWorkspaceUUIDIn(workspaceUUIDs);
    }

    /**
     * Deletes the referenced {@link AcctWorkspace workspace}
     */
    public void deleteWorkspace(AcctWorkspace workspace) {
        workspacesRepository.delete(workspace);
    }

    /**
     * Creates a new {@link AcctAccount account}
     *
     * @return a reference to the newly created entity
     */
    public AcctAccount createNewAccount() {
        return accountsRepository.createNew();
    }

    /**
     * Retrieves the account having the given account UUID or an empty optional
     * if such an account does not exist
     *
     * @param accountUUID the given account UUID
     */
    public Optional<AcctAccount> findAccountByAccountUUID(String accountUUID) {
        return accountsRepository.findFirstByAccountUUID(accountUUID);
    }

    /**
     * Persists the referenced {@link AcctAccount account}
     *
     * @param account the referenced account
     * @return a reference to the persisted entity
     */
    public AcctAccount saveAccount(AcctAccount account) {
        return accountsRepository.saveAccount(account);
    }

    /**
     * Retrieves a collection of {@link AcctAccount accounts} that are
     * contained by the referenced {@link AcctWorkspace workspace}
     *
     * @param workspace the referenced workspace
     */
    public Collection<AcctAccount> findAllAccountsInWorkspace(AcctWorkspace workspace) {
        return accountsRepository.findAllByWorkspace(workspace);
    }

    /**
     * Deletes the referenced {@link AcctAccount account}
     */
    public void deleteAccount(AcctAccount account) {
        accountsRepository.deleteAccount(account);
    }

}
