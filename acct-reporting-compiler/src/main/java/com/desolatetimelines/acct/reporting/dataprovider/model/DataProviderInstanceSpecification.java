package com.desolatetimelines.acct.reporting.dataprovider.model;

import com.desolatetimelines.acct.reporting.dataprovider.service.AcctReportingDataCompiler;
import com.desolatetimelines.acct.reporting.dataprovider.service.AcctReportingDataProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNull;
import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNullOrEmpty;

/**
 * This is used in the {@link ReportCompilationRequest report compilation request} to identify one of the
 * {@link AcctReportingDataProvider data providers}
 * that are employed for provisioning data to the
 * {@link AcctReportingDataCompiler report compiler}.
 * The report compiler creates a new instance of the referenced data provider and uses the given instance name
 * for identifying the instance throughout the various report compilation stages. When the new data provider
 * instance is created, it is also initialized with the properties that are part of this object.
 *
 * @param instanceName                   The name used for identifying the instance across the various
 *                                       compilation stages. Needs to be unique within the context of the
 *                                       report.
 * @param dataProviderUUID               Uniquely identifies the data provider. Used for referencing the data
 *                                       provider.
 * @param dataProviderInstanceProperties The properties to be put into the data provider instance when it is
 *                                       initialized.
 */
public record DataProviderInstanceSpecification(
    String instanceName,
    UUID dataProviderUUID,
    Map<String, String> dataProviderInstanceProperties
) {
    public static DataProviderInstanceSpecificationBuilder builder() {
        return new DataProviderInstanceSpecificationBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DataProviderInstanceSpecification that = (DataProviderInstanceSpecification) o;
        return Objects.equals(instanceName, that.instanceName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(instanceName);
    }

    public static void validate(DataProviderInstanceSpecification dataProviderInstance) {
        throwIfNull(dataProviderInstance, () -> new IllegalArgumentException("Null DataProviderInstanceSpecification reference provided"));
        throwIfNull(dataProviderInstance.instanceName(), () -> new IllegalArgumentException("Missing instance name"));
        throwIfNull(dataProviderInstance.dataProviderUUID(), () -> new IllegalArgumentException("Missing data provider UUID"));
        throwIfNull(dataProviderInstance.dataProviderInstanceProperties(), () -> new IllegalArgumentException("Missing data provider instance properties"));
    }

    /**
     * Builder for the {@link DataProviderInstanceSpecification} type.
     */
    public static final class DataProviderInstanceSpecificationBuilder {
        private String instanceName;
        private UUID dataProviderUUID;
        private final Map<String, String> dataProviderInstanceProperties = new HashMap<>();

        /**
         * Sets the {@link DataProviderInstanceSpecification#instanceName() instance name}.
         */
        public DataProviderInstanceSpecificationBuilder withInstanceName(String instanceName) {
            this.instanceName = instanceName;
            return this;
        }

        /**
         * Sets the {@link DataProviderInstanceSpecification#dataProviderUUID() data provider UUID}.
         */
        public DataProviderInstanceSpecificationBuilder withDataProviderUUID(UUID dataProviderUUID) {
            this.dataProviderUUID = dataProviderUUID;
            return this;
        }

        public DataProviderInstanceSpecificationBuilder withDataProviderInstanceProperty(
            String propertyName,
            String propertyValue
        ) {
            this.dataProviderInstanceProperties.put(propertyName, propertyValue);
            return this;
        }

        public DataProviderInstanceSpecificationBuilder withDataProviderInstanceProperties(
            Map<String, String> dataProviderInstanceProperties
        ) {
            throwIfNullOrEmpty(
                dataProviderInstanceProperties,
                () -> new IllegalArgumentException("dataProviderInstanceProperties not provided")
            );

            this.dataProviderInstanceProperties.putAll(dataProviderInstanceProperties);
            return this;
        }

        /**
         * Creates a new {@link DataProviderInstanceSpecification} instance, runs the validation and
         * returns a reference to the newly created instance.
         */
        public DataProviderInstanceSpecification build() {
            final DataProviderInstanceSpecification dataProviderInstanceSpecification =
                new DataProviderInstanceSpecification(
                    instanceName,
                    dataProviderUUID,
                    dataProviderInstanceProperties
                );

            DataProviderInstanceSpecification.validate(dataProviderInstanceSpecification);

            return dataProviderInstanceSpecification;
        }
    }
}
