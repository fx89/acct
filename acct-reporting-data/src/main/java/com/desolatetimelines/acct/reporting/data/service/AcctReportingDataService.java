package com.desolatetimelines.acct.reporting.data.service;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.reporting.model.*;
import com.desolatetimelines.acct.reporting.repository.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

/**
 * Facade for the ACCT Workspace data layer
 */
@SuppressWarnings("UnusedReturnValue")
@Service
public class AcctReportingDataService {

    private final AcctDashboardsRepository dashboardsRepository;

    private final AcctDataProviderInstancesRepository dataProviderInstancesRepository;

    private final AcctDataProviderInstanceRuntimeParametersRepository dataProviderInstanceRuntimeParametersRepository;

    private final AcctDataProviderInstancePropertiesRepository dataProviderInstancePropertiesRepository;

    private final AcctReportsRepository reportsRepository;

    private final AcctReportSeriesRepository reportSeriesRepository;

    private final AcctReportDataProviderInstancesRepository reportDataProviderInstancesRepository;

    private final AcctDashboardReportsRepository dashboardReportsRepository;

    private final AcctDashboardReportFiltersRepository dashboardReportFiltersRepository;

    public AcctReportingDataService(
        AcctDashboardsRepository dashboardsRepository,
        AcctDataProviderInstancesRepository dataProviderInstancesRepository,
        AcctDataProviderInstanceRuntimeParametersRepository dataProviderInstanceRuntimeParametersRepository,
        AcctDataProviderInstancePropertiesRepository dataProviderInstancePropertiesRepository,
        AcctReportsRepository reportsRepository,
        AcctReportSeriesRepository reportSeriesRepository,
        AcctReportDataProviderInstancesRepository reportDataProviderInstancesRepository,
        AcctDashboardReportsRepository dashboardReportsRepository,
        AcctDashboardReportFiltersRepository dashboardReportFiltersRepository
    ) {
        this.dashboardsRepository = dashboardsRepository;
        this.dataProviderInstancesRepository = dataProviderInstancesRepository;
        this.dataProviderInstanceRuntimeParametersRepository = dataProviderInstanceRuntimeParametersRepository;
        this.dataProviderInstancePropertiesRepository = dataProviderInstancePropertiesRepository;
        this.reportsRepository = reportsRepository;
        this.reportSeriesRepository = reportSeriesRepository;
        this.reportDataProviderInstancesRepository = reportDataProviderInstancesRepository;
        this.dashboardReportsRepository = dashboardReportsRepository;
        this.dashboardReportFiltersRepository = dashboardReportFiltersRepository;
    }

    /**
     * Creates a new {@link AcctDashboard dashboard}
     *
     * @return a reference to the newly created dashboard
     */
    public AcctDashboard createNewDashboard() {
        return dashboardsRepository.createNew();
    }

    /**
     * Persists the referenced dashboard
     *
     * @param dashboard the referenced dashboard
     * @return a reference to the persisted dashboard
     */
    public AcctDashboard saveDashboard(AcctDashboard dashboard) {
        return dashboardsRepository.save(dashboard);
    }

    /**
     * Returns a collection of all the dashboards that are using one of the icons represented by the
     * given list of dashboard icon UUIDs
     *
     * @param dashboardIconUUIDs the given list of dashboard icon UUIDs
     */
    public Collection<AcctDashboard> findDashboardsByDashboardIconUUIDIn(Collection<String> dashboardIconUUIDs) {
        return dashboardsRepository.findAllByDashboardIconUUIDIn(dashboardIconUUIDs);
    }

    /**
     * Returns a collection of all the dashboards that are using one of the workspaces represented by the
     * given list of workspace UUIDs
     *
     * @param workspaceUUIDs the given list of workspace UUIDs
     */
    public Collection<AcctDashboard> findDashboardsByWorkspaceUUIDIn(Collection<String> workspaceUUIDs) {
        return dashboardsRepository.findAllByWorkspaceUUIDIn(workspaceUUIDs);
    }

