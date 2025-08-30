package com.desolatetimelines.acct.reporting.ws.model;

/**
 * Container for the UUID of a newly created or updated dashboard
 *
 * @param dashboardUUID The contained UUID
 */
public record DashboardUUIDResponse(
    String dashboardUUID
) {
}
