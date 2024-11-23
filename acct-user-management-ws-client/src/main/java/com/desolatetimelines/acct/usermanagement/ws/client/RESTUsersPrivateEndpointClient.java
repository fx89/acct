package com.desolatetimelines.acct.usermanagement.ws.client;

import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserDetails;
import com.desolatetimelines.acct.usermanagement.ws.privateendpoint.UsersPrivateEndpoint;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${USER_MANAGEMENT_APPLICATION_NAME}-users-private",
    name = "${USER_MANAGEMENT_APPLICATION_NAME}/${USER_MANAGEMENT_SERVER_CONTEXT_PATH}/users"
)
public interface RESTUsersPrivateEndpointClient extends UsersPrivateEndpoint {

    @Override
    @GetMapping(value = "/username", produces = APPLICATION_JSON_VALUE)
    AcctUserDetails getUserByUsername(@RequestParam("username") String username);

}
