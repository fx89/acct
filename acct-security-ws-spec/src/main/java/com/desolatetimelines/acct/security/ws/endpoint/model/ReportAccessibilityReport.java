package com.desolatetimelines.acct.security.ws.endpoint.model;

/**
 * Result of verifying report accessibility to a given user
 *
 * @param accessible    true / false - tells if the user can access the report
 * @param isGroupReport true / false - tells if the access is given via a users group
 */
public record ReportAccessibilityReport(
    boolean accessible,
    boolean isGroupReport
) {
}
