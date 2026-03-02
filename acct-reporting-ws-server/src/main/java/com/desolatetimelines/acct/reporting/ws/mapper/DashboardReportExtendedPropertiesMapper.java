package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.model.DashboardReportExtendedDetails;
import com.desolatetimelines.acct.reporting.ws.model.DashboardReportExtendedProperties;
import com.desolatetimelines.acct.reporting.ws.model.DashboardReportFilterProperties;

import java.util.Set;

import static com.desolatetimelines.acct.reporting.ws.mapper.ReportSeriesMapper.fromAcctReportSeriesSet;
import static com.desolatetimelines.acct.reporting.ws.mapper.ReportTypeMapper.fromAcctReportType;
import static java.util.stream.Collectors.toSet;

/**
 * Provides mappers for the {@link DashboardReportExtendedProperties} type.
 */
public abstract class DashboardReportExtendedPropertiesMapper {

    public static DashboardReportExtendedProperties fromDashboardReportExtendedDetails(
        DashboardReportExtendedDetails dashboardReportExtendedDetails
    ) {
        final DashboardReportExtendedProperties.DashboardReportExtendedPropertiesBuilder builder =
            DashboardReportExtendedProperties.builderExt()
                .withReportUUID(dashboardReportExtendedDetails.getReportUUID())
                .withReportType(fromAcctReportType(dashboardReportExtendedDetails.getReportType()))
                .withReportName(dashboardReportExtendedDetails.getReportName())
                .withReportDescription(dashboardReportExtendedDetails.getReportDescription())
                .withReportCategoryColumnName(dashboardReportExtendedDetails.getReportCategoryColumnName())
                .withContainerName(dashboardReportExtendedDetails.getContainerName())
                .withContainerWidthPx(dashboardReportExtendedDetails.getContainerWidthPx())
                .withContainerHeightPx(dashboardReportExtendedDetails.getContainerHeightPx())
                .withRowNumber(dashboardReportExtendedDetails.getRowNumber())
                .withColumnNumber(dashboardReportExtendedDetails.getColumnNumber());

        if (dashboardReportExtendedDetails.getReportSeries() != null &&
            !dashboardReportExtendedDetails.getReportSeries().isEmpty()
        ) {
            builder.withReportSeries(fromAcctReportSeriesSet(dashboardReportExtendedDetails.getReportSeries()));
        }

        if (!dashboardReportExtendedDetails.getFilters().isEmpty()) {
            builder.withFilters(
                dashboardReportExtendedDetails.getFilters().entrySet()
                    .stream()
                    .map(entry ->
                        new DashboardReportFilterProperties(entry.getKey(), entry.getValue()))
                    .collect(toSet())
            );
        }

        return builder.build();
    }

    public static Set<DashboardReportExtendedProperties> fromSetOfDashboardReportExtendedDetails(
        Set<DashboardReportExtendedDetails> dashboardReportExtendedDetailsSet
    ) {
        if (dashboardReportExtendedDetailsSet == null) {
            return null;
        }

        return
            dashboardReportExtendedDetailsSet.stream()
                .map(DashboardReportExtendedPropertiesMapper::fromDashboardReportExtendedDetails)
                .collect(toSet());
    }

}
