package com.desolatetimelines.acct.usage.ws.redist.service;

import java.util.Collection;

/**
 * ACCT services should implement this interface to provide usage information via the redistributable
 * {@link com.desolatetimelines.acct.usage.ws.redist.controller.InUseEndpointController in-use endpoint controller}.
 * This controller is active out of the box for each project that imports the Usage WS redistributable module.
 */
public interface InUseItemsService {

    /**
     * Returns a collection of the UUIDs of the items of the given object type that are currently
     * in use by the service and that are identified by the UUIDs in the given item UUIDs list
     *
     * @param objectType the given object type
     * @param itemUUIDs  the given item UUIDs list
     */
    Collection<String> getInUseItemUUIDs(String objectType, Collection<String> itemUUIDs);

}