    /**
     * Returns a collection of all the dashboards that are part of the referenced workspace and are identified
     * by one of the UUIDs from the referenced collection of dashboard UUIDs
     *
     * @param workspaceUUID  the UUID of the referenced workspace
     * @param dashboardUUIDs the referenced collection of dashboard UUIDs
     */
    public Collection<AcctDashboard> findDashboardsByWorkspaceUUIDAndDashboardUUIDIn(
        String workspaceUUID,
        Collection<String> dashboardUUIDs
    ) {
        return dashboardsRepository.findAllByWorkspaceUUIDAndDashboardUUIDIn(workspaceUUID, dashboardUUIDs);
    }

    /**
     * Retrieves the dashboard with the given dashboard UUID. If no such dashboard exists, then an empty
     * optional is returned.
     *
     * @param dashboardUUID the given dashboard UUID
     */
    public Optional<AcctDashboard> findDashboardByDashboardUUID(String dashboardUUID) {
        return dashboardsRepository.findFirstByDashboardUUID(dashboardUUID);
    }

    /**
     * Deletes the referenced dashboard
     *
     * @param dashboard the referenced dashboard
     */
    public void deleteDashboard(AcctDashboard dashboard) {
        dashboardsRepository.delete(dashboard);
    }

    /**
     * Creates a new {@link AcctDataProviderInstance data provider instance}
     *
     * @return a reference to the newly created data provider instance
     */
    public AcctDataProviderInstance createNewDataProviderInstance() {
        return dataProviderInstancesRepository.createNew();
    }

    /**
     * Retrieves the data provider instance with the given UUID. If no such instance exists, then an empty
     * optional is returned.
     *
     * @param dataProviderInstanceUUID the given data provider instance UUID
     * @return an {@link Optional} containing the data provider instance if found,
     * otherwise an empty optional
     */
    public Optional<AcctDataProviderInstance> findDataProviderInstanceByDataProviderInstanceUUID(
        String dataProviderInstanceUUID
    ) {
        return
            dataProviderInstancesRepository.findFirstByDataProviderInstanceUUID(dataProviderInstanceUUID);
    }

    /**
     * Persists the referenced data provider instance
     *
     * @param dataProviderInstance the referenced data provider instance
     * @return a reference to the persisted data provider instance
     */
    public AcctDataProviderInstance saveDataProviderInstance(
        AcctDataProviderInstance dataProviderInstance
    ) {
        return dataProviderInstancesRepository.save(dataProviderInstance);
    }

    /**
     * Returns a set of all the {@link AcctDataProviderInstance data provider instances}
     * that are registered in the ACCT reporting data store
     */
    public Set<AcctDataProviderInstance> findAllAcctDataProviderInstances() {
        return dataProviderInstancesRepository.findAll();
    }

    public void cascadeDeleteDataProviderInstance(AcctDataProviderInstance dataProviderInstance) {
        // Delete any data provider instance properties that are linked to the data provider instance
        dataProviderInstancePropertiesRepository.deleteByDataProviderInstance(dataProviderInstance);

        // Delete any data provider instance runtime parameters that are linked to the data provider instance
        deleteDataProviderInstanceRuntimeParametersByDataProviderInstance(dataProviderInstance);

        // Delete the data provider instance itself
        dataProviderInstancesRepository.delete(dataProviderInstance);
    }

    /**
     * Creates a new {@link AcctDataProviderInstanceProperty data provider instance property}
     *
     * @return a reference to the newly created data provider instance property
     */
    public AcctDataProviderInstanceProperty createNewDataProviderInstanceProperty() {
        return dataProviderInstancePropertiesRepository.createNew();
    }

