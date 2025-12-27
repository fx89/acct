package com.desolatetimelines.acct.reporting.ws.mapper;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.common.ws.model.AcctPage;
import com.desolatetimelines.acct.reporting.model.ExtendedReportDetails;
import com.desolatetimelines.acct.reporting.ws.model.ReportExtendedProperties;

import java.util.Collections;

import static com.desolatetimelines.acct.common.ws.mapper.AcctPageInfoMapper.fromPage;
import static com.desolatetimelines.acct.reporting.ws.mapper.ReportPropertiesMapper.fromReportDetails;

/**
 * Provides mappers for the {@link ReportExtendedProperties} type.
 */
public abstract class ReportExtendedPropertiesMapper {

    public static ReportExtendedProperties fromExtendedReportDetails(
        ExtendedReportDetails extendedReportDetails
    ) {
        if (extendedReportDetails == null) {
            return null;
        }

        return
            new ReportExtendedProperties(
                extendedReportDetails.reportUUID(),
                fromReportDetails(extendedReportDetails.reportDetails())
            );
    }

    public static AcctPage<ReportExtendedProperties> fromPageOfExtendedReportDetails(
        Page<ExtendedReportDetails> page,
        int pageNumber
    ) {
        if (page == null) {
            return null;
        }

        return
            new AcctPage<>(
                page.data() == null
                    ? Collections.emptyList()
                    : page.data().stream().map(ReportExtendedPropertiesMapper::fromExtendedReportDetails).toList(),
                fromPage(page, pageNumber)
            );
    }

}
