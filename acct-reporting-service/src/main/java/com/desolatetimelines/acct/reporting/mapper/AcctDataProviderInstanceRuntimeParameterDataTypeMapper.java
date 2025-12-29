package com.desolatetimelines.acct.reporting.mapper;

import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderReportParameterType;
import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstanceRuntimeParameterDataType;
import com.desolatetimelines.acct.reporting.model.DataProviderInstanceDetails;

/**
 * Provides mapping methods for the {@link AcctDataProviderInstanceRuntimeParameterDataType} type
 */
public abstract class AcctDataProviderInstanceRuntimeParameterDataTypeMapper {

    public static AcctDataProviderInstanceRuntimeParameterDataType
    fromDataProviderParameterDataType(
        DataProviderInstanceDetails.DataProviderParameterDataType dataProviderParameterDataType
    ) {
        return switch (dataProviderParameterDataType) {
            case NUMERIC -> AcctDataProviderInstanceRuntimeParameterDataType.NUMERIC;
            case BOOLEAN -> AcctDataProviderInstanceRuntimeParameterDataType.BOOLEAN;
            case STRING -> AcctDataProviderInstanceRuntimeParameterDataType.STRING;
            case DATETIME -> AcctDataProviderInstanceRuntimeParameterDataType.DATETIME;
        };
    }

    public static DataProviderInstanceDetails.DataProviderParameterDataType
    toDataProviderParameterDataType(
        AcctDataProviderInstanceRuntimeParameterDataType acctDataProviderInstanceRuntimeParameterDataType
    ) {
        return switch (acctDataProviderInstanceRuntimeParameterDataType) {
            case NUMERIC -> DataProviderInstanceDetails.DataProviderParameterDataType.NUMERIC;
            case BOOLEAN -> DataProviderInstanceDetails.DataProviderParameterDataType.BOOLEAN;
            case STRING -> DataProviderInstanceDetails.DataProviderParameterDataType.STRING;
            case DATETIME -> DataProviderInstanceDetails.DataProviderParameterDataType.DATETIME;
        };
    }

    public static AcctDataProviderInstanceRuntimeParameterDataType fromAcctReportingDataProviderReportParameterType(
        AcctReportingDataProviderReportParameterType acctReportingDataProviderReportParameterType
    ) {
        return switch (acctReportingDataProviderReportParameterType) {
            case NUMERIC -> AcctDataProviderInstanceRuntimeParameterDataType.NUMERIC;
            case BOOLEAN -> AcctDataProviderInstanceRuntimeParameterDataType.BOOLEAN;
            case STRING -> AcctDataProviderInstanceRuntimeParameterDataType.STRING;
            case DATETIME -> AcctDataProviderInstanceRuntimeParameterDataType.DATETIME;
        };
    }

    public static AcctReportingDataProviderReportParameterType toAcctReportingDataProviderReportParameterType(
        AcctDataProviderInstanceRuntimeParameterDataType acctDataProviderInstanceRuntimeParameterDataType
    ) {
        return switch (acctDataProviderInstanceRuntimeParameterDataType) {
            case NUMERIC -> AcctReportingDataProviderReportParameterType.NUMERIC;
            case BOOLEAN -> AcctReportingDataProviderReportParameterType.BOOLEAN;
            case STRING -> AcctReportingDataProviderReportParameterType.STRING;
            case DATETIME -> AcctReportingDataProviderReportParameterType.DATETIME;
        };
    }

}
