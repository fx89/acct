package com.desolatetimelines.acct.reporting.model;

public interface AcctDataProviderInstanceProperty {

    AcctDataProviderInstance getDataProviderInstance();

    void setDataProviderInstance(AcctDataProviderInstance instance);

    String getPropertyName();

    void setPropertyName(String propertyName);

    String getPropertyValue();

    void setPropertyValue(String propertyValue);

}
