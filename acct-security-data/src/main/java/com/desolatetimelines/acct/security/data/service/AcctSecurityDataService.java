package com.desolatetimelines.acct.security.data.service;

import com.desolatetimelines.acct.security.model.AcctGroupPrivilege;
import com.desolatetimelines.acct.security.repository.AcctGroupPrivilegesRepository;
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

    public AcctSecurityDataService(AcctGroupPrivilegesRepository groupPrivilegesRepository) {
        this.groupPrivilegesRepository = groupPrivilegesRepository;
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

}
