package com.desolatetimelines.acct.reporting.model;

import java.util.HashSet;
import java.util.Set;

import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNull;
import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNullOrEmpty;

public record ReportDetails(
    String reportName,
    String reportDescription,
    AcctReportType reportType,
    Set<String> dataProviderInstanceUUIDs,
    String reportSQL,
    String reportCategoryColumnName,
    Set<ReportSeriesDetails> reportSeries
) {

    public static ReportDetailsBuilder builder() {
        return new ReportDetailsBuilder();
    }

    @SuppressWarnings("UnusedReturnValue")
    public static class ReportDetailsBuilder {
        private String reportName;
        private String reportDescription;
        private AcctReportType reportType;
        private String reportSQL;
        private String reportCategoryColumnName;
        private final Set<String> dataProviderInstanceUUIDs = new HashSet<>();
        private final Set<ReportSeriesDetails> reportSeries = new HashSet<>();

        public ReportDetailsBuilder withReportName(String reportName) {
            this.reportName = reportName;
            return this;
        }

        public ReportDetailsBuilder withReportDescription(String reportDescription) {
            this.reportDescription = reportDescription;
            return this;
        }

        public ReportDetailsBuilder withReportType(AcctReportType reportType) {
            this.reportType = reportType;
            return this;
        }

        public ReportDetailsBuilder withReportSQL(String reportSQL) {
            this.reportSQL = reportSQL;
            return this;
        }

        public ReportDetailsBuilder withReportCategoryColumnName(String reportCategoryColumnName) {
            this.reportCategoryColumnName = reportCategoryColumnName;
            return this;
        }

        public ReportDetailsBuilder withDataProviderInstanceUUID(String dataProviderInstanceUUID) {
            throwIfNull(dataProviderInstanceUUID, () -> new IllegalArgumentException("Null data provider instance UUID reference provided"));
            this.dataProviderInstanceUUIDs.add(dataProviderInstanceUUID);
            return this;
        }

        public ReportDetailsBuilder withDataProviderInstanceUUIDs(Set<String> dataProviderInstanceUUIDs) {
            throwIfNullOrEmpty(dataProviderInstanceUUIDs, () -> new IllegalArgumentException("Data provider instance UUIDs not provided"));
            dataProviderInstanceUUIDs.forEach(this::withDataProviderInstanceUUID);
            return this;
        }

        public ReportDetailsBuilder withReportSeries(ReportSeriesDetails reportSeries) {
            throwIfNull(reportSeries, () -> new IllegalArgumentException("Null report series reference provided"));
            this.reportSeries.add(reportSeries);
            return this;
        }

        public ReportDetailsBuilder withReportSeries(Set<ReportSeriesDetails> reportSeries) {
            throwIfNullOrEmpty(reportSeries, () -> new IllegalArgumentException("Report series not provided"));
            reportSeries.forEach(this::withReportSeries);
            return this;
        }

        public ReportDetails build() {
            throwIfNullOrEmpty(reportName, () -> new IllegalArgumentException("Report name not provided"));
            throwIfNullOrEmpty(reportDescription, () -> new IllegalArgumentException("Report description not provided"));
            throwIfNull(reportType, () -> new IllegalArgumentException("Report type not provided"));
            throwIfNullOrEmpty(reportSQL, () -> new IllegalArgumentException("Report SQL not provided"));
            throwIfNullOrEmpty(dataProviderInstanceUUIDs, () -> new IllegalArgumentException("Data provider instance UUIDs not provided"));

            if (reportType != AcctReportType.TABLE) {
                throwIfNullOrEmpty(reportSeries, () -> new IllegalArgumentException("Report series not provided"));
            }

            return
                new ReportDetails(
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