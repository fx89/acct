package com.desolatetimelines.acct.workspace.ws.model;

/**
 * Describes the workspace properties accepted by REST APIs
 *
 * @param workspaceName        The human-readable name of the workspace
 * @param workspaceDescription The human-readable description of the workspace
 * @param workspaceIconUUID    The UUID of the icon used when displaying the workspace
 * @param defaultCurrencyUUID  The UUID of the workspace' default currency
 */
public record WorkspaceProperties(
    String workspaceName,
    String workspaceDescription,
    String workspaceIconUUID,
    String defaultCurrencyUUID
) {
}
