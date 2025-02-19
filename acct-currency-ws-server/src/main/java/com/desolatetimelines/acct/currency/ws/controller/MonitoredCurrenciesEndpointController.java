package com.desolatetimelines.acct.currency.ws.controller;


import com.desolatetimelines.acct.currency.service.AcctCurrencyService;
import com.desolatetimelines.acct.currency.ws.endpoint.MonitoredCurrenciesEndpoint;
import com.desolatetimelines.acct.currency.ws.model.MonitoredCurrencyProperties;
import com.desolatetimelines.acct.currency.ws.model.MonitoredCurrencySaveRequest;
import com.desolatetimelines.acct.currency.ws.model.MonitoredCurrencyUUIDResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.desolatetimelines.acct.currency.privilegesprovider.model.CurrencyPrivilegeIds.MONITORED_CURRENCIES_READ;
import static com.desolatetimelines.acct.currency.privilegesprovider.model.CurrencyPrivilegeIds.MONITORED_CURRENCIES_SAVE;
import static com.desolatetimelines.acct.currency.ws.mapper.MonitoredCurrencyPropertiesMapper.fromCollectionOfAcctMonitoredCurrencies;
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

}
