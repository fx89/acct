package com.desolatetimelines.acct.security.client.data;

import com.desolatetimelines.acct.security.client.model.ResourceType;
import com.desolatetimelines.acct.security.client.model.UserResourceAccessRights;
import com.desolatetimelines.acct.security.ws.client.RESTDashboardOwnershipEndpointClient;
import com.desolatetimelines.acct.security.ws.client.RESTReportOwnershipEndpointClient;
import com.desolatetimelines.acct.security.ws.client.RESTWorkspaceOwnershipEndpointClient;
import com.desolatetimelines.acct.security.ws.endpoint.model.*;
import org.springframework.stereotype.Service;

/**
 * Provides data access operations based on the REST Security client,
 * along with dedicated functionality to help ascertain access rights
 */
@Service
public class AcctSecurityClientService {

    private final RESTWorkspaceOwnershipEndpointClient workspaceOwnershipEndpointClient;

    private final RESTReportOwnershipEndpointClient reportOwnershipEndpointClient;

    private final RESTDashboardOwnershipEndpointClient dashboardOwnershipEndpointClient;

    public AcctSecurityClientService(
        RESTWorkspaceOwnershipEndpointClient workspaceOwnershipEndpointClient,
        RESTReportOwnershipEndpointClient reportOwnershipEndpointClient,
        RESTDashboardOwnershipEndpointClient dashboardOwnershipEndpointClient
    ) {
        this.workspaceOwnershipEndpointClient = workspaceOwnershipEndpointClient;
        this.reportOwnershipEndpointClient = reportOwnershipEndpointClient;
        this.dashboardOwnershipEndpointClient = dashboardOwnershipEndpointClient;
    }

    /**
     * Adds a new workspace owner entity as defined in the referenced data object
     *
     * @param workspaceOwner the referenced data object
     */
    public void addWorkspaceOwner(WorkspaceOwner workspaceOwner) {
        workspaceOwnershipEndpointClient.addWorkspaceOwner(workspaceOwner);
    }

    /**
     * Deletes the workspace ownership record wirth the properties of the given
     * {@link WorkspaceOwner workspace owner record}
     */
    public void deleteWorkspaceOwner(WorkspaceOwner workspaceOwner) {
        workspaceOwnershipEndpointClient.deleteWorkspaceOwner(
            workspaceOwner.ownerType(),
            workspaceOwner.ownerUUID(),
            workspaceOwner.workspaceUUID()
        );
    }

    public OwnedWorkspacesGroup getUserAccessibleWorkspaces(String userUUID) {
        return workspaceOwnershipEndpointClient.getUserAccessibleWorkspaces(userUUID);
    }

    /**
     * Returns true if the referenced user has the rights to perform a certain operation
     * on the referenced resource of the given resource type under the circumstances given
     * by the referenced access rights.<br />
     * <br />
     * Queries the ownership endpoints of the security service to find out if
     * the user has access to the resource and then, based on the referenced
     * access rights, which are client and operation-specific, determines if
     * the user has the right to perform the operation on the resource.
     *
     * @param resourceType             the given resource type
     * @param userUUID                 the UUID of the referenced user
     * @param resourceUUID             the UUID of the referenced resource
     * @param userResourceAccessRights the referenced access rights
     */
    public boolean resourceIsAccessibleToUser(
        ResourceType resourceType,
        String userUUID,
        String resourceUUID,
        UserResourceAccessRights userResourceAccessRights
    ) {
        // If the user may access any resource then any resource is accessible to the user
        // and there's no need to verify any more rules
        if (userResourceAccessRights.anyResources()) {
            return true;
        }

        // If the user does not have the rights to access any resources then the user's
        // right to access the resource must be analyzed

        // Fetch the accessibility report from the security service
        final AccessibilityReport accessibilityReport =
            fetchAccessibilityReport(resourceType, userUUID, resourceUUID);

        // If the resource is accessible then...
        if (accessibilityReport.accessible()) {
            // If it's accessible via a group
            if (accessibilityReport.isGroupResource()) {
                // The user must have group resource access rights to access the group resource
                return userResourceAccessRights.groupResources();
            }
            // If it's directly accessible
            else {
                // The user must have own resource access rights to access the resource
                return userResourceAccessRights.ownResources();
            }
        }

        // If none of the above, it means the user does not have access to the resource
        return false;
    }

    /**
     * Queries the security service for the accessibility report for the given
     * resource type, user UUID and resource UUID
     *
     * @param resourceType the given resource type
     * @param userUUID     the given user UUID
     * @param resourceUUID the given resource UUID
     */
    private AccessibilityReport fetchAccessibilityReport(
        ResourceType resourceType,
        String userUUID,
        String resourceUUID
    ) {
        // If the resource type is WORKSPACE then fetch the workspace accessibility report
        if (ResourceType.WORKSPACE == resourceType) {
            return
                AccessibilityReport.fromWorkspaceAccessibilityReport(
                    workspaceOwnershipEndpointClient.isUserAccessibleWorkspace(userUUID, resourceUUID)
                );
        }

        // If the resource type is DASHBOARD then fetch the dashboard accessibility report
        if (ResourceType.DASHBOARD == resourceType) {
            return
                AccessibilityReport.fromDashboardAccessibilityReport(
                    dashboardOwnershipEndpointClient.isUserAccessibleDashboard(userUUID, resourceUUID)
                );
        }

        // If the resource type is REPORT then fetch the report accessibility report
        if (ResourceType.REPORT == resourceType) {
            return
                AccessibilityReport.fromReportAccessibilityReport(
                    reportOwnershipEndpointClient.isUserAccessibleReport(userUUID, resourceUUID)
                );
        }

        throw new IllegalArgumentException("Unsupported resource type: " + resourceType.name());
    }

    /**
     * Generic accessibility report, usable for all resource types
     *
     * @param accessible      Set to true if the resource is accessible to the user either directly or through a group
     * @param isGroupResource Set to true if the resource is not accessible to the user directly, but rather through a group
     */
    private record AccessibilityReport(
        boolean accessible,
        boolean isGroupResource
    ) {
        public static AccessibilityReport fromWorkspaceAccessibilityReport(
            WorkspaceAccessibilityReport workspaceAccessibilityReport
        ) {
            return new AccessibilityReport(
                workspaceAccessibilityReport.accessible(),
                workspaceAccessibilityReport.isGroupWorkspace()
            );
        }

        public static AccessibilityReport fromDashboardAccessibilityReport(
            DashboardAccessibilityReport dashboardAccessibilityReport
        ) {
            return new AccessibilityReport(
                dashboardAccessibilityReport.accessible(),
                dashboardAccessibilityReport.isGroupDashboard()
            );
        }

        public static AccessibilityReport fromReportAccessibilityReport(
            ReportAccessibilityReport reportAccessibilityReport
        ) {
            return new AccessibilityReport(
                reportAccessibilityReport.accessible(),
                reportAccessibilityReport.isGroupReport()
            );
        }
    }
}
