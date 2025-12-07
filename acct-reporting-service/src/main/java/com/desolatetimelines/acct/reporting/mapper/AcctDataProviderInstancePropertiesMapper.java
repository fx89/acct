package com.desolatetimelines.acct.reporting.mapper;

import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstanceProperty;
import com.desolatetimelines.acct.reporting.model.DataProviderInstanceDetails;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Provides mappers for the {@link AcctDataProviderInstanceProperty} type
 */
public abstract class AcctDataProviderInstancePropertiesMapper {

    public static DataProviderInstanceDetails.DataProviderInstanceProperty
    toDataProviderInstanceProperty(AcctDataProviderInstanceProperty acctDataProviderInstanceProperty) {
        if (acctDataProviderInstanceProperty == null) {
            return null;
        }

        return
            DataProviderInstanceDetails.DataProviderInstanceProperty.builder()
                .withPropertyName(acctDataProviderInstanceProperty.getPropertyName())
                .withPropertyValue(acctDataProviderInstanceProperty.getPropertyValue())
                .build();
    }

    public static Set<DataProviderInstanceDetails.DataProviderInstanceProperty>
    toDataProviderInstanceProperties(
        Set<AcctDataProviderInstanceProperty> acctDataProviderInstanceProperties
    ) {
        if (acctDataProviderInstanceProperties == null) {
            return null;
        }

        return
            acctDataProviderInstanceProperties.stream()
                .map(AcctDataProviderInstancePropertiesMapper::toDataProviderInstanceProperty)
                .collect(Collectors.toSet());
    }

}
