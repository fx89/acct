package com.desolatetimelines.acct.security.ws.endpoint.model;

import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNull;
import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNullOrEmpty;

/**
 * Defines a report ownership record
 *
 * @param ownerType  the type of owner
 * @param ownerUUID  the UUID of the owner
 * @param reportUUID the UUID of the report
 */
public record ReportOwner(
    OwnerType ownerType,
    String ownerUUID,
    String reportUUID
) {
    public static ReportOwnerBuilder builder() {
        return new ReportOwnerBuilder();
    }

    public static class ReportOwnerBuilder {
        private OwnerType ownerType;
        private String ownerUUID;
        private String reportUUID;

        public ReportOwnerBuilder withOwnerType(OwnerType ownerType) {
            this.ownerType = ownerType;
            return this;
        }

        public ReportOwnerBuilder withOwnerUUID(String ownerUUID) {
            this.ownerUUID = ownerUUID;
            return this;
        }

        public ReportOwnerBuilder withReportUUID(String reportUUID) {
            this.reportUUID = reportUUID;
            return this;
        }

        public ReportOwner build() {
            throwIfNull(ownerType, () -> new IllegalArgumentException("The owner type was not provided"));
            throwIfNullOrEmpty(ownerUUID, () -> new IllegalArgumentException("The owner UUID was not provided"));
            throwIfNullOrEmpty(reportUUID, () -> new IllegalArgumentException("The report UUID was not provided"));

            return new ReportOwner(
                ownerType,
                ownerUUID,
                reportUUID
            );
        }
    }
}
