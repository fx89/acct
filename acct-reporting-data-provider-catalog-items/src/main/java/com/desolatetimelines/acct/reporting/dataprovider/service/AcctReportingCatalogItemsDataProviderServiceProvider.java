package com.desolatetimelines.acct.reporting.dataprovider.service;

import com.desolatetimelines.acct.catalog.ws.client.RESTBanksEndpointClient;
import com.desolatetimelines.acct.catalog.ws.client.RESTCurrenciesEndpointClient;
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

    /**
     * The UUID of the catalog banks data provider
     */
    private static final UUID CATALOG_BANKS_DATA_PROVIDER_UUID =
        UUID.fromString("81346e9f-7e8c-41e8-95a4-44d17279c37b");

    /**
     * The UUID of the catalog currencies data provider
     */
    private static final UUID CATALOG_CURRENCIES_DATA_PROVIDER_UUID =
        UUID.fromString("ba8c15e4-6abf-4dc1-91c0-7a8462add0f8");

    private static final Set<AcctReportingDataProviderId> SUPPORTED_DATA_PROVIDER_IDS =
        Set.of(
            AcctReportingDataProviderId.builder()
                .withUuid(CATALOG_ITEMS_DATA_PROVIDER_UUID)
                .withHumanReadableName("Catalog items")
                .withDescription("Provides a list of all the registered catalog items together with the sub-categories and categories that they are part of")
                .build(),

            AcctReportingDataProviderId.builder()
                .withUuid(CATALOG_BANKS_DATA_PROVIDER_UUID)
                .withHumanReadableName("Catalog banks")
                .withDescription("Provides a list of all the banks that are registered in the catalog")
                .build(),

            AcctReportingDataProviderId.builder()
                .withUuid(CATALOG_CURRENCIES_DATA_PROVIDER_UUID)
                .withHumanReadableName("Catalog currencies")
                .withDescription("Provides a list of all the currencies that are registered in the catalog")
                .build()
        );

    private final RESTItemsEndpointClient itemsEndpointClient;

    private final RESTBanksEndpointClient banksEndpointClient;

    private final RESTCurrenciesEndpointClient currenciesEndpointClient;

    public AcctReportingCatalogItemsDataProviderServiceProvider(
        RESTItemsEndpointClient itemsEndpointClient,
        RESTBanksEndpointClient banksEndpointClient,
        RESTCurrenciesEndpointClient currenciesEndpointClient
    ) {
        this.itemsEndpointClient = itemsEndpointClient;
        this.banksEndpointClient = banksEndpointClient;
        this.currenciesEndpointClient = currenciesEndpointClient;
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

        if (Objects.equals(CATALOG_BANKS_DATA_PROVIDER_UUID, dataProviderUUID)) {
            return new AcctReportingCatalogBanksDataProvider(banksEndpointClient);
        }

        if (Objects.equals(CATALOG_CURRENCIES_DATA_PROVIDER_UUID, dataProviderUUID)) {
            return new AcctReportingCatalogCurrenciesDataProvider(currenciesEndpointClient);
        }

        throw new AcctReportingUnsupportedDataProviderException(
            "The data provider with the given UUID is not supported by this service provider"
        );
    }

}
