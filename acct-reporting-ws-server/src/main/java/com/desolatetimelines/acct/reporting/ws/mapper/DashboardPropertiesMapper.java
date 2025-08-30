package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.model.DashboardDetails;
import com.desolatetimelines.acct.reporting.ws.model.DashboardProperties;

/**
 * Contains mapper methods for the {@link DashboardProperties} type
 */
public abstract class DashboardPropertiesMapper {

    /**
     * Transforms the referenced dashboard properties into a dashboard details object
     * that can be used as DTO to transfer data into the services layer
     */
    public static DashboardDetails toDashboardDetails(DashboardProperties dashboardProperties) {
        return DashboardDetails.builder()
            .withDashboardName(dashboardProperties.dashboardName())
            .withDashboardDescription(dashboardProperties.dashboardDescription())
            .withDashboardIconUUID(dashboardProperties.dashboardIconUUID())
            .build();
    }

}
