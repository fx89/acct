package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.model.AcctReportType;
import com.desolatetimelines.acct.reporting.ws.model.ReportType;

/**
 * Provides mappers for the {@link ReportType type}
 */
public abstract class ReportTypeMapper {

    public static AcctReportType toAcctReportType(ReportType reportType) {
        return
            switch (reportType) {
                case PIE -> AcctReportType.PIE;
                case SERIES -> AcctReportType.SERIES;
                case TABLE -> AcctReportType.TABLE;
            };
    }

}
