package com.desolatetimelines.acct.usage.ws.endpoint;

import java.util.Collection;

/**
 * Allows services to respond to queries from other services about items that are currently in use
 */
public interface InUseEndpoint {

    /**
     * Returns a list of UUIDs of the items of the given object type that are in use,
     * out of the given list of UUIDs
     *
     * @param objectType    the given object types
     * @param itemUUIDsList the given list of UUIDs
     */
    Collection<String> getItemsInUseOfType(String objectType, Collection<String> itemUUIDsList);

}
