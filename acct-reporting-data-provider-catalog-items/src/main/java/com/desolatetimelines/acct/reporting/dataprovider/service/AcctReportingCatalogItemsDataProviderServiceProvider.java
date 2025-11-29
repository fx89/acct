package com.desolatetimelines.acct.reporting.dataprovider.service;

import com.desolatetimelines.acct.catalog.ws.client.RESTItemsEndpointClient;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingUnsupportedDataProviderException;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderId;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * {@link AbstractAcctReportingDataProviderServiceProvider service provider} for the
 * {@link AcctReportingCatalogItemsDataProvider catalog items data provider}
 */
@Service
public class AcctReportingCatalogItemsDataProviderServiceProvider
    extends AbstractAcctReportingDataProviderServiceProvider {

    /**
     * The UUID of the catalog items data provider
     */
    private static final UUID CATALOG_ITEMS_DATA_PROVIDER_UUID =
        UUID.fromString("fba6cbbd-d338-488f-867e-e89362aa06ba");

    private static final Set<AcctReportingDataProviderId> SUPPORTED_DATA_PROVIDER_IDS =
        Set.of(
            AcctReportingDataProviderId.builder()
                .withUuid(CATALOG_ITEMS_DATA_PROVIDER_UUID)
                .withHumanReadableName("Catalog items")
                .withDescription("Provides a list of all the registered catalog items together with the sub-categories and categories that they are part of")
                .build()
        );

    private final RESTItemsEndpointClient itemsEndpointClient;

    public AcctReportingCatalogItemsDataProviderServiceProvider(
        RESTItemsEndpointClient itemsEndpointClient
    ) {
        this.itemsEndpointClient = itemsEndpointClient;
    }

    @Override
    public Set<AcctReportingDataProviderId> getSupportedDataProviderIds() {
        return SUPPORTED_DATA_PROVIDER_IDS;
    }

    @Override
    AcctReportingDataProvider newInstance(UUID dataProviderUUID) throws AcctReportingUnsupportedDataProviderException {
        if (Objects.equals(CATALOG_ITEMS_DATA_PROVIDER_UUID, dataProviderUUID)) {
            return new AcctReportingCatalogItemsDataProvider(itemsEndpointClient);
        }

        throw new AcctReportingUnsupportedDataProviderException(
            "The data provider with the given UUID is not supported by this service provider"
        );
    }

}
