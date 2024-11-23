package com.desolatetimelines.acct.security.ws.service;

import com.desolatetimelines.acct.usage.ws.redist.service.InUseItemsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AcctSecurityInUseItemsService implements InUseItemsService {

    @Override
    public Collection<String> getInUseItemUUIDs(String objectType, Collection<String> itemUUIDs) {
        return List.of("test 3");
    }

}