    /**
     * Persists the referenced data provider instance property
     *
     * @param instanceProperty the referenced data provider instance property
     * @return a reference to the persisted data provider instance property
     */
    @SuppressWarnings("UnusedReturnValue")
    public AcctDataProviderInstanceProperty saveDataProviderInstanceProperty(
        AcctDataProviderInstanceProperty instanceProperty
    ) {
        return dataProviderInstancePropertiesRepository.save(instanceProperty);
    }

    /**
     * Returns a set of all {@link AcctDataProviderInstanceProperty instance properties} that are linked
     * to the referenced {@link AcctDataProviderInstance data provider instance}.
     *
     * @param dataProviderInstance Reference to the data provider instance that contains the properties
     *                             to be fetched.
     */
    public Set<AcctDataProviderInstanceProperty> findAllDataProviderInstancePropertiesByDataProviderInstance(
        AcctDataProviderInstance dataProviderInstance
    ) {
        return
            findAllDataProviderInstancePropertiesByDataProviderInstanceIn(
                Set.of(dataProviderInstance)
            );
    }

    /**
     * Returns a set of all {@link AcctDataProviderInstanceProperty instance properties} that are linked
     * to any of the referenced {@link AcctDataProviderInstance data provider instances}.
     *
     * @param dataProviderInstances A set that contains references to the data provider instances that
     *                              contain the properties to be fetched.
     */
    public Set<AcctDataProviderInstanceProperty> findAllDataProviderInstancePropertiesByDataProviderInstanceIn(
        Set<AcctDataProviderInstance> dataProviderInstances
    ) {
        return dataProviderInstancePropertiesRepository.findAllByDataProviderInstanceIn(dataProviderInstances);
    }

    /**
     * Deletes all of the
     * {@link com.desolatetimelines.acct.reporting.model.AcctDataProviderInstanceProperty instance properties}
     * that are linked to the referenced {@link AcctDataProviderInstance data provider instance}.
     *
     * @param dataProviderInstance reference to the data provider instance from which the properties are removed.
     */
    public void deleteDataProviderInstancePropertiesByDataProviderInstance(
        AcctDataProviderInstance dataProviderInstance
    ) {
        dataProviderInstancePropertiesRepository.deleteByDataProviderInstance(dataProviderInstance);
    }

    /**
     * Creates a new {@link AcctDataProviderInstanceRuntimeParameter data provider instance runtime parameter}
     *
     * @return a reference to the newly created data provider instance runtime parameter
     */
    public AcctDataProviderInstanceRuntimeParameter createNewDataProviderInstanceRuntimeParameter() {
        return dataProviderInstanceRuntimeParametersRepository.createNew();
    }

    /**
     * Persists the referenced data provider instance runtime parameter
     *
     * @param runtimeParameter the referenced data provider instance runtime parameter
     * @return a reference to the persisted data provider instance runtime parameter
     */
    @SuppressWarnings("UnusedReturnValue")
    public AcctDataProviderInstanceRuntimeParameter saveDataProviderInstanceRuntimeParameter(
        AcctDataProviderInstanceRuntimeParameter runtimeParameter
    ) {
        return dataProviderInstanceRuntimeParametersRepository.save(runtimeParameter);
    }

    /**
     * Returns a set of all {@link AcctDataProviderInstanceRuntimeParameter runtime parameters} that
     * are linked to the referenced {@link AcctDataProviderInstance data provider instance}.
     *
     * @param dataProviderInstance Reference to the data provider instance that contains the runtime
     *                             parameters to be fetched.
     */
    public Set<AcctDataProviderInstanceRuntimeParameter> findAllDataProviderInstanceRuntimeParametersByDataProviderInstance(
        AcctDataProviderInstance dataProviderInstance
    ) {
        return
            findAllDataProviderInstanceRuntimeParametersByDataProviderInstanceIn(
                Set.of(dataProviderInstance)
            );
    }

