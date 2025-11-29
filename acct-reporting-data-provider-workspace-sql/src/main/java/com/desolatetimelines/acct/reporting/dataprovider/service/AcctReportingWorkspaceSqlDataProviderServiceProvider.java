package com.desolatetimelines.acct.reporting.dataprovider.service;

import com.desolatetimelines.acct.reporting.dataprovider.exception.AcctReportingUnsupportedDataProviderException;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderId;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderInstancePropertySpec;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderReportParameterType;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static com.desolatetimelines.acct.reporting.dataprovider.service.Constants.INSTANCE_PROPERTY_NAME_SQL;

/**
 * {@link AbstractAcctReportingDataProviderServiceProvider service provider} for the
 * {@link AcctReportingWorkspaceSqlDataProvider workspace SQL data provider}
 */
@Service
public class AcctReportingWorkspaceSqlDataProviderServiceProvider
    extends AbstractAcctReportingDataProviderServiceProvider {

    /**
     * The UUID of the workspace SQL data provider
     */
    private static final UUID WORKSPACE_SQL_DATA_PROVIDER_UUID =
        UUID.fromString("726151dd-23d0-47cd-8692-994c39b33c43");

    private static final Set<AcctReportingDataProviderId> SUPPORTED_DATA_PROVIDER_IDS =
        Set.of(
            AcctReportingDataProviderId.builder()
                .withUuid(WORKSPACE_SQL_DATA_PROVIDER_UUID)
                .withHumanReadableName("Workspace SQL")
                .withDescription("Allows running raw SQL on the workspace schema to produce user-defined reports")
                .withInstanceProperty(
                    new AcctReportingDataProviderInstancePropertySpec(
                        INSTANCE_PROPERTY_NAME_SQL,
                        AcctReportingDataProviderReportParameterType.STRING
                    )
                )
                .build()
        );

    private final AcctWorkspaceDataSourceProvider dataSourceProvider;

    public AcctReportingWorkspaceSqlDataProviderServiceProvider(
        AcctWorkspaceDataSourceProvider dataSourceProvider
    ) {
        this.dataSourceProvider = dataSourceProvider;
    }

    @Override
    public Set<AcctReportingDataProviderId> getSupportedDataProviderIds() {
        return SUPPORTED_DATA_PROVIDER_IDS;
    }

    @Override
    AcctReportingDataProvider newInstance(UUID dataProviderUUID) throws AcctReportingUnsupportedDataProviderException {
        if (Objects.equals(WORKSPACE_SQL_DATA_PROVIDER_UUID, dataProviderUUID)) {
            return new AcctReportingWorkspaceSqlDataProvider(dataSourceProvider);
        }

        throw new AcctReportingUnsupportedDataProviderException(
            "The data provider with the given UUID is not supported by this service provider"
        );
    }

}
