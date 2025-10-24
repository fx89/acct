package com.desolatetimelines.acct.reporting.dataprovider.service;

import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingDataProviderInitializationException;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingUnsupportedDataProviderException;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderId;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * The data provider service provider is the entry point of any module that adds
 * {@link AcctReportingDataProvider data providers} to the ACCT reporting function.
 * It is responsible for provisioning instances of the data providers supported by
 * the current module. Each provisioned data provider instance is initialized with
 * the given instance properties.
 */
public abstract class AbstractAcctReportingDataProviderServiceProvider {

    /**
     * Returns a set of the identifiers of all the {@link AcctReportingDataProvider data providers}
     * supported by the current module
     */
    public abstract Set<AcctReportingDataProviderId> getSupportedDataProviderIds();

    public AcctReportingDataProviderId findSupportedDataProviderByDataProviderUUID(UUID dataProviderUUID) {
        return
            getSupportedDataProviderIds()
                .stream()
                .filter(dataProviderId -> dataProviderId.uuid() == dataProviderUUID)
                .findFirst()
                .orElseThrow(() -> new AcctReportingUnsupportedDataProviderException(
                    "Data provider with UUID = " + dataProviderUUID + " is not supported"
                ));
    }

    public AcctReportingDataProvider provideByUUIDAndInstanceProperties(
        UUID dataProviderUUID,
        Map<String, String> dataProviderInstanceProperties
    ) throws AcctReportingDataProviderInitializationException {
        // Acquire a new instance of the referenced data provider
        final AcctReportingDataProvider dataProviderInstance = newInstance(dataProviderUUID);

        // Initialize the newly acquired data provider instance
        dataProviderInstance.initialize(dataProviderInstanceProperties);

        // Return a reference to the initialized data provider instance
        return dataProviderInstance;
    }

    /**
     * Creates a new instance of the data provider identified by the given data provider UUID
     *
     * @param dataProviderUUID Uniquely identifies the data provider across the ACCT ecosystem
     * @return a reference to the newly created data provider instance
     * @throws AcctReportingUnsupportedDataProviderException if the current module does support the data
     *                                                       provider with the given data provider UUID
     */
    abstract AcctReportingDataProvider newInstance(UUID dataProviderUUID)
        throws AcctReportingUnsupportedDataProviderException;

}
