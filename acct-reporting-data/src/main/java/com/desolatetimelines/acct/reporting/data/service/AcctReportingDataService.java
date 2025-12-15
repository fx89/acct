package com.desolatetimelines.acct.reporting.data.service;

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

    public AcctReportingDataService(
        AcctDashboardsRepository dashboardsRepository,
        AcctDataProviderInstancesRepository dataProviderInstancesRepository,
        AcctDataProviderInstanceRuntimeParametersRepository dataProviderInstanceRuntimeParametersRepository,
        AcctDataProviderInstancePropertiesRepository dataProviderInstancePropertiesRepository,
        AcctReportsRepository reportsRepository,
        AcctReportSeriesRepository reportSeriesRepository,
        AcctReportDataProviderInstancesRepository reportDataProviderInstancesRepository
    ) {
        this.dashboardsRepository = dashboardsRepository;
        this.dataProviderInstancesRepository = dataProviderInstancesRepository;
        this.dataProviderInstanceRuntimeParametersRepository = dataProviderInstanceRuntimeParametersRepository;
        this.dataProviderInstancePropertiesRepository = dataProviderInstancePropertiesRepository;
        this.reportsRepository = reportsRepository;
        this.reportSeriesRepository = reportSeriesRepository;
        this.reportDataProviderInstancesRepository = reportDataProviderInstancesRepository;
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
        return dataProviderInstancePropertiesRepository.findAllByDataProviderInstance(dataProviderInstance);
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
        return dataProviderInstanceRuntimeParametersRepository.findAllByDataProviderInstance(dataProviderInstance);
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
        return reportSeriesRepository.findAllByReport(report);
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
     * Looks up all the {@link AcctReportDataProviderInstance report data provider instances}
     * associated with the referenced {@link AcctReport report}.
     *
     * @param report A reference to the report for which the data provider instances need to
     *               be fetched.
     * @return A set of all the data provider instances associated with the referenced report.
     * If none found, then an {@link Collections#emptySet() empty set} is returned.
     */
    public Set<AcctReportDataProviderInstance> findReportDataProviderInstances(AcctReport report) {
        return reportDataProviderInstancesRepository.findAllByReport(report);
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
}
