package com.desolatetimelines.acct.reporting.ws.model;

import org.springframework.lang.NonNull;

/**
 * Container for the properties of a data provider instance property. Used for both read and write purposes.
 *
 * @param propertyName  Uniquely identifies the instance property within the context of the data
 *                      provider instance. Must match the name of one of the instance properties
 *                      defined by the {@link com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderId data provider}
 * @param propertyValue The value set by the user for the instance property. Must be parsable into
 *                      the data type defined by the data provider.
 */
public record DataProviderInstanceProperty(
    @NonNull String propertyName,
    @NonNull String propertyValue
) {
}
