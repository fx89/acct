package com.desolatetimelines.acct.workspace.data.service;

import com.desolatetimelines.acct.workspace.model.AcctWorkspace;
import com.desolatetimelines.acct.workspace.repository.AcctWorkspacesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Facade for the ACCT Workspace data layer
 */
@Service
public class AcctWorkspaceDataService {

    private final AcctWorkspacesRepository workspacesRepository;

    public AcctWorkspaceDataService(AcctWorkspacesRepository workspacesRepository) {
        this.workspacesRepository = workspacesRepository;
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
     * Deletes the referenced {@link AcctWorkspace workspace}
     */
    public void deleteWorkspace(AcctWorkspace workspace) {
        workspacesRepository.delete(workspace);
    }


}
