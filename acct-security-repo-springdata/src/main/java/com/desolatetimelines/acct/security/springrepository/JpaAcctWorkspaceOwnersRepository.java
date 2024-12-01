package com.desolatetimelines.acct.security.springrepository;

import com.desolatetimelines.acct.security.model.JpaAcctWorkspaceOwner;
import com.desolatetimelines.acct.security.model.OwnerType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface JpaAcctWorkspaceOwnersRepository extends CrudRepository<JpaAcctWorkspaceOwner, Long> {

    Optional<JpaAcctWorkspaceOwner> findFirstByOwnerTypeAndOwnerUUIDAndWorkspaceUUID(
        OwnerType ownerType,
        String ownerUUID,
        String workspaceUUID
    );

    Set<JpaAcctWorkspaceOwner> findAllByWorkspaceUUIDIn(Collection<String> workspaceUUIDs);

    Set<JpaAcctWorkspaceOwner> findAllByOwnerTypeInAndOwnerUUID(Collection<OwnerType> ownerType, String ownerUUID);

    Set<JpaAcctWorkspaceOwner> findAllByOwnerTypeAndOwnerUUIDIn(OwnerType ownerType, Collection<String> ownerUUID);

    Set<JpaAcctWorkspaceOwner> findAllByOwnerType(OwnerType ownerType);

}
