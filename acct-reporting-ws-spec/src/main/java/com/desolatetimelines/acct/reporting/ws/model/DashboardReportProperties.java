package com.desolatetimelines.acct.reporting.ws.model;

import java.util.Objects;
import java.util.Set;

/**
 * Sets a report at a given location on a dashboard. If there is already a report set at the given location,
 * then that report is replace by the newly referenced report.
 *
 * @param reportUUID        Unique identifier for the report to set at the given location on the dashboard.
 * @param rowNumber         Vertical coordinate of the location where to put the report on the dashboard.
 *                          Any positive number is permitted, however, the dashboard will be ugly if a sensible
 *                          number is not chosen.
 * @param columnNumber      Horizontal coordinate of the location where to put the report on the dashboard.
 *                          Any positive number is permitted, however, the dashboard will look ugly if a sensible
 *                          number is not chosen.
 * @param containerName     The title of the cell on which the report is to be displayed.
 * @param containerHeightPx The height of the report.
 * @param filters           A set of {@link DashboardReportFilterProperties filters} to be added to the report.
 */
public record DashboardReportProperties(
    String reportUUID,
    Integer rowNumber,
    Integer columnNumber,
    String containerName,
    Integer containerHeightPx,
    Set<DashboardReportFilterProperties> filters
) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DashboardReportProperties that = (DashboardReportProperties) o;
        return Objects.equals(rowNumber, that.rowNumber) && Objects.equals(columnNumber, that.columnNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowNumber, columnNumber);
    }
}
