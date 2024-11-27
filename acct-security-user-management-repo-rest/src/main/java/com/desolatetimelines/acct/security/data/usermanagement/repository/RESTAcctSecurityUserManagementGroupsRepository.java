package com.desolatetimelines.acct.security.data.usermanagement.repository;

import com.desolatetimelines.acct.usermanagement.ws.client.RESTGroupsEndpointClient;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctGroupDetails;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link AcctSecurityUserManagementGroupsRepository} that uses the
 * {@link RESTGroupsEndpointClient} to retrieve and persist data
 */
@Service
public class RESTAcctSecurityUserManagementGroupsRepository implements AcctSecurityUserManagementGroupsRepository {

    private final RESTGroupsEndpointClient groupsEndpointClient;

    public RESTAcctSecurityUserManagementGroupsRepository(RESTGroupsEndpointClient groupsEndpointClient) {
        this.groupsEndpointClient = groupsEndpointClient;
    }

    @Override
    public Set<String> findAllGroupUUIDsByUserUUID(String userUUID) {
        return
            groupsEndpointClient.getUserGroups(userUUID)
                .stream()
                .map(AcctGroupDetails::groupUUID)
                .collect(Collectors.toSet());
    }
}
