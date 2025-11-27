package com.desolatetimelines.acct.currency.ws.client;

import com.desolatetimelines.acct.currency.ws.endpoint.MonitoredCurrenciesEndpoint;
import com.desolatetimelines.acct.currency.ws.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

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

    @Override
    @GetMapping(value = "/collectors", produces = APPLICATION_JSON_VALUE)
    Collection<MonitoredCurrencyCollector> getAvailableMonitoredCurrencyCollectors();

    @Override
    @GetMapping(value = "/records", produces = APPLICATION_JSON_VALUE)
    Collection<MonitoredCurrencyRecordProperties> getMonitoredCurrencyRecords(
        @RequestParam(name = "monitoredCurrencyUUID") String monitoredCurrencyUUID
    );

    @Override
    @GetMapping(value = "/records/interval", produces = APPLICATION_JSON_VALUE)
    Collection<MonitoredCurrencyRecordProperties> getMonitoredCurrencyRecordsBetweenDates(
        @RequestParam(name = "monitoredCurrencyUUID") String monitoredCurrencyUUID,
        @RequestParam(name = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
        @RequestParam(name = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    );

    @Override
    @DeleteMapping(value = "", produces = APPLICATION_JSON_VALUE)
    void deleteMonitoredCurrency(
        @RequestParam(name = "monitoredCurrencyUUID") String monitoredCurrencyUUID
    );

    @Override
    @PostMapping(value = "/collectManually", produces = APPLICATION_JSON_VALUE)
    void collectManually(
        @RequestParam(name = "monitoredCurrencyUUID") String monitoredCurrencyUUID
    );

    @Override
    @PutMapping(value = "/records")
    void addMonitoredCurrencyRecords(
        @RequestParam(name = "monitoredCurrencyUUID") String monitoredCurrencyUUID,
        @RequestBody Collection<MonitoredCurrencyRecordProperties> records
    );

}
