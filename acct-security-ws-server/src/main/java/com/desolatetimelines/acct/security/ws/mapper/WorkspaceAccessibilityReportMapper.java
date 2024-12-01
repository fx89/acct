package com.desolatetimelines.acct.security.ws.mapper;

import com.desolatetimelines.acct.security.model.AccessibilityReport;
import com.desolatetimelines.acct.security.ws.endpoint.model.WorkspaceAccessibilityReport;

/**
 * Provides mappers for the {@link WorkspaceAccessibilityReport} type
 */
public class WorkspaceAccessibilityReportMapper {

    public static WorkspaceAccessibilityReport fromAccessibilityReport(AccessibilityReport accessibilityReport) {
        return
            new WorkspaceAccessibilityReport(
                accessibilityReport.accessible(),
                accessibilityReport.isGroupResource()
            );
    }

}
