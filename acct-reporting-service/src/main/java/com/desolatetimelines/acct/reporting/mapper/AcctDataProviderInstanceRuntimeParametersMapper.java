package com.desolatetimelines.acct.reporting.mapper;

import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderReportParameterSpec;
import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstance;
import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstanceRuntimeParameter;
import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstanceRuntimeParameterDataType;

import static com.desolatetimelines.acct.reporting.mapper.AcctDataProviderInstanceRuntimeParameterDataTypeMapper.fromAcctReportingDataProviderReportParameterType;

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

}
