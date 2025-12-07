package com.desolatetimelines.acct.reporting.service;

import com.desolatetimelines.acct.common.model.ObjectTypes;
import com.desolatetimelines.acct.reporting.data.service.AcctReportingDataService;
import com.desolatetimelines.acct.reporting.dataprovider.model.AcctReportingDataProviderId;
import com.desolatetimelines.acct.reporting.dataprovider.service.AcctReportingDataCompiler;
import com.desolatetimelines.acct.reporting.exception.AcctReportingServiceException;
import com.desolatetimelines.acct.reporting.exception.AcctReportingServiceNotFoundException;
import com.desolatetimelines.acct.reporting.exception.AcctReportingServiceSecurityException;
import com.desolatetimelines.acct.reporting.mapper.DashboardReadablePropertiesMapper;
import com.desolatetimelines.acct.reporting.model.*;
import com.desolatetimelines.acct.security.client.data.AcctSecurityClientService;
import com.desolatetimelines.acct.security.client.model.ResourceType;
import com.desolatetimelines.acct.security.client.model.UserResourceAccessRights;
import com.desolatetimelines.acct.security.ws.endpoint.model.DashboardOwner;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnedDashboardsGroup;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;
import com.desolatetimelines.acct.usage.ws.client.RESTUsageEndpointClient;
import com.desolatetimelines.acct.usage.ws.model.ServiceItemTypesList;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.desolatetimelines.acct.common.model.ObjectTypes.*;
import static com.desolatetimelines.acct.reporting.mapper.AcctDataProviderInstanceRuntimeParameterDataTypeMapper.fromDataProviderParameterDataType;
import static com.desolatetimelines.acct.reporting.mapper.AcctDataProviderInstanceRuntimeParametersMapper.fromAcctReportingDataProviderReportParameterSpec;
import static com.desolatetimelines.acct.reporting.privilegesprovider.model.ReportingPrivilegeIds.DASHBOARDS_DELETE_GROUP;

/**
 * Reporting services layer
 */
@Service
public class AcctReportingService {

    private final RESTUsageEndpointClient usageEndpointClient;

    private final AcctSecurityClientService securityClientService;

    private final AcctReportingErrorCodesRegistryService errors;

    private final AcctReportingDataService dataService;

    private final AcctReportingDataCompiler reportCompiler;

    private final String applicationName;

    private final String contextPath;

    public AcctReportingService(
        RESTUsageEndpointClient usageEndpointClient,
        AcctSecurityClientService securityClientService,
        AcctReportingErrorCodesRegistryService errors,
        AcctReportingDataService dataService,
        AcctReportingDataCompiler reportCompiler,
        @Value("${REPORTING_APPLICATION_NAME}") String applicationName,
        @Value("${REPORTING_SERVER_CONTEXT_PATH}") String contextPath
    ) {
        this.usageEndpointClient = usageEndpointClient;
        this.securityClientService = securityClientService;
        this.errors = errors;
        this.dataService = dataService;
        this.reportCompiler = reportCompiler;
        this.applicationName = applicationName;
        this.contextPath = contextPath;
    }

    /**
     * Registers in-use item types with the usage service upon startup
     */
    @SuppressWarnings("unused")
    @EventListener(ApplicationReadyEvent.class)
    protected void registerInUseObjectTypes() {
        usageEndpointClient.registerItemTypesForService(
            ServiceItemTypesList.builder()
                .withServiceName(applicationName)
                .withServiceContextPath(contextPath)
                .withItemType(List.of(
                    ObjectTypes.ICON.name(),
                    ObjectTypes.WORKSPACE.name()
                ))
                .build()
        );
    }

