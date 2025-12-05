package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.model.DataProviderInstanceDetails;
import com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceProperties;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Provides mapping methods for the {@link DataProviderInstanceDetails} type
 */
public abstract class DataProviderInstanceDetailsMapper {

    /**
     * Converts the referenced
     * {@link DataProviderInstanceProperties presentation layer data provider instance properties holder}
     * into a new {@link DataProviderInstanceDetails services layer data provider instance properties holder}
     */
    public static DataProviderInstanceDetails fromDataProviderInstanceProperties(
        DataProviderInstanceProperties dataProviderInstanceProperties
    ) {
        return new DataProviderInstanceDetails(
            dataProviderInstanceProperties.instanceName(),
            dataProviderInstanceProperties.dataProviderUUID(),
            mapProperties(dataProviderInstanceProperties.instanceProperties()),
            mapRuntimeParameters(dataProviderInstanceProperties.runtimeParameters())
        );
    }

    private static Set<DataProviderInstanceDetails.DataProviderInstanceProperty> mapProperties(
        Set<com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceProperty> instanceProperties
    ) {
        return instanceProperties.stream()
            .map(prop -> new DataProviderInstanceDetails.DataProviderInstanceProperty(
                prop.propertyName(),
                prop.propertyValue()))
            .collect(toSet());
    }

    private static Set<DataProviderInstanceDetails.DataProviderInstanceRuntimeParameter> mapRuntimeParameters(
        Set<com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceRuntimeParameter> runtimeParameters
    ) {
        return runtimeParameters.stream()
            .map(param -> new DataProviderInstanceDetails.DataProviderInstanceRuntimeParameter(
                param.parameterName(),
                param.parameterDefaultValue(),
                DataProviderInstanceDetails.DataProviderParameterDataType.valueOf(param.parameterDataType().name()),
                param.mandatory()))
            .collect(toSet());
    }

}
