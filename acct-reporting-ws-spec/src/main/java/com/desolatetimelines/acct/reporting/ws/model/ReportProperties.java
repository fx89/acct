package com.desolatetimelines.acct.reporting.ws.model;

import java.util.HashSet;
import java.util.Set;

import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNull;
import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNullOrEmpty;

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

    public static ReportPropertiesBuilder builder() {
        return new ReportPropertiesBuilder();
    }

    public static class ReportPropertiesBuilder {
        private String reportName;
        private String reportDescription;
        private ReportType reportType;
        private final Set<String> dataProviderInstanceUUIDs = new HashSet<>();
        private String reportSQL;
        private String reportCategoryColumnName;
        private final Set<ReportSeries> reportSeries = new HashSet<>();

        public ReportPropertiesBuilder withReportName(String reportName) {
            this.reportName = reportName;
            return this;
        }

        public ReportPropertiesBuilder withReportDescription(String reportDescription) {
            this.reportDescription = reportDescription;
            return this;
        }

        public ReportPropertiesBuilder withReportType(ReportType reportType) {
            this.reportType = reportType;
            return this;
        }

        public ReportPropertiesBuilder withDataProviderInstanceUUIDs(Set<String> dataProviderInstanceUUIDs) {
            throwIfNullOrEmpty(dataProviderInstanceUUIDs, () -> new IllegalArgumentException("Data provider instance UUIDs not provided"));
            this.dataProviderInstanceUUIDs.addAll(dataProviderInstanceUUIDs);
            return this;
        }

        public ReportPropertiesBuilder withReportSQL(String reportSQL) {
            this.reportSQL = reportSQL;
            return this;
        }

        public ReportPropertiesBuilder withReportCategoryColumnName(String reportCategoryColumnName) {
            this.reportCategoryColumnName = reportCategoryColumnName;
            return this;
        }

        public ReportPropertiesBuilder withReportSeries(Set<ReportSeries> reportSeries) {
            throwIfNullOrEmpty(reportSeries, () -> new IllegalArgumentException("Report series not provided"));
            this.reportSeries.addAll(reportSeries);
            return this;
        }

        public ReportProperties build() {
            throwIfNullOrEmpty(reportName, () -> new IllegalArgumentException("Report name not provided"));
            throwIfNull(reportType, () -> new IllegalArgumentException("Report type not provided"));
            throwIfNullOrEmpty(dataProviderInstanceUUIDs, () -> new IllegalArgumentException("Data provider instance UUIDs not provided"));
            throwIfNullOrEmpty(reportSQL, () -> new IllegalArgumentException("Report SQL not provided"));

            if (reportType != ReportType.TABLE) {
                throwIfNullOrEmpty(reportSeries, () -> new IllegalArgumentException("Report series not provided"));
            }

            return
                new ReportProperties(
                    reportName,
                    reportDescription,
                    reportType,
                    dataProviderInstanceUUIDs,
                    reportSQL,
                    reportCategoryColumnName,
                    reportSeries
                );
        }
    }

}
