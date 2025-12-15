package com.desolatetimelines.acct.reporting.ws.model;

import java.util.Set;

/**
 * Container for the properties of ACCT reports.
 *
 * @param reportName                Human-readable name that identifies the report in the ACCT ecosystem. Uniqueness is
 *                                  not enforced, but it's recommended.
 * @param reportDescription         Human-readable description that offers details on what the report is about.
 * @param reportType                Defines how the report is rendered in the dashboard.
 * @param dataProviderInstanceUUIDs A set of UUIDs for the data providers employed by the report.
 * @param reportSQL                 The SQL query that joins the result sets produced by the data providers employed by
 *                                  the report and produces the final data set that backs the report.
 * @param reportCategoryColumnName  The name of the column in the final data set where the values of the category axis
 *                                  are found. Not used in {@link ReportType#TABLE tables}.
 * @param reportSeries              A set of series to be rendered in {@link ReportType#SERIES series charts}. Unique by
 *                                  {@link ReportSeries#reportSeriesName() series name}. Not used in
 *                                  {@link ReportType#TABLE tables} and {@link ReportType#PIE pie charts}.
 */
public record ReportProperties(
    String reportName,
    String reportDescription,
    ReportType reportType,
    Set<String> dataProviderInstanceUUIDs,
    String reportSQL,
    String reportCategoryColumnName,
    Set<ReportSeries> reportSeries
) {
}
