package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstanceRuntimeParameter;
import com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceRuntimeParameter;

import java.util.Set;
import java.util.stream.Collectors;

import static com.desolatetimelines.acct.reporting.ws.mapper.DataProviderParameterDataTypesMapper.fromAcctDataProviderInstanceRuntimeParameterDataType;

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

}
