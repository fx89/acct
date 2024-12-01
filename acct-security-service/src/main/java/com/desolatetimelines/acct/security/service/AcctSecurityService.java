package com.desolatetimelines.acct.security.service;

import com.desolatetimelines.acct.common.ObjectTypes;
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

import static com.desolatetimelines.acct.common.Streams.multiConcat;
import static com.desolatetimelines.acct.security.model.AccessibilityReport.*;
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
                    ObjectTypes.WORKSPACE.name(),
                    ObjectTypes.DASHBOARD.name(),
                    ObjectTypes.REPORT.name(),
                    ObjectTypes.GROUP.name()
                ))
                .build()
        );
    }

    /**
     * Returns a set of UUIDs for the items of the given type that are in use by the Security service
     * and have the UUID equal to one of the UUIDs in the given list of UUIDs
     *
     * @param objectType the given type
     * @param itemUUIDs  the given list of UUIDs
     * @throws IllegalArgumentException in case the given object type is not supported
     */
    public Collection<String> getInUseItemUUIDs(String objectType, Collection<String> itemUUIDs) {
        // If the object type is WORKSPACE then search for workspaces referenced by workspace owner entities
        if (Objects.equals(objectType, ObjectTypes.WORKSPACE.name())) {
            return
                securityDataService.findAllWorkspaceOwnersByWorkspaceUUIDIn(itemUUIDs)
                    .stream()
                    .map(AcctWorkspaceOwner::getWorkspaceUUID)
                    .collect(Collectors.toSet());
        }

        // If the object type is DASHBOARD then search for dashboards referenced by dashboard owner entities
        if (Objects.equals(objectType, ObjectTypes.DASHBOARD.name())) {
            return
                securityDataService.findAllDashboardOwnersByDashboardUUIDIn(itemUUIDs)
                    .stream()
                    .map(AcctDashboardOwner::getDashboardUUID)
                    .collect(Collectors.toSet());
        }

        // If the object type is REPORT then search for reports referenced by report owner entities
        if (Objects.equals(objectType, ObjectTypes.REPORT.name())) {
            return
                securityDataService.findAllReportOwnersByReportUUIDIn(itemUUIDs)
                    .stream()
                    .map(AcctReportOwner::getReportUUID)
                    .collect(Collectors.toSet());
        }

        // If the object type is GROUP then search groups referenced by group privilege entities
        if (Objects.equals(objectType, ObjectTypes.GROUP.name())) {
            return
                securityDataService.findAllGroupPrivilegesByGroupUUIDIn(itemUUIDs)
                    .stream()
                    .map(AcctGroupPrivilege::getGroupUUID)
                    .collect(Collectors.toSet());
        }

        throw new IllegalArgumentException("Object type [" + objectType + "] not supported");
    }

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
     * Returns a set of workspace UUIDs for the workspaces owned by the owner
     * of the given owner type having the given owner UUID
     *
     * @param ownerType the given owner type
     * @param ownerUUID the given owner UUID
     */
    public Set<String> getWorkspacesOwnedByOwnerOfType(OwnerType ownerType, String ownerUUID) {
        return
            securityDataService.findWorkspacesOwnedByOwnerOfType(Set.of(ownerType), ownerUUID)
                .stream()
                .map(AcctWorkspaceOwner::getWorkspaceUUID)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a set of workspace UUIDs for the workspaces owned by the owner
     * having the given owner UUID
     *
     * @param ownerUUID the given owner UUID
     */
    public Set<String> getWorkspacesOwnedByOwner(String ownerUUID) {
        return
            securityDataService.findWorkspacesOwnedByOwnerOfType(
                    Set.of(OwnerType.GROUP, OwnerType.USER, OwnerType.PUBLIC),
                    ownerUUID
                )
                .stream()
                .map(AcctWorkspaceOwner::getWorkspaceUUID)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a set of {@link AcctWorkspaceOwner workspace owner entities} for
     * the user with the given user UUID
     *
     * @param userUUID the given user UUID
     */
    public Set<AcctWorkspaceOwner> getWorkspacesOwnedByUser(String userUUID) {
        // Get the user workspaces
        final Set<AcctWorkspaceOwner> userWorkspaces =
            securityDataService.findWorkspacesOwnedByOwnerOfType(
                Set.of(OwnerType.USER),
                userUUID
            );

        // Get the public workspaces
        final Set<AcctWorkspaceOwner> publicWorkspaces =
            securityDataService.getPublicWorkspaces();

        // Get the UUIDs of the groups assigned to the user and then
        // search for the workspaces owned by each of those groups
        final Set<AcctWorkspaceOwner> groupWorkspaces =
            securityDataService.findWorkspacesOwnedByOwnersOfType(
                OwnerType.GROUP,
                securityUserManagementDataService.getUUIDsOfGroupsAssignedToUser(userUUID)
            );

        // Concatenate the streams and return the result
        return
            multiConcat(userWorkspaces.stream(), groupWorkspaces.stream(), publicWorkspaces.stream())
                .collect(Collectors.toSet());
    }

    public AccessibilityReport getWorkspaceOwnedByUser(String userUUID, String workspaceUUIDs) {
        // Check for direct ownership
        final Optional<AcctWorkspaceOwner> directOwnership =
            securityDataService.findWorkspaceOwner(OwnerType.USER, userUUID, workspaceUUIDs);

        // If direct ownership is found, return a direct ownership report
        if (directOwnership.isPresent()) {
            return DIRECT_OWNERSHIP_REPORT;
        }

        // If direct ownership is not found, check for group ownership

        // To do that, get the groups mapped to the user
        final Set<String> userGroupUUIDs =
            securityUserManagementDataService.getUUIDsOfGroupsAssignedToUser(userUUID);

        // If there's no group then return a negative ownership report
        if (userGroupUUIDs.isEmpty()) {
            return NEGATIVE_OWNERSHIP_REPORT;
        }

        // If there are groups assigned to the user, look for
        final Set<AcctWorkspaceOwner> groupOwnership =
            securityDataService.findWorkspaceOwners(OwnerType.GROUP, userGroupUUIDs, workspaceUUIDs);

        // If nothing is found, return a negative ownership report
        if (groupOwnership.isEmpty()) {
            return NEGATIVE_OWNERSHIP_REPORT;
        }

        // If anything is found, return a group ownership report
        return GROUP_OWNERSHIP_REPORT;
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
    public AcctWorkspaceOwner createWorkspaceOwner(OwnerType ownerType, String ownerUUID, String workspaceUUID) {
        return securityDataService.createWorkspaceOwner(ownerType, ownerUUID, workspaceUUID);
    }

    /**
     * Deletes the {@link AcctWorkspaceOwner workspace owner} of the given owner type
     * for the given owner UUID and the given workspace UUID
     *
     * @param ownerType     the given owner type
     * @param ownerUUID     the given owner UUID
     * @param workspaceUUID the given workspace UUID
     */
    @Transactional
    public void deleteWorkspaceOwner(OwnerType ownerType, String ownerUUID, String workspaceUUID) {
        securityDataService.deleteWorkspaceOwner(ownerType, ownerUUID, workspaceUUID);
    }
}
