package com.desolatetimelines.acct.security.service;

import com.desolatetimelines.acct.security.data.service.AcctSecurityResourceOwnersDataService;
import com.desolatetimelines.acct.security.data.usermanagement.service.AcctSecurityUserManagementDataService;
import com.desolatetimelines.acct.security.model.AccessibilityReport;
import com.desolatetimelines.acct.security.model.AcctResourceOwner;
import com.desolatetimelines.acct.security.model.OwnerType;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.desolatetimelines.acct.common.utils.Streams.multiConcat;
import static com.desolatetimelines.acct.security.model.AccessibilityReport.*;

/**
 * Provides functionality that is common for all {@link AcctResourceOwner resource owner}
 * types handled by the Security service
 *
 * @param <T>
 */
public class AcctSecurityResourceOwnersService<T extends AcctResourceOwner> {

    private final AcctSecurityResourceOwnersDataService<T> dataService;

    private final AcctSecurityUserManagementDataService securityUserManagementDataService;

    public AcctSecurityResourceOwnersService(
        AcctSecurityResourceOwnersDataService<T> dataService,
        AcctSecurityUserManagementDataService securityUserManagementDataService
    ) {
        this.dataService = dataService;
        this.securityUserManagementDataService = securityUserManagementDataService;
    }

    public Collection<String> getInUseResourceUUIDs(Collection<String> resourceUUIDs) {
        return
            dataService.findAllResourceOwnersByResourceUUIDIn(resourceUUIDs)
                .stream()
                .map(AcctResourceOwner::getResourceUUID)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a set of resource UUIDs for the resources owned by the owner
     * of the given owner type having the given owner UUID
     *
     * @param ownerType the given owner type
     * @param ownerUUID the given owner UUID
     */
    public Set<String> getResourcesOwnedByOwnerOfType(OwnerType ownerType, String ownerUUID) {
        return
            dataService.findResourcesOwnedByOwnerOfType(Set.of(ownerType), ownerUUID)
                .stream()
                .map(AcctResourceOwner::getResourceUUID)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a set of resource UUIDs for the resources owned by the owner
     * having the given owner UUID
     *
     * @param ownerUUID the given owner UUID
     */
    public Set<String> getResourcesOwnedByOwner(String ownerUUID) {
        return
            dataService.findResourcesOwnedByOwnerOfType(
                    Set.of(OwnerType.GROUP, OwnerType.USER, OwnerType.PUBLIC),
                    ownerUUID
                )
                .stream()
                .map(AcctResourceOwner::getResourceUUID)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a set of {@link AcctResourceOwner resource owner entities} for
     * the user with the given user UUID
     *
     * @param userUUID the given user UUID
     */
    public Set<T> getResourcesOwnedByUser(String userUUID) {
        // Get the user resources
        final Set<T> userResources =
            dataService.findResourcesOwnedByOwnerOfType(
                Set.of(OwnerType.USER),
                userUUID
            );

        // Get the public resources
        final Set<T> publicResources = dataService.getPublicResources();

        // Get the UUIDs of the groups assigned to the user and then
        // search for the resources owned by each of those groups
        final Set<T> groupResources =
            dataService.findResourcesOwnedByOwnersOfType(
                OwnerType.GROUP,
                securityUserManagementDataService.getUUIDsOfGroupsAssignedToUser(userUUID)
            );

        // Concatenate the streams and return the result
        return
            multiConcat(userResources.stream(), groupResources.stream(), publicResources.stream())
                .collect(Collectors.toSet());
    }

    /**
     * Returns an {@link AccessibilityReport accessibility report} for the given user UUID and resource UUID
     *
     * @param userUUID     the given user UUID
     * @param resourceUUID the given resource UUID
     */
    public AccessibilityReport getResourceOwnedByUser(String userUUID, String resourceUUID) {
        // Check for direct ownership
        final Optional<T> directOwnership =
            dataService.findResourceOwner(OwnerType.USER, userUUID, resourceUUID);

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
        final Set<T> groupOwnership =
            dataService.findResourceOwners(OwnerType.GROUP, userGroupUUIDs, resourceUUID);

        // If nothing is found, return a negative ownership report
        if (groupOwnership.isEmpty()) {
            return NEGATIVE_OWNERSHIP_REPORT;
        }

        // If anything is found, return a group ownership report
        return GROUP_OWNERSHIP_REPORT;
    }

    /**
     * Creates a {@link AcctResourceOwner resource owner} of the given owner type
     * for the given owner UUID and the given resource UUID
     *
     * @param ownerType    the given owner type
     * @param ownerUUID    the given owner UUID
     * @param resourceUUID the given resource UUID
     * @return the created resource owner
     */
    @Transactional
    public T createResourceOwner(OwnerType ownerType, String ownerUUID, String resourceUUID) {
        return dataService.createResourceOwner(ownerType, ownerUUID, resourceUUID);
    }

    /**
     * Deletes the {@link AcctResourceOwner resource owner} of the given owner type
     * for the given owner UUID and the given resource UUID
     *
     * @param ownerType    the given owner type
     * @param ownerUUID    the given owner UUID
     * @param resourceUUID the given resource UUID
     */
    @Transactional
    public void deleteResourceOwner(OwnerType ownerType, String ownerUUID, String resourceUUID) {
        dataService.deleteResourceOwner(ownerType, ownerUUID, resourceUUID);
    }

    /**
     * Deletes the {@link AcctResourceOwner resource owners} for the referenced resource
     *
     * @param resourceUUID Unique identifier of the referenced resource
     */
    @Transactional
    public void deleteResourceOwnersByResourceUUID(String resourceUUID) {
        dataService.deleteResourceOwnersByResourceUUID(resourceUUID);
    }

}
