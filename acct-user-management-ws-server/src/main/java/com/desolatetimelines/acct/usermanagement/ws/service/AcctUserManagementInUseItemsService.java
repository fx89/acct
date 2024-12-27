package com.desolatetimelines.acct.usermanagement.ws.service;

import com.desolatetimelines.acct.usage.ws.redist.service.InUseItemsService;
import com.desolatetimelines.acct.usermanagement.service.AcctUserManagementService;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementation of the {@link InUseItemsService} for the ACCT Usage service
 */
@Service
public class AcctUserManagementInUseItemsService implements InUseItemsService {

    private final AcctUserManagementService userManagementService;

    public AcctUserManagementInUseItemsService(AcctUserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @Override
    public Collection<String> getInUseItemUUIDs(String objectType, Collection<String> itemUUIDs) {
        return userManagementService.getInUseItemUUIDs(objectType, itemUUIDs);
    }
}