    /**
     * Returns the UUIDs of any used items of the given type and that can be found in the given list
     *
     * @param objectType the given type
     * @param itemUUIDs  the given list
     */
    public Collection<String> getInUseItemUUIDs(String objectType, Collection<String> itemUUIDs) {
        // If the object type is ICON then search dashboards for used icons
        if (Objects.equals(objectType, ObjectTypes.ICON.name())) {
            // Find any dashboards that are using any of the icons with the given UUIDs
            return
                dataService.findDashboardsByDashboardIconUUIDIn(itemUUIDs)
                    .stream()
                    .map(AcctDashboard::getDashboardIconUUID)
                    .toList();
        }

        // If the object type is WORKSPACE then search dashboards for used workspaces
        if (Objects.equals(objectType, ObjectTypes.WORKSPACE.name())) {
            // Find any dashboards that are using any of the workspaces with the given UUIDs
            return
                dataService.findDashboardsByWorkspaceUUIDIn(itemUUIDs)
                    .stream()
                    .map(AcctDashboard::getDashboardIconUUID)
                    .toList();
        }


        // If this point has been reached, it means that either the item type is not supported
        // or the code for handling the object type is missing from above
        throw new IllegalArgumentException("Object type [" + objectType + "] not supported");
    }

    /**
     * Creates or updates a dashboard with the properties in the referenced dashboard properties container,
     * within the workspace referenced via the given workspace UUID. If a dashboard UUID is given, then the
     * existing dashboard is identified and edited. If the dashboard UUID is not given, then a new dashboard
     * is created.
     *
     * @param workspaceUUID    The given workspace UUID
     * @param dashboardUUID    Uniquely identifies the dashboard to be edited. Set to null if the intention
     *                         is to create a new dashboard.
     * @param dashboardDetails The referenced dashboard properties container
     * @return a reference to the created or updated dashboard
     */
    @Transactional
    public AcctDashboard saveDashboard(
        String workspaceUUID,
        String dashboardUUID,
        DashboardDetails dashboardDetails,
        String userUUID
    ) {
        // Find the dashboard with the given dashboard UUID or create a new one in case the UUID is not given
        // If a dashboard UUID was given, but the dashboard does not exist, throw an exception.
        final AcctDashboard dashboard =
            Optional
                .ofNullable(dashboardUUID)
                .map(dataService::findDashboardByDashboardUUID)
                .orElseGet(() ->
                    Optional
                        .ofNullable(dataService.createNewDashboard())
                        .map(dash -> {
                            dash.setDashboardUUID(UUID.randomUUID().toString());
                            return dash;
                        })
                )
                .orElseThrow(() -> new AcctReportingServiceNotFoundException(errors, DASHBOARD, dashboardUUID));

        // Update the dashboard's properties
        dashboard.setWorkspaceUUID(workspaceUUID);
        dashboard.setDashboardName(dashboardDetails.dashboardName());
        dashboard.setDashboardDescription(dashboardDetails.dashboardDescription());
        dashboard.setDashboardIconUUID(dashboardDetails.dashboardIconUUID());

        // Persist the dashboard
        final AcctDashboard savedDashboard = dataService.saveDashboard(dashboard);

        // Create the user/dashboard ownership relation
        securityClientService.addDashboardOwner(
            DashboardOwner.builder()
                .withOwnerType(OwnerType.USER)
                .withOwnerUUID(userUUID)
                .withDashboardUUID(savedDashboard.getDashboardUUID())
                .build()
        );

        // Return a reference to the persisted dashboard
        return savedDashboard;
    }

