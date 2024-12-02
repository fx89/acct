package com.desolatetimelines.acct.security.ws.endpoint.model;

/**
 * Defines a dashboard ownership record
 *
 * @param ownerType     the type of owner
 * @param ownerUUID     the UUID of the owner
 * @param dashboardUUID the UUID of the dashboard
 */
public record DashboardOwner(
    OwnerType ownerType,
    String ownerUUID,
    String dashboardUUID
) {

}
