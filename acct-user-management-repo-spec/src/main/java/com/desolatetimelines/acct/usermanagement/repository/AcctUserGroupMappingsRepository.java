package com.desolatetimelines.acct.usermanagement.repository;

import com.desolatetimelines.acct.usermanagement.model.AcctUserGroupMapping;

/**
 * Repository for loading and persisting {@link AcctUserGroupMapping user / group mappings}
 */
public interface AcctUserGroupMappingsRepository {

    /**
     * Returns a new instance of {@link AcctUserGroupMapping}
     */
    AcctUserGroupMapping createNew();

    /**
     * Persists the referenced user and returns a reference to the persisted entity
     *
     * @param acctUserGroupMapping the referenced user
     */
    AcctUserGroupMapping save(AcctUserGroupMapping acctUserGroupMapping);

    /**
     * Deletes the mapping between the user with the given user UUID and the group with the given group UUID
     *
     * @param userUUID  the given user UUID
     * @param groupUUID the given group UUID
     */
    void deleteByUserUUIDAndGroupUUID(String userUUID, String groupUUID);

}
