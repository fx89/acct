package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderReportParameterSpec;
import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstanceRuntimeParameter;
import com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceRuntimeParameter;

import java.util.Set;
import java.util.stream.Collectors;

import static com.desolatetimelines.acct.reporting.ws.mapper.DataProviderParameterDataTypesMapper.fromAcctDataProviderInstanceRuntimeParameterDataType;
import static com.desolatetimelines.acct.reporting.ws.mapper.DataProviderParameterDataTypesMapper.fromAcctReportingDataProviderReportParameterType;

/**
 * Provides mappers for the {@link DataProviderInstanceRuntimeParameter} type
 */
public abstract class DataProviderInstanceRuntimeParametersMapper {

    public static DataProviderInstanceRuntimeParameter fromAcctDataProviderInstanceRuntimeParameter(
        AcctDataProviderInstanceRuntimeParameter acctDataProviderInstanceRuntimeParameter
    ) {
        if (acctDataProviderInstanceRuntimeParameter == null) {
            return null;
        }

        return
            DataProviderInstanceRuntimeParameter.builder()
                .withParameterName(acctDataProviderInstanceRuntimeParameter.getParameterName())
                .withParameterDefaultValue(acctDataProviderInstanceRuntimeParameter.getParameterDefaultValue())
                .withMandatory(acctDataProviderInstanceRuntimeParameter.isMandatory())
                .withParameterDataType(
                    fromAcctDataProviderInstanceRuntimeParameterDataType(
                        acctDataProviderInstanceRuntimeParameter.getParameterDataType()
                    )
                )
                .build();
    }

    public static DataProviderInstanceRuntimeParameter fromAcctReportingDataProviderReportParameterSpec(
        AcctReportingDataProviderReportParameterSpec acctDataProviderInstanceRuntimeParameterSpec
    ) {
        if (acctDataProviderInstanceRuntimeParameterSpec == null) {
            return null;
        }

        return
            DataProviderInstanceRuntimeParameter.builder()
                .withParameterName(acctDataProviderInstanceRuntimeParameterSpec.name())
                .withParameterDefaultValue(acctDataProviderInstanceRuntimeParameterSpec.parameterDefaultValue())
                .withMandatory(acctDataProviderInstanceRuntimeParameterSpec.mandatory())
                .withParameterDataType(
                    fromAcctReportingDataProviderReportParameterType(
                        acctDataProviderInstanceRuntimeParameterSpec.dataType()
                    )
                )
                .build();
    }

    public static Set<DataProviderInstanceRuntimeParameter> fromSetOfAcctDataProviderInstanceRuntimeParameter(
        Set<AcctDataProviderInstanceRuntimeParameter> acctDataProviderInstanceRuntimeParameter
    ) {
        if (acctDataProviderInstanceRuntimeParameter == null) {
            return null;
        }

        return
            acctDataProviderInstanceRuntimeParameter.stream()
                .map(DataProviderInstanceRuntimeParametersMapper::fromAcctDataProviderInstanceRuntimeParameter)
                .collect(Collectors.toSet());

    }

    public static Set<DataProviderInstanceRuntimeParameter> fromSetOfAcctDataProviderInstanceRuntimeParameterSpec(
        Set<AcctReportingDataProviderReportParameterSpec> acctDataProviderInstanceRuntimeParameterSpecs
    ) {
        if (acctDataProviderInstanceRuntimeParameterSpecs == null) {
            return null;
        }

        return
            acctDataProviderInstanceRuntimeParameterSpecs.stream()
                .map(DataProviderInstanceRuntimeParametersMapper::fromAcctReportingDataProviderReportParameterSpec)
                .collect(Collectors.toSet());

    }

}
