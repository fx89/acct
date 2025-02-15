package com.desolatetimelines.acct.catalog.ws.service;

import com.desolatetimelines.acct.catalog.service.AcctCatalogService;
import com.desolatetimelines.acct.usage.ws.redist.service.InUseItemsService;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementation of the {@link InUseItemsService} for the ACCT Usage service
 */
@Service
public class AcctCatalogInUseItemsService implements InUseItemsService {

    private final AcctCatalogService catalogService;

    public AcctCatalogInUseItemsService(AcctCatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Override
    public Collection<String> getInUseItemUUIDs(String objectType, Collection<String> itemUUIDs) {
        return catalogService.getInUseItemUUIDs(objectType, itemUUIDs);
    }
}
