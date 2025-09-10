package com.desolatetimelines.acct.reporting.model;

import com.desolatetimelines.acct.reporting.service.AcctReportingService;

/**
 * Container for dashboard properties that are readable by users within the
 * {@link AcctReportingService reporting service}
 *
 * @param dashboardUUID
 * @param dashboardName
 * @param dashboardDescription
 * @param dashboardIconUUID
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
