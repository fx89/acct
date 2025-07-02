package com.desolatetimelines.acct.security.ws.controller;

import com.desolatetimelines.acct.security.service.AcctSecurityService;
import com.desolatetimelines.acct.security.ws.endpoint.PrivilegesEndpoint;
import com.desolatetimelines.acct.security.ws.endpoint.model.GroupPrivileges;
import com.desolatetimelines.acct.security.ws.endpoint.model.Privilege;
import com.desolatetimelines.acct.security.ws.mapper.AcctPrivilegeMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static com.desolatetimelines.acct.security.privilegesprovider.model.SecurityPrivilegeIds.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/privileges")
public class PrivilegesController implements PrivilegesEndpoint {

    private final AcctSecurityService securityService;

    public PrivilegesController(AcctSecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend')")
    @GetMapping(value = "/byGroupUUIDs", produces = APPLICATION_JSON_VALUE)
    public Set<String> getGroupPrivilegesByGroupUUIDs(
        @RequestParam("groupUUIDs") Collection<String> groupUUIDs
    ) {
        return securityService.getGroupPrivilegesByGroupUUIDs(groupUUIDs);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + PRIVILEGES_READ + "')")
    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public Set<Privilege> getAllAssignablePrivileges() {
        return
            securityService.getAllPrivileges().stream()
                .map(AcctPrivilegeMapper::toPrivilege)
                .collect(Collectors.toSet());
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', '" + PRIVILEGES_SAVE + "')")
    @PutMapping(value = "/groupPrivileges", produces = APPLICATION_JSON_VALUE)
    public void assignPrivilegesToGroup(@RequestBody GroupPrivileges groupPrivilegeIDs) {
        securityService.assignPrivilegesToGroup(groupPrivilegeIDs.groupUUID(), groupPrivilegeIDs.privilegeNames());
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + PRIVILEGES_DELETE + "')")
    @DeleteMapping(value = "/groupPrivileges", produces = APPLICATION_JSON_VALUE)
    public void removePrivilegesFromGroup(
        @RequestParam("groupUUID") String groupUUID,
        @RequestBody Collection<String> privilegeIDs
    ) {
        securityService.removePrivilegesFromGroup(groupUUID, privilegeIDs);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + PRIVILEGES_READ + "')")
    @GetMapping(value = "/userPrivileges", produces = APPLICATION_JSON_VALUE)
    public Collection<String> getPrivilegesAssignedToUser(@RequestParam("userUUID") String userUUID) {
        return securityService.getPrivilegesAssignedToUser(userUUID);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + OWN_PRIVILEGES_READ + "')")
    @GetMapping(value = "/currentUserPrivileges", produces = APPLICATION_JSON_VALUE)
    public Collection<String> getPrivilegesAssignedToCurrentUser() {
        // If the JWT user access token was provided then the process can continue
        if (SecurityContextHolder.getContext().getAuthentication().getCredentials() instanceof Jwt jwt) {
            // Read the userUUID from the access token
            final String userUUID = (String) jwt.getClaims().get("userUUID");

            // Get and return the privileges
            return getPrivilegesAssignedToUser(userUUID);
        }
        // If no access token was provided, or if the wrong kind of access token was provided, then fail
        else {
            throw new IllegalArgumentException("Wrong access token was provided or no access token was provided at all");
        }
    }

}
