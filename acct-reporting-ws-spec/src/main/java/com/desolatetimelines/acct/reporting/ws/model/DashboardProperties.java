package com.desolatetimelines.acct.reporting.ws.model;

/**
 * Container for the editable properties of a dashboard.
 *
 * @param dashboardName        The unique human-readable name of the dashboard
 * @param dashboardDescription A text that describes the dashboard in more or less detail
 * @param dashboardIconUUID    UUID of the icon that is rendered alongside the dashboard name in the UI
 */
public record DashboardProperties(
    String dashboardName,
    String dashboardDescription,
    String dashboardIconUUID
) {
}
