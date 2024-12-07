package com.desolatetimelines.acct.usermanagement.ws.controller;

import com.desolatetimelines.acct.usermanagement.data.model.AcctUserCreationParameters;
import com.desolatetimelines.acct.usermanagement.model.AcctUser;
import com.desolatetimelines.acct.usermanagement.model.Page;
import com.desolatetimelines.acct.usermanagement.service.AcctUserManagementService;
import com.desolatetimelines.acct.usermanagement.ws.endpoint.UsersEndpoint;
import com.desolatetimelines.acct.usermanagement.ws.mapper.AcctPageInfoMapper;
import com.desolatetimelines.acct.usermanagement.ws.mapper.AcctUserDetailsMapper;
import com.desolatetimelines.acct.usermanagement.ws.mapper.AcctUserInfoMapper;
import com.desolatetimelines.acct.usermanagement.ws.model.*;
import com.desolatetimelines.acct.usermanagement.ws.privateendpoint.UsersPrivateEndpoint;
import com.desolatetimelines.acct.usermanagement.ws.service.AcctUsersEndpointControllerHelperService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import static com.desolatetimelines.acct.usermanagement.privilegesprovider.model.UserManagementPrivilegeIds.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/users")
public class UsersEndpointController implements UsersEndpoint, UsersPrivateEndpoint {

    private final AcctUserManagementService userManagementService;

    private final AcctUsersEndpointControllerHelperService helper;

    public UsersEndpointController(
        AcctUserManagementService userManagementService,
        AcctUsersEndpointControllerHelperService usersService
    ) {
        this.userManagementService = userManagementService;
        this.helper = usersService;
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
            // Read the access token
            final AcctUserDetails userDetails = helper.findUserDetailsByJwt(jwt, false);

            // Once the userUUID of the current user was identified, the password can be reset
            userManagementService.setUserPassword(
                userDetails.userUUID(),
                passwordSettingRequest.userEncryptedPassword()
            );
        }
        // If no access token was provided, or if the wrong kind of access token was provided, then fail
        else {
            throw new IllegalArgumentException("Wrong access token was provided or no access token was provided at all");
        }
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_" + USERS_READ_CURRENT + "')")
    @GetMapping(value = "/currentUser", produces = APPLICATION_JSON_VALUE)
    public AcctUserDetails getCurrentUser() {
        // If the JWT user access token was provided then the process can continue
        if (SecurityContextHolder.getContext().getAuthentication().getCredentials() instanceof Jwt jwt) {
            return helper.findUserDetailsByJwt(jwt);
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

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend','SCOPE_" + USERS_SOFT_DELETE + "')")
    @DeleteMapping(value = "")
    public void deleteUser(@RequestParam("userUUID") String userUUID) {
        userManagementService.softDeleteUserByUserUUID(userUUID);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend','SCOPE_" + USERS_UNDELETE + "')")
    @PutMapping("/undelete")
    public void undelete(@RequestParam("userUUID") String userUUID) {
        userManagementService.undeleteUserByUserUUID(userUUID);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend','SCOPE_" + USERS_READ + "')")
    @GetMapping("")
    public AcctPage<AcctUserInfo> findSortedPageOfUsersByLoginNameOrNamePattern(
        @RequestParam("pattern") String pattern,
        @RequestParam("pageNumber") int pageNumber,
        @RequestParam("pageSize") int pageSize
    ) {
        // Get the page
        final Page<AcctUser> page =
            userManagementService.findUsersByNameOrLoginNamePattern(pattern, pageNumber, pageSize);

        // Transform the page
        return new AcctPage<>(
            page.data().stream().map(AcctUserInfoMapper::fromAcctUser).toList(),
            AcctPageInfoMapper.fromPage(page, pageNumber)
        );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend','SCOPE_" + USERS_SAVE + "')")
    @PutMapping("/defaultWorkspace")
    public void setUserDefaultWorkspace(
        @RequestParam("userUUID") String userUUID,
        @RequestBody AcctWorkspaceUUIDRequest workspaceUUIDRequest
    ) {
        userManagementService.setUserDefaultWorkspace(userUUID, workspaceUUIDRequest.workspaceUUID());
    }
}
