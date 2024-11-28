package com.desolatetimelines.acct.security.springrepository;

import com.desolatetimelines.acct.security.model.JpaAcctWorkspaceOwner;
import com.desolatetimelines.acct.security.model.OwnerType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Set;

public interface JpaAcctWorkspaceOwnersRepository extends CrudRepository<JpaAcctWorkspaceOwner, Long> {

    Set<JpaAcctWorkspaceOwner> findAllByWorkspaceUUIDIn(Collection<String> workspaceUUIDs);

    Set<JpaAcctWorkspaceOwner> findAllByOwnerTypeInAndOwnerUUID(Collection<OwnerType> ownerType, String ownerUUID);

}
