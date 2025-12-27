package com.desolatetimelines.acct.reporting.mapper;

import com.desolatetimelines.acct.reporting.model.AcctReportSeries;
import com.desolatetimelines.acct.reporting.model.ReportSeriesDetails;

/**
 * Provides mappers for the {@link AcctReportSeries} type.
 */
public abstract class AcctReportSeriesMapper {

    public static ReportSeriesDetails toReportSeriesDetails(AcctReportSeries acctReportSeries) {
        if (acctReportSeries == null) {
            return null;
        }

        return
            ReportSeriesDetails.builder()
                .withReportSeriesType(acctReportSeries.getReportSeriesType())
                .withReportSeriesName(acctReportSeries.getReportSeriesName())
                .withReportColumnName(acctReportSeries.getReportColumnName())
                .build();
    }

}
