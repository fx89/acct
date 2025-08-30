package com.desolatetimelines.acct.reporting.ws.endpoint;

import com.desolatetimelines.acct.reporting.ws.model.DashboardProperties;
import com.desolatetimelines.acct.reporting.ws.model.DashboardUUIDResponse;

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

}
