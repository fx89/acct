package com.desolatetimelines.acct.usermanagement.data.service;

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

}
