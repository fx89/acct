package com.desolatetimelines.acct.security.data.usermanagement.repository;

import java.util.Set;

/**
 * Specification for the Security service-oriented User Management groups repository
 */
public interface AcctSecurityUserManagementGroupsRepository {

    /**
     * Returns a set of group UUIDs for the groups mapped to the user with the given user UUID
     *
     * @param userUUID the given user UUID
     */
    Set<String> findAllGroupUUIDsByUserUUID(String userUUID);

}
