package com.desolatetimelines.acct.reporting.ws.client;

import com.desolatetimelines.acct.common.ws.model.AcctPage;
import com.desolatetimelines.acct.reporting.ws.endpoint.ReportsEndpoint;
import com.desolatetimelines.acct.reporting.ws.model.ReportExtendedProperties;
import com.desolatetimelines.acct.reporting.ws.model.ReportProperties;
import com.desolatetimelines.acct.reporting.ws.model.ReportUUIDResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    AcctPage<ReportExtendedProperties> findSortedPageOfUserAccessibleReports(
        @RequestParam(name = "pageNumber") int pageNumber,
        @RequestParam(name = "pageSize") int pageSize
    );

}
