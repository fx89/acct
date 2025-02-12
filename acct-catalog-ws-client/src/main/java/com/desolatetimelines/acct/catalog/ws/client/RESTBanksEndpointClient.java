package com.desolatetimelines.acct.catalog.ws.client;

import com.desolatetimelines.acct.catalog.ws.endpoint.BanksEndpoint;
import com.desolatetimelines.acct.catalog.ws.model.BankProperties;
import com.desolatetimelines.acct.catalog.ws.model.BankSaveRequest;
import com.desolatetimelines.acct.catalog.ws.model.BankUUIDResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${CATALOG_APPLICATION_NAME}-banks",
    name = "${CATALOG_APPLICATION_NAME}/${CATALOG_SERVER_CONTEXT_PATH}/banks"
)
public interface RESTBanksEndpointClient extends BanksEndpoint {

    @Override
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    BankUUIDResponse saveBank(
        @RequestParam(name = "bankUUID", required = false) String bankUUID,
        @RequestBody BankSaveRequest request
    );

    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    Collection<BankProperties> getBanks();

    @Override
    @DeleteMapping(value = "", produces = APPLICATION_JSON_VALUE)
    void deleteBanks(@RequestParam(name = "bankUUIDs") Collection<String> bankUUIDs);

}
