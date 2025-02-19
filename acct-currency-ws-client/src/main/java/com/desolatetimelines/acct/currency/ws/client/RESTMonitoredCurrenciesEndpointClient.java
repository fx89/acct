package com.desolatetimelines.acct.currency.ws.client;

import com.desolatetimelines.acct.currency.ws.endpoint.MonitoredCurrenciesEndpoint;
import com.desolatetimelines.acct.currency.ws.model.MonitoredCurrencyProperties;
import com.desolatetimelines.acct.currency.ws.model.MonitoredCurrencySaveRequest;
import com.desolatetimelines.acct.currency.ws.model.MonitoredCurrencyUUIDResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${CURRENCY_APPLICATION_NAME}-banks",
    name = "${CURRENCY_APPLICATION_NAME}/${CURRENCY_SERVER_CONTEXT_PATH}/monitoredCurrencies"
)
public interface RESTMonitoredCurrenciesEndpointClient extends MonitoredCurrenciesEndpoint {

    @Override
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    MonitoredCurrencyUUIDResponse saveMonitoredCurrency(
        @RequestParam(name = "monitoredCurrencyUUID", required = false) String monitoredCurrencyUUID,
        @RequestBody MonitoredCurrencySaveRequest request
    );

    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    Collection<MonitoredCurrencyProperties> getMonitoredCurrencies();

}
