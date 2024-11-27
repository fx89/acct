package com.desolatetimelines.acct.security.repository;

import com.desolatetimelines.acct.security.model.AcctWorkspaceOwner;

import java.util.Collection;
import java.util.Set;

/**
 * Repository for loading and persisting {@link AcctWorkspaceOwner workspace owners}
 */
public interface AcctWorkspaceOwnersRepository {

    Set<AcctWorkspaceOwner> findAllByWorkspaceUUIDIn(Collection<String> workspaceUUIDs);

}
