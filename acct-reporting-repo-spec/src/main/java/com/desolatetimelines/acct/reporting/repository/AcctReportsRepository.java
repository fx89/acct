package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctReport;

import java.util.Optional;

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

}
