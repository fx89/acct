package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderDataSetColumn;
import com.desolatetimelines.acct.reporting.ws.model.AcctReportingDataSetColumn;

import java.util.LinkedHashSet;

import static com.desolatetimelines.acct.reporting.ws.mapper.AcctReportingDataSetColumnDataTypesMapper.fromAcctReportingDataProviderDataSetColumnDataType;

/**
 * Provides mappers for the {@link AcctReportingDataSetColumn} type.
 */
public abstract class AcctReportingDataSetColumnsMapper {

    public static AcctReportingDataSetColumn fromAcctReportingDataProviderDataSetColumn(
        AcctReportingDataProviderDataSetColumn acctReportingDataProviderDataSetColumn
    ) {
        return
            new AcctReportingDataSetColumn(
                acctReportingDataProviderDataSetColumn.name(),
                fromAcctReportingDataProviderDataSetColumnDataType(
                    acctReportingDataProviderDataSetColumn.dataType()
                )
            );
    }

    public static LinkedHashSet<AcctReportingDataSetColumn> fromSetOfAcctReportingDataProviderDataSetColumns(
        LinkedHashSet<AcctReportingDataProviderDataSetColumn> acctReportingDataProviderDataSetColumns
    ) {
        if (acctReportingDataProviderDataSetColumns == null) {
            return null;
        }

        final LinkedHashSet<AcctReportingDataSetColumn> acctReportingDataSetColumns =
            new LinkedHashSet<>(acctReportingDataProviderDataSetColumns.size());

        acctReportingDataProviderDataSetColumns.forEach(column ->
            acctReportingDataSetColumns.add(
                fromAcctReportingDataProviderDataSetColumn(column)
            )
        );

        return acctReportingDataSetColumns;
    }

}
