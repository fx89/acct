package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.model.AcctReportSeriesType;
import com.desolatetimelines.acct.reporting.ws.model.ReportSeriesType;

/**
 * Provides mappers for the {@link ReportSeriesType} type
 */
public class ReportSeriesTypeMapper {

    public static AcctReportSeriesType toAcctReportSeriesType(ReportSeriesType reportSeriesType) {
        return
            switch (reportSeriesType) {
                case AREA -> AcctReportSeriesType.AREA;
                case COLUMN -> AcctReportSeriesType.COLUMN;
                case LINE -> AcctReportSeriesType.LINE;
            };
    }

}
