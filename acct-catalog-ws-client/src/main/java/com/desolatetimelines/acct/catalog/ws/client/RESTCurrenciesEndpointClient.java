package com.desolatetimelines.acct.catalog.ws.client;

import com.desolatetimelines.acct.catalog.ws.endpoint.CurrenciesEndpoint;
import com.desolatetimelines.acct.catalog.ws.model.CurrencyProperties;
import com.desolatetimelines.acct.catalog.ws.model.CurrencySaveRequest;
import com.desolatetimelines.acct.catalog.ws.model.CurrencyUUIDResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${CATALOG_APPLICATION_NAME}-currencies",
    name = "${CATALOG_APPLICATION_NAME}/${CATALOG_SERVER_CONTEXT_PATH}/currencies"
)
public interface RESTCurrenciesEndpointClient extends CurrenciesEndpoint {

    @Override
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    CurrencyUUIDResponse saveCurrency(
        @RequestParam(name = "currencyUUID", required = false) String currencyUUID,
        @RequestBody CurrencySaveRequest request
    );

    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    Collection<CurrencyProperties> getCurrencies();

    @Override
    @DeleteMapping(value = "", produces = APPLICATION_JSON_VALUE)
    void deleteCurrencies(@RequestParam(name = "currencyUUIDs") Collection<String> currencyUUIDs);

}
