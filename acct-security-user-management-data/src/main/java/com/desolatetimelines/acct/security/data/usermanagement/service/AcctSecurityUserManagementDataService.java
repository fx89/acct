package com.desolatetimelines.acct.security.data.usermanagement.service;

import com.desolatetimelines.acct.security.data.usermanagement.repository.AcctSecurityUserManagementGroupsRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Provides user management data access functions specifically required by the security service
 */
@Service
public class AcctSecurityUserManagementDataService {

    private final AcctSecurityUserManagementGroupsRepository securityUserManagementGroupsRepository;

    public AcctSecurityUserManagementDataService(
        AcctSecurityUserManagementGroupsRepository securityUserManagementGroupsRepository
    ) {
        this.securityUserManagementGroupsRepository = securityUserManagementGroupsRepository;
    }

    public Set<String> getUUIDsOfGroupsAssignedToUser(String userUUID) {
        return securityUserManagementGroupsRepository.findAllGroupUUIDsByUserUUID(userUUID);
    }

}
