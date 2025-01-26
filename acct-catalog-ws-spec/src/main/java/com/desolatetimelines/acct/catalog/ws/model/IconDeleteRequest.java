package com.desolatetimelines.acct.catalog.ws.model;

import java.util.Collection;

/**
 * Body of the icons deletion request in the icons controller
 *
 * @param iconUUIDs a collection of the UUIDs of the icons to be deleted
 */
public record IconDeleteRequest(
    Collection<String> iconUUIDs
) {
}
