package com.desolatetimelines.acct.workspace.privilegesprovider.model;

import java.util.Map;

/**
 * Provides PRIVILEGE_ID constants for use throughout the project
 */
public abstract class WorkspacePrivilegeIds {

    public static final String WORKSPACES_SAVE_OWN = "WORKSPACES_SAVE_OWN";
    public static final String WORKSPACES_SAVE_GROUP = "WORKSPACES_SAVE_GROUP";
    public static final String WORKSPACES_SAVE_ANY = "WORKSPACES_SAVE_ANY";

    public static final String WORKSPACES_READ_OWN = "WORKSPACES_READ_OWN";
    public static final String WORKSPACES_READ_GROUP = "WORKSPACES_READ_GROUP";
    public static final String WORKSPACES_READ_ANY = "WORKSPACES_READ_ANY";

    public static final String WORKSPACES_DELETE_OWN = "WORKSPACES_DELETE_OWN";
    public static final String WORKSPACES_DELETE_GROUP = "WORKSPACES_DELETE_GROUP";
    public static final String WORKSPACES_DELETE_ANY = "WORKSPACES_DELETE_ANY";

    public static final String ACCOUNT_SAVE = "ACCOUNT_SAVE";
    public static final String ACCOUNT_READ = "ACCOUNT_READ";
    public static final String ACCOUNT_DELETE = "ACCOUNT_DELETE";


    private static final Map<WorkspaceServiceOperation, Map<ResourceOwnership, String>>
        workspacePrivilegesOperationAndOwnership =
        Map.of(
            WorkspaceServiceOperation.SAVE,
            Map.of(
                ResourceOwnership.OWN_RESOURCES, WORKSPACES_SAVE_OWN,
                ResourceOwnership.GROUP_RESOURCES, WORKSPACES_SAVE_GROUP,
                ResourceOwnership.ANY_RESOURCES, WORKSPACES_SAVE_ANY
            ),
            WorkspaceServiceOperation.READ,
            Map.of(
                ResourceOwnership.OWN_RESOURCES, WORKSPACES_READ_OWN,
                ResourceOwnership.GROUP_RESOURCES, WORKSPACES_READ_GROUP,
                ResourceOwnership.ANY_RESOURCES, WORKSPACES_READ_ANY
            ),
            WorkspaceServiceOperation.DELETE,
            Map.of(
                ResourceOwnership.OWN_RESOURCES, WORKSPACES_DELETE_OWN,
                ResourceOwnership.GROUP_RESOURCES, WORKSPACES_DELETE_GROUP,
                ResourceOwnership.ANY_RESOURCES, WORKSPACES_DELETE_ANY
            )
        );

    public static String getWorkspacePrivilegeId(WorkspaceServiceOperation operation, ResourceOwnership ownership) {
        // Get the ownership/privilegeId map for the referenced operation
        final Map<ResourceOwnership, String> operationResourceOwnershipPrivileges =
            workspacePrivilegesOperationAndOwnership.get(operation);

        // If not found, throw an exception
        if (operationResourceOwnershipPrivileges == null) {
            throw new IllegalArgumentException(
                "No privileges defined for the workspace " + operation.name() + " operation"
            );
        }

        // Get the privilegeId for the operation / ownership pair
        final String privilegeId = operationResourceOwnershipPrivileges.get(ownership);

        // If not found, throw an exception
        if (privilegeId == null) {
            throw new IllegalArgumentException(
                "No privileges defined for the workspace " + operation.name() + " operation " +
                    "and " + ownership.name() + " ownership"
            );
        }

        // If all goes well, return the privilegeId
        return privilegeId;
    }

}
