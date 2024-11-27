package com.desolatetimelines.acct.security.ws.service;

import com.desolatetimelines.acct.security.service.AcctSecurityService;
import com.desolatetimelines.acct.usage.ws.redist.service.InUseItemsService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AcctSecurityInUseItemsService implements InUseItemsService {

    private final AcctSecurityService acctSecurityService;

    public AcctSecurityInUseItemsService(AcctSecurityService acctSecurityService) {
        this.acctSecurityService = acctSecurityService;
    }

    @Override
    public Collection<String> getInUseItemUUIDs(String objectType, Collection<String> itemUUIDs) {
        return acctSecurityService.getInUseItemUUIDs(objectType, itemUUIDs);
    }

}
