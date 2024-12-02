package com.desolatetimelines.acct.security.ws.endpoint.model;

/**
 * Result of verifying dashboard accessibility to a given user
 *
 * @param accessible       true / false - tells if the user can access the dashboard
 * @param isGroupDashboard true / false - tells if the access is given via a users group
 */
public record DashboardAccessibilityReport(
    boolean accessible,
    boolean isGroupDashboard
) {
}
