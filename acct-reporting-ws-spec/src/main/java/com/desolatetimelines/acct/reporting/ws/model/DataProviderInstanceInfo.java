package com.desolatetimelines.acct.reporting.ws.model;

import java.util.Objects;

/**
 * Container for the basic information that identifies and describes a data provider instance.
 *
 * @param dataProviderInstanceUUID The unique identifier of the data provider instance.
 * @param dataProviderInstanceName The human-readable name of the data provider instance.
 * @param dataProviderUUID         The unique identifier of the data provider whose instance this is.
 */
public record DataProviderInstanceInfo(
    String dataProviderInstanceUUID,
    String dataProviderInstanceName,
    String dataProviderUUID
) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DataProviderInstanceInfo that = (DataProviderInstanceInfo) o;
        return Objects.equals(dataProviderInstanceUUID, that.dataProviderInstanceUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dataProviderInstanceUUID);
    }

    public static DataProviderInstanceInfoBuilder builder() {
        return new DataProviderInstanceInfoBuilder();
    }

    public static final class DataProviderInstanceInfoBuilder {
        private String dataProviderInstanceUUID;
        private String dataProviderInstanceName;
        private String dataProviderUUID;

        public DataProviderInstanceInfoBuilder withDataProviderInstanceUUID(
            String dataProviderInstanceUUID
        ) {
            this.dataProviderInstanceUUID = dataProviderInstanceUUID;
            return this;
        }

        public DataProviderInstanceInfoBuilder withDataProviderInstanceName(
            String dataProviderInstanceName
        ) {
            this.dataProviderInstanceName = dataProviderInstanceName;
            return this;
        }

        public DataProviderInstanceInfoBuilder withDataProviderUUID(
            String dataProviderUUID
        ) {
            this.dataProviderUUID = dataProviderUUID;
            return this;
        }

        public DataProviderInstanceInfo build() {
            return
                new DataProviderInstanceInfo(
                    dataProviderInstanceUUID,
                    dataProviderInstanceName,
                    dataProviderUUID
                );
        }
    }
}
