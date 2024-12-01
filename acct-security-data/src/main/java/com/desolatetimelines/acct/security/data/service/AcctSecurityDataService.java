package com.desolatetimelines.acct.security.data.service;

import com.desolatetimelines.acct.security.data.exception.AcctSecurityDataServiceNotFoundException;
import com.desolatetimelines.acct.security.model.*;
import com.desolatetimelines.acct.security.repository.AcctDashboardOwnersRepository;
import com.desolatetimelines.acct.security.repository.AcctGroupPrivilegesRepository;
import com.desolatetimelines.acct.security.repository.AcctReportOwnersRepository;
import com.desolatetimelines.acct.security.repository.AcctWorkspaceOwnersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

/**
 * Facade for the data layer of the security service, allowing for the
 * loading and persisting of security data objects
 */
@Service
public class AcctSecurityDataService {

    private final AcctGroupPrivilegesRepository groupPrivilegesRepository;

    private final AcctWorkspaceOwnersRepository workspaceOwnersRepository;

    private final AcctDashboardOwnersRepository dashboardOwnersRepository;

    private final AcctReportOwnersRepository reportOwnersRepository;

    public AcctSecurityDataService(
        AcctGroupPrivilegesRepository groupPrivilegesRepository,
        AcctWorkspaceOwnersRepository workspaceOwnersRepository,
        AcctDashboardOwnersRepository dashboardOwnersRepository,
        AcctReportOwnersRepository reportOwnersRepository
    ) {
        this.groupPrivilegesRepository = groupPrivilegesRepository;
        this.workspaceOwnersRepository = workspaceOwnersRepository;
        this.dashboardOwnersRepository = dashboardOwnersRepository;
        this.reportOwnersRepository = reportOwnersRepository;
    }

    /**
     * Retrieves all {@link AcctGroupPrivilege group privileges} mapped to the groups with the given UUIDs
     *
     * @param groupUUIDs the given UUIDs
     */
    public Set<AcctGroupPrivilege> findAllGroupPrivilegesByGroupUUIDIn(Collection<String> groupUUIDs) {
        return groupPrivilegesRepository.findAllByGroupUUIDIn(groupUUIDs);
    }

    /**
     * Retrieves all {@link AcctWorkspaceOwner workspace owners} for which the
     * {@link AcctWorkspaceOwner#getWorkspaceUUID() workspace UUID} matches one
     * of the UUIDs in the given list of workspace UUIDs
     *
     * @param workspaceUUIDs the given list of workspace UUIDs
     */
    public Set<AcctWorkspaceOwner> findAllWorkspaceOwnersByWorkspaceUUIDIn(Collection<String> workspaceUUIDs) {
        return workspaceOwnersRepository.findAllByWorkspaceUUIDIn(workspaceUUIDs);
    }

    /**
     * Retrieves all {@link AcctDashboardOwner dashboard owners} for which the
     * {@link AcctDashboardOwner#getDashboardUUID() dashboard UUID} matches one
     * of the UUIDs in the given list of dashboard UUIDs
     *
     * @param dashboardUUIDs the given list of dashboard UUIDs
     */
    public Set<AcctDashboardOwner> findAllDashboardOwnersByDashboardUUIDIn(Collection<String> dashboardUUIDs) {
        return dashboardOwnersRepository.findAllByDashboardUUIDIn(dashboardUUIDs);
    }

    /**
     * Retrieves all {@link AcctReportOwner report owners} for which the
     * {@link AcctReportOwner#getReportUUID() report UUID} matches one
     * of the UUIDs in the given list of report UUIDs
     *
     * @param reportUUIDs the given list of report UUIDs
     */
    public Set<AcctReportOwner> findAllReportOwnersByReportUUIDIn(Collection<String> reportUUIDs) {
        return reportOwnersRepository.findAllByReportUUIDIn(reportUUIDs);
    }

