package com.desolatetimelines.acct.security.ws.endpoint.model;

/**
 * Defines a report ownership record
 *
 * @param ownerType  the type of owner
 * @param ownerUUID  the UUID of the owner
 * @param reportUUID the UUID of the report
 */
public record ReportOwner(
    OwnerType ownerType,
    String ownerUUID,
    String reportUUID
) {

}
