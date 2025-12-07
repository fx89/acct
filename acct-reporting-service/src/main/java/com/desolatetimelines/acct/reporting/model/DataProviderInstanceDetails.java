package com.desolatetimelines.acct.reporting.model;

import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNull;
import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNullOrEmpty;

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
        public static DataProviderInstancePropertyBuilder builder() {
            return new DataProviderInstancePropertyBuilder();
        }

        public static class DataProviderInstancePropertyBuilder {
            private String propertyName;
            private String propertyValue;

            public DataProviderInstancePropertyBuilder withPropertyName(String propertyName) {
                this.propertyName = propertyName;
                return this;
            }

            public DataProviderInstancePropertyBuilder withPropertyValue(String propertyValue) {
                this.propertyValue = propertyValue;
                return this;
            }

            public DataProviderInstanceProperty build() {
                throwIfNullOrEmpty(propertyName, () -> new IllegalArgumentException("Property name not provided"));
                throwIfNullOrEmpty(propertyValue, () -> new IllegalArgumentException("Property value not provided"));

                return
                    new DataProviderInstanceProperty(
                        propertyName,
                        propertyValue
                    );
            }
        }
    }

    public record DataProviderInstanceRuntimeParameter(
        @NonNull String parameterName,
        @NonNull String parameterDefaultValue,
        @NonNull DataProviderParameterDataType parameterDataType,
        @NonNull Boolean mandatory
    ) {
        public static DataProviderInstanceRuntimeParameterBuilder builder() {
            return new DataProviderInstanceRuntimeParameterBuilder();
        }

        public static class DataProviderInstanceRuntimeParameterBuilder {
            private String parameterName;
            private String parameterDefaultValue;
            private DataProviderParameterDataType parameterDataType;
            private Boolean mandatory;

            public DataProviderInstanceRuntimeParameterBuilder withParameterName(String parameterName) {
                this.parameterName = parameterName;
                return this;
            }

            public DataProviderInstanceRuntimeParameterBuilder withParameterDefaultValue(
                String parameterDefaultValue
            ) {
                this.parameterDefaultValue = parameterDefaultValue;
                return this;
            }

            public DataProviderInstanceRuntimeParameterBuilder withParameterDataType(
                DataProviderParameterDataType parameterDataType
            ) {
                this.parameterDataType = parameterDataType;
                return this;
            }

            public DataProviderInstanceRuntimeParameterBuilder withMandatory(
                Boolean mandatory
            ) {
                this.mandatory = mandatory;
                return this;
            }

            public DataProviderInstanceRuntimeParameter build() {
                throwIfNullOrEmpty(parameterName, () -> new IllegalArgumentException("Parameter name not provided"));
                throwIfNullOrEmpty(parameterDefaultValue, () -> new IllegalArgumentException("Parameter default value not provided"));
                throwIfNull(parameterDataType, () -> new IllegalArgumentException("Parameter data type not provided"));
                throwIfNull(mandatory, () -> new IllegalArgumentException("Parameter mandatory flag not provided"));

                return
                    new DataProviderInstanceRuntimeParameter(
                        parameterName,
                        parameterDefaultValue,
                        parameterDataType,
                        mandatory
                    );
            }
        }
    }

    public enum DataProviderParameterDataType {
        STRING,
        NUMERIC,
        DATETIME,
        BOOLEAN
    }

    public static DataProviderInstanceDetailsBuilder builder() {
        return new DataProviderInstanceDetailsBuilder();
    }

    public static class DataProviderInstanceDetailsBuilder {
        private String name;
        private String dataProviderUUID;
        private final Set<DataProviderInstanceProperty> instanceProperties = new HashSet<>();
        private final Set<DataProviderInstanceRuntimeParameter> runtimeParameters = new HashSet<>();

        public DataProviderInstanceDetailsBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DataProviderInstanceDetailsBuilder withDataProviderUUID(String dataProviderUUID) {
            this.dataProviderUUID = dataProviderUUID;
            return this;
        }

        public DataProviderInstanceDetailsBuilder withInstanceProperty(
            DataProviderInstanceProperty instanceProperty
        ) {
            this.instanceProperties.add(instanceProperty);
            return this;
        }

        public DataProviderInstanceDetailsBuilder withInstanceProperties(
            Set<DataProviderInstanceProperty> instanceProperties
        ) {
            if (instanceProperties != null) {
                this.instanceProperties.addAll(instanceProperties);
            }

            return this;
        }

        public DataProviderInstanceDetailsBuilder withRuntimeParameter(
            DataProviderInstanceRuntimeParameter runtimeParameter
        ) {
            this.runtimeParameters.add(runtimeParameter);
            return this;
        }

        public DataProviderInstanceDetailsBuilder withRuntimeParameters(
            Set<DataProviderInstanceRuntimeParameter> runtimeParameters
        ) {
            if (runtimeParameters != null) {
                this.runtimeParameters.addAll(runtimeParameters);
            }

            return this;
        }

        public DataProviderInstanceDetails build() {
            throwIfNullOrEmpty(name, () -> new IllegalArgumentException("The name is missing"));
            throwIfNullOrEmpty(dataProviderUUID, () -> new IllegalArgumentException("The data provider UUID is missing"));

            return
                new DataProviderInstanceDetails(
                    name,
                    dataProviderUUID,
                    instanceProperties,
                    runtimeParameters
                );
        }
    }

}

