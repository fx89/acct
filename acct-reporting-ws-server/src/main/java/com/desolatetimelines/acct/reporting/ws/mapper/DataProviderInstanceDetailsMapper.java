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

    public static DataProviderInstanceProperties toDataProviderInstanceProperties(
        DataProviderInstanceDetails dataProviderInstanceDetails
    ) {
        return
            DataProviderInstanceProperties.builder()
                .withInstanceName(dataProviderInstanceDetails.name())
                .withDataProviderUUID(dataProviderInstanceDetails.dataProviderUUID())
                .withInstanceProperties(
                    mapToDataProviderInstanceProperties(
                        dataProviderInstanceDetails.instanceProperties()
                    )
                )
                .withRuntimeParameters(
                    mapToDataProviderInstanceRuntimeParameters(
                        dataProviderInstanceDetails.runtimeParameters()
                    )
                )
                .build();
    }

    private static Set<com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceProperty>
    mapToDataProviderInstanceProperties(
        Set<DataProviderInstanceDetails.DataProviderInstanceProperty> instanceProperties
    ) {
        if (instanceProperties == null) {
            return null;
        }

        return instanceProperties.stream()
            .map(instanceProperty -> new com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceProperty(
                instanceProperty.propertyName(),
                instanceProperty.propertyValue()
            ))
            .collect(toSet());
    }

    private static Set<com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceRuntimeParameter>
    mapToDataProviderInstanceRuntimeParameters(
        Set<DataProviderInstanceDetails.DataProviderInstanceRuntimeParameter> runtimeParameters
    ) {
        if (runtimeParameters == null) {
            return null;
        }

        return runtimeParameters.stream()
            .map(runtimeParameter ->
                com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceRuntimeParameter.builder()
                    .withParameterName(runtimeParameter.parameterName())
                    .withParameterDefaultValue(runtimeParameter.parameterDefaultValue())
                    .withParameterDataType(
                        toDataWsProviderParameterDataType(
                            runtimeParameter.parameterDataType()
                        )
                    )
                    .withMandatory(runtimeParameter.mandatory())
                    .build()
            )
            .collect(toSet());
    }

    private static com.desolatetimelines.acct.reporting.ws.model.DataProviderParameterDataType
    toDataWsProviderParameterDataType(
        DataProviderInstanceDetails.DataProviderParameterDataType dataProviderParameterDataType
    ) {
        if (dataProviderParameterDataType == null) {
            return null;
        }

        return
            switch (dataProviderParameterDataType) {
                case NUMERIC -> com.desolatetimelines.acct.reporting.ws.model.DataProviderParameterDataType.NUMERIC;
                case BOOLEAN -> com.desolatetimelines.acct.reporting.ws.model.DataProviderParameterDataType.BOOLEAN;
                case STRING -> com.desolatetimelines.acct.reporting.ws.model.DataProviderParameterDataType.STRING;
                case DATETIME -> com.desolatetimelines.acct.reporting.ws.model.DataProviderParameterDataType.DATETIME;
            };
    }

    private static Set<DataProviderInstanceDetails.DataProviderInstanceProperty> mapProperties(
        Set<com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceProperty> instanceProperties
    ) {
        if (instanceProperties == null) {
            return null;
        }

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
