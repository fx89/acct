package com.desolatetimelines.acct.usage.ws.endpoint;

import com.desolatetimelines.acct.usage.ws.model.ServiceItemTypesList;

/**
 * Allows services to register the types of items that they might use from other services.
 */
public interface UsageEndpoint {

    /**
     * Replaces the in-use item types list for a given service wirth the newly provided list
     *
     * @param serviceItemTypesList Container for the service name and item types list
     */
    void registerItemTypesForService(ServiceItemTypesList serviceItemTypesList);

}
