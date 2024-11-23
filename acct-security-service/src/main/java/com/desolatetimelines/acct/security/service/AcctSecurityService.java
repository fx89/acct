package com.desolatetimelines.acct.security.service;

import com.desolatetimelines.acct.common.ObjectTypes;
import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;
import com.desolatetimelines.acct.security.model.AcctGroupPrivilege;
import com.desolatetimelines.acct.usage.ws.client.RESTUsageEndpointClient;
import com.desolatetimelines.acct.usage.ws.model.ServiceItemTypesList;
import com.desolatetimelines.acct.usermanagement.data.service.AcctPrivilegesDataService;
import com.desolatetimelines.acct.usermanagement.data.service.AcctSecurityDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Main module of the security services layer
 */
@Service
public class AcctSecurityService {

    private final AcctSecurityDataService securityDataService;

    private final AcctPrivilegesDataService privilegesDataService;

    private final RESTUsageEndpointClient usageEndpointClient;

    private final String applicationName;

    private final String contextPath;

    public AcctSecurityService(
        AcctSecurityDataService securityDataService,
        AcctPrivilegesDataService privilegesDataService,
        RESTUsageEndpointClient usageEndpointClient,
        @Value("${SECURITY_APPLICATION_NAME}") String applicationName,
        @Value("${SECURITY_SERVER_CONTEXT_PATH}") String contextPath
    ) {
        this.securityDataService = securityDataService;
        this.privilegesDataService = privilegesDataService;
        this.usageEndpointClient = usageEndpointClient;
        this.applicationName = applicationName;
        this.contextPath = contextPath;
    }

    /**
     * Registers in-use item types with the usage service upon startup
     */
    @SuppressWarnings("unused")
    @EventListener(ApplicationReadyEvent.class)
    private void registerInUseObjectTypes() {
        usageEndpointClient.registerItemTypesForService(
            ServiceItemTypesList.builder()
                .withServiceName(applicationName)
                .withServiceContextPath(contextPath)
                .withItemType(List.of(
                    ObjectTypes.WORKSPACE.name(),
                    ObjectTypes.DASHBOARD.name(),
                    ObjectTypes.REPORT.name(),
                    ObjectTypes.GROUP.name()
                ))
                .build()
        );
    }

    /**
     * Retrieves a set of all the privileges mapped to all groups with the given UUIDs
     */
    public Set<String> getGroupPrivilegesByGroupUUIDs(Collection<String> groupUUIDs) {
        return
            securityDataService.findAllGroupPrivilegesByGroupUUIDIn(groupUUIDs)
                .stream()
                .map(AcctGroupPrivilege::getPrivilegeName)
                .collect(Collectors.toSet());
    }

    public Set<AcctPrivilege> getAllPrivileges() {
        return privilegesDataService.findAllPrivileges();
    }

}
