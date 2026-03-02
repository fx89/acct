package com.desolatetimelines.acct.reporting.model;

import java.util.Map;
import java.util.Set;

import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNull;
import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNullOrEmpty;

public class DashboardReportExtendedDetails extends DashboardReportDetails {

    private final String reportName;
    private final String reportDescription;
    private final AcctReportType reportType;
    private final String reportCategoryColumnName;
    private final Set<AcctReportSeries> reportSeries;

    public DashboardReportExtendedDetails(
        String reportUUID,
        Integer rowNumber,
        Integer columnNumber,
        String containerName,
        Integer containerWidthPx,
        Integer containerHeightPx,
        Map<String, String> filters,
        String reportName,
        String reportDescription,
        AcctReportType reportType,
        String reportCategoryColumnName,
        Set<AcctReportSeries> reportSeries
    ) {
        super(reportUUID, rowNumber, columnNumber, containerName, containerWidthPx, containerHeightPx, filters);

        this.reportName = reportName;
        this.reportDescription = reportDescription;
        this.reportType = reportType;
        this.reportCategoryColumnName = reportCategoryColumnName;
        this.reportSeries = reportSeries;
    }

    private DashboardReportExtendedDetails(
        DashboardReportDetails dashboardReportDetails,
        String reportName,
        String reportDescription,
        AcctReportType reportType,
        String reportCategoryColumnName,
        Set<AcctReportSeries> reportSeries
    ) {
        super(
            dashboardReportDetails.getReportUUID(),
            dashboardReportDetails.getRowNumber(),
            dashboardReportDetails.getColumnNumber(),
            dashboardReportDetails.getContainerName(),
            dashboardReportDetails.getContainerWidthPx(),
            dashboardReportDetails.getContainerHeightPx(),
            dashboardReportDetails.getFilters()
        );

        this.reportName = reportName;
        this.reportDescription = reportDescription;
        this.reportType = reportType;
        this.reportCategoryColumnName = reportCategoryColumnName;
        this.reportSeries = reportSeries;
    }

    public String getReportName() {
        return reportName;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public AcctReportType getReportType() {
        return reportType;
    }

    public String getReportCategoryColumnName() {
        return reportCategoryColumnName;
    }

    public Set<AcctReportSeries> getReportSeries() {
        return reportSeries;
    }

    public static DashboardReportExtendedDetailsBuilder builderExt() {
        return new DashboardReportExtendedDetailsBuilder();
    }

    public static class DashboardReportExtendedDetailsBuilder {

        private final DashboardReportDetails.DashboardReportDetailsBuilder dashboardReportDetailsBuilder =
            DashboardReportDetails.builder();

        private String reportName;
        private String reportDescription;
        private AcctReportType reportType;
        private String reportCategoryColumnName;
        private Set<AcctReportSeries> reportSeries;

        public DashboardReportExtendedDetailsBuilder() {
            super();
        }

        public DashboardReportExtendedDetailsBuilder withReportUUID(String reportUUID) {
            this.dashboardReportDetailsBuilder.withReportUUID(reportUUID);
            return this;
        }

        public DashboardReportExtendedDetailsBuilder withRowNumber(Integer rowNumber) {
            this.dashboardReportDetailsBuilder.withRowNumber(rowNumber);
            return this;
        }

        public DashboardReportExtendedDetailsBuilder withColumnNumber(Integer columnNumber) {
            this.dashboardReportDetailsBuilder.withColumnNumber(columnNumber);
            return this;
        }

        public DashboardReportExtendedDetailsBuilder withContainerName(String containerName) {
            this.dashboardReportDetailsBuilder.withContainerName(containerName);
            return this;
        }

        public DashboardReportExtendedDetailsBuilder withContainerWidthPx(Integer containerWidthPx) {
            this.dashboardReportDetailsBuilder.withContainerWidthPx(containerWidthPx);
            return this;
        }

        public DashboardReportExtendedDetailsBuilder withContainerHeightPx(Integer containerHeightPx) {
            this.dashboardReportDetailsBuilder.withContainerHeightPx(containerHeightPx);
            return this;
        }

        public DashboardReportExtendedDetailsBuilder withFilter(String filterName, String reportColumnName) {
            this.dashboardReportDetailsBuilder.withFilter(filterName, reportColumnName);
            return this;
        }

        public DashboardReportExtendedDetailsBuilder withFilters(Map<String, String> filters) {
            this.dashboardReportDetailsBuilder.withFilters(filters);
            return this;
        }

        public DashboardReportExtendedDetailsBuilder withReportName(String reportName) {
            this.reportName = reportName;
            return this;
        }

        public DashboardReportExtendedDetailsBuilder withReportDescription(String reportDescription) {
            this.reportDescription = reportDescription;
            return this;
        }

        public DashboardReportExtendedDetailsBuilder withReportType(AcctReportType reportType) {
            this.reportType = reportType;
            return this;
        }

        public DashboardReportExtendedDetailsBuilder withReportCategoryColumnName(String reportCategoryColumnName) {
            this.reportCategoryColumnName = reportCategoryColumnName;
            return this;
        }

        public DashboardReportExtendedDetailsBuilder withReportSeries(Set<AcctReportSeries> reportSeries) {
            throwIfNullOrEmpty(reportSeries, () -> new IllegalArgumentException("Report series improperly configured"));

            reportSeries.forEach(series -> {
                throwIfNullOrEmpty(series.getReportSeriesName(), () -> new IllegalArgumentException("Report series name not provided"));
                throwIfNullOrEmpty(series.getReportColumnName(), () -> new IllegalArgumentException("Report series column name not provided"));
                throwIfNull(series.getReportSeriesType(), () -> new IllegalArgumentException("Report series type not specified"));
            });

            this.reportSeries = reportSeries;

            return this;
        }

        public DashboardReportExtendedDetails build() {
            throwIfNullOrEmpty(reportName, () -> new IllegalArgumentException("Report name not provided"));
            throwIfNull(reportType, () -> new IllegalArgumentException("Report type not provided"));

            return new DashboardReportExtendedDetails(
                dashboardReportDetailsBuilder.build(),
                this.reportName,
                this.reportDescription,
                this.reportType,
                this.reportCategoryColumnName,
                this.reportSeries
            );
        }
    }
}
