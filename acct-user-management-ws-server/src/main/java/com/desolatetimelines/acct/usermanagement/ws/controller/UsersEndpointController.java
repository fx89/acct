package com.desolatetimelines.acct.usermanagement.ws.controller;

import com.desolatetimelines.acct.usermanagement.data.model.AcctUserCreationParameters;
import com.desolatetimelines.acct.usermanagement.service.AcctUserManagementService;
import com.desolatetimelines.acct.usermanagement.ws.endpoint.UsersEndpoint;
import com.desolatetimelines.acct.usermanagement.ws.mapper.AcctUserDetailsMapper;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctCurrentUserPasswordSettingRequest;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserCreationRequest;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserDetails;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserUUIDResponse;
import com.desolatetimelines.acct.usermanagement.ws.privateendpoint.UsersPrivateEndpoint;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import static com.desolatetimelines.acct.usermanagement.privilegesprovider.model.UserManagementPrivilegeIds.USERS_RESET_PASSWORD;
import static com.desolatetimelines.acct.usermanagement.privilegesprovider.model.UserManagementPrivilegeIds.USERS_SAVE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/users")
public class UsersEndpointController implements UsersEndpoint, UsersPrivateEndpoint {

    private final AcctUserManagementService userManagementService;

    public UsersEndpointController(
        AcctUserManagementService userManagementService
    ) {
        this.userManagementService = userManagementService;
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_backend')")
    @GetMapping(value = "/userUUID", produces = APPLICATION_JSON_VALUE)
    public AcctUserDetails getUserByUserUUID(@RequestParam("userUUID") String userUUID) {
        return
            AcctUserDetailsMapper
                .fromDataLayerAcctUserDetails(
                    userManagementService.findUserDetailsByUserUserUUID(userUUID)
                );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + USERS_SAVE + "')")
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public AcctUserUUIDResponse saveUser(@RequestBody AcctUserCreationRequest request) {
        return
            new AcctUserUUIDResponse(
                userManagementService.createUser(
                    AcctUserCreationParameters.builder()
                        .withUserName(request.userName())
                        .withDefaultWorkspaceUUID(request.defaultWorkspaceUUID())
                        .withUserIconUUID(request.userIconUUID())
                        .withUserLoginName(request.userLoginName())
                        .withUserEncryptedPassword(request.userEncryptedPassword())
                )
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_" + USERS_RESET_PASSWORD + "')")
    @PostMapping(value = "/currentUser", produces = APPLICATION_JSON_VALUE)
    public void setCurrentUserPassword(@RequestBody AcctCurrentUserPasswordSettingRequest passwordSettingRequest) {
        // If the JWT user access token was provided then the process can continue
        if (SecurityContextHolder.getContext().getAuthentication().getCredentials() instanceof Jwt jwt) {
            // Get the userUUID claim
            final String userUUID = (String) jwt.getClaims().get("userUUID");

            // If the userUUID claim is not part of the access token then the process cannot continue
            if (userUUID == null) {
                throw new IllegalArgumentException("The access token does not contain the [userUUID] claim");
            }

            // Once the userUUID of the current user was identified, the password can be reset
            userManagementService.setUserPassword(userUUID, passwordSettingRequest.userEncryptedPassword());
        }
        // If no access token was provided, or if the wrong kind of access token was provided, then fail
        else {
            throw new IllegalArgumentException("Wrong access token was provided or no access token was provided at all");
        }
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_backend')")
    @GetMapping(value = "/username", produces = APPLICATION_JSON_VALUE)
    public AcctUserDetails getUserByUsername(@RequestParam("username") String username) {
        return
            AcctUserDetailsMapper
                .fromDataLayerAcctUserDetails(
                    userManagementService.findUserDetailsByUserLoginName(username)
                );
    }
}
