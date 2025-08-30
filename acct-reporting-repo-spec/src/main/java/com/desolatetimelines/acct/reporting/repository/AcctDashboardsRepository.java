package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctDashboard;

import java.util.Collection;
import java.util.Optional;

/**
 * Repository for loading and persisting {@link AcctDashboard dashboards}
 */
public interface AcctDashboardsRepository {

    /**
     * Returns a new instance of {@link AcctDashboard}
     */
    AcctDashboard createNew();

    /**
     * Persists the referenced dashboard
     *
     * @param dashboard the referenced dashboard
     * @return a reference to the persisted dashboard
     */
    AcctDashboard save(AcctDashboard dashboard);

    /**
     * Returns a collection of all the dashboards that are using one of the icons represented by the
     * given list of dashboard icon UUIDs
     *
     * @param dashboardIconUUIDs the given list of dashboard icon UUIDs
     */
    Collection<AcctDashboard> findAllByDashboardIconUUIDIn(Collection<String> dashboardIconUUIDs);

    /**
     * Returns a collection of all the dashboards that are using one of the workspaces represented by the
     * given list of workspace UUIDs
     *
     * @param workspaceUUIDs the given list of workspace UUIDs
     */
    Collection<AcctDashboard> findAllByWorkspaceUUIDIn(Collection<String> workspaceUUIDs);

    /**
     * Retrieves the dashboard with the given dashboard UUID. If no such dashboard exists, then an empty
     * optional is returned.
     *
     * @param dashboardUUID the given dashboard UUID
     */
    Optional<AcctDashboard> findFirstByDashboardUUID(String dashboardUUID);
}
