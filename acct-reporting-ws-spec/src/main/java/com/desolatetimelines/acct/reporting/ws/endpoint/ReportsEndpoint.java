package com.desolatetimelines.acct.reporting.ws.endpoint;

import com.desolatetimelines.acct.reporting.ws.model.ReportProperties;
import com.desolatetimelines.acct.reporting.ws.model.ReportUUIDResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Specification for the Reports endpoint, which provides an API that lets consumers define, edit,
 * delete and list the structure of reports registered in the ACCT ecosystem.
 */
public interface ReportsEndpoint {

    /**
     * Creates or updates a report in the ACCT ecosystem.
     *
     * @param reportUUID       Unique identifier of the report. If missing, a new report is created.
     * @param reportProperties The report properties define the report behavior and appearance.
     * @return The UUID of the saved report, be it a new or existing report.
     */
    ReportUUIDResponse saveReportForCurrentUser(
        @Nullable String reportUUID,
        @NonNull ReportProperties reportProperties
    );

}
