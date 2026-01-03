package com.desolatetimelines.acct.reporting.ws.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNull;
import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNullOrEmpty;

/**
 * Sets a report at a given location on a dashboard. If there is already a report set at the given location,
 * then that report is replace by the newly referenced report.
 */
public class DashboardReportProperties {
    private final String reportUUID;
    private final Integer rowNumber;
    private final Integer columnNumber;
    private final String containerName;
    private final Integer containerHeightPx;
    private final Set<DashboardReportFilterProperties> filters;

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
    public DashboardReportProperties(
        String reportUUID,
        Integer rowNumber,
        Integer columnNumber,
        String containerName,
        Integer containerHeightPx,
        Set<DashboardReportFilterProperties> filters
    ) {
        this.reportUUID = reportUUID;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.containerName = containerName;
        this.containerHeightPx = containerHeightPx;
        this.filters = filters;
    }

    public String getReportUUID() {
        return reportUUID;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public Integer getColumnNumber() {
        return columnNumber;
    }

    public String getContainerName() {
        return containerName;
    }

    public Integer getContainerHeightPx() {
        return containerHeightPx;
    }

    public Set<DashboardReportFilterProperties> getFilters() {
        return filters;
    }

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

    public static DashboardReportPropertiesBuilder builder() {
        return new DashboardReportPropertiesBuilder();
    }

    public static class DashboardReportPropertiesBuilder {
        private String reportUUID;
        private Integer rowNumber;
        private Integer columnNumber;
        private String containerName;
        private Integer containerHeightPx;
        private final Set<DashboardReportFilterProperties> filters = new HashSet<>();

        public DashboardReportPropertiesBuilder withReportUUID(String reportUUID) {
            this.reportUUID = reportUUID;
            return this;
        }

        public DashboardReportPropertiesBuilder withRowNumber(Integer rowNumber) {
            this.rowNumber = rowNumber;
            return this;
        }

        public DashboardReportPropertiesBuilder withColumnNumber(Integer columnNumber) {
            this.columnNumber = columnNumber;
            return this;
        }

        public DashboardReportPropertiesBuilder withContainerName(String containerName) {
            this.containerName = containerName;
            return this;
        }

        public DashboardReportPropertiesBuilder withContainerHeightPx(Integer containerHeightPx) {
            this.containerHeightPx = containerHeightPx;
            return this;
        }

        public DashboardReportPropertiesBuilder withFilters(Set<DashboardReportFilterProperties> filters) {
            throwIfNullOrEmpty(filters, () -> new IllegalArgumentException("Filters not properly set"));

            filters.forEach(filter -> {
                throwIfNullOrEmpty(filter.filterName(), () -> new IllegalArgumentException("Filter name not provided"));
                throwIfNullOrEmpty(filter.reportColumnName(), () -> new IllegalArgumentException("Filter report column name not provided"));
            });

            this.filters.addAll(filters);

            return this;
        }

        public DashboardReportProperties build() {
            throwIfNullOrEmpty(reportUUID, () -> new IllegalArgumentException("Report UUID not provided"));
            throwIfNull(rowNumber, () -> new IllegalArgumentException("Row number not provided"));
            throwIfNull(columnNumber, () -> new IllegalArgumentException("Column number not provided"));
            throwIfNullOrEmpty(containerName, () -> new IllegalArgumentException("Container name not provided"));
            throwIfNull(containerHeightPx, () -> new IllegalArgumentException("Container height not provided"));

            return
                new DashboardReportProperties(
                    reportUUID,
                    rowNumber,
                    columnNumber,
                    containerName,
                    containerHeightPx,
                    filters
                );
        }

    }

}
