package com.desolatetimelines.acct.catalog.ws.controller;

import com.desolatetimelines.acct.catalog.service.AcctCatalogService;
import com.desolatetimelines.acct.catalog.ws.endpoint.ItemsEndpoint;
import com.desolatetimelines.acct.catalog.ws.mapper.IncomeOrExpenseItemCategoryPropertiesMapper;
import com.desolatetimelines.acct.catalog.ws.model.IncomeOrExpenseItemCategoryProperties;
import com.desolatetimelines.acct.catalog.ws.model.IncomeOrExpenseItemCategorySaveRequest;
import com.desolatetimelines.acct.catalog.ws.model.IncomeOrExpenseItemCategoryUUIDResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.desolatetimelines.acct.catalog.privilegesprovider.model.CatalogPrivilegeIds.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/items")
public class ItemsEndpointController implements ItemsEndpoint {

    private final AcctCatalogService catalogService;

    public ItemsEndpointController(AcctCatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + ITEM_CATEGORIES_SAVE + "')")
    @PutMapping(value = "/categories", produces = APPLICATION_JSON_VALUE)
    public IncomeOrExpenseItemCategoryUUIDResponse saveIncomeOrExpenseItem(
        @RequestParam(name = "incomeOrExpenseItemCategoryUUID", required = false) String incomeOrExpenseItemCategoryUUID,
        @RequestBody IncomeOrExpenseItemCategorySaveRequest request
    ) {
        return
            new IncomeOrExpenseItemCategoryUUIDResponse(
                catalogService.saveIncomeOrExpenseItem(
                    incomeOrExpenseItemCategoryUUID,
                    request.incomeOrExpenseItemCategoryName(),
                    request.incomeOrExpenseItemCategoryDescription(),
                    request.incomeOrExpenseItemCategoryIconUUID()
                ).getIncomeOrExpenseItemCategoryUUID()
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + ITEM_CATEGORIES_READ + "')")
    @GetMapping(value = "/categories", produces = APPLICATION_JSON_VALUE)
    public Collection<IncomeOrExpenseItemCategoryProperties> getIncomeOrExpenseItemCategories() {
        return
            IncomeOrExpenseItemCategoryPropertiesMapper
                .fromCollectionOfAcctIncomeOrExpenseItemCategories(
                    catalogService.getIncomeOrExpenseItemCategories()
                );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + ITEM_CATEGORIES_DELETE + "')")
    @DeleteMapping(value = "/categories")
    public void deleteIncomeOrExpenseItemCategories(
        @RequestParam(name = "categories") Collection<String> incomeOrExpenseItemCategoryUUIDs
    ) {
        catalogService.deleteIncomeOrExpenseItemCategories(incomeOrExpenseItemCategoryUUIDs);
    }

}
