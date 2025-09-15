package com.desolatetimelines.acct.reporting.ws.endpoint;

import com.desolatetimelines.acct.reporting.ws.model.DashboardProperties;
import com.desolatetimelines.acct.reporting.ws.model.DashboardUUIDResponse;
import com.desolatetimelines.acct.reporting.ws.model.UserAccessibleDashboardsContainer;

/**
 * Specification for the Dashboards endpoint, which provides an API that lets consumers define, edit,
 * delete and list the structure of user and group dashboards registered in the ACCT ecosystem.
 */
public interface DashboardsEndpoint {

    /**
     * Creates or updates a dashboard. The decision to create a new dashboard or edit an existing one
     * is based on the existence of the {@code dashboardUUID} parameter.
     *
     * @param workspaceUUID       UUID that identifies the workspace that contains the dashboard
     * @param dashboardUUID       UUID that uniquely identifies the dashboard within the ACCT ecosystem
     * @param dashboardProperties Container for the modifiable properties of a dashboard
     * @return A container for the UUID of the newly created or edited dashboard
     */
    DashboardUUIDResponse saveDashboard(
        String workspaceUUID,
        String dashboardUUID,
        DashboardProperties dashboardProperties
    );

    /**
     * Retrieves a set of dashboards that are accessible to the user and groups them by access method
     *
     * @param workspaceUUID The UUID of the workspace where the dashboards can be found
     * @return a container that groups user-accessible dashboards by access method into user dashboards and group dashboards
     */
    UserAccessibleDashboardsContainer getUserAccessibleDashboards(String workspaceUUID);

    /**
     * Deletes the referenced dashboard from the referenced workspace
     *
     * @param workspaceUUID Unique identifier of the referenced workspace
     * @param dashboardUUID Unique identifier of the referenced dashboard
     */
    void deleteDashboard(String workspaceUUID, String dashboardUUID);

}
