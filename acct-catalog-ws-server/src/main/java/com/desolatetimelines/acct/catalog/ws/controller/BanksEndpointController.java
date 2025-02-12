package com.desolatetimelines.acct.catalog.ws.controller;

import com.desolatetimelines.acct.catalog.service.AcctCatalogService;
import com.desolatetimelines.acct.catalog.ws.endpoint.BanksEndpoint;
import com.desolatetimelines.acct.catalog.ws.mapper.BankPropertiesMapper;
import com.desolatetimelines.acct.catalog.ws.model.BankProperties;
import com.desolatetimelines.acct.catalog.ws.model.BankSaveRequest;
import com.desolatetimelines.acct.catalog.ws.model.BankUUIDResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.desolatetimelines.acct.catalog.privilegesprovider.model.CatalogPrivilegeIds.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/banks")
public class BanksEndpointController implements BanksEndpoint {

    private final AcctCatalogService catalogService;

    public BanksEndpointController(AcctCatalogService catalogService) {
        this.catalogService = catalogService;
    }


    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + BANKS_SAVE + "')")
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public BankUUIDResponse saveBank(
        @RequestParam(name = "bankUUID", required = false) String bankUUID,
        @RequestBody BankSaveRequest request
    ) {
        return
            new BankUUIDResponse(
                catalogService.saveBank(
                    bankUUID,
                    request.bankCode(),
                    request.bankName(),
                    request.internetBankingURL(),
                    request.bankIconUUID()
                ).getBankUUID()
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + BANKS_READ + "')")
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public Collection<BankProperties> getBanks() {
        return BankPropertiesMapper.fromCollectionOfAcctBanks(catalogService.getBanks());
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + BANKS_DELETE + "')")
    @DeleteMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public void deleteBanks(@RequestParam(name = "bankUUIDs") Collection<String> bankUUIDs) {
        catalogService.deleteBanks(bankUUIDs);
    }

}
