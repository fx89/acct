package com.desolatetimelines.acct.security.ws.client;

import com.desolatetimelines.acct.security.ws.endpoint.PrivilegesEndpoint;
import com.desolatetimelines.acct.security.ws.endpoint.model.GroupPrivileges;
import com.desolatetimelines.acct.security.ws.endpoint.model.Privilege;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${SECURITY_APPLICATION_NAME}-privileges",
    name = "${SECURITY_APPLICATION_NAME}/${SECURITY_SERVER_CONTEXT_PATH}"
)
public interface RESTPrivilegesEndpointClient extends PrivilegesEndpoint {
    @Override
    @RequestMapping(value = "/privileges/byGroupUUIDs", method = RequestMethod.GET)
    Set<String> getGroupPrivilegesByGroupUUIDs(@RequestParam("groupUUIDs") Collection<String> groupUUIDs);

    @Override
    @RequestMapping(value = "/privileges", method = RequestMethod.GET)
    Set<Privilege> getAllAssignablePrivileges();

    @Override
    @PutMapping(value = "/groupPrivileges", produces = APPLICATION_JSON_VALUE)
    void assignPrivilegesToGroup(@RequestBody GroupPrivileges groupPrivilegeIDs);

    @Override
    @DeleteMapping(value = "/groupPrivileges", produces = APPLICATION_JSON_VALUE)
    void removePrivilegesFromGroup(
        @RequestParam("groupUUID") String groupUUID,
        @RequestBody Collection<String> privilegeIDs
    );

    @Override
    @GetMapping(value = "/userPrivileges", produces = APPLICATION_JSON_VALUE)
    Collection<String> getPrivilegesAssignedToUser(@RequestParam("userUUID") String userUUID);
}
