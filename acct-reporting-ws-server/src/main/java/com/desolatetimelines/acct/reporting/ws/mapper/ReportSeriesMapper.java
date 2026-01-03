package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.model.AcctReportSeries;
import com.desolatetimelines.acct.reporting.model.ReportSeriesDetails;
import com.desolatetimelines.acct.reporting.ws.model.ReportSeries;

import java.util.Set;
import java.util.stream.Collectors;

import static com.desolatetimelines.acct.reporting.ws.mapper.ReportSeriesTypeMapper.fromAcctReportSeriesType;
import static com.desolatetimelines.acct.reporting.ws.mapper.ReportSeriesTypeMapper.toAcctReportSeriesType;

/**
 * Provides mappers for the {@link ReportSeries} type.
 */
public abstract class ReportSeriesMapper {

    public static ReportSeriesDetails toReportSeriesDetails(ReportSeries reportSeries) {
        if (reportSeries == null) {
            return null;
        }

        return
            ReportSeriesDetails.builder()
                .withReportColumnName(reportSeries.reportColumnName())
                .withReportSeriesName(reportSeries.reportSeriesName())
                .withReportSeriesType(toAcctReportSeriesType(reportSeries.reportSeriesType()))
                .build();
    }

    public static ReportSeries fromReportSeriesDetails(ReportSeriesDetails reportSeriesDetails) {
        if (reportSeriesDetails == null) {
            return null;
        }

        return
            ReportSeries.builder()
                .withReportColumnName(reportSeriesDetails.reportColumnName())
                .withReportSeriesName(reportSeriesDetails.reportSeriesName())
                .withReportSeriesType(fromAcctReportSeriesType(reportSeriesDetails.reportSeriesType()))
                .build();
    }

    public static ReportSeries fromAcctReportSeries(AcctReportSeries acctReportSeries) {
        return
            ReportSeries.builder()
                .withReportSeriesName(acctReportSeries.getReportSeriesName())
                .withReportColumnName(acctReportSeries.getReportColumnName())
                .withReportSeriesType(fromAcctReportSeriesType(acctReportSeries.getReportSeriesType()))
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

    public static Set<ReportSeries> fromReportSeriesDetailsSet(Set<ReportSeriesDetails> reportSeriesDetails) {
        if (reportSeriesDetails == null) {
            return null;
        }

        return
            reportSeriesDetails.stream()
                .map(ReportSeriesMapper::fromReportSeriesDetails)
                .collect(Collectors.toSet());
    }

    public static Set<ReportSeries> fromAcctReportSeriesSet(Set<AcctReportSeries> AcctReportSeriesSet) {
        if (AcctReportSeriesSet == null) {
            return null;
        }

        return
            AcctReportSeriesSet.stream()
                .map(ReportSeriesMapper::fromAcctReportSeries)
                .collect(Collectors.toSet());
    }

}
