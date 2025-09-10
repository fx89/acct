package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.ws.model.DashboardReadableProperties;

/**
 * Provides mapping methods for the {@link DashboardReadableProperties} type
 */
public abstract class DashboardReadablePropertiesMapper {

    public static DashboardReadableProperties fromServicesLayerDashboardReadableProperties(
        com.desolatetimelines.acct.reporting.model.DashboardReadableProperties servicesLayerDashboardReadableProperties
    ) {
        return
            DashboardReadableProperties.builder()
                .withDashboardUUID(servicesLayerDashboardReadableProperties.dashboardUUID())
                .withDashboardName(servicesLayerDashboardReadableProperties.dashboardName())
                .withDashboardDescription(servicesLayerDashboardReadableProperties.dashboardDescription())
                .withDashboardIconUUID(servicesLayerDashboardReadableProperties.dashboardIconUUID())
                .build();
    }

}
