package com.desolatetimelines.acct.reporting.ws.endpoint;

import com.desolatetimelines.acct.reporting.ws.model.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Map;
import java.util.Set;

/**
 * Specification for the Data Provider Instances endpoint, which allows creating, editing,
 * listing, getting the details of and deleting data provider instances.
 */
public interface DataProviderInstancesEndpoint {

    /**
     * Creates or updates a {@link DataProviderInstanceProperties data provider instance}
     *
     * @param dataProviderInstanceUUID       Optional UUID of the data provider instance to be updated,
     *                                       If not provided, then a new data provider instance is created
     *                                       with a new UUID.
     * @param dataProviderInstanceProperties Container for the editable properties of the data provider
     *                                       instance to be created or updated.
     * @return a container for the UUID of the data provider instance that was created or updated. In case
     * the data provider instance was created, the UUID of the new data provider instance is contained here.
     * If the data provider instance was updated, then the UUID given as parameter to this method is presented.
     */
    DataProviderInstanceUUIDResponse saveDataProviderInstance(
        @Nullable String dataProviderInstanceUUID,
        DataProviderInstanceProperties dataProviderInstanceProperties
    );

    /**
     * Returns a set of {@link DataProviderInstanceInfo data provider instances} to be displayed to the user.
     */
    Set<DataProviderInstanceInfo> getDataProviderInstances();

    /**
     * Returns a container with the {@link DataProviderInstanceProperties details} of the data provider
     * instance identified by the given data provider instance UUID.
     *
     * @param dataProviderInstanceUUID Unique identifier to the data provider instance whose details are
     *                                 being retrieved.
     */
    DataProviderInstanceProperties getDataProviderInstanceDetails(
        @NonNull String dataProviderInstanceUUID
    );

    /**
     * Returns a set of {@link DataProviderInstanceRuntimeParameter descriptors} for the runtime parameters
     * defined by the data provider instance and the data provider pointed to by the data provider instance.
     *
     * @param dataProviderInstanceUUID Unique identifier for the data provider instance whose runtime
     *                                 parameters are being fetched.
     */
    Set<DataProviderInstanceRuntimeParameter> getDataProviderInstanceRuntimeParameters(
        String dataProviderInstanceUUID
    );

    /**
     * Returns the data set produced by the referenced data provider instance using the default
     * values of the runtime parameters.
     *
     * @param dataProviderInstanceUUID Unique identifier for the referenced data provider instance.
     * @param runtimeParameters        Key/Value map containing the runtime parameters for the data
     *                                 set compilation.
     */
    AcctReportingDataSet getDataProviderInstanceDataSet(
        @NonNull String dataProviderInstanceUUID,
        @NonNull Map<String, String> runtimeParameters
    );

}
