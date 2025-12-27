package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.model.AcctReportSeriesType;
import com.desolatetimelines.acct.reporting.ws.model.ReportSeriesType;

import static com.desolatetimelines.acct.reporting.ws.model.ReportSeriesType.*;

/**
 * Provides mappers for the {@link ReportSeriesType} type
 */
public class ReportSeriesTypeMapper {

    public static AcctReportSeriesType toAcctReportSeriesType(ReportSeriesType reportSeriesType) {
        if (reportSeriesType == null) {
            return null;
        }

        return
            switch (reportSeriesType) {
                case AREA -> AcctReportSeriesType.AREA;
                case COLUMN -> AcctReportSeriesType.COLUMN;
                case LINE -> AcctReportSeriesType.LINE;
            };
    }

    public static ReportSeriesType fromAcctReportSeriesType(AcctReportSeriesType acctReportSeriesType) {
        if (acctReportSeriesType == null) {
            return null;
        }

        return
            switch (acctReportSeriesType) {
                case AcctReportSeriesType.AREA -> AREA;
                case AcctReportSeriesType.COLUMN -> COLUMN;
                case AcctReportSeriesType.LINE -> LINE;
            };
    }

}
