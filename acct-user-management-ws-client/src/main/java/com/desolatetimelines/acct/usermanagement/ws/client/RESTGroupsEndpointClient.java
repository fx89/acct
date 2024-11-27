package com.desolatetimelines.acct.usermanagement.ws.client;

import com.desolatetimelines.acct.usermanagement.ws.model.AcctGroupDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${USER_MANAGEMENT_APPLICATION_NAME}-groups",
    name = "${USER_MANAGEMENT_APPLICATION_NAME}/${USER_MANAGEMENT_SERVER_CONTEXT_PATH}/groups"
)
public interface RESTGroupsEndpointClient {

    @GetMapping(value = "/userGroups", produces = APPLICATION_JSON_VALUE)
    Collection<AcctGroupDetails> getUserGroups(@RequestParam("userUUID") String userUUID);

}
