package com.desolatetimelines.acct.security.data.service;

import com.desolatetimelines.acct.security.data.exception.AcctSecurityDataServiceNotFoundException;
import com.desolatetimelines.acct.security.model.AcctResourceOwner;
import com.desolatetimelines.acct.security.model.OwnerType;
import com.desolatetimelines.acct.security.repository.AcctResourceOwnersRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * Data service providing common operations that apply to all child types of
 * the {@link AcctResourceOwner generic resource owner}
 *
 * @param <T>
 */
public class AcctSecurityResourceOwnersDataService<T extends AcctResourceOwner> {

    private final AcctResourceOwnersRepository<T> resourceOwnersRepository;

    public AcctSecurityResourceOwnersDataService(AcctResourceOwnersRepository<T> resourceOwnersRepository) {
        this.resourceOwnersRepository = resourceOwnersRepository;
    }

    /**
     * Retrieves all {@link AcctResourceOwner resource owners} for which the
     * {@link AcctResourceOwner#getResourceUUID() resource UUID} matches one
     * of the UUIDs in the given list of resource UUIDs
     *
     * @param resourceUUIDs the given list of resource UUIDs
     */
    public Set<T> findAllResourceOwnersByResourceUUIDIn(Collection<String> resourceUUIDs) {
        return resourceOwnersRepository.findAllByResourceUUIDIn(resourceUUIDs);
    }

    /**
     * Returns a set of {@link AcctResourceOwner resource owners} for the resources owned by the owner
     * of one of the given owner types having the given owner UUID
     *
     * @param ownerTypes the given owner types
     * @param ownerUUID  the given owner UUID
     */
    public Set<T> findResourcesOwnedByOwnerOfType(Set<OwnerType> ownerTypes, String ownerUUID) {
        return resourceOwnersRepository.findAllByOwnerTypeInAndOwnerUUID(ownerTypes, ownerUUID);
    }

    /**
     * Returns an optional {@link AcctResourceOwner resource owner} record for the given
     * owner type, the given owner UUID and the given resource UUID. If there is no such
     * record, the returned optional is empty.
     *
     * @param ownerType    the given owner type
     * @param ownerUUID    the given owner UID
     * @param resourceUUID the given resource UUID
     */
    public Optional<T> findResourceOwner(
        OwnerType ownerType,
        String ownerUUID,
        String resourceUUID
    ) {
        return
            resourceOwnersRepository
                .findFirstByOwnerTypeAndOwnerUUIDAndResourceUUID(
                    ownerType,
                    ownerUUID,
                    resourceUUID
                );
    }

    /**
     * Returns a set of {@link AcctResourceOwner resource owners} for the resource owned by the owners
     * of the given owner type, having the given owner UUIDs and the given resource UUID
     *
     * @param ownerType    the given owner type
     * @param ownerUUIDs   the given owner UUIDs
     * @param resourceUUID the given resource UUID
     */
    public Set<T> findResourceOwners(
        OwnerType ownerType,
        Collection<String> ownerUUIDs,
        String resourceUUID
    ) {
        return
            resourceOwnersRepository
                .findAllByOwnerTypeAndOwnerUUIDInAndResourceUUID(
                    ownerType,
                    ownerUUIDs,
                    resourceUUID
                );
    }

    /**
     * Returns a set of {@link AcctResourceOwner resource owners} for the resource owned by the owners
     * of the given owner type having the given owner UUIDs
     *
     * @param ownerType  the given owner type
     * @param ownerUUIDs the given owner UUIDs
     */
    public Set<T> findResourcesOwnedByOwnersOfType(
        OwnerType ownerType,
        Collection<String> ownerUUIDs
    ) {
        return resourceOwnersRepository.findAllByOwnerTypeAndOwnerUUIDIn(ownerType, ownerUUIDs);
    }

    /**
     * Returns a set of {@link OwnerType#PUBLIC public} {@link AcctResourceOwner resource owners}
     */
    public Set<T> getPublicResources() {
        return resourceOwnersRepository.findAllByOwnerType(OwnerType.PUBLIC);
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
    public T createResourceOwner(OwnerType ownerType, String ownerUUID, String resourceUUID) {
        // Create the new resource owner
        final T newResourceOwner = resourceOwnersRepository.createNew();

        // Set the properties of the new resource owner
        newResourceOwner.setOwnerType(ownerType);
        newResourceOwner.setOwnerUUID(ownerUUID);
        newResourceOwner.setResourceUUID(resourceUUID);

        // Save the new resource owner and return a reference to the saved entity
        return resourceOwnersRepository.save(newResourceOwner);
    }

    /**
     * Deletes the {@link AcctResourceOwner resource owner} of the given owner type
     * for the given owner UUID and the given resource UUID
     *
     * @param ownerType    the given owner type
     * @param ownerUUID    the given owner UUID
     * @param resourceUUID the given resource UUID
     */
    public void deleteResourceOwner(OwnerType ownerType, String ownerUUID, String resourceUUID) {
        // Get the resource owner or throw a "Not Found" exception
        final T resourceOwner =
            resourceOwnersRepository.findFirstByOwnerTypeAndOwnerUUIDAndResourceUUID(
                    ownerType, ownerUUID, resourceUUID
                )
                .orElseThrow(() -> new AcctSecurityDataServiceNotFoundException(
                    "Resource owner not found"
                ));

        // Delete the resource owner
        resourceOwnersRepository.delete(resourceOwner);
    }

    /**
     * Deletes all the {@link AcctResourceOwner resource owners}, regardless of the type,
     * that reference the resource with the given resource UUID
     *
     * @param resourceUUID the given resource UUID
     */
    public void deleteResourceOwnersByResourceUUID(String resourceUUID) {
        resourceOwnersRepository.deleteAllByResourceUUID(resourceUUID);
    }

}
