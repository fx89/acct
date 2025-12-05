package com.desolatetimelines.acct.reporting.ws.model;

import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderId;
import org.springframework.lang.NonNull;

import java.util.Set;

/**
 * Container for the editable properties of a data provider instance.
 *
 * @param instanceName       The human-readable name of the data provider instance. Identifies the data
 *                           provider instance in the GUI.
 * @param dataProviderUUID   The UUID of the data provider whose instance this is.
 * @param instanceProperties A set of {@link DataProviderInstanceProperty properties with constant values}
 *                           to be given to the data provider instance upon initialization. These are stated
 *                           by the {@link AcctReportingDataProviderId data provider} and serve the purpose
 *                           of configuration variables.
 * @param runtimeParameters  A set of {@link DataProviderInstanceRuntimeParameter runtime parameters} that
 *                           are not defined by the {@link AcctReportingDataProviderId data provider} itself,
 *                           but are required for the custom functionality added through one or more instance
 *                           properties (i.e. a SQL query). If the data provider already defines this runtime
 *                           parameter, then it does not need to exist here. Unlike instance properties, which
 *                           are constant throughout the life span of the data provider instance, the runtime
 *                           parameters change their values with each invocation of the data provider instance.
 *                           The values of the runtime parameters are provided by users via the GUI.
 */
public record DataProviderInstanceProperties(
    @NonNull String instanceName,
    @NonNull String dataProviderUUID,
    @NonNull Set<DataProviderInstanceProperty> instanceProperties,
    @NonNull Set<DataProviderInstanceRuntimeParameter> runtimeParameters
) {
}
