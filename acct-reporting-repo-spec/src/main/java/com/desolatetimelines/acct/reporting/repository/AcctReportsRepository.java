package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.reporting.model.AcctReport;

import java.util.Optional;
import java.util.Set;

/**
 * Repository for loading and persisting {@link AcctReport reports}
 */
public interface AcctReportsRepository {

    /**
     * Returns a new {@link AcctReport} instance
     */
    AcctReport createNew();

    /**
     * Persists a new or existing {@link AcctReport report}
     *
     * @param report reference to the report to be persisted
     * @return A reference to the persisted report
     */
    AcctReport save(AcctReport report);

    /**
     * Looks up the {@link AcctReport report} having the given {@code reportUUID}.
     * If not found, an empty optional is returned.
     *
     * @param reportUUID unique identifier for the sought report.
     * @return An optional that may contain a reference to the referenced report.
     */
    Optional<AcctReport> findFirstByReportUUID(String reportUUID);

    /**
     * Retrieves a {@link Page page} of {@link AcctReport reports} identified by the given UUIDs. The page is sorted
     * by {@link AcctReport#getReportName() report name}.
     *
     * @param reportUUIDs A set of UUIDs that identify the reports to be fetched. If the size of this set is larger
     *                    than the given page size, then only the reports that are relevant to the given combination
     *                    of page number and page size are fetched.
     * @param pageNumber  Identifies the page to be fetched.
     * @param pageSize    Determines the number of reports to be retrieved in a given page.
     */
    Page<AcctReport> findAllByReportUUIDIn(Set<String> reportUUIDs, int pageNumber, int pageSize);

    /**
     * Deletes the referenced {@link AcctReport report}.
     *
     * @param report Reference to the report to be deleted.
     */
    void delete(AcctReport report);

}
