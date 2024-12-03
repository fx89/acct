package com.desolatetimelines.acct.usermanagement.ws.service;

import com.desolatetimelines.acct.usermanagement.service.AcctUserManagementService;
import com.desolatetimelines.acct.usermanagement.ws.controller.UsersEndpointController;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctGroupInfo;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

/**
 * Provides functionality used in the {@link UsersEndpointController}
 */
@Service
public class AcctUsersEndpointControllerHelperService {

    private final AcctUserManagementService userManagementService;

    public AcctUsersEndpointControllerHelperService(AcctUserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    /**
     * Creates a new {@link AcctUserDetails user details object} using the details
     * from the given {@link Jwt access token}. Regardless of weather or not the
     * user is part of a group, the returned user details object does not contain
     * any reference to any group whatsoever.
     *
     * @param jwt the given access token
     */
    public AcctUserDetails findUserDetailsByJwt(Jwt jwt) {
        return findUserDetailsByJwt(jwt, true);
    }

    /**
     * Creates a new {@link AcctUserDetails user details object} using the details
     * from the given {@link Jwt access token}. Optionally, retrieves the groups
     * that the user is part of and adds them to the user details object.
     *
     * @param jwt        the given access token
     * @param withGroups set this option to true to retrieve the groups
     */
    public AcctUserDetails findUserDetailsByJwt(Jwt jwt, boolean withGroups) {
        // Get the claims available in the access token
        final String userUUID = (String) jwt.getClaims().get("userUUID");
        final String userName = (String) jwt.getClaims().get("humanReadableName");
        final String userLoginName = (String) jwt.getClaims().get("sub");
        final String userIconUUID = (String) jwt.getClaims().get("iconUUID");
        final String defaultWorkspaceUUID = (String) jwt.getClaims().get("defaultWorkspaceUUID");

        // If the userUUID claim is not part of the access token then the process cannot continue
        if (userUUID == null) {
            throw new IllegalArgumentException("The access token does not contain the [userUUID] claim");
        }

        // Compose the object
        final AcctUserDetails.AcctUserDetailsBuilder builder =
            AcctUserDetails.builder()
                .withUserUUID(userUUID)
                .withUserName(userName)
                .withUserLoginName(userLoginName)
                .withUserIconUUID(userIconUUID)
                .withDefaultWorkspaceUUID(defaultWorkspaceUUID);

        // If groups are requested, add the groups
        if (withGroups) {
            userManagementService.getUserGroups(userUUID)
                .forEach(group -> builder.withUserGroup(
                    AcctGroupInfo.builder()
                        .withGroupUUID(group.groupUUID())
                        .withGroupName(group.groupName())
                        .build()
                ));
        }

        // Build and return
        return builder.build();
    }

}
