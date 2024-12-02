package com.desolatetimelines.acct.security.ws.client;

import com.desolatetimelines.acct.security.ws.endpoint.ReportOwnershipEndpoint;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnedReportsGroup;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;
import com.desolatetimelines.acct.security.ws.endpoint.model.ReportAccessibilityReport;
import com.desolatetimelines.acct.security.ws.endpoint.model.ReportOwner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
    contextId = "${SECURITY_APPLICATION_NAME}-report-ownership",
    name = "${SECURITY_APPLICATION_NAME}/${SECURITY_SERVER_CONTEXT_PATH}/reportOwners"
)
public interface RESTReportOwnershipEndpointClient extends ReportOwnershipEndpoint {

    @Override
    @GetMapping(value = "/ownedReports", produces = APPLICATION_JSON_VALUE)
    Collection<String> getReportsOwnedByOwnerOfType(
        @RequestParam("ownerType") OwnerType ownerType,
        @RequestParam("ownerUUID") String ownerUUID
    );

    @Override
    @GetMapping(value = "/userAccessibleReports", produces = APPLICATION_JSON_VALUE)
    OwnedReportsGroup getUserAccessibleReports(@RequestParam("userUUID") String userUUID);

    @Override
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    void addReportOwner(@RequestBody ReportOwner reportOwner);

    @Override
    @DeleteMapping(value = "")
    void deleteReportOwner(
        @RequestParam("ownerType") OwnerType ownerType,
        @RequestParam("ownerUUID") String ownerUUID,
        @RequestParam("reportUUID") String reportUUID
    );

    @Override
    @GetMapping(value = "/userAccessibleReport")
    ReportAccessibilityReport isUserAccessibleReport(
        @RequestParam("userUUID") String userUUID,
        @RequestParam("reportUUID") String reportUUID
    );

}
