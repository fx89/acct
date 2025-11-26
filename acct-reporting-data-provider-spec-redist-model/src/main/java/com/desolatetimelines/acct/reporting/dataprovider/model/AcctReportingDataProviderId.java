package com.desolatetimelines.acct.reporting.dataprovider.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNull;

/**
 * Provides data provider identity information required by both users and internal ACCT processes
 *
 * @param uuid               The unique identifier of this data provider, used for referencing the data provider from
 *                           within the context of one or more reports
 * @param humanReadableName  Preferably, but not necessarily, unique human-readable name to identify the data provider
 *                           in the UI
 * @param description        Detailed description that allows users to better understand what kind of data the data
 *                           provider provisions
 * @param instanceProperties A set of properties that assigned to instances of the data provider at build time
 * @param parameters         A set of the parameters that the data provider uses when compiling the report data
 */
public record AcctReportingDataProviderId(
    UUID uuid,
    String humanReadableName,
    String description,
    Set<AcctReportingDataProviderInstancePropertySpec> instanceProperties,
    Set<AcctReportingDataProviderReportParameterSpec> parameters
) {

    public static AcctReportingDataProviderIdBuilder builder() {
        return new AcctReportingDataProviderIdBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AcctReportingDataProviderId that = (AcctReportingDataProviderId) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }


    /**
     * Builder for the {@link AcctReportingDataProviderId} type
     */
    public static final class AcctReportingDataProviderIdBuilder {
        private UUID uuid;
        private String humanReadableName;
        private String description;
        private final Set<AcctReportingDataProviderInstancePropertySpec> instanceProperties = new HashSet<>();
        private final Set<AcctReportingDataProviderReportParameterSpec> parameters = new HashSet<>();

        private AcctReportingDataProviderIdBuilder() {
        }

        /**
         * Sets the unique identifier of this data provider, used for referencing the data provider
         * from within the context of one or more reports
         */
        public AcctReportingDataProviderIdBuilder withUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        /**
         * Sets the preferably, but not necessarily, unique human-readable name to identify the
         * data provider in the UI
         */
        public AcctReportingDataProviderIdBuilder withHumanReadableName(String humanReadableName) {
            this.humanReadableName = humanReadableName;
            return this;
        }

        /**
         * Sets the detailed description that allows users to better understand what kind of data
         * the data provider provisions
         */
        public AcctReportingDataProviderIdBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * Adds the specification for one of the properties that the data provider instance requires
         * when it is initialized
         */
        public AcctReportingDataProviderIdBuilder withInstanceProperty(
            AcctReportingDataProviderInstancePropertySpec instanceProperty
        ) {
            this.instanceProperties.add(instanceProperty);
            return this;
        }

        /**
         * Adds the specification for one or more of the properties that the data provider instance
         * requires when it is initialized
         */
        public AcctReportingDataProviderIdBuilder withInstanceProperties(
            Set<AcctReportingDataProviderInstancePropertySpec> instanceProperties
        ) {
            this.instanceProperties.addAll(instanceProperties);
            return this;
        }

        /**
         * Adds the specification for one of the parameters that the data provider takes at runtime
         */
        public AcctReportingDataProviderIdBuilder withParameter(AcctReportingDataProviderReportParameterSpec parameter) {
            this.parameters.add(parameter);
            return this;
        }

        /**
         * Adds the specification for one or more of the parameters that the data provider takes at
         * runtime
         */
        public AcctReportingDataProviderIdBuilder withParameters(Set<AcctReportingDataProviderReportParameterSpec> parameters) {
            this.parameters.addAll(parameters);
            return this;
        }

        /**
         * Builds a new instance of {@link AcctReportingDataProviderId} with the properties given to
         * this builder
         */
        public AcctReportingDataProviderId build() {
            throwIfNull(uuid, () -> new IllegalArgumentException("UUID not provider"));
            throwIfNull(humanReadableName, () -> new IllegalArgumentException("humanReadableName not provider"));
            throwIfNull(description, () -> new IllegalArgumentException("description not provider"));

            return
                new AcctReportingDataProviderId(
                    uuid,
                    humanReadableName,
                    description,
                    instanceProperties,
                    parameters
                );
        }
    }
}
