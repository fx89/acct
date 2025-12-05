package com.desolatetimelines.acct.reporting.data.service;

import com.desolatetimelines.acct.reporting.model.AcctDashboard;
import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstance;
import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstanceProperty;
import com.desolatetimelines.acct.reporting.model.AcctDataProviderInstanceRuntimeParameter;
import com.desolatetimelines.acct.reporting.repository.AcctDashboardsRepository;
import com.desolatetimelines.acct.reporting.repository.AcctDataProviderInstancePropertiesRepository;
import com.desolatetimelines.acct.reporting.repository.AcctDataProviderInstanceRuntimeParametersRepository;
import com.desolatetimelines.acct.reporting.repository.AcctDataProviderInstancesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * Facade for the ACCT Workspace data layer
 */
@Service
public class AcctReportingDataService {

    private final AcctDashboardsRepository dashboardsRepository;

    private final AcctDataProviderInstancesRepository dataProviderInstancesRepository;

    private final AcctDataProviderInstanceRuntimeParametersRepository dataProviderInstanceRuntimeParametersRepository;

    private final AcctDataProviderInstancePropertiesRepository dataProviderInstancePropertiesRepository;

    public AcctReportingDataService(
        AcctDashboardsRepository dashboardsRepository,
        AcctDataProviderInstancesRepository dataProviderInstancesRepository,
        AcctDataProviderInstanceRuntimeParametersRepository dataProviderInstanceRuntimeParametersRepository, AcctDataProviderInstancePropertiesRepository dataProviderInstancePropertiesRepository
    ) {
        this.dashboardsRepository = dashboardsRepository;
        this.dataProviderInstancesRepository = dataProviderInstancesRepository;
        this.dataProviderInstanceRuntimeParametersRepository = dataProviderInstanceRuntimeParametersRepository;
        this.dataProviderInstancePropertiesRepository = dataProviderInstancePropertiesRepository;
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
}
