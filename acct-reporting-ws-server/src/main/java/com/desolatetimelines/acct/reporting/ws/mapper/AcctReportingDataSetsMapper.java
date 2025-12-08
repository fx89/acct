package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSet;
import com.desolatetimelines.acct.reporting.ws.model.AcctReportingDataSet;

import static com.desolatetimelines.acct.reporting.ws.mapper.AcctReportingDataSetColumnsMapper.fromSetOfAcctReportingDataProviderDataSetColumns;

/**
 * Provides mappers for the {@link AcctReportingDataSet} type.
 */
public abstract class AcctReportingDataSetsMapper {

    public static AcctReportingDataSet fromAcctReportingDataProviderDataSet(
        AcctReportingDataProviderDataSet acctReportingDataProviderDataSet
    ) {
        return
            new AcctReportingDataSet(
                acctReportingDataProviderDataSet.recordCount(),
                fromSetOfAcctReportingDataProviderDataSetColumns(acctReportingDataProviderDataSet.columns()),
                acctReportingDataProviderDataSet.data()
            );
    }

}
