package com.desolatetimelines.acct.reporting.data.service;

import com.desolatetimelines.acct.reporting.model.AcctDashboard;
import com.desolatetimelines.acct.reporting.repository.AcctDashboardsRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Facade for the ACCT Workspace data layer
 */
@Service
public class AcctReportingDataService {

    private final AcctDashboardsRepository dashboardsRepository;

    public AcctReportingDataService(
        AcctDashboardsRepository dashboardsRepository
    ) {
        this.dashboardsRepository = dashboardsRepository;
    }

    public Collection<AcctDashboard> findDashboardsByDashboardIconUUIDIn(Collection<String> dashboardIconUUIDs) {
        return dashboardsRepository.findAllByDashboardIconUUIDIn(dashboardIconUUIDs);
    }

    public Collection<AcctDashboard> findDashboardsByWorkspaceUUIDIn(Collection<String> workspaceUUIDs) {
        return dashboardsRepository.findAllByWorkspaceUUIDIn(workspaceUUIDs);
    }

}