    /**
     * Retrieves two sets of {@link com.desolatetimelines.acct.reporting.model.DashboardReadableProperties dashboard properties}
     * for: <ul>
     * <li>the dashboards that are directly accessible by the user</li>
     * <li>the dashboards that are accessible by the user via a users group</li>
     * </ul>
     * The set of dashboards accessible via users groups is retrieved with the help of the security service
     *
     * @param workspaceUUID The UUID of the workspace that the dashboards are part of
     * @param userUUID      The UUID of the user requesting to list the dashboards
     * @return a container for the two aforementioned sets of dashboards
     */
    public DashboardsContainer readUserAccessibleDashboards(String workspaceUUID, String userUUID) {
        // Fetch the lists of user-accessible dashboards
        final OwnedDashboardsGroup userAccessibleDashboard =
            securityClientService.getUserAccessibleDashboards(userUUID);

        // Fetch the group dashboards that are part of the workspaces
        final Collection<AcctDashboard> groupDashboards =
            dataService.findDashboardsByWorkspaceUUIDAndDashboardUUIDIn(
                workspaceUUID,
                userAccessibleDashboard.groupDashboards()
            );

        // Fetch the user dashboards that are part of the workspaces
        final Collection<AcctDashboard> userDashboards =
            dataService.findDashboardsByWorkspaceUUIDAndDashboardUUIDIn(
                workspaceUUID,
                userAccessibleDashboard.userDashboards()
            );

        // Build the container and return a reference
        return
            DashboardsContainer.builder()
                .withUserDashboards(userDashboards.stream().map(DashboardReadablePropertiesMapper::fromAcctDashboard).toList())
                .withGroupDashboards(groupDashboards.stream().map(DashboardReadablePropertiesMapper::fromAcctDashboard).toList())
                .build();
    }

    /**
     * Deletes the referenced dashboard from the referenced workspace, if accessible by the referenced user
     * either directly or indirectly via a group
     *
     * @param workspaceUUID  Unique identifier for the referenced workspace
     * @param dashboardUUID  Unique identifier for the referenced dashboard
     * @param userUUID       Unique identifier for the referenced user
     * @param privilegeNames Collection that contains the names of all the privileges given to the user
     */
    @Transactional
    public void deleteDashboard(
        String workspaceUUID,
        String dashboardUUID,
        String userUUID,
        Collection<String> privilegeNames
    ) {
        // Check if the user owns the dashboard directly
        final boolean isUserDashboard =
            securityClientService.resourceIsAccessibleToUser(
                ResourceType.DASHBOARD,
                userUUID,
                dashboardUUID,
                UserResourceAccessRights.builder()
                    .withOwnResources(true)
                    .withGroupResources(false)
                    .withAnyResources(false)
                    .build()
            );

        // Check if the user owns the dashboard via a users group
        final boolean isGroupDashboard =
            securityClientService.resourceIsAccessibleToUser(
                ResourceType.DASHBOARD,
                userUUID,
                dashboardUUID,
                UserResourceAccessRights.builder()
                    .withOwnResources(false)
                    .withGroupResources(true)
                    .withAnyResources(false)
                    .build()
            );

        // The user does not have the rights to delete the dashboard if the dashboard is not owned
        // by the user or if the user doesn't have the right to delete group dashboards
        if (!(isUserDashboard || (isGroupDashboard && privilegeNames.contains(DASHBOARDS_DELETE_GROUP)))) {
            throw new AcctReportingServiceSecurityException(
                errors,
                DASHBOARD,
                dashboardUUID
            );
        }

        // If the user has the proper access right, then retrieve the dashboard or throw an exception if not found
        final AcctDashboard dashboard =
            dataService.findDashboardsByWorkspaceUUIDAndDashboardUUIDIn(workspaceUUID, List.of(dashboardUUID)).stream()
                .findFirst()
                .orElseThrow(() -> new AcctReportingServiceNotFoundException(errors, DASHBOARD, dashboardUUID));

        // Delete the dashboard ownership records
        securityClientService.deleteAllDashboardOwnersByDashboardUUID(dashboardUUID);

        // Delete the dashboard
        dataService.deleteDashboard(dashboard);

    }

    /**
     * Returns a set of {@link AcctReportingDataProviderId data provider meta-data entries} that
     * provide information about the registered data providers, which are available for use in
     * the data provisioning stage of various reports.
     */
    public Set<AcctReportingDataProviderId> getDataProviders() {
        return reportCompiler.getDataProviderIds();
    }

