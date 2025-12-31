package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctDashboard;
import com.desolatetimelines.acct.reporting.model.AcctDashboardReport;
import com.desolatetimelines.acct.reporting.model.AcctDashboardReportFilter;

import java.util.Collections;
import java.util.Set;

/**
 * Repository for loading and persisting {@link AcctDashboardReportFilter dashboard report filters}
 */
public interface AcctDashboardReportFiltersRepository {

    /**
     * Creates a new {@link AcctDashboardReportFilter dashboard report filter} instance.
     *
     * @return A reference to the newly created instance.
     */
    AcctDashboardReportFilter createNew();

    /**
     * Persists the referenced {@link AcctDashboardReportFilter dashboard report filter}.
     *
     * @param dashboardReportFilter Reference to the dashboard report filter to be persisted.
     * @return A reference to the persisted dashboard report filter.
     */
    AcctDashboardReportFilter save(AcctDashboardReportFilter dashboardReportFilter);

    /**
     * Retrieves a set of all the {@link AcctDashboardReportFilter dashboard report filters} that are
     * connected to the referenced {@link AcctDashboardReport dashboard report}.
     *
     * @param dashboardReport A reference to the dashboard report for which the filters are being retrieved.
     * @return The set of dashboard report filters related to the referenced dashboard report. If no such
     * dashboard report filters exist, then an {@link Collections#emptySet() empty set} is returned.
     */
    Set<AcctDashboardReportFilter> findAllByDashboardReport(AcctDashboardReport dashboardReport);

    /**
     * Deletes the referenced {@link AcctDashboardReportFilter dashboard report filter}.
     *
     * @param dashboardReportFilter Reference to the dashboard report filter to be deleted.
     */
    void delete(AcctDashboardReportFilter dashboardReportFilter);

    /**
     * Deletes all {@link AcctDashboardReportFilter dashboard report filters} mapped to all the
     * {@link AcctDashboardReport dashboards reports} mapped to the referenced {@link AcctDashboard dashboard}.
     *
     * @param dashboard Reference to the dashboard for which the report filters need to be deleted.
     */
    void deleteByDashboardReportDashboard(AcctDashboard dashboard);

}
