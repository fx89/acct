package com.desolatetimelines.acct.reporting.ws.model;

import java.util.Objects;

/**
 * Adds identifying properties to the {@link ReportProperties report properties}
 *
 * @param reportUUID       Uniquely identifies the report within the ACCT ecosystem.
 * @param reportProperties Contains the properties of the report.
 */
public record ReportExtendedProperties(
    String reportUUID,
    ReportProperties reportProperties
) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReportExtendedProperties that = (ReportExtendedProperties) o;
        return Objects.equals(reportUUID, that.reportUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(reportUUID);
    }
}
