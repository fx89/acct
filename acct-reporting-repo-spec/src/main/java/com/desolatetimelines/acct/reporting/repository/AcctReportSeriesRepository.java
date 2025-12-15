package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctReport;
import com.desolatetimelines.acct.reporting.model.AcctReportSeries;

import java.util.Collections;
import java.util.Set;

/**
 * Repository for loading and persisting {@link AcctReportSeries report series}
 */
public interface AcctReportSeriesRepository {

    /**
     * Creates a new {@link AcctReportSeries} instance.
     *
     * @return a reference to the newly created instance.
     */
    AcctReportSeries createNew();

    /**
     * Persists a new or existing {@link AcctReportSeries report series}.
     *
     * @param reportSeries reference to the report series to be persisted.
     * @return A reference to the persisted report series.
     */
    AcctReportSeries save(AcctReportSeries reportSeries);

    /**
     * Returns a set of all {@link AcctReportSeries report series} that are
     * associated with the referenced report.
     *
     * @param report A reference to the report whose report series are to
     *               be fetched.
     * @return A set containing the fetched report series. If none found,
     * then an {@link Collections#emptySet() empty set} is returned.
     */
    Set<AcctReportSeries> findAllByReport(AcctReport report);

    /**
     * Deletes the referenced {@link AcctReportSeries report series}.
     *
     * @param reportSeries A reference to the report series to be deleted.
     */
    void delete(AcctReportSeries reportSeries);

}
