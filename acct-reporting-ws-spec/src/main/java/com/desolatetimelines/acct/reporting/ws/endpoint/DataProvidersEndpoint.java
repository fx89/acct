package com.desolatetimelines.acct.reporting.ws.endpoint;

import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderId;

import java.util.Set;

/**
 * Specification for the data providers endpoint, which provides information on the registered
 * data providers, which can be used for generating reports.
 */
public interface DataProvidersEndpoint {

    /**
     * Retrieves a set of {@link AcctReportingDataProviderId reporting data provider meta-data}
     * items that describe the registered data providers, their instance properties and their
     * runtime parameters.
     */
    Set<AcctReportingDataProviderId> getDataProviders();

}
