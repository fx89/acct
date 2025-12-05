package com.desolatetimelines.acct.reporting.model;

import org.springframework.lang.NonNull;

import java.util.Set;

public record DataProviderInstanceDetails(
    String name,
    String dataProviderUUID,
    Set<DataProviderInstanceProperty> instanceProperties,
    Set<DataProviderInstanceRuntimeParameter> runtimeParameters
) {

    public record DataProviderInstanceProperty(
        @NonNull String propertyName,
        @NonNull String propertyValue
    ) {
    }

    public record DataProviderInstanceRuntimeParameter(
        @NonNull String parameterName,
        @NonNull String parameterDefaultValue,
        @NonNull DataProviderParameterDataType parameterDataType,
        @NonNull Boolean mandatory
    ) {
    }

    public enum DataProviderParameterDataType {
        STRING,
        NUMERIC,
        DATETIME,
        BOOLEAN
    }

}

