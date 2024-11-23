package com.desolatetimelines.acct.security.ws.controller;

import com.desolatetimelines.acct.security.service.AcctSecurityService;
import com.desolatetimelines.acct.security.ws.endpoint.PrivilegesEndpoint;
import com.desolatetimelines.acct.security.ws.endpoint.model.Privilege;
import com.desolatetimelines.acct.security.ws.mapper.AcctPrivilegeMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/privileges")
public class PrivilegesController implements PrivilegesEndpoint {

    private final AcctSecurityService securityService;

    public PrivilegesController(AcctSecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_backend')")
    @GetMapping(value = "/byGroupUUIDs", produces = APPLICATION_JSON_VALUE)
    public Set<String> getGroupPrivilegesByGroupUUIDs(
        @RequestParam("groupUUIDs") Collection<String> groupUUIDs
    ) {
        return securityService.getGroupPrivilegesByGroupUUIDs(groupUUIDs);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_backend')")
    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public Set<Privilege> getAllAssignablePrivileges() {
        return
            securityService.getAllPrivileges().stream()
                .map(AcctPrivilegeMapper::toPrivilege)
                .collect(Collectors.toSet());
    }

}
