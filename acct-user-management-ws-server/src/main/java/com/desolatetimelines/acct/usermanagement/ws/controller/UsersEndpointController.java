package com.desolatetimelines.acct.usermanagement.ws.controller;

import com.desolatetimelines.acct.usermanagement.data.model.AcctUserCreationParameters;
import com.desolatetimelines.acct.usermanagement.service.AcctUserManagementService;
import com.desolatetimelines.acct.usermanagement.ws.endpoint.UsersEndpoint;
import com.desolatetimelines.acct.usermanagement.ws.mapper.AcctUserDetailsMapper;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserCreationRequest;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserDetails;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserUUIDResponse;
import com.desolatetimelines.acct.usermanagement.ws.privateendpoint.UsersPrivateEndpoint;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', '" + USERS_SAVE + "')")
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
