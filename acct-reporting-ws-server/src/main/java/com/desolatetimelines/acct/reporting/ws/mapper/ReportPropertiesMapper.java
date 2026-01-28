package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.model.ReportDetails;
import com.desolatetimelines.acct.reporting.ws.model.ReportProperties;

import static com.desolatetimelines.acct.reporting.ws.mapper.ReportSeriesMapper.fromReportSeriesDetailsSet;
import static com.desolatetimelines.acct.reporting.ws.mapper.ReportSeriesMapper.toReportSeriesDetailsSet;
import static com.desolatetimelines.acct.reporting.ws.mapper.ReportTypeMapper.fromAcctReportType;
import static com.desolatetimelines.acct.reporting.ws.mapper.ReportTypeMapper.toAcctReportType;

/**
 * Provides mappers for the {@link ReportProperties type}
 */
public abstract class ReportPropertiesMapper {

    public static ReportDetails toReportDetails(ReportProperties reportProperties) {

        final ReportDetails.ReportDetailsBuilder builder =
            ReportDetails.builder()
                .withReportName(reportProperties.reportName())
                .withReportDescription(reportProperties.reportDescription())
                .withReportType(toAcctReportType(reportProperties.reportType()))
                .withReportSQL(reportProperties.reportSQL())
                .withReportCategoryColumnName(reportProperties.reportCategoryColumnName())
                .withDataProviderInstanceUUIDs(reportProperties.dataProviderInstanceUUIDs());

        if (reportProperties.reportSeries() != null && !reportProperties.reportSeries().isEmpty()) {
            builder.withReportSeries(toReportSeriesDetailsSet(reportProperties.reportSeries()));
        }

        return builder.build();
    }

    public static ReportProperties fromReportDetails(ReportDetails reportDetails) {
        if (reportDetails == null) {
            return null;
        }

        final ReportProperties.ReportPropertiesBuilder builder =
            ReportProperties.builder()
                .withReportName(reportDetails.reportName())
                .withReportDescription(reportDetails.reportDescription())
                .withReportSQL(reportDetails.reportSQL())
                .withReportType(fromAcctReportType(reportDetails.reportType()))
                .withReportCategoryColumnName(reportDetails.reportCategoryColumnName())
                .withDataProviderInstanceUUIDs(reportDetails.dataProviderInstanceUUIDs());

        if (reportDetails.reportSeries() != null && !reportDetails.reportSeries().isEmpty()) {
            builder.withReportSeries(fromReportSeriesDetailsSet(reportDetails.reportSeries()));
        }

        return builder.build();
    }

}
