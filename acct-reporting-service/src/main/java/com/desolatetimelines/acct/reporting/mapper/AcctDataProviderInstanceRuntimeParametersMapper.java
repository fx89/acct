package com.desolatetimelines.acct.reporting.mapper;

import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderReportParameterSpec;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderReportParameterType;
import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstance;
import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstanceRuntimeParameter;
import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstanceRuntimeParameterDataType;
import com.desolatetimelines.acct.reporting.model.DataProviderInstanceDetails;

import java.util.Set;
import java.util.stream.Collectors;

import static com.desolatetimelines.acct.reporting.mapper.AcctDataProviderInstanceRuntimeParameterDataTypeMapper.fromAcctReportingDataProviderReportParameterType;
import static com.desolatetimelines.acct.reporting.mapper.AcctDataProviderInstanceRuntimeParameterDataTypeMapper.toDataProviderParameterDataType;

/**
 * Provides mappers for the {@link AcctDataProviderInstanceRuntimeParameter} type
 */
public abstract class AcctDataProviderInstanceRuntimeParametersMapper {

    public static AcctDataProviderInstanceRuntimeParameter fromAcctReportingDataProviderReportParameterSpec(
        AcctReportingDataProviderReportParameterSpec acctReportingDataProviderReportParameterSpec,
        AcctDataProviderInstance parentAcctDataProviderInstance

    ) {
        if (acctReportingDataProviderReportParameterSpec == null) {
            return null;
        }

        return
            new AcctDataProviderInstanceRuntimeParameter() {

                final AcctDataProviderInstanceRuntimeParameterDataType dataType =
                    fromAcctReportingDataProviderReportParameterType(
                        acctReportingDataProviderReportParameterSpec.dataType()
                    );

                @Override
                public AcctDataProviderInstance getDataProviderInstance() {
                    return parentAcctDataProviderInstance;
                }

                @Override
                public void setDataProviderInstance(AcctDataProviderInstance instance) {
                    throw new UnsupportedOperationException("read only");
                }

                @Override
                public String getParameterName() {
                    return acctReportingDataProviderReportParameterSpec.name();
                }

                @Override
                public void setParameterName(String parameterName) {
                    throw new UnsupportedOperationException("read only");
                }

                @Override
                public String getParameterDefaultValue() {
                    return "";
                }

                @Override
                public void setParameterDefaultValue(String defaultValue) {
                    throw new UnsupportedOperationException("read only");
                }

                @Override
                public AcctDataProviderInstanceRuntimeParameterDataType getParameterDataType() {
                    return dataType;
                }

                @Override
                public void setParameterDataType(AcctDataProviderInstanceRuntimeParameterDataType dataType) {
                    throw new UnsupportedOperationException("read only");
                }

                @Override
                public Boolean isMandatory() {
                    return acctReportingDataProviderReportParameterSpec.mandatory();
                }

                @Override
                public void setMandatory(Boolean mandatory) {
                    throw new UnsupportedOperationException("read only");
                }
            };
    }

    public static DataProviderInstanceDetails.DataProviderInstanceRuntimeParameter toDataProviderInstanceDetailsDataProviderInstanceRuntimeParameter(
        AcctDataProviderInstanceRuntimeParameter acctDataProviderInstanceRuntimeParameter
    ) {
        if (acctDataProviderInstanceRuntimeParameter == null) {
            return null;
        }

        return
            DataProviderInstanceDetails.DataProviderInstanceRuntimeParameter.builder()
                .withParameterName(acctDataProviderInstanceRuntimeParameter.getParameterName())
                .withParameterDefaultValue(acctDataProviderInstanceRuntimeParameter.getParameterDefaultValue())
                .withMandatory(acctDataProviderInstanceRuntimeParameter.isMandatory())
                .withParameterDataType(
                    toDataProviderParameterDataType(
                        acctDataProviderInstanceRuntimeParameter.getParameterDataType()
                    )
                )
                .build();
    }

    public static Set<DataProviderInstanceDetails.DataProviderInstanceRuntimeParameter>
    toDataProviderInstanceDetailsDataProviderInstanceRuntimeParameters(
        Set<AcctDataProviderInstanceRuntimeParameter> acctDataProviderInstanceRuntimeParameters
    ) {
        if (acctDataProviderInstanceRuntimeParameters == null) {
            return null;
        }

        return
            acctDataProviderInstanceRuntimeParameters.stream()
                .map(AcctDataProviderInstanceRuntimeParametersMapper::toDataProviderInstanceDetailsDataProviderInstanceRuntimeParameter)
                .collect(Collectors.toSet());
    }

    public static AcctReportingDataProviderReportParameterSpec
    toAcctReportingDataProviderReportParameterSpec(
        AcctDataProviderInstanceRuntimeParameter acctDataProviderInstanceRuntimeParameter
    ) {
        if (acctDataProviderInstanceRuntimeParameter == null) {
            return null;
        }

        return
            new AcctReportingDataProviderReportParameterSpec(
                acctDataProviderInstanceRuntimeParameter.getParameterName(),
                switch (acctDataProviderInstanceRuntimeParameter.getParameterDataType()) {
                    case NUMERIC -> AcctReportingDataProviderReportParameterType.NUMERIC;
                    case BOOLEAN -> AcctReportingDataProviderReportParameterType.BOOLEAN;
                    case STRING -> AcctReportingDataProviderReportParameterType.STRING;
                    case DATETIME -> AcctReportingDataProviderReportParameterType.DATETIME;
                },
                acctDataProviderInstanceRuntimeParameter.getParameterDefaultValue(),
                acctDataProviderInstanceRuntimeParameter.isMandatory()
            );
    }

    public static Set<AcctReportingDataProviderReportParameterSpec>
    toSetOfAcctReportingDataProviderReportParameterSpecs(
        Set<AcctDataProviderInstanceRuntimeParameter> acctDataProviderInstanceRuntimeParameters
    ) {
        if (acctDataProviderInstanceRuntimeParameters == null) {
            return null;
        }

        return
            acctDataProviderInstanceRuntimeParameters.stream()
                .map(AcctDataProviderInstanceRuntimeParametersMapper::toAcctReportingDataProviderReportParameterSpec)
                .collect(Collectors.toSet());
    }

}