    /**
     * Creates an ACCT group / privilege mapping using the given group UUID and privilege ID
     *
     * @param groupUUID   the given group UUID
     * @param privilegeId the given privilege ID
     * @return a reference to the newly created ACCT group / privilege mapping
     */
    public AcctGroupPrivilege createAcctGroupPrivilege(String groupUUID, String privilegeId) {
        // Create a new entity
        final AcctGroupPrivilege newAcctGroupPrivilege = groupPrivilegesRepository.createNew();

        // Set the properties
        newAcctGroupPrivilege.setGroupUUID(groupUUID);
        newAcctGroupPrivilege.setPrivilegeName(privilegeId);

        // Persist
        return groupPrivilegesRepository.save(newAcctGroupPrivilege);
    }

    /**
     * Deletes the group / privilege mappings for the given group and privileges
     */
    public void deleteGroupPrivilegeMappings(String groupUUID, Collection<String> privilegeIDs) {
        groupPrivilegesRepository.deleteAllByGroupUUIDAndPrivilegeNameIn(groupUUID, privilegeIDs);
    }

    /**
     * Returns a set of {@link AcctWorkspaceOwner workspace owners} for the workspaces owned by the owner
     * of one of the given owner types having the given owner UUID
     *
     * @param ownerTypes the given owner types
     * @param ownerUUID  the given owner UUID
     */
    public Set<AcctWorkspaceOwner> getWorkspacesOwnedByOwnerOfType(Set<OwnerType> ownerTypes, String ownerUUID) {
        return workspaceOwnersRepository.findAllByOwnerTypeInAndOwnerUUID(ownerTypes, ownerUUID);
    }

    /**
     * Returns a set of {@link AcctWorkspaceOwner workspace owners} for the workspaces owned by the owners
     * of one of the given owner type having the given owner UUIDs
     *
     * @param ownerType  the given owner type
     * @param ownerUUIDs the given owner UUIDs
     */
    public Set<AcctWorkspaceOwner> getWorkspacesOwnedByOwnersOfType(
        OwnerType ownerType,
        Collection<String> ownerUUIDs
    ) {
        return workspaceOwnersRepository.findAllByOwnerTypeAndOwnerUUIDIn(ownerType, ownerUUIDs);
    }

    /**
     * Returns a set of {@link OwnerType#PUBLIC public} {@link AcctWorkspaceOwner workspace owners}
     */
    public Set<AcctWorkspaceOwner> getPublicWorkspaces() {
        return workspaceOwnersRepository.findAllByOwnerType(OwnerType.PUBLIC);
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
    public AcctWorkspaceOwner createWorkspaceOwner(OwnerType ownerType, String ownerUUID, String workspaceUUID) {
        // Create the new workspace owner
        final AcctWorkspaceOwner newWorkspaceOwner = workspaceOwnersRepository.createNew();

        // Set the properties of the new workspace owner
        newWorkspaceOwner.setOwnerType(ownerType);
        newWorkspaceOwner.setOwnerUUID(ownerUUID);
        newWorkspaceOwner.setWorkspaceUUID(workspaceUUID);

        // Save the new workspace owner and return a reference to the saved entity
        return workspaceOwnersRepository.save(newWorkspaceOwner);
    }

    /**
     * Deletes the {@link AcctWorkspaceOwner workspace owner} of the given owner type
     * for the given owner UUID and the given workspace UUID
     *
     * @param ownerType     the given owner type
     * @param ownerUUID     the given owner UUID
     * @param workspaceUUID the given workspace UUID
     */
    public void deleteWorkspaceOwner(OwnerType ownerType, String ownerUUID, String workspaceUUID) {
        // Get the workspace owner or throw a "Not Found" exception
        final AcctWorkspaceOwner workspaceOwner =
            workspaceOwnersRepository.findFirstByOwnerTypeAndOwnerUUIDAndWorkspaceUUID(
                    ownerType, ownerUUID, workspaceUUID
                )
                .orElseThrow(() -> new AcctSecurityDataServiceNotFoundException(
                    "Workspace owner not found"
                ));

        // Delete the workspace owner
        workspaceOwnersRepository.delete(workspaceOwner);
    }

}
