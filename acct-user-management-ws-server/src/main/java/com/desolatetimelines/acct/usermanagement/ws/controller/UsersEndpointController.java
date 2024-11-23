package com.desolatetimelines.acct.usermanagement.ws.controller;

import com.desolatetimelines.acct.usermanagement.service.AcctUserManagementService;
import com.desolatetimelines.acct.usermanagement.ws.endpoint.UsersEndpoint;
import com.desolatetimelines.acct.usermanagement.ws.mapper.AcctUserDetailsMapper;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserDetails;
import com.desolatetimelines.acct.usermanagement.ws.privateendpoint.UsersPrivateEndpoint;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
