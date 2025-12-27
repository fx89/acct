package com.desolatetimelines.acct.reporting.ws.endpoint;

import com.desolatetimelines.acct.common.ws.model.AcctPage;
import com.desolatetimelines.acct.reporting.ws.model.ReportExtendedProperties;
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

    /**
     * Retrieves a page of {@link ReportExtendedProperties reports} that are accessible to the
     * current user either directly or via a group that the user is part of.
     *
     * @param pageNumber The number of the page to be retrieved.
     * @param pageSize   The number of elements to be contained within the page.
     * @return The requested page or an empty page if the page with the requested number does not exist.
     */
    AcctPage<ReportExtendedProperties> findSortedPageOfUserAccessibleReports(
        int pageNumber,
        int pageSize
    );

}
