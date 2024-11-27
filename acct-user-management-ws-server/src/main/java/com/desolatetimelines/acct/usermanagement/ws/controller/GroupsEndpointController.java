package com.desolatetimelines.acct.usermanagement.ws.controller;

import com.desolatetimelines.acct.usermanagement.service.AcctUserManagementService;
import com.desolatetimelines.acct.usermanagement.ws.endpoint.GroupsEndpoint;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctGroupDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static com.desolatetimelines.acct.usermanagement.privilegesprovider.model.UserManagementPrivilegeIds.GROUPS_READ;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/groups")
public class GroupsEndpointController implements GroupsEndpoint {

    private final AcctUserManagementService userManagementService;

    public GroupsEndpointController(AcctUserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + GROUPS_READ + "')")
    @GetMapping(value = "/userGroups", produces = APPLICATION_JSON_VALUE)
    public Collection<AcctGroupDetails> getUserGroups(@RequestParam("userUUID") String userUUID) {
        return
            userManagementService.getUserGroups(userUUID)
                .stream()
                .map(groupDetails ->
                    AcctGroupDetails.builder()
                        .withGroupUUID(groupDetails.groupUUID())
                        .withGroupName(groupDetails.groupName())
                        .withGroupDescription(groupDetails.groupDescription())
                        .withGroupIconUUID(groupDetails.groupIconUUID())
                        .build()
                )
                .toList();
    }

}
