package com.desolatetimelines.acct.reporting.ws.client;

import com.desolatetimelines.acct.common.ws.model.AcctPage;
import com.desolatetimelines.acct.reporting.ws.endpoint.ReportsEndpoint;
import com.desolatetimelines.acct.reporting.ws.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${REPORTING_APPLICATION_NAME}-reports",
    name = "${REPORTING_APPLICATION_NAME}/${REPORTING_SERVER_CONTEXT_PATH}/reports"
)
public interface RESTReportsEndpointClient extends ReportsEndpoint {

    @Override
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    ReportUUIDResponse saveReportForCurrentUser(
        @Nullable @RequestParam(name = "reportUUID") String reportUUID,
        @NonNull @RequestBody ReportProperties reportProperties
    );

    @Override
    @DeleteMapping(value = "")
    void deleteReport(
        @RequestParam(name = "reportUUID") String reportUUID
    );

    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    AcctPage<ReportExtendedProperties> findSortedPageOfUserAccessibleReports(
        @RequestParam(name = "pageNumber") int pageNumber,
        @RequestParam(name = "pageSize") int pageSize
    );

    @Override
    @GetMapping(value = "/parameters", produces = APPLICATION_JSON_VALUE)
    Set<DataProviderInstanceRuntimeParameter> getReportRuntimeParameters(
        @RequestParam(name = "reportUUID") String reportUUID
    );

    @Override
    @PostMapping(value = "/data", produces = APPLICATION_JSON_VALUE)
    AcctReportingDataSet getReportDataWithRuntimeParameters(
        @RequestParam(name = "reportUUID") String reportUUID,
        @RequestBody Set<ReportParameter> parameters
    );

}
