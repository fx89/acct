package com.desolatetimelines.acct.usermanagement.ws.service;

import com.desolatetimelines.acct.usage.ws.redist.service.InUseItemsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Implementation of the {@link InUseItemsService} for the ACCT Usage service
 */
@Service
public class AcctUsageInUseItemsService implements InUseItemsService {
    @Override
    public Collection<String> getInUseItemUUIDs(String objectType, Collection<String> itemUUIDs) {
        return List.of("test 1", "test 2");
    }
}
