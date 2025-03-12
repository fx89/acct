package com.desolatetimelines.acct.currency.ws.service;

import com.desolatetimelines.acct.currency.service.AcctCurrencyService;
import com.desolatetimelines.acct.usage.ws.redist.service.InUseItemsService;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementation of the {@link InUseItemsService} for the ACCT Currency service
 */
@Service
public class AcctCurrencyInUseItemsService implements InUseItemsService {

    private final AcctCurrencyService currencyService;

    public AcctCurrencyInUseItemsService(AcctCurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public Collection<String> getInUseItemUUIDs(String objectType, Collection<String> itemUUIDs) {
        return currencyService.getInUseItemUUIDs(objectType, itemUUIDs);
    }
}
