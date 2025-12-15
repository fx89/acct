package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.model.ReportSeriesDetails;
import com.desolatetimelines.acct.reporting.ws.model.ReportSeries;

import java.util.Set;
import java.util.stream.Collectors;

import static com.desolatetimelines.acct.reporting.ws.mapper.ReportSeriesTypeMapper.toAcctReportSeriesType;

/**
 * Provides mappers for the {@link ReportSeries} type.
 */
public abstract class ReportSeriesMapper {

    public static ReportSeriesDetails toReportSeriesDetails(ReportSeries reportSeries) {
        return
            ReportSeriesDetails.builder()
                .withReportColumnName(reportSeries.reportColumnName())
                .withReportSeriesName(reportSeries.reportSeriesName())
                .withReportSeriesType(toAcctReportSeriesType(reportSeries.reportSeriesType()))
                .build();
    }

    public static Set<ReportSeriesDetails> toReportSeriesDetailsSet(Set<ReportSeries> reportSeries) {
        if (reportSeries == null) {
            return null;
        }

        return
            reportSeries.stream()
                .map(ReportSeriesMapper::toReportSeriesDetails)
                .collect(Collectors.toSet());
    }

}
