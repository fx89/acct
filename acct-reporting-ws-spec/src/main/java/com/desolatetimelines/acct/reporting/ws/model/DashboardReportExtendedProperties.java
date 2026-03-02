package com.desolatetimelines.acct.reporting.ws.model;

import java.util.HashSet;
import java.util.Set;

import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNull;
import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNullOrEmpty;

public class DashboardReportExtendedProperties extends DashboardReportProperties {

    private final String reportName;
    private final String reportDescription;
    private final ReportType reportType;
    private final String reportCategoryColumnName;
    private final Set<ReportSeries> reportSeries;

    /**
     * Sets a report at a given location on a dashboard. If there is already a report set at the given location,
     * then that report is replace by the newly referenced report.
     *
     * @param reportUUID               Unique identifier for the report to set at the given location on the dashboard.
     * @param reportName               Human-readable name of the report.
     * @param reportDescription        Detailed description of the data provided by the report.
     * @param rowNumber                Vertical coordinate of the location where to put the report on the dashboard.
     *                                 Any positive number is permitted, however, the dashboard will be ugly if a sensible
     *                                 number is not chosen.
     * @param columnNumber             Horizontal coordinate of the location where to put the report on the dashboard.
     *                                 Any positive number is permitted, however, the dashboard will look ugly if a sensible
     *                                 number is not chosen.
     * @param containerName            The title of the cell on which the report is to be displayed.
     * @param containerWidthPx         The width of the report.
     * @param containerHeightPx        The height of the report.
     * @param filters                  A set of {@link DashboardReportFilterProperties filters} to be added to the report.
     * @param reportType               Determines how the report is displayed on the dashboard.
     * @param reportCategoryColumnName The column that provides the values to be displayed on the horizontal axis of
     *                                 the chart, in case the report is displayed as a chart.
     * @param reportSeries             Determines the report columns that are displayed on the dashboard report and the
     *                                 way in which they are displayed.
     */
    public DashboardReportExtendedProperties(
        String reportUUID,
        String reportName,
        String reportDescription,
        Integer rowNumber,
        Integer columnNumber,
        String containerName,
        Integer containerWidthPx,
        Integer containerHeightPx,
        Set<DashboardReportFilterProperties> filters,
        ReportType reportType,
        String reportCategoryColumnName,
        Set<ReportSeries> reportSeries
    ) {
        super(reportUUID, rowNumber, columnNumber, containerName, containerWidthPx, containerHeightPx, filters);

        this.reportName = reportName;
        this.reportDescription = reportDescription;
        this.reportType = reportType;
        this.reportCategoryColumnName = reportCategoryColumnName;
        this.reportSeries = reportSeries;
    }

    private DashboardReportExtendedProperties(
        DashboardReportProperties dashboardReportProperties,
        String reportName,
        String reportDescription,
        ReportType reportType,
        String reportCategoryColumnName,
        Set<ReportSeries> reportSeries
    ) {
        super(
            dashboardReportProperties.getReportUUID(),
            dashboardReportProperties.getRowNumber(),
            dashboardReportProperties.getColumnNumber(),
            dashboardReportProperties.getContainerName(),
            dashboardReportProperties.getContainerWidthPx(),
            dashboardReportProperties.getContainerHeightPx(),
            dashboardReportProperties.getFilters()
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

    public ReportType getReportType() {
        return reportType;
    }

    public String getReportCategoryColumnName() {
        return reportCategoryColumnName;
    }

    public Set<ReportSeries> getReportSeries() {
        return reportSeries;
    }

    public static DashboardReportExtendedPropertiesBuilder builderExt() {
        return new DashboardReportExtendedPropertiesBuilder();
    }

    public static class DashboardReportExtendedPropertiesBuilder {
        private final DashboardReportPropertiesBuilder dashboardReportPropertiesBuilder =
            DashboardReportProperties.builder();

        private String reportName;
        private String reportDescription;
        private ReportType reportType;
        private String reportCategoryColumnName;
        private final Set<ReportSeries> reportSeries = new HashSet<>();

        public DashboardReportExtendedPropertiesBuilder withReportUUID(String reportUUID) {
            this.dashboardReportPropertiesBuilder.withReportUUID(reportUUID);
            return this;
        }

        public DashboardReportExtendedPropertiesBuilder withRowNumber(Integer rowNumber) {
            this.dashboardReportPropertiesBuilder.withRowNumber(rowNumber);
            return this;
        }

        public DashboardReportExtendedPropertiesBuilder withColumnNumber(Integer columnNumber) {
            this.dashboardReportPropertiesBuilder.withColumnNumber(columnNumber);
            return this;
        }

        public DashboardReportExtendedPropertiesBuilder withContainerName(String containerName) {
            this.dashboardReportPropertiesBuilder.withContainerName(containerName);
            return this;
        }

        public DashboardReportExtendedPropertiesBuilder withContainerWidthPx(Integer containerWidthPx) {
            this.dashboardReportPropertiesBuilder.withContainerWidthPx(containerWidthPx);
            return this;
        }

        public DashboardReportExtendedPropertiesBuilder withContainerHeightPx(Integer containerHeightPx) {
            this.dashboardReportPropertiesBuilder.withContainerHeightPx(containerHeightPx);
            return this;
        }

        public DashboardReportExtendedPropertiesBuilder withFilters(Set<DashboardReportFilterProperties> filters) {
            this.dashboardReportPropertiesBuilder.withFilters(filters);
            return this;
        }

        public DashboardReportExtendedPropertiesBuilder withReportName(String reportName) {
            this.reportName = reportName;
            return this;
        }

        public DashboardReportExtendedPropertiesBuilder withReportDescription(String reportDescription) {
            this.reportDescription = reportDescription;
            return this;
        }

        public DashboardReportExtendedPropertiesBuilder withReportType(ReportType reportType) {
            this.reportType = reportType;
            return this;
        }

        public DashboardReportExtendedPropertiesBuilder withReportCategoryColumnName(String reportCategoryColumnName) {
            this.reportCategoryColumnName = reportCategoryColumnName;
            return this;
        }

        public DashboardReportExtendedPropertiesBuilder withReportSeries(Set<ReportSeries> reportSeries) {
            throwIfNullOrEmpty(reportSeries, () -> new IllegalArgumentException("Report series improperly set"));

            reportSeries.forEach(series -> {
                throwIfNullOrEmpty(series.reportSeriesName(), () -> new IllegalArgumentException("Missing report series name"));
                throwIfNullOrEmpty(series.reportColumnName(), () -> new IllegalArgumentException("Missing report series column name"));
                throwIfNull(series.reportSeriesType(), () -> new IllegalArgumentException("Missing report series type"));
            });

            this.reportSeries.addAll(reportSeries);

            return this;
        }

        public DashboardReportExtendedProperties build() {
            throwIfNullOrEmpty(reportName, () -> new IllegalArgumentException("Report name not specified"));
            throwIfNull(reportType, () -> new IllegalArgumentException("Report type not specified"));

            final DashboardReportProperties dashboardReportProperties =
                dashboardReportPropertiesBuilder.build();

            return
                new DashboardReportExtendedProperties(
                    dashboardReportProperties,
                    reportName,
                    reportDescription,
                    reportType,
                    reportCategoryColumnName,
                    reportSeries
                );
        }

    }

}