    /**
     * Creates a new {@link AcctDataProviderInstance data provider instance} or updates an existing one.
     *
     * @param dataProviderInstanceUUID    The UUID that identifies the data provider instance to be updated.
     *                                    If this parameter is missing, a new data provider instance is created.
     * @param dataProviderInstanceDetails Container for the attributes of the data provider instance,
     *                                    including {@link AcctDataProviderInstanceProperty instance properties}
     *                                    and {@link AcctDataProviderInstanceRuntimeParameter runtime parameters}.
     *                                    In case an existing data provider instance is updated, any existing
     *                                    instance properties or runtime parameters are removed and replaced
     *                                    by the ones contained here.
     * @return the UUID of the persisted data provider instance.
     */
    @Transactional
    public String saveDataProviderInstance(
        String dataProviderInstanceUUID,
        DataProviderInstanceDetails dataProviderInstanceDetails
    ) {
        // Make sure the referenced data provider exists before registering an instance
        final AcctReportingDataProviderId dataProviderId =
            findDataProvider(dataProviderInstanceDetails.dataProviderUUID());

        // Make sure all the instance properties have been provided with values
        final Set<String> suppliedInstancePropertyNames =
            dataProviderInstanceDetails.instanceProperties().stream()
                .map(DataProviderInstanceDetails.DataProviderInstanceProperty::propertyName)
                .collect(Collectors.toSet());

        if (!dataProviderId.instanceProperties().stream()
            .allMatch(p -> suppliedInstancePropertyNames.contains(p.name()))
        ) {
            throw new AcctReportingServiceException(errors.DATA_PROVIDER_INSTANCE_PROPERTY_NOT_SUPPLIED);
        }

        // If the data provider instance UUID was supplied, then find it in the database
        // or fail if not found. If the data provider instance UUID was not supplied, then
        // create a new data provider instance.
        final AcctDataProviderInstance dataProviderInstance =
            Optional
                .ofNullable(dataProviderInstanceUUID)
                .map(this::findDataProviderInstance)
                .orElseGet(() -> {
                    final AcctDataProviderInstance newInstance = dataService.createNewDataProviderInstance();
                    newInstance.setDataProviderInstanceUUID(UUID.randomUUID().toString());
                    return newInstance;
                });


        // Set the properties
        dataProviderInstance.setDataProviderInstanceName(dataProviderInstanceDetails.name());
        dataProviderInstance.setDataProviderUUID(dataProviderInstanceDetails.dataProviderUUID());

        // Save the data provider instance
        final AcctDataProviderInstance savedDataProviderInstance =
            dataService.saveDataProviderInstance(dataProviderInstance);

        // Fetch any existing data provider instance properties
        final Set<AcctDataProviderInstanceProperty> existingInstanceProperties =
            dataService.findAllDataProviderInstancePropertiesByDataProviderInstance(
                savedDataProviderInstance
            );

        // Fetch any existing data provider instance runtime parameters
        final Set<AcctDataProviderInstanceRuntimeParameter> existingRuntimeParameters =
            dataService.findAllDataProviderInstanceRuntimeParametersByDataProviderInstance(
                savedDataProviderInstance
            );

        // Create or update the instance properties for the data provider instance
        dataProviderInstanceDetails.instanceProperties().forEach(instanceProperty -> {
            final AcctDataProviderInstanceProperty storedInstanceProperty =
                existingInstanceProperties.stream()
                    .filter(p -> Objects.equals(p.getPropertyName(), instanceProperty.propertyName()))
                    .findFirst()
                    .orElseGet(dataService::createNewDataProviderInstanceProperty);

            storedInstanceProperty.setDataProviderInstance(savedDataProviderInstance);
            storedInstanceProperty.setPropertyName(instanceProperty.propertyName());
            storedInstanceProperty.setPropertyValue(instanceProperty.propertyValue());

            dataService.saveDataProviderInstanceProperty(storedInstanceProperty);
        });

        // Create or update the runtime parameters for the data provider instance
        dataProviderInstanceDetails.runtimeParameters().forEach(runtimeParameter -> {
            final AcctDataProviderInstanceRuntimeParameter storedRuntimeParameter =
                existingRuntimeParameters.stream()
                    .filter(p -> Objects.equals(p.getParameterName(), runtimeParameter.parameterName()))
                    .findFirst()
                    .orElseGet(dataService::createNewDataProviderInstanceRuntimeParameter);

            storedRuntimeParameter.setDataProviderInstance(savedDataProviderInstance);
            storedRuntimeParameter.setParameterName(runtimeParameter.parameterName());
            storedRuntimeParameter.setParameterDefaultValue(runtimeParameter.parameterDefaultValue());
            storedRuntimeParameter.setMandatory(runtimeParameter.mandatory());
            storedRuntimeParameter.setParameterDataType(fromDataProviderParameterDataType(runtimeParameter.parameterDataType()));

            dataService.saveDataProviderInstanceRuntimeParameter(storedRuntimeParameter);
        });

        // Delete any runtime parameters that have not been supplied in the request
        existingRuntimeParameters.stream()
            .filter(p -> dataProviderInstanceDetails.runtimeParameters().stream().noneMatch(sp -> Objects.equals(sp.parameterName(), p.getParameterName())))
            .forEach(dataService::deleteDataProviderInstanceRuntimeParameter);

        // Return the UUID of the saved data provider instance
        return savedDataProviderInstance.getDataProviderInstanceUUID();
    }

