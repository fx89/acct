package com.desolatetimelines.acct.reporting.data.service;

import com.desolatetimelines.acct.reporting.model.AcctDashboard;
import com.desolatetimelines.acct.reporting.repository.AcctDashboardsRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Facade for the ACCT Workspace data layer
 */
@Service
public class AcctReportingDataService {

    private final AcctDashboardsRepository dashboardsRepository;

    public AcctReportingDataService(
        AcctDashboardsRepository dashboardsRepository
    ) {
        this.dashboardsRepository = dashboardsRepository;
    }

    /**
     * Creates a new {@link AcctDashboard dashboard}
     *
     * @return a reference to the newly created dashboard
     */
    public AcctDashboard createNewDashboard() {
        return dashboardsRepository.createNew();
    }

    /**
     * Persists the referenced dashboard
     *
     * @param dashboard the referenced dashboard
     * @return a reference to the persisted dashboard
     */
    public AcctDashboard saveDashboard(AcctDashboard dashboard) {
        return dashboardsRepository.save(dashboard);
    }

    /**
     * Returns a collection of all the dashboards that are using one of the icons represented by the
     * given list of dashboard icon UUIDs
     *
     * @param dashboardIconUUIDs the given list of dashboard icon UUIDs
     */
    public Collection<AcctDashboard> findDashboardsByDashboardIconUUIDIn(Collection<String> dashboardIconUUIDs) {
        return dashboardsRepository.findAllByDashboardIconUUIDIn(dashboardIconUUIDs);
    }

    /**
     * Returns a collection of all the dashboards that are using one of the workspaces represented by the
     * given list of workspace UUIDs
     *
     * @param workspaceUUIDs the given list of workspace UUIDs
     */
    public Collection<AcctDashboard> findDashboardsByWorkspaceUUIDIn(Collection<String> workspaceUUIDs) {
        return dashboardsRepository.findAllByWorkspaceUUIDIn(workspaceUUIDs);
    }

    /**
     * Returns a collection of all the dashboards that are part of the referenced workspace and are identified
     * by one of the UUIDs from the referenced collection of dashboard UUIDs
     *
     * @param workspaceUUID  the UUID of the referenced workspace
     * @param dashboardUUIDs the referenced collection of dashboard UUIDs
     */
    public Collection<AcctDashboard> findDashboardsByWorkspaceUUIDAndDashboardUUIDIn(
        String workspaceUUID,
        Collection<String> dashboardUUIDs
    ) {
        return dashboardsRepository.findAllByWorkspaceUUIDAndDashboardUUIDIn(workspaceUUID, dashboardUUIDs);
    }

    /**
     * Retrieves the dashboard with the given dashboard UUID. If no such dashboard exists, then an empty
     * optional is returned.
     *
     * @param dashboardUUID the given dashboard UUID
     */
    public Optional<AcctDashboard> findDashboardByDashboardUUID(String dashboardUUID) {
        return dashboardsRepository.findFirstByDashboardUUID(dashboardUUID);
    }

    /**
     * Deletes the referenced dashboard
     *
     * @param dashboard the referenced dashboard
     */
    public void deleteDashboard(AcctDashboard dashboard) {
        dashboardsRepository.delete(dashboard);
    }

}
