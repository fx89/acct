package com.desolatetimelines.acct.currency.ws.controller;


import com.desolatetimelines.acct.currency.service.AcctCurrencyService;
import com.desolatetimelines.acct.currency.ws.endpoint.MonitoredCurrenciesEndpoint;
import com.desolatetimelines.acct.currency.ws.model.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.desolatetimelines.acct.currency.privilegesprovider.model.CurrencyPrivilegeIds.*;
import static com.desolatetimelines.acct.currency.ws.mapper.MonitoredCurrencyCollectorsMapper.fromMapOfStringAndCurrencyCollectorService;
import static com.desolatetimelines.acct.currency.ws.mapper.MonitoredCurrencyPropertiesMapper.fromCollectionOfAcctMonitoredCurrencies;
import static com.desolatetimelines.acct.currency.ws.mapper.MonitoredCurrencyRecordPropertiesMapper.fromCollectionOfAcctMonitoredCurrencyRecord;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/monitoredCurrencies")
public class MonitoredCurrenciesEndpointController implements MonitoredCurrenciesEndpoint {

    private final AcctCurrencyService currencyService;

    public MonitoredCurrenciesEndpointController(AcctCurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + MONITORED_CURRENCIES_SAVE + "')")
    @PutMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public MonitoredCurrencyUUIDResponse saveMonitoredCurrency(
        @RequestParam(name = "monitoredCurrencyUUID", required = false) String monitoredCurrencyUUID,
        @RequestBody MonitoredCurrencySaveRequest request
    ) {
        return
            new MonitoredCurrencyUUIDResponse(
                currencyService.saveMonitoredCurrency(
                        monitoredCurrencyUUID,
                        request.bankUUID(),
                        request.currencyUUID(),
                        request.quoteCurrencyUUID(),
                        request.collectorName(),
                        request.scheduledTimeHhMm()
                    )
                    .getMonitoredCurrencyUUID()
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + MONITORED_CURRENCIES_READ + "')")
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public Collection<MonitoredCurrencyProperties> getMonitoredCurrencies() {
        return fromCollectionOfAcctMonitoredCurrencies(currencyService.getMonitoredCurrencies());
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + MONITORED_CURRENCY_COLLECTORS_READ + "')")
    @GetMapping(value = "/collectors", produces = APPLICATION_JSON_VALUE)
    public Collection<MonitoredCurrencyCollector> getAvailableMonitoredCurrencyCollectors() {
        return
            fromMapOfStringAndCurrencyCollectorService(
                currencyService.getCurrencyCollectorsByName()
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + MONITORED_CURRENCY_RECORDS_READ + "')")
    @GetMapping(value = "/records", produces = APPLICATION_JSON_VALUE)
    public Collection<MonitoredCurrencyRecordProperties> getMonitoredCurrencyRecords(
        @RequestParam(name = "monitoredCurrencyUUID") String monitoredCurrencyUUID
    ) {
        return
            fromCollectionOfAcctMonitoredCurrencyRecord(
                currencyService.getMonitoredCurrencyRecordsSortedByDate(monitoredCurrencyUUID)
            );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + MONITORED_CURRENCIES_DELETE + "')")
    @DeleteMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public void deleteMonitoredCurrency(
        @RequestParam(name = "monitoredCurrencyUUID") String monitoredCurrencyUUID
    ) {
        currencyService.deleteMonitoredCurrencyByMonitoredCurrencyUUID(monitoredCurrencyUUID);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('SCOPE_backend', 'SCOPE_" + MONITORED_CURRENCIES_COLLECT_MANUALLY + "')")
    @PostMapping(value = "/collectManually", produces = APPLICATION_JSON_VALUE)
    public void collectManually(
        @RequestParam(name = "monitoredCurrencyUUID") String monitoredCurrencyUUID
    ) {
        currencyService.manuallyCollectMonitoredCurrencyRecords(monitoredCurrencyUUID);
    }

}
