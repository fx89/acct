package com.desolatetimelines.acct.reporting.model;

public interface AcctDataProviderInstanceRuntimeParameter {

    AcctDataProviderInstance getDataProviderInstance();

    void setDataProviderInstance(AcctDataProviderInstance instance);

    String getParameterName();

    void setParameterName(String parameterName);

    String getParameterDefaultValue();

    void setParameterDefaultValue(String defaultValue);

    AcctDataProviderInstanceRuntimeParameterDataType getParameterDataType();

    void setParameterDataType(AcctDataProviderInstanceRuntimeParameterDataType dataType);

    Boolean isMandatory();

    void setMandatory(Boolean mandatory);

}