    public Set<AcctDataProviderInstance> getDataProviderInstances() {
        return dataService.findAllAcctDataProviderInstances();
    }

    public Set<AcctDataProviderInstanceRuntimeParameter> getDataProviderInstanceRuntimeParameters(
        String dataProviderInstanceUUID
    ) {
        // Get the data provider instance or fail
        final AcctDataProviderInstance dataProviderInstance =
            findDataProviderInstance(dataProviderInstanceUUID);

        // Get the data provider referenced by the data provider instance or fail
        final AcctReportingDataProviderId dataProviderId =
            findDataProvider(dataProviderInstance.getDataProviderUUID());

        // Get any runtime parameters that might be registered for the data provider instance
        final Stream<AcctDataProviderInstanceRuntimeParameter> instanceRuntimeParameters =
            dataService.findAllDataProviderInstanceRuntimeParametersByDataProviderInstance(
                dataProviderInstance
            ).stream();

        // Get any runtime parameters that might be defined by the data provider
        final Stream<AcctDataProviderInstanceRuntimeParameter> dataProviderRuntimeParameters =
            dataProviderId.parameters().stream()
                .map(p ->
                    fromAcctReportingDataProviderReportParameterSpec(p, dataProviderInstance)
                );

        // Join the runtime parameters provided by both the data provider and the data provider instance
        return
            Stream
                .concat(
                    dataProviderRuntimeParameters,
                    instanceRuntimeParameters
                )
                .collect(Collectors.toSet());
    }

    private AcctDataProviderInstance findDataProviderInstance(String dataProviderInstanceUUID) {
        return
            dataService.findDataProviderInstanceByDataProviderInstanceUUID(dataProviderInstanceUUID)
                .orElseThrow(
                    () -> new AcctReportingServiceNotFoundException(
                        errors,
                        DATA_PROVIDER_INSTANCE,
                        dataProviderInstanceUUID
                    )
                );
    }

    private AcctReportingDataProviderId findDataProvider(String dataProviderUUID) {
        return
            getDataProviders().stream()
                .filter(dpId ->
                    Objects.equals(
                        dpId.uuid().toString(),
                        dataProviderUUID
                    )
                )
                .findFirst()
                .orElseThrow(
                    () -> new AcctReportingServiceNotFoundException(
                        errors,
                        DATA_PROVIDER,
                        dataProviderUUID
                    )
                );
    }

}
