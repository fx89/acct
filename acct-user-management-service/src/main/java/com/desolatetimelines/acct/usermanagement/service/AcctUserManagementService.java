package com.desolatetimelines.acct.usermanagement.service;

import com.desolatetimelines.acct.common.ObjectTypes;
import com.desolatetimelines.acct.usage.ws.client.RESTUsageEndpointClient;
import com.desolatetimelines.acct.usage.ws.model.ServiceItemTypesList;
import com.desolatetimelines.acct.usermanagement.data.model.AcctUserDetails;
import com.desolatetimelines.acct.usermanagement.data.service.AcctUserManagementDataService;
import com.desolatetimelines.acct.usermanagement.exception.AcctUserManagementNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Account Management services layer facade
 */
@Service
public class AcctUserManagementService {

    private final AcctUserManagementDataService dataService;

    private final RESTUsageEndpointClient usageEndpointClient;

    private final String applicationName;

    private final String contextPath;

    public AcctUserManagementService(
        AcctUserManagementDataService dataService,
        RESTUsageEndpointClient usageEndpointClient,
        @Value("${USER_MANAGEMENT_APPLICATION_NAME}") String applicationName,
        @Value("${USER_MANAGEMENT_SERVER_CONTEXT_PATH}") String contextPath
    ) {
        this.dataService = dataService;
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
                    ObjectTypes.ICON.name(),
                    ObjectTypes.WORKSPACE.name()
                ))
                .build()
        );
    }

    /**
     * Find the user with the given login name and return the details, or throw an exception
     *
     * @param userLoginName the given login name
     */
    public AcctUserDetails findUserDetailsByUserLoginName(String userLoginName) {
        return
            dataService.retrieveUserDetailsByUserLoginName(userLoginName)
                .orElseThrow(() -> new AcctUserManagementNotFoundException("User not found"));
    }

    /**
     * Find the user with the given UUID and return the details, or throw an exception
     *
     * @param userUUID the given UUID
     */
    public AcctUserDetails findUserDetailsByUserUserUUID(String userUUID) {
        return
            dataService.retrieveUserDetailsByUserUUID(userUUID)
                .orElseThrow(() -> new AcctUserManagementNotFoundException("User not found"));
    }

}
