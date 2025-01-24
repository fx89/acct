package com.desolatetimelines.acct.catalog.ws.model;

/**
 * Container for the UUID of an icon, to use as return type for REST APIs
 *
 * @param iconUUID the UUID of the icon
 */
public record IconUUIDResponse(
    String iconUUID
) {
}
