package com.desolatetimelines.acct.security.ws.controller;

import com.desolatetimelines.acct.security.service.AcctSecurityService;
import com.desolatetimelines.acct.security.ws.endpoint.PrivilegesEndpoint;
import com.desolatetimelines.acct.security.ws.endpoint.model.GroupPrivileges;
import com.desolatetimelines.acct.security.ws.endpoint.model.Privilege;
import com.desolatetimelines.acct.security.ws.mapper.AcctPrivilegeMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static com.desolatetimelines.acct.security.privilegesprovider.model.SecurityPrivilegeIds.PRIVILEGES_READ;
import static com.desolatetimelines.acct.security.privilegesprovider.model.SecurityPrivilegeIds.PRIVILEGES_SAVE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/privileges")
public class PrivilegesController implements PrivilegesEndpoint {

    private final AcctSecurityService securityService;

    public PrivilegesController(AcctSecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend')")
    @GetMapping(value = "/byGroupUUIDs", produces = APPLICATION_JSON_VALUE)
    public Set<String> getGroupPrivilegesByGroupUUIDs(
        @RequestParam("groupUUIDs") Collection<String> groupUUIDs
    ) {
        return securityService.getGroupPrivilegesByGroupUUIDs(groupUUIDs);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', '" + PRIVILEGES_READ + "')")
    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public Set<Privilege> getAllAssignablePrivileges() {
        return
            securityService.getAllPrivileges().stream()
                .map(AcctPrivilegeMapper::toPrivilege)
                .collect(Collectors.toSet());
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', '" + PRIVILEGES_SAVE + "')")
    @PutMapping(value = "/groupPrivileges", produces = APPLICATION_JSON_VALUE)
    public void assignPrivilegesToGroup(@RequestBody GroupPrivileges groupPrivileges) {
        securityService.assignPrivilegesToGroup(groupPrivileges.groupUUID(), groupPrivileges.privilegeNames());
    }

}