    /**
     * Returns a set of all {@link AcctDataProviderInstanceRuntimeParameter runtime parameters} that
     * are linked to any of the referenced {@link AcctDataProviderInstance data provider instances}.
     *
     * @param dataProviderInstances A set of references to the data provider instances that contain
     *                              the runtime parameters to be fetched.
     */
    public Set<AcctDataProviderInstanceRuntimeParameter> findAllDataProviderInstanceRuntimeParametersByDataProviderInstanceIn(
        Set<AcctDataProviderInstance> dataProviderInstances
    ) {
        return dataProviderInstanceRuntimeParametersRepository.findAllByDataProviderInstanceIn(dataProviderInstances);
    }

    /**
     * Deletes all of the
     * {@link AcctDataProviderInstanceRuntimeParameter runtime parameters} that are linked to the referenced
     * {@link AcctDataProviderInstance data provider instance}.
     *
     * @param dataProviderInstance reference to the data provider instance from which the properties are removed.
     */
    public void deleteDataProviderInstanceRuntimeParametersByDataProviderInstance(
        AcctDataProviderInstance dataProviderInstance
    ) {
        dataProviderInstanceRuntimeParametersRepository.deleteByDataProviderInstance(dataProviderInstance);
    }

    /**
     * Deletes the referenced {@link AcctDataProviderInstanceRuntimeParameter data provider instance runtime parameter}.
     *
     * @param runtimeParameter Reference to the data provider instance runtime parameter to be deleted.
     */
    public void deleteDataProviderInstanceRuntimeParameter(
        AcctDataProviderInstanceRuntimeParameter runtimeParameter
    ) {
        dataProviderInstanceRuntimeParametersRepository.delete(runtimeParameter);
    }

    /**
     * Creates a new {@link AcctReport report}.
     *
     * @return a reference to the newly created report instance.
     */
    public AcctReport createNewReport() {
        return reportsRepository.createNew();
    }

    /**
     * Persists the referenced {@link AcctReport report}.
     *
     * @param report reference to the report to be persisted.
     * @return a reference to the persisted report.
     */
    public AcctReport saveReport(AcctReport report) {
        return reportsRepository.save(report);
    }

    /**
     * Retrieves the report with the given report UUID. If no such report exists, then an empty
     * optional is returned.
     *
     * @param reportUUID the given report UUID
     */
    public Optional<AcctReport> findReportByReportUUID(String reportUUID) {
        return reportsRepository.findFirstByReportUUID(reportUUID);
    }

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
    public Page<AcctReport> findAllReportsByReportUUIDIn(Set<String> reportUUIDs, int pageNumber, int pageSize) {
        return reportsRepository.findAllByReportUUIDIn(reportUUIDs, pageNumber, pageSize);
    }

    /**
     * Creates a new {@link AcctReportSeries report series}.
     *
     * @return a reference to the newly created report series instance.
     */
    public AcctReportSeries createNewReportSeries() {
        return reportSeriesRepository.createNew();
    }

    /**
     * Persists the referenced {@link AcctReportSeries report series}.
     *
     * @param reportSeries reference to the report series to be persisted.
     * @return a reference to the persisted report series.
     */
    public AcctReportSeries saveReportSeries(AcctReportSeries reportSeries) {
        return reportSeriesRepository.save(reportSeries);
    }

    /**
     * Retrieves a set of all the {@link AcctReportSeries report series} associated with the
     * referenced report.
     *
     * @param report reference to the report for which the series are being retrieved.
     * @return a set of all the report series associated with the referenced report.
     */
    public Set<AcctReportSeries> findAllReportSeriesByReport(AcctReport report) {
        return findAllReportSeriesByReportIn(Set.of(report));
    }

    /**
     * Retrieves a set of all the {@link AcctReportSeries report series} associated with any
     * of the referenced reports.
     *
     * @param reports A set that contains the reports for which the report series are being fetched.
     */
    public Set<AcctReportSeries> findAllReportSeriesByReportIn(Set<AcctReport> reports) {
        return reportSeriesRepository.findAllByReportIn(reports);
    }

