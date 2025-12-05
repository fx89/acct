package com.desolatetimelines.acct.reporting.ws.model;

import com.desolatetimelines.acct.reporting.ws.endpoint.DataProviderInstancesEndpoint;

/**
 * Contains the UUID of a {@link DataProviderInstanceProperties data provider instance} that has been created or updated by the
 * {@link DataProviderInstancesEndpoint#saveDataProviderInstance(String, DataProviderInstanceProperties) data provider instances endpoint}
 *
 * @param dataProviderInstanceUUID The UUID of the created or updated data provider instance
 */
public record DataProviderInstanceUUIDResponse(
    String dataProviderInstanceUUID
) {

}
