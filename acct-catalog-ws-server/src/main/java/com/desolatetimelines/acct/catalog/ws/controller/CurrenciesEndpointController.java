package com.desolatetimelines.acct.catalog.ws.controller;

import com.desolatetimelines.acct.catalog.service.AcctCatalogService;
import com.desolatetimelines.acct.catalog.ws.endpoint.CurrenciesEndpoint;
import com.desolatetimelines.acct.catalog.ws.mapper.CurrencyPropertiesMapper;
import com.desolatetimelines.acct.catalog.ws.model.CurrencyProperties;
import com.desolatetimelines.acct.catalog.ws.model.CurrencySaveRequest;
import com.desolatetimelines.acct.catalog.ws.model.CurrencyUUIDResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.desolatetimelines.acct.catalog.privilegesprovider.model.CatalogPrivilegeIds.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/currencies")
public class CurrenciesEndpointController implements CurrenciesEndpoint {

    private final AcctCatalogService catalogService;

    public CurrenciesEndpointController(AcctCatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + BANKS_SAVE + "')")
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public CurrencyUUIDResponse saveCurrency(
        @RequestParam(name = "currencyUUID", required = false) String currencyUUID,
        @RequestBody CurrencySaveRequest request
    ) {
        return
            new CurrencyUUIDResponse(
                catalogService.saveCurrency(
                    currencyUUID,
                    request.currencyCode(),
                    request.currencyName(),
                    request.currencyIconUUID()
                ).getCurrencyUUID()
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + BANKS_READ + "')")
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public Collection<CurrencyProperties> getCurrencies() {
        return
            CurrencyPropertiesMapper.fromCollectionOfAcctCurrencies(
                catalogService.getCurrencies()
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + BANKS_DELETE + "')")
    @DeleteMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public void deleteCurrencies(
        @RequestParam(name = "currencyUUIDs") Collection<String> currencyUUIDs
    ) {
        catalogService.deleteCurrencies(currencyUUIDs);
    }
}