    /**
     * Removes the referenced {@link AcctReportSeries report series} from the data store.
     *
     * @param reportSeries reference to the report series to be removed.
     */
    public void deleteReportSeries(AcctReportSeries reportSeries) {
        reportSeriesRepository.delete(reportSeries);
    }

    /**
     * Creates a new instance of {@link AcctReportDataProviderInstance}.
     *
     * @return A reference to the newly created instance.
     */
    public AcctReportDataProviderInstance createNewReportDataProviderInstance() {
        return reportDataProviderInstancesRepository.createNew();
    }

    /**
     * Persists a new or existing instance of {@link AcctReportDataProviderInstance}.
     *
     * @param reportDataProviderInstance A reference to the instance to be persisted.
     * @return A reference to the persisted instance.
     */
    public AcctReportDataProviderInstance saveReportDataProviderInstance(
        AcctReportDataProviderInstance reportDataProviderInstance
    ) {
        return reportDataProviderInstancesRepository.save(reportDataProviderInstance);
    }

    /**
     * Retrieves a set of all the {@link AcctReportDataProviderInstance report data provider instances}
     * associated with any of the referenced reports.
     *
     * @param reports A set that contains the reports for which the data provider instances are being fetched.
     */
    public Set<AcctReportDataProviderInstance> findAllDataProviderInstancesByReportIn(Set<AcctReport> reports) {
        return reportDataProviderInstancesRepository.findAllByReportIn(reports);
    }

    /**
     * Looks up all the {@link AcctReportDataProviderInstance report data provider instances}
     * associated with the referenced {@link AcctReport report}.
     *
     * @param report A reference to the report for which the data provider instances need to
     *               be fetched.
     * @return A set of all the data provider instances associated with the referenced report.
     * If none found, then an {@link Collections#emptySet() empty set} is returned.
     */
    public Set<AcctReportDataProviderInstance> findReportDataProviderInstancesByReport(AcctReport report) {
        return findAllDataProviderInstancesByReportIn(Set.of(report));
    }

    /**
     * Deletes the referenced {@link AcctReportDataProviderInstance report data provider instance}.
     *
     * @param reportDataProviderInstance A reference to the report data provider instance to be
     *                                   deleted.
     */
    public void deleteReportDataProviderInstance(AcctReportDataProviderInstance reportDataProviderInstance) {
        reportDataProviderInstancesRepository.delete(reportDataProviderInstance);
    }

    /**
     * Creates a new {@link AcctDashboardReport dashboard report} instance.
     *
     * @return A reference to the newly created instance.
     */
    public AcctDashboardReport createNewDashboardReport() {
        return dashboardReportsRepository.createNew();
    }

    /**
     * Persists the referenced {@link AcctDashboardReport dashboard report}.
     *
     * @return A reference to the persisted dashboard report.
     */
    public AcctDashboardReport saveDashboardReport(AcctDashboardReport dashboardReport) {
        return dashboardReportsRepository.save(dashboardReport);
    }

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
    public Optional<AcctDashboardReport> findDashboardReportByDashboardAndRowNumberAndColumnNumber(
        AcctDashboard dashboard,
        Integer rowNumber,
        Integer columnNumber
    ) {
        return
            dashboardReportsRepository.findFirstByDashboardAndRowNumberAndColumnNumber(
                dashboard,
                rowNumber,
                columnNumber
            );
    }

    /**
     * Returns a set of {@link AcctDashboardReport dashboard reports} for which the
     * {@link AcctDashboard#getDashboardUUID() UUID} of the {@link AcctDashboardReport#getDashboard() parent dashboard}
     * matches the given {@code dashboardUUID}.
     *
     * @param dashboardUUID Unique identifier of the dashboard for which reports are being fetched.
     * @return The set of dashboard reports that belong to the referenced dashboard or an
     * {@link Collections#emptySet() empty set}, in case no such dashboard reports exist.
     */
    public Set<AcctDashboardReport> findAllDashboardReportsByDashboardDashboardUUID(String dashboardUUID) {
        return dashboardReportsRepository.findAllByDashboardDashboardUUID(dashboardUUID);
    }

