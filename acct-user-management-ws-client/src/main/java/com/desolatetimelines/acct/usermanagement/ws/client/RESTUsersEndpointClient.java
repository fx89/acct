package com.desolatetimelines.acct.usermanagement.ws.client;

import com.desolatetimelines.acct.usermanagement.ws.endpoint.UsersEndpoint;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctCurrentUserPasswordSettingRequest;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserCreationRequest;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserDetails;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserUUIDResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "")
    void deleteUser(@RequestParam("userUUID") String userUUID);

    @Override
    default void setCurrentUserPassword(AcctCurrentUserPasswordSettingRequest passwordSettingRequest) {
        throw new UnsupportedOperationException("This operation is not intended for back-end clients");
    }

    @Override
    default AcctUserDetails getCurrentUser() {
        throw new UnsupportedOperationException("This operation is not intended for back-end clients");
    }

    @Override
    @PutMapping("/undelete")
    void undelete(@RequestParam("userUUID") String userUUID);
}
