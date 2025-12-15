package com.desolatetimelines.acct.reporting.ws.model;

import java.util.Objects;

/**
 * Defines the type, source column and display name of a report series.
 *
 * @param reportColumnName The name of the report column where the report series data comes from.
 * @param reportSeriesName The name that identifies the series in the chart.
 * @param reportSeriesType The way in which the series is rendered on the chart.
 */
public record ReportSeries(
    String reportColumnName,
    String reportSeriesName,
    ReportSeriesType reportSeriesType
) {

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReportSeries that = (ReportSeries) o;
        return Objects.equals(reportSeriesName, that.reportSeriesName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(reportSeriesName);
    }
}