    /**
     * Deletes all {@link AcctDashboardReport dashboard reports} mapped to the referenced dashboard.
     *
     * @param dashboard Reference to the dashboard for which the reports need to be deleted.
     */
    public void deleteDashboardReportsByDashboard(AcctDashboard dashboard) {
        dashboardReportsRepository.deleteByDashboard(dashboard);
    }

    /**
     * Deletes the referenced dashboard report.
     *
     * @param dashboardReport Reference to the dashboard report to be deleted.
     */
    public void deleteDashboardReport(AcctDashboardReport dashboardReport) {
        dashboardReportsRepository.delete(dashboardReport);
    }

    /**
     * Creates a new {@link AcctDashboardReportFilter dashboard report filter} instance.
     *
     * @return A reference to the newly created instance.
     */
    public AcctDashboardReportFilter createNewDashboardReportFilter() {
        return dashboardReportFiltersRepository.createNew();
    }

    /**
     * Persists the referenced {@link AcctDashboardReportFilter dashboard report filter}.
     *
     * @param dashboardReportFilter Reference to the dashboard report filter to be persisted.
     * @return A reference to the persisted dashboard report filter.
     */
    public AcctDashboardReportFilter saveDashboardReportFilter(AcctDashboardReportFilter dashboardReportFilter) {
        return dashboardReportFiltersRepository.save(dashboardReportFilter);
    }

    /**
     * Retrieves a set of all the {@link AcctDashboardReportFilter dashboard report filters} that are
     * connected to the referenced {@link AcctDashboardReport dashboard report}.
     *
     * @param dashboardReport A reference to the dashboard report for which the filters are being retrieved.
     * @return The set of dashboard report filters related to the referenced dashboard report. If no such
     * dashboard report filters exist, then an {@link Collections#emptySet() empty set} is returned.
     */
    public Set<AcctDashboardReportFilter> findAllDashboardReportFiltersByDashboardReport(
        AcctDashboardReport dashboardReport
    ) {
        return findAllDashboardReportFiltersByDashboardReportIn(Set.of(dashboardReport));
    }

    /**
     * Retrieves a set of all the {@link AcctDashboardReportFilter dashboard report filters} that are
     * connected to any of the referenced {@link AcctDashboardReport dashboard reports}.
     *
     * @param dashboardReports A set of references to the dashboard reports for which the filters are being retrieved.
     * @return The set of dashboard report filters related to the referenced dashboard reports. If no such
     * dashboard report filters exist, then an {@link Collections#emptySet() empty set} is returned.
     */
    public Set<AcctDashboardReportFilter> findAllDashboardReportFiltersByDashboardReportIn(
        Set<AcctDashboardReport> dashboardReports
    ) {
        return dashboardReportFiltersRepository.findAllByDashboardReportIn(dashboardReports);
    }

    /**
     * Deletes the referenced {@link AcctDashboardReportFilter dashboard report filter}.
     *
     * @param dashboardReportFilter Reference to the dashboard report filter to be deleted.
     */
    public void deleteDashboardReportFilter(AcctDashboardReportFilter dashboardReportFilter) {
        dashboardReportFiltersRepository.delete(dashboardReportFilter);
    }

    /**
     * Deletes all {@link AcctDashboardReportFilter dashboard report filters} mapped to all the
     * {@link AcctDashboardReport dashboards reports} mapped to the referenced {@link AcctDashboard dashboard}.
     *
     * @param dashboard Reference to the dashboard for which the report filters need to be deleted.
     */
    public void deleteDashboardReportFiltersByDashboardReportDashboard(AcctDashboard dashboard) {
        dashboardReportFiltersRepository.deleteByDashboardReportDashboard(dashboard);
    }

}
