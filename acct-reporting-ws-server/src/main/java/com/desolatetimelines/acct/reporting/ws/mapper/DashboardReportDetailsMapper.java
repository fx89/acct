package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.model.DashboardReportDetails;
import com.desolatetimelines.acct.reporting.ws.model.DashboardReportProperties;

/**
 * Provides mappers for the {@link DashboardReportDetails} type.
 */
public class DashboardReportDetailsMapper {

    public static DashboardReportDetails fromDashboardReportProperties(
        DashboardReportProperties dashboardReportProperties
    ) {
        if (dashboardReportProperties == null) {
            return null;
        }

        final DashboardReportDetails.DashboardReportDetailsBuilder builder =
            DashboardReportDetails.builder()
                .withReportUUID(dashboardReportProperties.getReportUUID())
                .withColumnNumber(dashboardReportProperties.getColumnNumber())
                .withRowNumber(dashboardReportProperties.getRowNumber())
                .withContainerHeightPx(dashboardReportProperties.getContainerHeightPx())
                .withContainerName(dashboardReportProperties.getContainerName());

        if (dashboardReportProperties.getFilters() != null) {
            dashboardReportProperties.getFilters().forEach(filter ->
                builder.withFilter(
                    filter.filterName(),
                    filter.reportColumnName()
                )
            );
        }

        return builder.build();
    }

}
