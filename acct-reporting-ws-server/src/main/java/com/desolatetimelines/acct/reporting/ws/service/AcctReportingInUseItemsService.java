package com.desolatetimelines.acct.reporting.ws.service;

import com.desolatetimelines.acct.reporting.service.AcctReportingService;
import com.desolatetimelines.acct.usage.ws.redist.service.InUseItemsService;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementation of the {@link InUseItemsService} for the ACCT Reporting service
 */
@Service
public class AcctReportingInUseItemsService implements InUseItemsService {

    private final AcctReportingService reportingService;

    public AcctReportingInUseItemsService(AcctReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @Override
    public Collection<String> getInUseItemUUIDs(String objectType, Collection<String> itemUUIDs) {
        return reportingService.getInUseItemUUIDs(objectType, itemUUIDs);
    }

}
