package com.desolatetimelines.acct.usermanagement.data.service;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;
import com.desolatetimelines.acct.privilegesprovider.service.AcctServicePrivilegesProvider;
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

    public AcctPrivilegesDataService(
        List<AcctServicePrivilegesProvider> privilegesProviders
    ) {
        privileges =
            privilegesProviders.stream()
                .map(AcctServicePrivilegesProvider::getPrivileges)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a set of all registered privileges
     */
    public Set<AcctPrivilege> findAllPrivileges() {
        return privileges;
    }

}
