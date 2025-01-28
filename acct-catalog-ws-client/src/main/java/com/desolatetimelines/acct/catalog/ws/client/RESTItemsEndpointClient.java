package com.desolatetimelines.acct.catalog.ws.client;

import com.desolatetimelines.acct.catalog.ws.endpoint.ItemsEndpoint;
import com.desolatetimelines.acct.catalog.ws.model.IncomeOrExpenseItemCategoryProperties;
import com.desolatetimelines.acct.catalog.ws.model.IncomeOrExpenseItemCategorySaveRequest;
import com.desolatetimelines.acct.catalog.ws.model.IncomeOrExpenseItemCategoryUUIDResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${CATALOG_APPLICATION_NAME}-items",
    name = "${CATALOG_APPLICATION_NAME}/${CATALOG_SERVER_CONTEXT_PATH}/items"
)
public interface RESTItemsEndpointClient extends ItemsEndpoint {

    @Override
    @PutMapping(value = "/categories", produces = APPLICATION_JSON_VALUE)
    IncomeOrExpenseItemCategoryUUIDResponse saveIncomeOrExpenseItem(
        @RequestParam(name = "incomeOrExpenseItemCategoryUUID", required = false) String incomeOrExpenseItemCategoryUUID,
        @RequestBody IncomeOrExpenseItemCategorySaveRequest request
    );

    @Override
    @GetMapping(value = "/categories", produces = APPLICATION_JSON_VALUE)
    Collection<IncomeOrExpenseItemCategoryProperties> getIncomeOrExpenseItemCategories();

}
