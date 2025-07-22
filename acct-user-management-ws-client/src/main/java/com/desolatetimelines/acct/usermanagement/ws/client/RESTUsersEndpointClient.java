package com.desolatetimelines.acct.usermanagement.ws.client;

import com.desolatetimelines.acct.common.ws.model.AcctPage;
import com.desolatetimelines.acct.common.ws.model.AcctStatusResponse;
import com.desolatetimelines.acct.usermanagement.ws.endpoint.UsersEndpoint;
import com.desolatetimelines.acct.usermanagement.ws.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${USER_MANAGEMENT_APPLICATION_NAME}-users",
    name = "${USER_MANAGEMENT_APPLICATION_NAME}/${USER_MANAGEMENT_SERVER_CONTEXT_PATH}/users"
)
public interface RESTUsersEndpointClient extends UsersEndpoint {

    @Override
    @RequestMapping(value = "/userUUID", method = RequestMethod.GET)
    AcctUserDetails getUserByUserUUID(@RequestParam("userUUID") String userUUID);

    @Override
    @RequestMapping(value = "", method = RequestMethod.PUT)
    AcctUserUUIDResponse saveUser(@RequestBody AcctUserCreationRequest request);

    @Override
    @PostMapping(value = "/currentUser/userName", produces = APPLICATION_JSON_VALUE)
    AcctStatusResponse setCurrentUserName(
        @RequestBody AcctUserNameUpdateRequest userNameUpdateRequest
    );

    @PostMapping(value = "/currentUser/defaultWorkspace", produces = APPLICATION_JSON_VALUE)
    AcctStatusResponse setCurrentUserDefaultWorkspace(
        @RequestBody AcctUserDefaultWorkspaceUpdateRequest userDefaultWorkspaceUpdateRequest
    );

    @Override
    @GetMapping(value = "")
    void deleteUser(@RequestParam("userUUID") String userUUID);

    @Override
    @DeleteMapping(value = "/currentUser/softDelete")
    AcctStatusResponse deleteCurrentUser();

    @Override
    @PostMapping(value = "/currentUser", produces = APPLICATION_JSON_VALUE)
    default AcctStatusResponse setCurrentUserPassword(AcctCurrentUserPasswordSettingRequest passwordSettingRequest) {
        throw new UnsupportedOperationException("This operation is not intended for back-end clients");
    }

    @Override
    @PostMapping(value = "/currentUser/icon", produces = APPLICATION_JSON_VALUE)
    AcctStatusResponse setCurrentUserIcon(AcctUserIconUpdateRequest iconSettingRequest);

    @Override
    default AcctUserDetails getCurrentUser() {
        throw new UnsupportedOperationException("This operation is not intended for back-end clients");
    }

    @Override
    @PutMapping("/undelete")
    void undelete(@RequestParam("userUUID") String userUUID);

    @Override
    @GetMapping("")
    AcctPage<AcctUserInfo> findSortedPageOfUsersByLoginNameOrNamePattern(
        @RequestParam("pattern") String pattern,
        @RequestParam("pageNumber") int pageNumber,
        @RequestParam("pageSize") int pageSize
    );

    @Override
    @PutMapping("/defaultWorkspace")
    void setUserDefaultWorkspace(
        @RequestParam("userUUID") String userUUID,
        @RequestBody AcctWorkspaceUUIDRequest workspaceUUIDRequest
    );
}
