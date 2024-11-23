package com.desolatetimelines.acct.security.repository;

import com.desolatetimelines.acct.security.model.AcctGroupPrivilege;

import java.util.Collection;
import java.util.Set;

/**
 * Repository for loading and persisting {@link AcctGroupPrivilege group privileges}
 */
public interface AcctGroupPrivilegesRepository {

    AcctGroupPrivilege createNew();

    Set<AcctGroupPrivilege> findAllByGroupUUIDIn(Collection<String> groupUUIDs);

}
