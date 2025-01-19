package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.workspace.model.AcctWorkspace;

import java.util.Collection;
import java.util.Optional;

/**
 * Repository for loading and persisting {@link AcctWorkspace workspaces}
 */
public interface AcctWorkspacesRepository {

    /**
     * Returns a new instance of {@link AcctWorkspace}
     */
    AcctWorkspace createNew();

    /**
     * Saves the referenced {@link AcctWorkspace workspace}
     *
     * @param workspace the referenced workspace
     * @return a reference to the saved entity
     */
    AcctWorkspace save(AcctWorkspace workspace);

    /**
     * Returns a reference to the {@link AcctWorkspace workspace} entity with the given
     * workspace UUID or an empty optional if such an entity does not exist
     *
     * @param workspaceUUID the given workspace UUID
     */
    Optional<AcctWorkspace> findFirstByWorkspaceUUID(String workspaceUUID);

    /**
     * Retrieves a collection of {@link AcctWorkspace workspaces} for the UUIDs
     * in the given collection of workspace UUIDs
     *
     * @param workspaceUUIDs the given collection of workspace UUIDs
     */
    Collection<AcctWorkspace> findAllByWorkspaceUUIDIn(Collection<String> workspaceUUIDs);

    /**
     * Returns a collection of {@link AcctWorkspace workspaces} for which the
     * {@link AcctWorkspace#getWorkspaceIconUUID() workspace icon UUID} is one
     * of the UUIDs in the given collection. In case there's no match, an empty
     * collection is returned.
     *
     * @param workspaceIconUUIDs the given collection
     */
    Collection<AcctWorkspace> findAllByWorkspaceIconUUIDIn(Collection<String> workspaceIconUUIDs);

    /**
     * Returns a collection of {@link AcctWorkspace workspaces} for which the
     * {@link AcctWorkspace#getDefaultCurrencyUUID() default currenct UUID} is
     * one of the UUIDs in the given collection. In case there's no match, an
     * empty collection is returned.
     *
     * @param currencyUUIDs the given collection
     */
    Collection<AcctWorkspace> findAllByDefaultCurrencyUUIDIn(Collection<String> currencyUUIDs);

    /**
     * Deletes the referenced {@link AcctWorkspace workspace}
     */
    void delete(AcctWorkspace workspace);

}
