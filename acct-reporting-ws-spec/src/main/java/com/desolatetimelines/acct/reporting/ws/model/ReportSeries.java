package com.desolatetimelines.acct.reporting.ws.model;

import java.util.Objects;

import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNull;
import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNullOrEmpty;

/**
 * Defines the type, source column and display name of a report series.
 *
 * @param reportColumnName  The name of the report column where the report series data comes from.
 * @param reportSeriesName  The name that identifies the series in the chart.
 * @param reportSeriesType  The way in which the series is rendered on the chart.
 * @param reportSeriesOrder The number of precedence that determines the order in which series are displayed on the chart.
 */
public record ReportSeries(
    String reportColumnName,
    String reportSeriesName,
    ReportSeriesType reportSeriesType,
    Integer reportSeriesOrder
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

    public static ReportSeriesBuilder builder() {
        return new ReportSeriesBuilder();
    }

    public static class ReportSeriesBuilder {
        private String reportColumnName;
        private String reportSeriesName;
        private ReportSeriesType reportSeriesType;
        private Integer reportSeriesOrder = 0;

        public ReportSeriesBuilder withReportColumnName(String reportColumnName) {
            this.reportColumnName = reportColumnName;
            return this;
        }

        public ReportSeriesBuilder withReportSeriesName(String reportSeriesName) {
            this.reportSeriesName = reportSeriesName;
            return this;
        }

        public ReportSeriesBuilder withReportSeriesType(ReportSeriesType reportSeriesType) {
            this.reportSeriesType = reportSeriesType;
            return this;
        }

        public ReportSeriesBuilder withReportSeriesOrder(Integer reportSeriesOrder) {
            this.reportSeriesOrder = reportSeriesOrder;
            return this;
        }

        public ReportSeries build() {
            throwIfNullOrEmpty(reportColumnName, () -> new IllegalArgumentException("Report column name not provided"));
            throwIfNullOrEmpty(reportSeriesName, () -> new IllegalArgumentException("Report series name not provided"));
            throwIfNull(reportSeriesType, () -> new IllegalArgumentException("Report series type not provided"));
            throwIfNull(reportSeriesOrder, () -> new IllegalArgumentException("Report series order not provided"));

            return
                new ReportSeries(
                    reportColumnName,
                    reportSeriesName,
                    reportSeriesType,
                    reportSeriesOrder
                );
        }
    }
}
