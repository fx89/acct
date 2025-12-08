package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSetColumnDataType;
import com.desolatetimelines.acct.reporting.ws.model.AcctReportingDataSetColumnDataType;

/**
 * Provides mappers for the {@link AcctReportingDataSetColumnDataType} type.
 */
public abstract class AcctReportingDataSetColumnDataTypesMapper {

    public static AcctReportingDataSetColumnDataType fromAcctReportingDataProviderDataSetColumnDataType(
        AcctReportingDataProviderDataSetColumnDataType acctReportingDataProviderDataSetColumnDataType
    ) {
        return
            switch (acctReportingDataProviderDataSetColumnDataType) {
                case STRING -> AcctReportingDataSetColumnDataType.STRING;
                case NUMERIC -> AcctReportingDataSetColumnDataType.NUMERIC;
                case TIMESTAMP -> AcctReportingDataSetColumnDataType.TIMESTAMP;
                case TEXT -> AcctReportingDataSetColumnDataType.TEXT;
            };
    }

}
