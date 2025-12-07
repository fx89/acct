package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstance;
import com.desolatetimelines.acct.reporting.ws.model.DataProviderInstanceInfo;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Provides mappers for the {@link DataProviderInstanceInfo} type.
 */
public abstract class DataProviderInstanceInfoMapper {

    public static DataProviderInstanceInfo fromAcctDataProviderInstance(
        AcctDataProviderInstance acctDataProviderInstance
    ) {
        if (acctDataProviderInstance == null) {
            return null;
        }

        return
            DataProviderInstanceInfo.builder()
                .withDataProviderInstanceUUID(acctDataProviderInstance.getDataProviderInstanceUUID())
                .withDataProviderInstanceName(acctDataProviderInstance.getDataProviderInstanceName())
                .withDataProviderUUID(acctDataProviderInstance.getDataProviderUUID())
                .build();
    }

    public static Set<DataProviderInstanceInfo> fromSetOfAcctDataProviderInstance(
        Set<AcctDataProviderInstance> setOfAcctDataProviderInstance
    ) {
        if (setOfAcctDataProviderInstance == null) {
            return null;
        }

        return
            setOfAcctDataProviderInstance.stream()
                .map(DataProviderInstanceInfoMapper::fromAcctDataProviderInstance)
                .collect(Collectors.toSet());
    }

}
