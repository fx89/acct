package com.desolatetimelines.acct.catalog.ws.controller;

import com.desolatetimelines.acct.catalog.service.AcctCatalogService;
import com.desolatetimelines.acct.catalog.ws.endpoint.ItemsEndpoint;
import com.desolatetimelines.acct.catalog.ws.mapper.IncomeOrExpenseItemCategoryPropertiesMapper;
import com.desolatetimelines.acct.catalog.ws.mapper.IncomeOrExpenseItemPropertiesMapper;
import com.desolatetimelines.acct.catalog.ws.mapper.IncomeOrExpenseItemSubcategoryPropertiesMapper;
import com.desolatetimelines.acct.catalog.ws.model.*;
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
    public IncomeOrExpenseItemCategoryUUIDResponse saveIncomeOrExpenseItemCategory(
        @RequestParam(name = "incomeOrExpenseItemCategoryUUID", required = false) String incomeOrExpenseItemCategoryUUID,
        @RequestBody IncomeOrExpenseItemCategorySaveRequest request
    ) {
        return
            new IncomeOrExpenseItemCategoryUUIDResponse(
                catalogService.saveIncomeOrExpenseItemCategory(
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

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + ITEM_SUBCATEGORIES_SAVE + "')")
    @PutMapping(value = "/subcategories", produces = APPLICATION_JSON_VALUE)
    public IncomeOrExpenseItemSubcategoryUUIDResponse saveIncomeOrExpenseItemSubcategory(
        @RequestParam(name = "incomeOrExpenseItemCategoryUUID") String incomeOrExpenseItemCategoryUUID,
        @RequestParam(name = "incomeOrExpenseItemSubcategoryUUID", required = false) String incomeOrExpenseItemSubcategoryUUID,
        @RequestBody IncomeOrExpenseItemSubcategorySaveRequest request
    ) {
        return
            new IncomeOrExpenseItemSubcategoryUUIDResponse(
                catalogService.saveIncomeOrExpenseItemSubcategory(
                    incomeOrExpenseItemCategoryUUID,
                    incomeOrExpenseItemSubcategoryUUID,
                    request.incomeOrExpenseItemSubcategoryName(),
                    request.incomeOrExpenseItemSubcategoryDescription(),
                    request.incomeOrExpenseItemSubcategoryIconUUID()
                ).getIncomeOrExpenseItemSubcategoryUUID()
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + ITEM_SUBCATEGORIES_READ + "')")
    @GetMapping(value = "/subcategories", produces = APPLICATION_JSON_VALUE)
    public Collection<IncomeOrExpenseItemSubcategoryProperties> getIncomeOrExpenseItemSubcategories(
        @RequestParam(name = "incomeOrExpenseItemCategoryUUID") String incomeOrExpenseItemCategoryUUID
    ) {
        return
            IncomeOrExpenseItemSubcategoryPropertiesMapper.fromCollectionOfAcctIncomeOrExpenseItemSubcategories(
                catalogService.getIncomeOrExpenseItemSubcategories(incomeOrExpenseItemCategoryUUID)
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + ITEM_SUBCATEGORIES_DELETE + "')")
    @DeleteMapping(value = "/subcategories", produces = APPLICATION_JSON_VALUE)
    public void deleteIncomeOrExpenseItemSubcategories(
        @RequestParam(name = "incomeOrExpenseItemSubcategoryUUIDs")
        Collection<String> incomeOrExpenseItemSubcategoryUUIDs
    ) {
        catalogService.deleteIncomeOrExpenseItemSubcategories(incomeOrExpenseItemSubcategoryUUIDs);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + ITEMS_SAVE + "')")
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public IncomeOrExpenseItemUUIDResponse saveIncomeOrExpenseItem(
        @RequestParam(name = "incomeOrExpenseItemSubcategoryUUID") String incomeOrExpenseItemSubcategoryUUID,
        @RequestParam(name = "incomeOrExpenseItemUUID", required = false) String incomeOrExpenseItemUUID,
        @RequestBody IncomeOrExpenseItemSaveRequest request
    ) {
        return
            new IncomeOrExpenseItemUUIDResponse(
                catalogService.saveIncomeOrExpenseItem(
                    incomeOrExpenseItemSubcategoryUUID,
                    incomeOrExpenseItemUUID,
                    request.incomeOrExpenseItemName(),
                    request.incomeOrExpenseItemDescription(),
                    request.incomeOrExpenseItemIconUUID()
                ).getIncomeOrExpenseItemUUID()
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + ITEMS_READ + "')")
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public Collection<IncomeOrExpenseItemProperties> getIncomeOrExpenseItems(
        @RequestParam(name = "incomeOrExpenseItemSubcategoryUUID") String incomeOrExpenseItemSubcategoryUUID
    ) {
        return
            IncomeOrExpenseItemPropertiesMapper.fromCollectionOfAcctIncomeOrExpenseItems(
                catalogService.getIncomeOrExpenseItems(incomeOrExpenseItemSubcategoryUUID)
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + ITEMS_DELETE + "')")
    @DeleteMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public void deleteIncomeOrExpenseItems(
        @RequestParam(name = "incomeOrExpenseItemUUIDs") Collection<String> incomeOrExpenseItemUUIDs
    ) {
        catalogService.deleteIncomeOrExpenseItems(incomeOrExpenseItemUUIDs);
    }

}
