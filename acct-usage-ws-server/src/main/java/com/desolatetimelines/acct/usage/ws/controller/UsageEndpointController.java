package com.desolatetimelines.acct.usage.ws.controller;

import com.desolatetimelines.acct.usage.service.AcctUsageService;
import com.desolatetimelines.acct.usage.ws.endpoint.UsageEndpoint;
import com.desolatetimelines.acct.usage.ws.model.ServiceItemTypesList;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/")
public class UsageEndpointController implements UsageEndpoint {

    private final AcctUsageService usageService;

    public UsageEndpointController(AcctUsageService usageService) {
        this.usageService = usageService;
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_backend')")
    @PutMapping(value = "/registerItemTypesForService", consumes = APPLICATION_JSON_VALUE)
    public void registerItemTypesForService(
        @RequestBody ServiceItemTypesList serviceItemTypesList
    ) {
        usageService.registerItemTypesForService(
            serviceItemTypesList.serviceName(),
            serviceItemTypesList.serviceContextPath(),
            serviceItemTypesList.itemTypes()
        );
    }

}
