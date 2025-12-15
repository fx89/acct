package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.reporting.model.ReportDetails;
import com.desolatetimelines.acct.reporting.ws.model.ReportProperties;

import static com.desolatetimelines.acct.reporting.ws.mapper.ReportSeriesMapper.toReportSeriesDetailsSet;
import static com.desolatetimelines.acct.reporting.ws.mapper.ReportTypeMapper.toAcctReportType;

/**
 * Provides mappers for the {@link ReportProperties type}
 */
public abstract class ReportPropertiesMapper {

    public static ReportDetails toReportDetails(ReportProperties reportProperties) {
        return
            ReportDetails.builder()
                .withReportName(reportProperties.reportName())
                .withReportDescription(reportProperties.reportDescription())
                .withReportType(toAcctReportType(reportProperties.reportType()))
                .withReportSQL(reportProperties.reportSQL())
                .withReportCategoryColumnName(reportProperties.reportCategoryColumnName())
                .withDataProviderInstanceUUIDs(reportProperties.dataProviderInstanceUUIDs())
                .withReportSeries(toReportSeriesDetailsSet(reportProperties.reportSeries()))
                .build();
    }

}
