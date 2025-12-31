package com.desolatetimelines.acct.reporting.repository;

import com.desolatetimelines.acct.reporting.model.AcctDashboard;
import com.desolatetimelines.acct.reporting.model.AcctDashboardReport;

import java.util.Optional;

/**
 * Repository for loading and persisting {@link AcctDashboardReport dashboard reports}
 */
public interface AcctDashboardReportsRepository {

    /**
     * Creates a new {@link AcctDashboardReport dashboard report} instance.
     *
     * @return A reference to the newly created instance.
     */
    AcctDashboardReport createNew();

    /**
     * Persists the referenced {@link AcctDashboardReport dashboard report}.
     *
     * @return A reference to the persisted dashboard report.
     */
    AcctDashboardReport save(AcctDashboardReport dashboardReport);

    /**
     * Looks up the {@link AcctDashboardReport dashboard report} that is set to be
     * displayed on the referenced dashboard at the referenced location.
     *
     * @param dashboard    Reference to the dashboard where the dashboard report is expected to be found.
     * @param rowNumber    The vertical coordinate of the referenced location.
     * @param columnNumber The horizontal coordinate of the referenced location.
     * @return An optional reference to the referenced dashboard report. If there is no such dashboard
     * report, then an {@link Optional#empty() empty optional} is returned.
     */
    Optional<AcctDashboardReport> findFirstByDashboardAndRowNumberAndColumnNumber(
        AcctDashboard dashboard,
        Integer rowNumber,
        Integer columnNumber
    );

    /**
     * Deletes the referenced dashboard report.
     *
     * @param dashboardReport Reference to the dashboard report to be deleted.
     */
    void delete(AcctDashboardReport dashboardReport);

    /**
     * Deletes all {@link AcctDashboardReport dashboard reports} mapped to the referenced dashboard.
     *
     * @param dashboard Reference to the dashboard for which the reports need to be deleted.
     */
    void deleteByDashboard(AcctDashboard dashboard);

}
