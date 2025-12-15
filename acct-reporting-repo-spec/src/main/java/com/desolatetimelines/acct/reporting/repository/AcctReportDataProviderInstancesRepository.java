package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctReport;
import com.desolatetimelines.acct.reporting.model.AcctReportDataProviderInstance;

import java.util.Collections;
import java.util.Set;

/**
 * Repository for loading and persisting {@link AcctReportDataProviderInstance} entities
 */
public interface AcctReportDataProviderInstancesRepository {

    /**
     * Creates a new instance of {@link AcctReportDataProviderInstance}.
     *
     * @return A reference to the newly created instance.
     */
    AcctReportDataProviderInstance createNew();

    /**
     * Persists a new or existing instance of {@link AcctReportDataProviderInstance}.
     *
     * @param reportDataProviderInstance A reference to the instance to be persisted.
     * @return A reference to the persisted instance.
     */
    AcctReportDataProviderInstance save(AcctReportDataProviderInstance reportDataProviderInstance);

    /**
     * Looks up all the {@link AcctReportDataProviderInstance report data provider instances}
     * associated with the referenced {@link AcctReport report}.
     *
     * @param report A reference to the report for which the data provider instances need to
     *               be fetched.
     * @return A set of all the data provider instances associated with the referenced report.
     * If none found, then an {@link Collections#emptySet() empty set} is returned.
     */
    Set<AcctReportDataProviderInstance> findAllByReport(AcctReport report);

    /**
     * Deletes the referenced {@link AcctReportDataProviderInstance report data provider instance}.
     *
     * @param reportDataProviderInstance A reference to the report data provider instance to be
     *                                   deleted.
     */
    void delete(AcctReportDataProviderInstance reportDataProviderInstance);

}
