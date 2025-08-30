package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctDashboard;

import java.util.Collection;

/**
 * Repository for loading and persisting {@link AcctDashboard dashboards}
 */
public interface AcctDashboardsRepository {

    /**
     * Returns a new instance of {@link AcctDashboard}
     */
    AcctDashboard createNew();

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

}
