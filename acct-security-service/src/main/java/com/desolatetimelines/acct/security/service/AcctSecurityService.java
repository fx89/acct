package com.desolatetimelines.acct.security.service;

import com.desolatetimelines.acct.common.model.ObjectTypes;
import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;
import com.desolatetimelines.acct.security.data.service.AcctSecurityDataService;
import com.desolatetimelines.acct.security.data.usermanagement.service.AcctSecurityUserManagementDataService;
import com.desolatetimelines.acct.security.model.*;
import com.desolatetimelines.acct.security.privilegesprovider.service.AcctPrivilegesDataService;
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

import static java.util.Collections.emptySet;

/**
 * Main module of the security services layer
 */
@Service
public class AcctSecurityService {

    private final AcctSecurityDataService securityDataService;

    private final AcctPrivilegesDataService privilegesDataService;

    private final AcctSecurityUserManagementDataService securityUserManagementDataService;

    private final RESTUsageEndpointClient usageEndpointClient;

    private final String applicationName;

    private final String contextPath;

    private final Map<Class<? extends AcctResourceOwner>, AcctSecurityResourceOwnersService<?>>
        resourceOwnersDataServices;

    public AcctSecurityService(
        AcctSecurityDataService securityDataService,
        AcctPrivilegesDataService privilegesDataService,
        AcctSecurityUserManagementDataService securityUserManagementDataService,
        RESTUsageEndpointClient usageEndpointClient,
        @Value("${SECURITY_APPLICATION_NAME}") String applicationName,
        @Value("${SECURITY_SERVER_CONTEXT_PATH}") String contextPath
    ) {
        this.securityDataService = securityDataService;
        this.privilegesDataService = privilegesDataService;
        this.securityUserManagementDataService = securityUserManagementDataService;
        this.usageEndpointClient = usageEndpointClient;
        this.applicationName = applicationName;
        this.contextPath = contextPath;

        resourceOwnersDataServices = Map.of(
            AcctWorkspaceOwner.class,
            new AcctSecurityResourceOwnersService<>(
                securityDataService.getResourceOwnerDataService(AcctWorkspaceOwner.class),
                securityUserManagementDataService
            ),

            AcctDashboardOwner.class,
            new AcctSecurityResourceOwnersService<>(
                securityDataService.getResourceOwnerDataService(AcctDashboardOwner.class),
                securityUserManagementDataService
            ),

            AcctReportOwner.class,
            new AcctSecurityResourceOwnersService<>(
                securityDataService.getResourceOwnerDataService(AcctReportOwner.class),
                securityUserManagementDataService
            )
        );
    }

