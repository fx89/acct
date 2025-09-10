package com.desolatetimelines.acct.security.ws.endpoint.model;

/**
 * Defines a dashboard ownership record
 *
 * @param ownerType     the type of owner
 * @param ownerUUID     the UUID of the owner
 * @param dashboardUUID the UUID of the dashboard
 */
public record DashboardOwner(
    OwnerType ownerType,
    String ownerUUID,
    String dashboardUUID
) {

    public static DashboardOwnerBuilder builder() {
        return new DashboardOwnerBuilder();
    }

    public static final class DashboardOwnerBuilder {
        private OwnerType ownerType;
        private String ownerUUID;
        private String dashboardUUID;

        private DashboardOwnerBuilder() {
        }

        public DashboardOwnerBuilder withOwnerType(OwnerType ownerType) {
            this.ownerType = ownerType;
            return this;
        }

        public DashboardOwnerBuilder withOwnerUUID(String ownerUUID) {
            this.ownerUUID = ownerUUID;
            return this;
        }

        public DashboardOwnerBuilder withDashboardUUID(String dashboardUUID) {
            this.dashboardUUID = dashboardUUID;
            return this;
        }

        public DashboardOwner build() {
            return new DashboardOwner(ownerType, ownerUUID, dashboardUUID);
        }
    }
}
