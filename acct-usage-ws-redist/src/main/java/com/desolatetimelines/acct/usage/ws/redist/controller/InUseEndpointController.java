package com.desolatetimelines.acct.usage.ws.redist.controller;

import com.desolatetimelines.acct.usage.ws.endpoint.InUseEndpoint;
import com.desolatetimelines.acct.usage.ws.redist.service.InUseItemsAggregationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/")
public class InUseEndpointController implements InUseEndpoint {

    private final InUseItemsAggregationService inUseItemsAggregationService;

    public InUseEndpointController(InUseItemsAggregationService inUseItemsAggregationService) {
        this.inUseItemsAggregationService = inUseItemsAggregationService;
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_backend')")
    @PostMapping(value = "/itemsInUse", consumes = APPLICATION_JSON_VALUE)
    public Collection<String> getItemsInUseOfType(
        @RequestParam(name = "objectType") String objectType,
        @RequestBody Collection<String> itemUUIDsList
    ) {
        return inUseItemsAggregationService.getInUseItemUUIDs(objectType, itemUUIDsList);
    }

}