    // ----------------------------------------------------------------------------------------------------------------

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
                    ObjectTypes.WORKSPACE.name(),
                    ObjectTypes.DASHBOARD.name(),
                    ObjectTypes.REPORT.name(),
                    ObjectTypes.GROUP.name()
                ))
                .build()
        );
    }

    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Retrieves a set of all the privileges mapped to all groups with the given UUIDs
     */
    public Set<String> getGroupPrivilegesByGroupUUIDs(Collection<String> groupUUIDs) {
        return
            securityDataService.findAllGroupPrivilegesByGroupUUIDIn(groupUUIDs)
                .stream()
                .map(AcctGroupPrivilege::getPrivilegeName)
                .collect(Collectors.toSet());
    }

    public Set<AcctPrivilege> getAllPrivileges() {
        return privilegesDataService.findAllPrivileges();
    }

    /**
     * Assigns the privileges referenced in the given privileges collection to the group
     * with the given group UUID. If any privilege is already assigned then it remains assigned.
     * The privilege IDs in the given privileges collection are validated against the currently
     * available list of valid privileges
     *
     * @param groupUUID    the given group UUID
     * @param privilegeIds the given privileges collection
     */
    public void assignPrivilegesToGroup(String groupUUID, Collection<String> privilegeIds) {
        // Validate the privileges
        privilegesDataService.validatePrivileges(privilegeIds);

        // Get the current privileges
        final Set<String> alreadyAssignedPrivilegeIds =
            securityDataService.findAllGroupPrivilegesByGroupUUIDIn(List.of(groupUUID))
                .stream()
                .map(AcctGroupPrivilege::getPrivilegeName)
                .collect(Collectors.toSet());

        // Filter out any already assigned privilege
        final Stream<String> notYetAssignedPrivileges =
            privilegeIds.stream()
                .filter(privilegeId -> !alreadyAssignedPrivilegeIds.contains(privilegeId));

        // Create and save a new ACCT group / privilege mapping for the given group UUID
        // and each of the provided, not yet assigned, privilege IDs
        notYetAssignedPrivileges.forEach(privilegeId ->
            securityDataService.createAcctGroupPrivilege(groupUUID, privilegeId)
        );
    }

    /**
     * Unmaps the privileges referenced by the given collection of privilege IDs
     * from the group identified by the given group UUID
     *
     * @param groupUUID    the given group UUID
     * @param privilegeIds the given collection of privilege IDs
     */
    @Transactional
    public void removePrivilegesFromGroup(String groupUUID, Collection<String> privilegeIds) {
        securityDataService.deleteGroupPrivilegeMappings(groupUUID, privilegeIds);
    }

    /**
     * Retrieves a set of privilege Ids for the privileges assigned to the groups
     * mapped to the user with the given user UUID
     *
     * @param userUUID the given user UUID
     */
    public Set<String> getPrivilegesAssignedToUser(String userUUID) {
        // First, get the groups mapped to the user
        final Set<String> groupUUIDs =
            securityUserManagementDataService.getUUIDsOfGroupsAssignedToUser(userUUID);

        // If the set is empty, return an empty set
        if (groupUUIDs.isEmpty()) {
            return emptySet();
        }

        // Lastly, get the privileges mapped to the groups
        return
            securityDataService.findAllGroupPrivilegesByGroupUUIDIn(groupUUIDs)
                .stream()
                .map(AcctGroupPrivilege::getPrivilegeName)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a set of UUIDs for the items of the given type that are in use by the Security service
     * and have the UUID equal to one of the UUIDs in the given list of UUIDs
     *
     * @param objectType    the given type
     * @param resourceUUIDs the given list of UUIDs
     * @throws IllegalArgumentException in case the given object type is not supported
     */
    public Collection<String> getInUseItemUUIDs(String objectType, Collection<String> resourceUUIDs) {
        // If the object type is WORKSPACE then search for workspaces referenced by workspace owner entities
        if (Objects.equals(objectType, ObjectTypes.WORKSPACE.name())) {
            return resourceOwnersDataServices.get(AcctWorkspaceOwner.class).getInUseResourceUUIDs(resourceUUIDs);
        }

        // If the object type is DASHBOARD then search for dashboards referenced by dashboard owner entities
        if (Objects.equals(objectType, ObjectTypes.DASHBOARD.name())) {
            return resourceOwnersDataServices.get(AcctDashboardOwner.class).getInUseResourceUUIDs(resourceUUIDs);
        }

        // If the object type is REPORT then search for reports referenced by report owner entities
        if (Objects.equals(objectType, ObjectTypes.REPORT.name())) {
            return resourceOwnersDataServices.get(AcctReportOwner.class).getInUseResourceUUIDs(resourceUUIDs);
        }

        // If the object type is GROUP then search groups referenced by group privilege entities
        if (Objects.equals(objectType, ObjectTypes.GROUP.name())) {
            return
                securityDataService.findAllGroupPrivilegesByGroupUUIDIn(resourceUUIDs)
                    .stream()
                    .map(AcctGroupPrivilege::getGroupUUID)
                    .collect(Collectors.toSet());
        }

        throw new IllegalArgumentException("Object type [" + objectType + "] not supported");
    }

    // Workspace owners -----------------------------------------------------------------------------------------------

    /**
     * Returns a set of workspace UUIDs for the workspaces owned by the owner
     * having the given owner UUID
     *
     * @param ownerUUID the given owner UUID
     */
    public Set<String> getWorkspacesOwnedByOwner(String ownerUUID) {
        return
            getResourceOwnersService(AcctWorkspaceOwner.class)
                .getResourcesOwnedByOwner(ownerUUID);
    }

    /**
     * Returns a set of workspace UUIDs for the workspaces owned by the owner
     * of the given owner type having the given owner UUID
     *
     * @param ownerType the given owner type
     * @param ownerUUID the given owner UUID
     */
    public Set<String> getWorkspacesOwnedByOwnerOfType(OwnerType ownerType, String ownerUUID) {
        return
            getResourceOwnersService(AcctWorkspaceOwner.class)
                .getResourcesOwnedByOwnerOfType(ownerType, ownerUUID);
    }

    /**
     * Returns a set of {@link AcctWorkspaceOwner workspace owner entities} for
     * the user with the given user UUID
     *
     * @param userUUID the given user UUID
     */
    public Set<AcctWorkspaceOwner> getWorkspacesOwnedByUser(String userUUID) {
        return
            getResourceOwnersService(AcctWorkspaceOwner.class)
                .getResourcesOwnedByUser(userUUID)
                .stream()
                .map(resource -> (AcctWorkspaceOwner) resource)
                .collect(Collectors.toSet());
    }

    /**
     * Creates a {@link AcctWorkspaceOwner workspace owner} of the given owner type
     * for the given owner UUID and the given workspace UUID
     *
     * @param ownerType     the given owner type
     * @param ownerUUID     the given owner UUID
     * @param workspaceUUID the given workspace UUID
     * @return the created workspace owner
     */
    @Transactional
    @SuppressWarnings("UnusedReturnValue")
    public AcctWorkspaceOwner createWorkspaceOwner(OwnerType ownerType, String ownerUUID, String workspaceUUID) {
        return (AcctWorkspaceOwner)
            getResourceOwnersService(AcctWorkspaceOwner.class)
                .createResourceOwner(ownerType, ownerUUID, workspaceUUID);
    }

    /**
     * Returns an {@link AccessibilityReport accessibility report} for the given user UUID and workspace UUID
     *
     * @param userUUID      the given user UUID
     * @param workspaceUUID the given dashboard UUID
     */
    public AccessibilityReport getWorkspaceOwnedByUser(String userUUID, String workspaceUUID) {
        return
            getResourceOwnersService(AcctWorkspaceOwner.class)
                .getResourceOwnedByUser(userUUID, workspaceUUID);
    }

    /**
     * Deletes the {@link AcctDashboardOwner dashboard owner} of the given owner type
     * for the given owner UUID and the given dashboard UUID
     *
     * @param ownerType     the given owner type
     * @param ownerUUID     the given owner UUID
     * @param workspaceUUID the given dashboard UUID
     */
    @Transactional
    public void deleteWorkspaceOwner(OwnerType ownerType, String ownerUUID, String workspaceUUID) {
        getResourceOwnersService(AcctWorkspaceOwner.class)
            .deleteResourceOwner(ownerType, ownerUUID, workspaceUUID);
    }

    // Dashboard owners -----------------------------------------------------------------------------------------------

    /**
     * Returns a set of dashboard UUIDs for the dashboards owned by the owner
     * having the given owner UUID
     *
     * @param ownerUUID the given owner UUID
     */
    public Set<String> getDashboardsOwnedByOwner(String ownerUUID) {
        return
            getResourceOwnersService(AcctDashboardOwner.class)
                .getResourcesOwnedByOwner(ownerUUID);
    }

    /**
     * Returns a set of dashboard UUIDs for the dashboards owned by the owner
     * of the given owner type having the given owner UUID
     *
     * @param ownerType the given owner type
     * @param ownerUUID the given owner UUID
     */
    public Set<String> getDashboardsOwnedByOwnerOfType(OwnerType ownerType, String ownerUUID) {
        return
            getResourceOwnersService(AcctDashboardOwner.class)
                .getResourcesOwnedByOwnerOfType(ownerType, ownerUUID);
    }

    /**
     * Returns a set of {@link AcctDashboardOwner dashboard owner entities} for
     * the user with the given user UUID
     *
     * @param userUUID the given user UUID
     */
    public Set<AcctDashboardOwner> getDashboardsOwnedByUser(String userUUID) {
        return
            getResourceOwnersService(AcctDashboardOwner.class)
                .getResourcesOwnedByUser(userUUID)
                .stream()
                .map(resource -> (AcctDashboardOwner) resource)
                .collect(Collectors.toSet());
    }

    /**
     * Creates a {@link AcctDashboardOwner dashboard owner} of the given owner type
     * for the given owner UUID and the given dashboard UUID
     *
     * @param ownerType     the given owner type
     * @param ownerUUID     the given owner UUID
     * @param dashboardUUID the given dashboard UUID
     * @return the created dashboard owner
     */
    @Transactional
    @SuppressWarnings("UnusedReturnValue")
    public AcctDashboardOwner createDashboardOwner(OwnerType ownerType, String ownerUUID, String dashboardUUID) {
        return (AcctDashboardOwner)
            getResourceOwnersService(AcctDashboardOwner.class)
                .createResourceOwner(ownerType, ownerUUID, dashboardUUID);
    }

    /**
     * Returns an {@link AccessibilityReport accessibility report} for the given user UUID and dashboard UUID
     *
     * @param userUUID      the given user UUID
     * @param dashboardUUID the given workspace UUID
     */
    public AccessibilityReport getDashboardOwnedByUser(String userUUID, String dashboardUUID) {
        return
            getResourceOwnersService(AcctDashboardOwner.class)
                .getResourceOwnedByUser(userUUID, dashboardUUID);
    }

    /**
     * Deletes the {@link AcctDashboardOwner dashboard owner} of the given owner type
     * for the given owner UUID and the given dashboard UUID
     *
     * @param ownerType     the given owner type
     * @param ownerUUID     the given owner UUID
     * @param dashboardUUID the given dashboard UUID
     */
    @Transactional
    public void deleteDashboardOwner(OwnerType ownerType, String ownerUUID, String dashboardUUID) {
        getResourceOwnersService(AcctDashboardOwner.class)
            .deleteResourceOwner(ownerType, ownerUUID, dashboardUUID);
    }

    /**
     * Deletes all the {@link AcctDashboardOwner dashboard owners} that reference the
     * given dashboard UUID
     *
     * @param dashboardUUID the given dashboard UUID
     */
    @Transactional
    public void deleteDashboardOwnersByDashboardUUID(String dashboardUUID) {
        getResourceOwnersService(AcctDashboardOwner.class)
            .deleteResourceOwnersByResourceUUID(dashboardUUID);
    }

    // Report owners --------------------------------------------------------------------------------------------------

    /**
     * Returns a set of report UUIDs for the reports owned by the owner
     * having the given owner UUID
     *
     * @param ownerUUID the given owner UUID
     */
    public Set<String> getReportsOwnedByOwner(String ownerUUID) {
        return
            getResourceOwnersService(AcctReportOwner.class)
                .getResourcesOwnedByOwner(ownerUUID);
    }

    /**
     * Returns a set of report UUIDs for the reports owned by the owner
     * of the given owner type having the given owner UUID
     *
     * @param ownerType the given owner type
     * @param ownerUUID the given owner UUID
     */
    public Set<String> getReportsOwnedByOwnerOfType(OwnerType ownerType, String ownerUUID) {
        return
            getResourceOwnersService(AcctReportOwner.class)
                .getResourcesOwnedByOwnerOfType(ownerType, ownerUUID);
    }

    /**
     * Returns a set of {@link AcctReportOwner report owner entities} for
     * the user with the given user UUID
     *
     * @param userUUID the given user UUID
     */
    public Set<AcctReportOwner> getReportsOwnedByUser(String userUUID) {
        return
            getResourceOwnersService(AcctReportOwner.class)
                .getResourcesOwnedByUser(userUUID)
                .stream()
                .map(resource -> (AcctReportOwner) resource)
                .collect(Collectors.toSet());
    }

    /**
     * Creates a {@link AcctReportOwner report owner} of the given owner type
     * for the given owner UUID and the given report UUID
     *
     * @param ownerType  the given owner type
     * @param ownerUUID  the given owner UUID
     * @param reportUUID the given report UUID
     * @return the created report owner
     */
    @Transactional
    @SuppressWarnings("UnusedReturnValue")
    public AcctReportOwner createReportOwner(OwnerType ownerType, String ownerUUID, String reportUUID) {
        return (AcctReportOwner)
            getResourceOwnersService(AcctReportOwner.class)
                .createResourceOwner(ownerType, ownerUUID, reportUUID);
    }

    /**
     * Returns an {@link AccessibilityReport accessibility report} for the given user UUID and report UUID
     *
     * @param userUUID   the given user UUID
     * @param reportUUID the given report UUID
     */
    public AccessibilityReport getReportOwnedByUser(String userUUID, String reportUUID) {
        return
            getResourceOwnersService(AcctReportOwner.class)
                .getResourceOwnedByUser(userUUID, reportUUID);
    }

    /**
     * Deletes the {@link AcctReportOwner report owner} of the given owner type
     * for the given owner UUID and the given report UUID
     *
     * @param ownerType  the given owner type
     * @param ownerUUID  the given owner UUID
     * @param reportUUID the given report UUID
     */
    @Transactional
    public void deleteReportOwner(OwnerType ownerType, String ownerUUID, String reportUUID) {
        getResourceOwnersService(AcctReportOwner.class)
            .deleteResourceOwner(ownerType, ownerUUID, reportUUID);
    }

    // ----------------------------------------------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    private <T extends AcctResourceOwner> AcctSecurityResourceOwnersService<T> getResourceOwnersService(
        Class<? extends AcctResourceOwner> resourceOwnerType
    ) {
        return
            (AcctSecurityResourceOwnersService<T>)
                resourceOwnersDataServices.get(resourceOwnerType);
    }
}
