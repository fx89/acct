package com.desolatetimelines.acct.security.ws.endpoint.model;

/**
 * Result of verifying workspace accessibility to a given user
 *
 * @param accessible       true / false - tells if the user can access the workspace
 * @param isGroupWorkspace true / false - tells if the access is given via a users group
 */
public record WorkspaceAccessibilityReport(
    boolean accessible,
    boolean isGroupWorkspace
) {
}
