package com.desolatetimelines.acct.reporting.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNullOrEmpty;

public record DashboardReportDetails(
    String reportUUID,
    Integer rowNumber,
    Integer columnNumber,
    String containerName,
    Integer containerHeightPx,
    Map<String, String> filters
) {

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DashboardReportDetails that = (DashboardReportDetails) o;
        return Objects.equals(rowNumber, that.rowNumber) && Objects.equals(columnNumber, that.columnNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowNumber, columnNumber);
    }

    public static DashboardReportDetailsBuilder builder() {
        return new DashboardReportDetailsBuilder();
    }

    public static class DashboardReportDetailsBuilder {
        private String reportUUID;
        private Integer rowNumber;
        private Integer columnNumber;
        private String containerName;
        private Integer containerHeightPx;
        private final Map<String, String> filters = new HashMap<>();

        public DashboardReportDetailsBuilder withReportUUID(String reportUUID) {
            this.reportUUID = reportUUID;
            return this;
        }

        public DashboardReportDetailsBuilder withRowNumber(Integer rowNumber) {
            this.rowNumber = rowNumber;
            return this;
        }

        public DashboardReportDetailsBuilder withColumnNumber(Integer columnNumber) {
            this.columnNumber = columnNumber;
            return this;
        }

        public DashboardReportDetailsBuilder withContainerName(String containerName) {
            this.containerName = containerName;
            return this;
        }

        public DashboardReportDetailsBuilder withContainerHeightPx(Integer containerHeightPx) {
            this.containerHeightPx = containerHeightPx;
            return this;
        }

        public DashboardReportDetailsBuilder withFilter(String filterName, String reportColumnName) {
            throwIfNullOrEmpty(filterName, () -> new IllegalArgumentException("Filter name not provided"));
            throwIfNullOrEmpty(reportColumnName, () -> new IllegalArgumentException("Report column name not provided"));

            this.filters.put(filterName, reportColumnName);

            return this;
        }

        public DashboardReportDetailsBuilder withFilters(Map<String, String> filters) {
            throwIfNullOrEmpty(filters, () -> new IllegalArgumentException("Filters not provided"));

            filters.forEach((filterName, reportColumnName) -> {
                throwIfNullOrEmpty(reportColumnName, () -> new IllegalArgumentException("Report column name not provided"));

                this.filters.put(filterName, reportColumnName);
            });

            return this;
        }

        public DashboardReportDetails build() {
            throwIfNullOrEmpty(reportUUID, () -> new IllegalArgumentException("Report UUID not provided"));
            throwIfNullOrEmpty(containerName, () -> new IllegalArgumentException("Container name UUID not provided"));

            if (rowNumber < 0) {
                throw new IllegalArgumentException("The row number must be positive");
            }

            if (columnNumber < 0) {
                throw new IllegalArgumentException("The column number must be positive");
            }

            if (containerHeightPx <= 0) {
                throw new IllegalArgumentException("The container height must be positive");
            }

            return
                new DashboardReportDetails(
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
