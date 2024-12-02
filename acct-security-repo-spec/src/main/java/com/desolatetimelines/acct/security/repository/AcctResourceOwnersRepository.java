package com.desolatetimelines.acct.security.repository;

import com.desolatetimelines.acct.security.model.AcctResourceOwner;
import com.desolatetimelines.acct.security.model.AcctWorkspaceOwner;
import com.desolatetimelines.acct.security.model.OwnerType;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * Repository for loading and persisting {@link AcctWorkspaceOwner workspace owners}
 */
public interface AcctResourceOwnersRepository<T extends AcctResourceOwner> {

    T createNew();

    T save(T resourceOwner);

    void delete(T resourceOwner);

    Optional<T> findFirstByOwnerTypeAndOwnerUUIDAndResourceUUID(
        OwnerType ownerType,
        String ownerUUID,
        String resourceUUID
    );

    Set<T> findAllByOwnerTypeAndOwnerUUIDInAndResourceUUID(
        OwnerType ownerType,
        Collection<String> ownerUUID,
        String resourceUUID
    );

    Set<T> findAllByResourceUUIDIn(Collection<String> resourceUUIDs);

    Set<T> findAllByOwnerTypeInAndOwnerUUID(Collection<OwnerType> ownerTypes, String ownerUUID);

    Set<T> findAllByOwnerTypeAndOwnerUUIDIn(OwnerType ownerType, Collection<String> ownerUUIDs);

    Set<T> findAllByOwnerType(OwnerType ownerType);

}
