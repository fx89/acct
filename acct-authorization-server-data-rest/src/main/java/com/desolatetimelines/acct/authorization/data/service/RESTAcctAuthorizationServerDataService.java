package com.desolatetimelines.acct.authorization.data.service;

import com.desolatetimelines.acct.authorization.data.model.AcctUser;
import com.desolatetimelines.acct.security.ws.client.RESTPrivilegesEndpointClient;
import com.desolatetimelines.acct.usermanagement.ws.client.RESTUsersPrivateEndpointClient;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctGroupInfo;
import com.desolatetimelines.acct.usermanagement.ws.privateendpoint.UsersPrivateEndpoint;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link AcctAuthorizationServerDataService} interface
 * that uses the ACCT REST clients to acquire user data
 */
@Service
@Profile("acctAuthServerDataREST")
public class RESTAcctAuthorizationServerDataService implements AcctAuthorizationServerDataService {

    private final UsersPrivateEndpoint privateUsersEndpoint;

    private final RESTPrivilegesEndpointClient privilegesEndpoint;

    public RESTAcctAuthorizationServerDataService(
        RESTUsersPrivateEndpointClient privateUsersEndpoint,
        RESTPrivilegesEndpointClient privilegesEndpoint
    ) {
        this.privateUsersEndpoint = privateUsersEndpoint;
        this.privilegesEndpoint = privilegesEndpoint;
    }


    @Override
    public Optional<AcctUser> getUserByLoginName(String loginName) {
        return
            // Attempt to find the user with the given loginName
            Optional.ofNullable(privateUsersEndpoint.getUserByUsername(loginName))
                // If the user is found, proceed to processing
                .map(acctUserDetails -> {
                    // Get the privileges for the groups mapped to the user
                    final Set<String> privileges =
                        privilegesEndpoint.getGroupPrivilegesByGroupUUIDs(
                            acctUserDetails.userGroups().stream()
                                .map(AcctGroupInfo::groupUUID)
                                .collect(Collectors.toSet())
                        );

                    // Create the acct-enhanced user details
                    return
                        AcctUser.builder()
                            .withUsername(acctUserDetails.userLoginName())
                            .withPassword(acctUserDetails.userEncryptedPassword())
                            .withGrantedAuthorities(privileges)
                            .withUserUUID(acctUserDetails.userUUID())
                            .withUserIconUUID(acctUserDetails.userIconUUID())
                            .withUserHumanReadableName(acctUserDetails.userName())
                            .withDefaultWorkspaceUUID(acctUserDetails.defaultWorkspaceUUID())
                            .build();
                });
    }
}
