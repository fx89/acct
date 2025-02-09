package com.desolatetimelines.acct.catalog.ws.client;

import com.desolatetimelines.acct.catalog.ws.endpoint.ItemsEndpoint;
import com.desolatetimelines.acct.catalog.ws.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${CATALOG_APPLICATION_NAME}-items",
    name = "${CATALOG_APPLICATION_NAME}/${CATALOG_SERVER_CONTEXT_PATH}/items"
)
public interface RESTItemsEndpointClient extends ItemsEndpoint {

    @Override
    @PutMapping(value = "/categories", produces = APPLICATION_JSON_VALUE)
    IncomeOrExpenseItemCategoryUUIDResponse saveIncomeOrExpenseItemCategory(
        @RequestParam(name = "incomeOrExpenseItemCategoryUUID", required = false) String incomeOrExpenseItemCategoryUUID,
        @RequestBody IncomeOrExpenseItemCategorySaveRequest request
    );

    @Override
    @GetMapping(value = "/categories", produces = APPLICATION_JSON_VALUE)
    Collection<IncomeOrExpenseItemCategoryProperties> getIncomeOrExpenseItemCategories();

    @Override
    @DeleteMapping(value = "/categories")
    void deleteIncomeOrExpenseItemCategories(
        @RequestParam(name = "categories") Collection<String> incomeOrExpenseItemCategoryUUIDs
    );

    @Override
    @PutMapping(value = "/subcategories", produces = APPLICATION_JSON_VALUE)
    IncomeOrExpenseItemSubcategoryUUIDResponse saveIncomeOrExpenseItemSubcategory(
        @RequestParam(name = "incomeOrExpenseItemCategoryUUID") String incomeOrExpenseItemCategoryUUID,
        @RequestParam(name = "incomeOrExpenseItemSubcategoryUUID", required = false) String incomeOrExpenseItemSubcategoryUUID,
        @RequestBody IncomeOrExpenseItemSubcategorySaveRequest request
    );

    @Override
    @GetMapping(value = "/subcategories", produces = APPLICATION_JSON_VALUE)
    Collection<IncomeOrExpenseItemSubcategoryProperties> getIncomeOrExpenseItemSubcategories(
        @RequestParam(name = "incomeOrExpenseItemCategoryUUID") String incomeOrExpenseItemCategoryUUID
    );

    @Override
    @DeleteMapping(value = "/subcategories", produces = APPLICATION_JSON_VALUE)
    void deleteIncomeOrExpenseItemSubcategories(
        @RequestParam(name = "incomeOrExpenseItemSubcategoryUUIDs")
        Collection<String> incomeOrExpenseItemSubcategoryUUIDs
    );

    @Override
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    IncomeOrExpenseItemUUIDResponse saveIncomeOrExpenseItem(
        @RequestParam(name = "incomeOrExpenseItemSubcategoryUUID") String incomeOrExpenseItemSubcategoryUUID,
        @RequestParam(name = "incomeOrExpenseItemUUID", required = false) String incomeOrExpenseItemUUID,
        @RequestBody IncomeOrExpenseItemSaveRequest request
    );

    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    Collection<IncomeOrExpenseItemProperties> getIncomeOrExpenseItems(
        @RequestParam(name = "incomeOrExpenseItemSubcategoryUUID") String incomeOrExpenseItemSubcategoryUUID
    );

    @Override
    @DeleteMapping(value = "", produces = APPLICATION_JSON_VALUE)
    void deleteIncomeOrExpenseItems(
        @RequestParam(name = "incomeOrExpenseItemUUIDs") Collection<String> incomeOrExpenseItemUUIDs
    );

}
