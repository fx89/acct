package com.desolatetimelines.acct.usermanagement.ws.client;

import com.desolatetimelines.acct.usermanagement.ws.endpoint.GroupsEndpoint;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctGroupDetails;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctGroupUUIDResponse;
import com.desolatetimelines.acct.common.ws.model.AcctPage;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUsersGroupCreationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${USER_MANAGEMENT_APPLICATION_NAME}-groups",
    name = "${USER_MANAGEMENT_APPLICATION_NAME}/${USER_MANAGEMENT_SERVER_CONTEXT_PATH}/groups"
)
public interface RESTGroupsEndpointClient extends GroupsEndpoint {

    @Override
    @GetMapping(value = "/userGroups", produces = APPLICATION_JSON_VALUE)
    Collection<AcctGroupDetails> getUserGroups(@RequestParam("userUUID") String userUUID);

    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    AcctPage<AcctGroupDetails> findSortedPageOfGroupsByNamePattern(
        @RequestParam("pattern") String pattern,
        @RequestParam("pageNumber") int pageNumber,
        @RequestParam("pageSize") int pageSize
    );

    @Override
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    AcctGroupUUIDResponse saveUsersGroup(
        @RequestParam(value = "groupUUID", required = false) String groupUUID,
        @RequestBody AcctUsersGroupCreationRequest usersGroupCreationRequest
    );

    @Override
    @DeleteMapping("")
    void deleteUsersGroup(@RequestParam("groupUUID") String groupUUID);

}
