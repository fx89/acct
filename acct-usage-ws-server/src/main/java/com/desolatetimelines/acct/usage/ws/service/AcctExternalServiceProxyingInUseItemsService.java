package com.desolatetimelines.acct.usage.ws.service;

import com.desolatetimelines.acct.usage.service.AcctUsageService;
import com.desolatetimelines.acct.usage.ws.redist.service.InUseItemsService;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * This {@link InUseItemsService} proxies the
 * {@link com.desolatetimelines.acct.usage.ws.redist.controller.InUseEndpointController in-use endpoint controllers}
 * of the ACCT services that are registered with the ACCT Usage service.
 */
@Service
public class AcctExternalServiceProxyingInUseItemsService implements InUseItemsService {

    private final AcctUsageService acctUsageService;

    public AcctExternalServiceProxyingInUseItemsService(AcctUsageService acctUsageService) {
        this.acctUsageService = acctUsageService;
    }

    @Override
    public Collection<String> getInUseItemUUIDs(String objectType, Collection<String> itemUUIDs) {
        return acctUsageService.getInUseItemUUIDs(objectType, itemUUIDs);
    }

}
