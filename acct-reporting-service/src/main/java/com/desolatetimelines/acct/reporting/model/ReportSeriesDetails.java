package com.desolatetimelines.acct.reporting.model;

import java.util.Objects;

import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNull;
import static com.desolatetimelines.acct.common.utils.ValidationUtils.throwIfNullOrEmpty;

public record ReportSeriesDetails(
    String reportColumnName,
    String reportSeriesName,
    AcctReportSeriesType reportSeriesType,
    Integer reportSeriesOrder
) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReportSeriesDetails that = (ReportSeriesDetails) o;
        return Objects.equals(reportSeriesName, that.reportSeriesName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(reportSeriesName);
    }

    public static ReportSeriesDetailsBuilder builder() {
        return new ReportSeriesDetailsBuilder();
    }

    public static class ReportSeriesDetailsBuilder {
        private String reportColumnName;
        private String reportSeriesName;
        private AcctReportSeriesType reportSeriesType;
        private Integer reportSeriesOrder = 0;

        public ReportSeriesDetailsBuilder withReportColumnName(String reportColumnName) {
            this.reportColumnName = reportColumnName;
            return this;
        }

        public ReportSeriesDetailsBuilder withReportSeriesName(String reportSeriesName) {
            this.reportSeriesName = reportSeriesName;
            return this;
        }

        public ReportSeriesDetailsBuilder withReportSeriesType(AcctReportSeriesType reportSeriesType) {
            this.reportSeriesType = reportSeriesType;
            return this;
        }

        public ReportSeriesDetailsBuilder withReportSeriesOrder(Integer reportSeriesOrder) {
            this.reportSeriesOrder = reportSeriesOrder;
            return this;
        }

        public ReportSeriesDetails build() {
            throwIfNullOrEmpty(reportColumnName, () -> new IllegalArgumentException("Report column name not provided"));
            throwIfNullOrEmpty(reportSeriesName, () -> new IllegalArgumentException("Report series name not provided"));
            throwIfNull(reportSeriesType, () -> new IllegalArgumentException("Report series type not provided"));
            throwIfNull(reportSeriesOrder, () -> new IllegalArgumentException("Report series order not provided"));

            return
                new ReportSeriesDetails(
                    reportColumnName,
                    reportSeriesName,
                    reportSeriesType,
                    reportSeriesOrder
                );
        }
    }
}
