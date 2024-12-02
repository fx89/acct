package com.desolatetimelines.acct.security.ws.mapper;

import com.desolatetimelines.acct.security.model.AccessibilityReport;
import com.desolatetimelines.acct.security.ws.endpoint.model.DashboardAccessibilityReport;

/**
 * Provides mappers for the {@link DashboardAccessibilityReport} type
 */
public class DashboardAccessibilityReportMapper {

    public static DashboardAccessibilityReport fromAccessibilityReport(AccessibilityReport accessibilityReport) {
        return
            new DashboardAccessibilityReport(
                accessibilityReport.accessible(),
                accessibilityReport.isGroupResource()
            );
    }

}
