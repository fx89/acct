package com.desolatetimelines.acct.security.ws.mapper;

import com.desolatetimelines.acct.security.model.AccessibilityReport;
import com.desolatetimelines.acct.security.ws.endpoint.model.ReportAccessibilityReport;

/**
 * Provides mappers for the {@link ReportAccessibilityReport} type
 */
public class ReportAccessibilityReportMapper {

    public static ReportAccessibilityReport fromAccessibilityReport(AccessibilityReport accessibilityReport) {
        return
            new ReportAccessibilityReport(
                accessibilityReport.accessible(),
                accessibilityReport.isGroupResource()
            );
    }

}
