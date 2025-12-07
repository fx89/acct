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

}
