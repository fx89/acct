package com.desolatetimelines.acct.usermanagement.ws.controller;

import com.desolatetimelines.acct.usermanagement.model.AcctUsersGroup;
import com.desolatetimelines.acct.usermanagement.model.Page;
import com.desolatetimelines.acct.usermanagement.service.AcctUserManagementService;
import com.desolatetimelines.acct.usermanagement.ws.endpoint.GroupsEndpoint;
import com.desolatetimelines.acct.usermanagement.ws.mapper.AcctGroupDetailsMapper;
import com.desolatetimelines.acct.usermanagement.ws.mapper.AcctPageInfoMapper;
import com.desolatetimelines.acct.usermanagement.ws.mapper.AcctUsersGroupCreationRequestsMapper;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctGroupDetails;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctGroupUUIDResponse;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctPage;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUsersGroupCreationRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.desolatetimelines.acct.usermanagement.privilegesprovider.model.UserManagementPrivilegeIds.GROUPS_READ;
import static com.desolatetimelines.acct.usermanagement.privilegesprovider.model.UserManagementPrivilegeIds.GROUPS_SAVE;
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

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + GROUPS_READ + "')")
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public AcctPage<AcctGroupDetails> findSortedPageOfGroupsByNamePattern(
        @RequestParam("pattern") String pattern,
        @RequestParam("pageNumber") int pageNumber,
        @RequestParam("pageSize") int pageSize
    ) {
        // Get the page
        final Page<AcctUsersGroup> page =
            userManagementService.findGroupsByNamePattern(pattern, pageNumber, pageSize);

        // Transform the page
        return new AcctPage<>(
            page.data().stream().map(AcctGroupDetailsMapper::fromDataLayerAcctUserDetails).toList(),
            AcctPageInfoMapper.fromPage(page, pageNumber)
        );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + GROUPS_SAVE + "')")
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public AcctGroupUUIDResponse saveUsersGroup(
        @RequestParam(value = "groupUUID", required = false) String groupUUID,
        @RequestBody AcctUsersGroupCreationRequest usersGroupCreationRequest
    ) {
        return
            new AcctGroupUUIDResponse(
                userManagementService
                    .saveUsersGroup(
                        AcctUsersGroupCreationRequestsMapper.toAcctUsersGroup(
                            groupUUID,
                            usersGroupCreationRequest
                        )
                    )
            );
    }

}
