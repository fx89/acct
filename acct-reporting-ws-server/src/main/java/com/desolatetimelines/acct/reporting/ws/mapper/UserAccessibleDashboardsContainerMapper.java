package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.model.DashboardsContainer;
import com.desolatetimelines.acct.reporting.ws.model.UserAccessibleDashboardsContainer;

import static com.desolatetimelines.acct.reporting.ws.mapper.DashboardReadablePropertiesMapper.fromServicesLayerDashboardReadableProperties;

/**
 * Provides mapping methods for the {@link UserAccessibleDashboardsContainer} class
 */
public abstract class UserAccessibleDashboardsContainerMapper {

    public static UserAccessibleDashboardsContainer fromServicesLayerDashboardsContainer(
        DashboardsContainer servicesLayerUserAccessibleDashboardsContainer
    ) {
        // Create a builder
        final UserAccessibleDashboardsContainer.UserAccessibleDashboardsContainerBuilder builder =
            UserAccessibleDashboardsContainer.builder();

        // Map the group dashboards and add them to the builder
        servicesLayerUserAccessibleDashboardsContainer.groupDashboards().forEach(dashboard ->
            builder.withGroupDashboard(fromServicesLayerDashboardReadableProperties(dashboard))
        );

        // Map the user dashboards and add them to the builder
        servicesLayerUserAccessibleDashboardsContainer.userDashboards().forEach(dashboard ->
            builder.withUserDashboard(fromServicesLayerDashboardReadableProperties(dashboard))
        );

        // Build the user accessible dashboards container
        return builder.build();
    }

}
