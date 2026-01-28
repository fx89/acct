package com.desolatetimelines.acct.reporting.ws.endpoint;

import com.desolatetimelines.acct.common.ws.model.AcctPage;
import com.desolatetimelines.acct.reporting.ws.model.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Set;

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

    /**
     * Retrieves a set of all the runtime parameters accepted by the referenced report.
     *
     * @param reportUUID Uniquely identifies the report.
     */
    Set<DataProviderInstanceRuntimeParameter> getReportRuntimeParameters(String reportUUID);

    /**
     * Runs the referenced report and returns the data set resulted from running the report.
     *
     * @param reportUUID Uniquely identifies the report to run.
     * @param parameters Contains the runtime parameters for the report. At least the mandatory
     *                   parameters need to be provided.
     * @return A data structure that contains the retrieved data and the meta-data that defines it.
     */
    AcctReportingDataSet getReportDataWithRuntimeParameters(
        String reportUUID,
        Set<ReportParameter> parameters
    );

    /**
     * Deletes the referenced report.
     *
     * @param reportUUID Uniquely identifies the report to delete.
     */
    void deleteReport(String reportUUID);

}
