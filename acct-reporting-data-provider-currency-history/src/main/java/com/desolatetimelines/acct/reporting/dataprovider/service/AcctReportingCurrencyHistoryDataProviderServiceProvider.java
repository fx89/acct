package com.desolatetimelines.acct.reporting.dataprovider.service;

import com.desolatetimelines.acct.catalog.ws.client.RESTBanksEndpointClient;
import com.desolatetimelines.acct.catalog.ws.client.RESTCurrenciesEndpointClient;
import com.desolatetimelines.acct.currency.ws.client.RESTMonitoredCurrenciesEndpointClient;
import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingUnsupportedDataProviderException;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderId;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderInstancePropertySpec;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderReportParameterType;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static com.desolatetimelines.acct.reporting.dataprovider.service.Constants.*;

/**
 * {@link AbstractAcctReportingDataProviderServiceProvider service provider} for the
 * {@link AcctReportingCurrencyHistoryDataProvider currency history data provider}
 */
@Service
public class AcctReportingCurrencyHistoryDataProviderServiceProvider
    extends AbstractAcctReportingDataProviderServiceProvider {

    /**
     * The UUID of the currency history data provider
     */
    private static final UUID CURRENCY_HISTORY_DATA_PROVIDER_UUID =
        UUID.fromString("3e27e13e-704b-42df-9b6a-6bcbef93b1f5");

    private static final Set<AcctReportingDataProviderId> SUPPORTED_DATA_PROVIDER_IDS =
        Set.of(
            AcctReportingDataProviderId.builder()
                .withUuid(CURRENCY_HISTORY_DATA_PROVIDER_UUID)
                .withHumanReadableName("Currency history")
                .withDescription("Provides history records for a chosen currency code and a given time interval")
                .withInstanceProperty(
                    new AcctReportingDataProviderInstancePropertySpec(
                        INSTANCE_PROPERTY_NAME_CURRENCY_CODE,
                        AcctReportingDataProviderReportParameterType.STRING
                    )
                )
                .withInstanceProperty(
                    new AcctReportingDataProviderInstancePropertySpec(
                        INSTANCE_PROPERTY_NAME_BANK_CODE,
                        AcctReportingDataProviderReportParameterType.STRING
                    )
                )
                .withInstanceProperty(
                    new AcctReportingDataProviderInstancePropertySpec(
                        INSTANCE_PROPERTY_NAME_NUM_DAYS_AGO,
                        AcctReportingDataProviderReportParameterType.NUMERIC
                    )
                )
                .build()
        );

    private final RESTCurrenciesEndpointClient currenciesEndpointClient;

    private final RESTBanksEndpointClient banksEndpointClient;

    private final RESTMonitoredCurrenciesEndpointClient monitoredCurrenciesEndpointClient;

    public AcctReportingCurrencyHistoryDataProviderServiceProvider(
        RESTCurrenciesEndpointClient currenciesEndpointClient,
        RESTBanksEndpointClient banksEndpointClient,
        RESTMonitoredCurrenciesEndpointClient monitoredCurrenciesEndpointClient
    ) {
        this.currenciesEndpointClient = currenciesEndpointClient;
        this.banksEndpointClient = banksEndpointClient;
        this.monitoredCurrenciesEndpointClient = monitoredCurrenciesEndpointClient;
    }

    @Override
    public Set<AcctReportingDataProviderId> getSupportedDataProviderIds() {
        return SUPPORTED_DATA_PROVIDER_IDS;
    }

    @Override
    AcctReportingDataProvider newInstance(UUID dataProviderUUID) throws AcctReportingUnsupportedDataProviderException {
        if (Objects.equals(CURRENCY_HISTORY_DATA_PROVIDER_UUID, dataProviderUUID)) {
            return new AcctReportingCurrencyHistoryDataProvider(
                currenciesEndpointClient,
                banksEndpointClient,
                monitoredCurrenciesEndpointClient
            );
        }

        throw new AcctReportingUnsupportedDataProviderException(
            "The data provider with the given UUID is not supported by this service provider"
        );
    }

}
