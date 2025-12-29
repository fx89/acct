package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderReportParameterType;
import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstanceRuntimeParameterDataType;
import com.desolatetimelines.acct.reporting.ws.model.DataProviderParameterDataType;

/**
 * Provides mappers for the {@link DataProviderParameterDataType} type
 */
public abstract class DataProviderParameterDataTypesMapper {

    public static DataProviderParameterDataType fromAcctDataProviderInstanceRuntimeParameterDataType(
        AcctDataProviderInstanceRuntimeParameterDataType acctDataProviderInstanceRuntimeParameterDataType
    ) {
        switch (acctDataProviderInstanceRuntimeParameterDataType) {
            case STRING -> {
                return DataProviderParameterDataType.STRING;
            }
            case NUMERIC -> {
                return DataProviderParameterDataType.NUMERIC;
            }
            case DATETIME -> {
                return DataProviderParameterDataType.DATETIME;
            }
            case BOOLEAN -> {
                return DataProviderParameterDataType.BOOLEAN;
            }
        }

        throw new IllegalArgumentException("Unsupported parameter data type");
    }

    public static DataProviderParameterDataType fromAcctReportingDataProviderReportParameterType(
        AcctReportingDataProviderReportParameterType acctReportingDataProviderReportParameterType
    ) {
        return
            switch (acctReportingDataProviderReportParameterType) {
                case STRING -> DataProviderParameterDataType.STRING;
                case NUMERIC -> DataProviderParameterDataType.NUMERIC;
                case DATETIME -> DataProviderParameterDataType.DATETIME;
                case BOOLEAN -> DataProviderParameterDataType.BOOLEAN;
            };
    }

}
