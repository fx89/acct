package com.desolatetimelines.acct.security.ws.client;

import com.desolatetimelines.acct.security.ws.endpoint.PrivilegesEndpoint;
import com.desolatetimelines.acct.security.ws.endpoint.model.Privilege;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Set;

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
}
