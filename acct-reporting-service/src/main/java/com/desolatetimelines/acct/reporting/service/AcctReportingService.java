package com.desolatetimelines.acct.reporting.service;

import com.desolatetimelines.acct.common.model.ObjectTypes;
import com.desolatetimelines.acct.reporting.data.service.AcctReportingDataService;
import com.desolatetimelines.acct.reporting.exception.AcctReportingServiceNotFoundException;
import com.desolatetimelines.acct.reporting.exception.AcctReportingServiceSecurityException;
import com.desolatetimelines.acct.reporting.mapper.DashboardReadablePropertiesMapper;
import com.desolatetimelines.acct.reporting.model.AcctDashboard;
import com.desolatetimelines.acct.reporting.model.DashboardDetails;
import com.desolatetimelines.acct.reporting.model.DashboardsContainer;
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

import static com.desolatetimelines.acct.common.model.ObjectTypes.DASHBOARD;
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

    private final String applicationName;

    private final String contextPath;

    public AcctReportingService(
        RESTUsageEndpointClient usageEndpointClient,
        AcctSecurityClientService securityClientService,
        AcctReportingErrorCodesRegistryService errors,
        AcctReportingDataService dataService,
        @Value("${REPORTING_APPLICATION_NAME}") String applicationName,
        @Value("${REPORTING_SERVER_CONTEXT_PATH}") String contextPath
    ) {
        this.usageEndpointClient = usageEndpointClient;
        this.securityClientService = securityClientService;
        this.errors = errors;
        this.dataService = dataService;
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

}
