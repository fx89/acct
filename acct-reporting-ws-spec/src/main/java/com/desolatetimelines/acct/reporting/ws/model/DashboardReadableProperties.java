package com.desolatetimelines.acct.reporting.ws.model;

import java.util.Objects;

/**
 * Container for the readable properties of a dashboard
 * // TODO: Is it worth dropping the readability of records to de-duplicate properties using inheritance? See DashboardProperties
 *
 * @param dashboardUUID        Unique identifier for the dashboard within the ACCT ecosystem
 * @param dashboardName        The unique human-readable name of the dashboard
 * @param dashboardDescription A text that describes the dashboard in more or less detail
 * @param dashboardIconUUID    UUID of the icon that is rendered alongside the dashboard name in the UI
 */
public record DashboardReadableProperties(
    String dashboardUUID,
    String dashboardName,
    String dashboardDescription,
    String dashboardIconUUID
) {
    public static DashboardReadablePropertiesBuilder builder() {
        return new DashboardReadablePropertiesBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DashboardReadableProperties that = (DashboardReadableProperties) o;
        return Objects.equals(dashboardUUID, that.dashboardUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dashboardUUID);
    }


    public static final class DashboardReadablePropertiesBuilder {
        private String dashboardUUID;
        private String dashboardName;
        private String dashboardDescription;
        private String dashboardIconUUID;

        private DashboardReadablePropertiesBuilder() {
        }

        public DashboardReadablePropertiesBuilder withDashboardUUID(String dashboardUUID) {
            this.dashboardUUID = dashboardUUID;
            return this;
        }

        public DashboardReadablePropertiesBuilder withDashboardName(String dashboardName) {
            this.dashboardName = dashboardName;
            return this;
        }

        public DashboardReadablePropertiesBuilder withDashboardDescription(String dashboardDescription) {
            this.dashboardDescription = dashboardDescription;
            return this;
        }

        public DashboardReadablePropertiesBuilder withDashboardIconUUID(String dashboardIconUUID) {
            this.dashboardIconUUID = dashboardIconUUID;
            return this;
        }

        public DashboardReadableProperties build() {
            return new DashboardReadableProperties(
                dashboardUUID,
                dashboardName,
                dashboardDescription,
                dashboardIconUUID
            );
        }
    }
}
