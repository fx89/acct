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

    public static final String ACCOUNTS_SAVE = "ACCOUNTS_SAVE";
    public static final String ACCOUNTS_READ = "ACCOUNTS_READ";
    public static final String ACCOUNTS_DELETE = "ACCOUNTS_DELETE";

    public static final String ACCOUNT_RECORDS_SAVE = "ACCOUNT_RECORDS_SAVE";
    public static final String ACCOUNT_RECORDS_READ = "ACCOUNT_RECORDS_READ";
    public static final String ACCOUNT_RECORDS_DELETE = "ACCOUNT_RECORDS_DELETE";
    public static final String ACCOUNT_RECORDS_TRANSFER = "ACCOUNT_RECORDS_TRANSFER";
    public static final String ACCOUNT_RECORDS_EXCHANGE = "ACCOUNT_RECORDS_EXCHANGE";

    public static final String DEPOSITS_SAVE = "DEPOSITS_SAVE";
    public static final String DEPOSITS_READ = "DEPOSITS_READ";
    public static final String DEPOSITS_CAPITALIZE = "DEPOSITS_CAPITALIZE";

    public static final String AUTOCOMPLETE_GET = "AUTOCOMPLETE_GET";


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
