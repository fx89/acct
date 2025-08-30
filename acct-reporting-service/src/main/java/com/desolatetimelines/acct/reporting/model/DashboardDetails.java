package com.desolatetimelines.acct.reporting.model;

import com.desolatetimelines.acct.reporting.service.AcctReportingService;

/**
 * Container for dashboard properties that are editable within the
 * {@link AcctReportingService reporting service}
 *
 * @param dashboardName
 * @param dashboardDescription
 * @param dashboardIconUUID
 */
public record DashboardDetails(
    String dashboardName,
    String dashboardDescription,
    String dashboardIconUUID
) {

    public static DashboardDetailsBuilder builder() {
        return new DashboardDetailsBuilder();
    }

    public static final class DashboardDetailsBuilder {
        private String dashboardName;
        private String dashboardDescription;
        private String dashboardIconUUID;

        private DashboardDetailsBuilder() {
        }

        public DashboardDetailsBuilder withDashboardName(String dashboardName) {
            this.dashboardName = dashboardName;
            return this;
        }

        public DashboardDetailsBuilder withDashboardDescription(String dashboardDescription) {
            this.dashboardDescription = dashboardDescription;
            return this;
        }

        public DashboardDetailsBuilder withDashboardIconUUID(String dashboardIconUUID) {
            this.dashboardIconUUID = dashboardIconUUID;
            return this;
        }

        public DashboardDetails build() {
            return new DashboardDetails(
                dashboardName,
                dashboardDescription,
                dashboardIconUUID
            );
        }
    }
}
