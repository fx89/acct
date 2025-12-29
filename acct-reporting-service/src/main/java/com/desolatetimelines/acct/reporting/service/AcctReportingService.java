package com.desolatetimelines.acct.reporting.service;

import com.desolatetimelines.acct.common.model.ObjectTypes;
import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.common.utils.Streams;
import com.desolatetimelines.acct.reporting.data.service.AcctReportingDataService;
import com.desolatetimelines.acct.reporting.dataprovider.model.*;
import com.desolatetimelines.acct.reporting.dataprovider.service.AcctReportingDataCompiler;
import com.desolatetimelines.acct.reporting.exception.AcctReportingServiceException;
import com.desolatetimelines.acct.reporting.exception.AcctReportingServiceNotFoundException;
import com.desolatetimelines.acct.reporting.exception.AcctReportingServiceSecurityException;
import com.desolatetimelines.acct.reporting.mapper.AcctReportSeriesMapper;
import com.desolatetimelines.acct.reporting.mapper.DashboardReadablePropertiesMapper;
import com.desolatetimelines.acct.reporting.model.*;
import com.desolatetimelines.acct.security.client.data.AcctSecurityClientService;
import com.desolatetimelines.acct.security.client.model.ResourceType;
import com.desolatetimelines.acct.security.client.model.UserResourceAccessRights;
import com.desolatetimelines.acct.security.ws.endpoint.model.*;
import com.desolatetimelines.acct.usage.ws.client.RESTUsageEndpointClient;
import com.desolatetimelines.acct.usage.ws.model.ServiceItemTypesList;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.desolatetimelines.acct.common.model.ObjectTypes.*;
import static com.desolatetimelines.acct.common.utils.Collections.intersect;
import static com.desolatetimelines.acct.common.utils.Collections.minus;
import static com.desolatetimelines.acct.reporting.mapper.AcctDataProviderInstancePropertiesMapper.toDataProviderInstanceProperties;
import static com.desolatetimelines.acct.reporting.mapper.AcctDataProviderInstanceRuntimeParameterDataTypeMapper.fromDataProviderParameterDataType;
import static com.desolatetimelines.acct.reporting.mapper.AcctDataProviderInstanceRuntimeParameterDataTypeMapper.toAcctReportingDataProviderReportParameterType;
import static com.desolatetimelines.acct.reporting.mapper.AcctDataProviderInstanceRuntimeParametersMapper.*;
import static com.desolatetimelines.acct.reporting.privilegesprovider.model.ReportingPrivilegeIds.DASHBOARDS_DELETE_GROUP;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toSet;

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
                .collect(toSet());

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

    /**
     * Returns a {@link DataProviderInstanceDetails container} with the details of the referenced data
     * provider instance and the related instance properties and instance-defined runtime parameters.
     *
     * @param dataProviderInstanceUUID Unique identifier for the data provider instance whose details
     *                                 are being retrieved.
     */
    public DataProviderInstanceDetails getDataProviderInstanceDetails(String dataProviderInstanceUUID) {
        // Find the data provider instance
        final AcctDataProviderInstance dataProviderInstance =
            findDataProviderInstance(dataProviderInstanceUUID);

        // Find the instance properties for the instance
        final Set<AcctDataProviderInstanceProperty> instanceProperties =
            dataService.findAllDataProviderInstancePropertiesByDataProviderInstance(dataProviderInstance);

        // Find the runtime parameters for the instance
        final Set<AcctDataProviderInstanceRuntimeParameter> runtimeParameters =
            dataService.findAllDataProviderInstanceRuntimeParametersByDataProviderInstance(dataProviderInstance);

        // Put it all together
        return
            DataProviderInstanceDetails.builder()
                .withName(dataProviderInstance.getDataProviderInstanceName())
                .withDataProviderUUID(dataProviderInstance.getDataProviderUUID())
                .withInstanceProperties(toDataProviderInstanceProperties(instanceProperties))
                .withRuntimeParameters(
                    toDataProviderInstanceDetailsDataProviderInstanceRuntimeParameters(runtimeParameters)
                )
                .build();
    }

    /**
     * Returns a set of {@link AcctDataProviderInstanceRuntimeParameter data provider instnace runtime parameters}
     * that are defined by either the referenced data provider instance or the related data provider.
     *
     * @param dataProviderInstanceUUID Unique identifier for the data provider instance whose runtime parameters
     *                                 are being retrieved.
     */
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
                .collect(toSet());
    }

    public AcctReportingDataProviderDataSet getDataProviderInstanceDataSet(
        String dataProviderInstanceUUID,
        Map<String, String> runtimeParameters
    ) {
        // Find the data provider instance or fail
        final AcctDataProviderInstance dataProviderInstance =
            findDataProviderInstance(dataProviderInstanceUUID);

        // Find the data provider instance properties
        final Set<AcctDataProviderInstanceProperty> instanceProperties =
            dataService.findAllDataProviderInstancePropertiesByDataProviderInstance(
                dataProviderInstance
            );

        // Get the instance properties into a key/value map
        final Map<String, String> kvmInstanceProperties =
            instanceProperties.stream()
                .collect(
                    Collectors.toMap(
                        AcctDataProviderInstanceProperty::getPropertyName,
                        AcctDataProviderInstanceProperty::getPropertyValue
                    )
                );

        // Get the additional runtime parameters for the instance
        final Set<AcctReportingDataProviderReportParameterSpec> additionalParameters =
            toSetOfAcctReportingDataProviderReportParameterSpecs(
                dataService.findAllDataProviderInstanceRuntimeParametersByDataProviderInstance(
                    dataProviderInstance
                )
            );

        // Create a compilation request that contains only the referenced data provider instance
        // and feed it into the report compiler to produce the requested data set
        return
            reportCompiler.compileReport(
                ReportCompilationRequest.builder()
                    .withInstance(
                        DataProviderInstanceSpecification.builder()
                            .withInstanceName("temp_instance")
                            .withDataProviderInstanceProperties(kvmInstanceProperties)
                            .withDataProviderUUID(UUID.fromString(dataProviderInstance.getDataProviderUUID()))
                            .withAdditionalParameters(additionalParameters)
                            .build()
                    )
                    .withReportSqlStatement("SELECT * from temp_instance")
                    .withReportParameters(runtimeParameters)
                    .build()
            );
    }

    @Transactional
    public void deleteDataProviderInstance(String dataProviderInstanceUUID) {
        // Find the data provider instance or fail
        final AcctDataProviderInstance dataProviderInstance =
            findDataProviderInstance(dataProviderInstanceUUID);

        // Cascade-delete data provider instance
        dataService.cascadeDeleteDataProviderInstance(dataProviderInstance);
    }

    @Transactional
    public AcctReport saveReport(
        String reportUUID,
        ReportDetails reportDetails,
        String userUUID
    ) {
        // Acquire the report by either fetching the referenced report and raising an error if it doesn't exist
        // or creating a new report in case the UUID was not provided.
        final AcctReport report =
            Optional
                .ofNullable(reportUUID)
                .map(this::findReport)
                .orElseGet(() -> {
                    final AcctReport newReport = dataService.createNewReport();
                    newReport.setReportUUID(UUID.randomUUID().toString());
                    return newReport;
                });

        // Set the report properties
        report.setReportName(reportDetails.reportName());
        report.setReportDescription(reportDetails.reportDescription());
        report.setReportSQLStatement(reportDetails.reportSQL());
        report.setReportType(reportDetails.reportType());
        report.setReportCategoryColumnName(reportDetails.reportCategoryColumnName());

        // Save the report
        final AcctReport savedReport = dataService.saveReport(report);

        // Update the report series to match those in the request
        updateReportSeries(savedReport, reportDetails.reportSeries());

        // Update the report data provider instances to match those in the request
        updateReportDataProviderInstances(savedReport, reportDetails.dataProviderInstanceUUIDs());

        // Add the ownership mapping
        securityClientService.addReportOwner(
            ReportOwner.builder()
                .withOwnerType(OwnerType.USER)
                .withOwnerUUID(userUUID)
                .withReportUUID(savedReport.getReportUUID())
                .build()
        );

        // Return a reference to the saved report
        return savedReport;
    }

    public Page<ExtendedReportDetails> findSortedPageOfUserAccessibleReports(
        int pageNumber,
        int pageSize,
        String userUUID
    ) {
        // Fetch the lists of accessible reports
        final OwnedReportsGroup ownedReportsGroup = securityClientService.getUserAccessibleReports(userUUID);

        // concatenate the lists of accessible reports into a single set
        final Set<String> userAccessibleReports =
            Streams.multiConcat(
                ownedReportsGroup.groupReports().stream(),
                ownedReportsGroup.publicReports().stream(),
                ownedReportsGroup.userReports().stream()
            ).collect(toSet());

        // Fetch the requested page
        final Page<AcctReport> pageOfReports =
            dataService.findAllReportsByReportUUIDIn(
                userAccessibleReports,
                pageNumber,
                pageSize
            );

        // Put the page content into a set
        final Set<AcctReport> reportsInPage = new HashSet<>(pageOfReports.data());

        // Fetch the data provider instances for all the reports in the page
        final Set<AcctReportDataProviderInstance> dataProviderInstances =
            dataService.findAllDataProviderInstancesByReportIn(reportsInPage);

        // Fetch the report series for all the reports in the page
        final Set<AcctReportSeries> series =
            dataService.findAllReportSeriesByReportIn(reportsInPage);

        // Map the fetched entities to the response type
        return new Page<>(
            reportsInPage.stream()
                .map(report -> combineExtendedReportDetails(report, dataProviderInstances, series))
                .toList(),
            pageOfReports.numElements(),
            pageOfReports.maxElements()
        );
    }

    public AcctReportingDataProviderDataSet getReportDataWithParameters(
        String reportUUID,
        Map<String, String> parameters,
        String userUUID
    ) {
        // Make sure the user has access to the report
        enforceReportAccessibility(reportUUID, userUUID);

        // Find the report or throw an exception
        final AcctReport report = findReport(reportUUID);

        // Find the data provider instances for the report
        final Set<AcctReportDataProviderInstance> reportDataProviderInstances =
            dataService.findReportDataProviderInstancesByReport(report);

        // Map the found report data provider instances to data provider instances
        final Set<AcctDataProviderInstance> dataProviderInstances =
            reportDataProviderInstances.stream()
                .map(AcctReportDataProviderInstance::getDataProviderInstance)
                .collect(toSet());

        // Build the data provider instance specifications to feed into the report compilation request
        final Set<DataProviderInstanceSpecification> dataProviderInstanceSpecifications =
            buildDataProviderInstanceSpecifications(dataProviderInstances);

        // Build the report compilation request
        final ReportCompilationRequest reportCompilationRequest =
            ReportCompilationRequest.builder()
                .withInstances(dataProviderInstanceSpecifications)
                .withReportParameters(parameters)
                .withReportSqlStatement(report.getReportSQLStatement())
                .build();

        // Execute the report compilation request and retrieve the data set
        return reportCompiler.compileReport(reportCompilationRequest);
    }

    public Set<AcctReportingDataProviderReportParameterSpec> getReportRuntimeParameters(
        String reportUUID,
        String userUUID
    ) {
        // Make sure the user has access to the report
        enforceReportAccessibility(reportUUID, userUUID);

        // Find the report or throw an exception
        final AcctReport report = findReport(reportUUID);

        // Find the data provider instances for the report
        final Set<AcctReportDataProviderInstance> reportDataProviderInstances =
            dataService.findReportDataProviderInstancesByReport(report);

        // Map the found report data provider instances to data provider instances
        final Set<AcctDataProviderInstance> dataProviderInstances =
            reportDataProviderInstances.stream()
                .map(AcctReportDataProviderInstance::getDataProviderInstance)
                .collect(toSet());

        // Build the data provider instance specifications to feed into the report compilation request
        final Set<DataProviderInstanceSpecification> dataProviderInstanceSpecifications =
            buildDataProviderInstanceSpecifications(dataProviderInstances);

        // Get the runtime parameters for any and all the data provider instance specifications
        return reportCompiler.getReportParameters(dataProviderInstanceSpecifications);
    }

    private Set<DataProviderInstanceSpecification> buildDataProviderInstanceSpecifications(
        Set<AcctDataProviderInstance> acctDataProviderInstances
    ) {
        // Find the data provider instance properties for the data provider instances found at the previous step
        final Set<AcctDataProviderInstanceProperty> instanceProperties =
            dataService.findAllDataProviderInstancePropertiesByDataProviderInstanceIn(acctDataProviderInstances);

        // Find additional parameters
        final Set<AcctDataProviderInstanceRuntimeParameter> runtimeParameters =
            dataService.findAllDataProviderInstanceRuntimeParametersByDataProviderInstanceIn(acctDataProviderInstances);

        // Build the set of data provider instance specifications to be fed into the report compilation request builder
        return
            acctDataProviderInstances.stream()
                .map(dataProviderInstance ->
                    DataProviderInstanceSpecification.builder()
                        .withDataProviderUUID(UUID.fromString(dataProviderInstance.getDataProviderUUID()))
                        .withInstanceName(dataProviderInstance.getDataProviderInstanceName())
                        .withDataProviderInstanceProperties(
                            instanceProperties.stream()
                                .filter(instanceProperty ->
                                    Objects.equals(
                                        instanceProperty.getDataProviderInstance(),
                                        dataProviderInstance
                                    )
                                )
                                .collect(Collectors.toMap(
                                    AcctDataProviderInstanceProperty::getPropertyName,
                                    AcctDataProviderInstanceProperty::getPropertyValue
                                ))
                        )
                        .withAdditionalParameters(
                            runtimeParameters.stream()
                                .filter(runtimeParameter ->
                                    Objects.equals(
                                        runtimeParameter.getDataProviderInstance(),
                                        dataProviderInstance
                                    )
                                )
                                .map(runtimeParameter ->
                                    new AcctReportingDataProviderReportParameterSpec(
                                        runtimeParameter.getParameterName(),
                                        toAcctReportingDataProviderReportParameterType(runtimeParameter.getParameterDataType()),
                                        runtimeParameter.isMandatory()
                                    )
                                )
                                .collect(toSet())
                        )
                        .build()
                )
                .collect(toSet());
    }

    private void enforceReportAccessibility(String reportUUID, String userUUID) {
        if (!securityClientService.resourceIsAccessibleToUser(
            ResourceType.REPORT,
            userUUID,
            reportUUID,
            UserResourceAccessRights.builder()
                .withAnyResources(true)
                .withGroupResources(true)
                .withOwnResources(true)
                .build()
        )) {
            throw new AcctReportingServiceSecurityException(
                errors,
                REPORT,
                reportUUID
            );
        }
    }

    private static ExtendedReportDetails combineExtendedReportDetails(
        AcctReport report,
        Set<AcctReportDataProviderInstance> dataProviderInstances,
        Set<AcctReportSeries> series
    ) {
        if (report == null) {
            return null;
        }

        return
            new ExtendedReportDetails(
                report.getReportUUID(),
                ReportDetails.builder()
                    .withReportName(report.getReportName())
                    .withReportDescription(report.getReportDescription())
                    .withReportSQL(report.getReportSQLStatement())
                    .withReportType(report.getReportType())
                    .withReportCategoryColumnName(report.getReportCategoryColumnName())
                    .withDataProviderInstanceUUIDs(
                        dataProviderInstances.stream()
                            .filter(dpi -> Objects.equals(dpi.getReport(), report))
                            .map(dpi -> dpi.getDataProviderInstance().getDataProviderInstanceUUID())
                            .collect(toSet())
                    )
                    .withReportSeries(
                        series.stream()
                            .filter(s -> Objects.equals(s.getReport(), report))
                            .map(AcctReportSeriesMapper::toReportSeriesDetails)
                            .collect(toSet())
                    )
                    .build()
            );
    }

    private void updateReportSeries(AcctReport report, Set<ReportSeriesDetails> reportSeriesDetails) {
        // Fetch any report series that might already have been set
        final Set<AcctReportSeries> reportSeries = dataService.findAllReportSeriesByReport(report);

        // Identify existing report series
        final Set<AcctReportSeries> existingReportSeries =
            intersect(
                reportSeries,
                reportSeriesDetails,
                AcctReportSeries::getReportSeriesName,
                ReportSeriesDetails::reportSeriesName
            );

        // Identify new report series
        final Set<ReportSeriesDetails> newReportSeries =
            minus(
                reportSeriesDetails,
                reportSeries,
                ReportSeriesDetails::reportSeriesName,
                AcctReportSeries::getReportSeriesName
            );

        // Identify removed report series
        final Set<AcctReportSeries> removedReportSeries =
            minus(
                reportSeries,
                reportSeriesDetails,
                AcctReportSeries::getReportSeriesName,
                ReportSeriesDetails::reportSeriesName
            );

        // Update the existing report series
        existingReportSeries.forEach(acctReportSeries -> {
            // Identify the source series
            final ReportSeriesDetails sourceReportSeries =
                reportSeriesDetails.stream()
                    .filter(rsd ->
                        Objects.equals(
                            rsd.reportSeriesName(),
                            acctReportSeries.getReportSeriesName()
                        )
                    )
                    .findFirst()
                    .orElseThrow();

            // Update the series details
            acctReportSeries.setReportColumnName(sourceReportSeries.reportColumnName());
            acctReportSeries.setReportSeriesType(sourceReportSeries.reportSeriesType());

            // Save the series
            dataService.saveReportSeries(acctReportSeries);
        });

        // Add the new report series
        newReportSeries.stream()
            .map(rs -> {
                final AcctReportSeries acctReportSeries = dataService.createNewReportSeries();
                acctReportSeries.setReport(report);

                acctReportSeries.setReportColumnName(rs.reportColumnName());
                acctReportSeries.setReportSeriesName(rs.reportSeriesName());
                acctReportSeries.setReportSeriesType(rs.reportSeriesType());

                return acctReportSeries;
            })
            .forEach(dataService::saveReportSeries);

        // Delete the removed report series
        removedReportSeries.forEach(dataService::deleteReportSeries);
    }

    private void updateReportDataProviderInstances(AcctReport report, Set<String> dataProviderInstanceUUIDs) {
        // Fetch any existing data provider instances mapped to the report
        final Set<AcctReportDataProviderInstance> reportDataProviderInstances =
            dataService.findReportDataProviderInstancesByReport(report);

        // Declare the key extractor function for report data provider instances
        final Function<AcctReportDataProviderInstance, String> reportDataProviderInstanceKeyExtractor =
            rdpi -> rdpi.getDataProviderInstance().getDataProviderInstanceUUID();

        // Identify new data provider instances
        final Set<String> newDataProviderInstanceUUIDs =
            minus(
                dataProviderInstanceUUIDs,
                reportDataProviderInstances,
                identity(),
                reportDataProviderInstanceKeyExtractor
            );

        // Identify removed data provider instances
        final Set<AcctReportDataProviderInstance> removedReportDataProviderInstances =
            minus(
                reportDataProviderInstances,
                dataProviderInstanceUUIDs,
                reportDataProviderInstanceKeyExtractor,
                identity()
            );

        // Add the new data provider instances
        newDataProviderInstanceUUIDs.forEach(dataProviderInstanceUUID -> {
            // Find the referenced data provider instance (also makes sure it exists)
            final AcctDataProviderInstance dataProviderInstance = findDataProviderInstance(dataProviderInstanceUUID);

            // Create a new report / data provider instance many to many mapper
            final AcctReportDataProviderInstance reportDataProviderInstance =
                dataService.createNewReportDataProviderInstance();

            // Populate the mapper
            reportDataProviderInstance.setReport(report);
            reportDataProviderInstance.setDataProviderInstance(dataProviderInstance);

            // Save the mapper
            dataService.saveReportDataProviderInstance(reportDataProviderInstance);
        });

        // Delete the removed data provider instance
        removedReportDataProviderInstances.forEach(dataService::deleteReportDataProviderInstance);
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

    private AcctReport findReport(String reportUUID) {
        return
            dataService.findReportByReportUUID(reportUUID)
                .orElseThrow(
                    () -> new AcctReportingServiceNotFoundException(
                        errors,
                        REPORT,
                        reportUUID
                    )
                );
    }
}
