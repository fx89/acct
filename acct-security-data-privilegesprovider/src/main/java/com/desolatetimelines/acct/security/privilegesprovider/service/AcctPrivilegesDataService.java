package com.desolatetimelines.acct.security.privilegesprovider.service;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;
import com.desolatetimelines.acct.privilegesprovider.service.AcctServicePrivilegesProvider;
import com.desolatetimelines.acct.security.privilegesprovider.exception.AcctPrivilegesDataServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Allows read and search operations in the combined set of privileges
 * coming from all injected privileges provider
 */
@Service
public class AcctPrivilegesDataService {

    private final Set<AcctPrivilege> privileges;

    final Collection<String> privilegeIds;

    public AcctPrivilegesDataService(
        List<AcctServicePrivilegesProvider> privilegesProviders
    ) {
        privileges =
            privilegesProviders.stream()
                .map(AcctServicePrivilegesProvider::getPrivileges)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        privilegeIds = findAllPrivileges().stream().map(AcctPrivilege::privilegeId).toList();
    }

    /**
     * Returns a set of all registered privileges
     */
    public Set<AcctPrivilege> findAllPrivileges() {
        return privileges;
    }

    /**
     * Validates each of the privilege Ids in the given collection
     *
     * @param privilegeIds the given collection
     * @throws AcctPrivilegesDataServiceException in case any of the privileges is invalid
     */
    public void validatePrivileges(Collection<String> privilegeIds) {
        privilegeIds.forEach(this::validatePrivilege);
    }

    /**
     * Validates the give privilege ID
     *
     * @param privilegeId the give privilege ID
     * @throws AcctPrivilegesDataServiceException in case the give privilege ID is invalid
     */
    public void validatePrivilege(String privilegeId) {
        if (!privilegeIds.contains(privilegeId)) {
            throw new AcctPrivilegesDataServiceException("Privilege ID [" + privilegeId + "] is invalid");
        }
    }
}
