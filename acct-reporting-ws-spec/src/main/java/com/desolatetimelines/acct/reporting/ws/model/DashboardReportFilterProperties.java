package com.desolatetimelines.acct.reporting.ws.model;

import java.util.Objects;

/**
 * States that a report filter should be set on the named column
 *
 * @param filterName       The display name of the filter
 * @param reportColumnName The column on which to apply the filter
 */
public record DashboardReportFilterProperties(
    String filterName,
    String reportColumnName
) {

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DashboardReportFilterProperties that = (DashboardReportFilterProperties) o;
        return Objects.equals(filterName, that.filterName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(filterName);
    }

}
