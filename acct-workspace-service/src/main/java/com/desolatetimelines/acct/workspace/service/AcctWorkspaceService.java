package com.desolatetimelines.acct.workspace.service;

import com.desolatetimelines.acct.common.model.ObjectTypes;
import com.desolatetimelines.acct.security.client.data.AcctSecurityClientService;
import com.desolatetimelines.acct.security.client.model.UserResourceAccessRights;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;
import com.desolatetimelines.acct.security.ws.endpoint.model.WorkspaceOwner;
import com.desolatetimelines.acct.usage.ws.client.RESTUsageEndpointClient;
import com.desolatetimelines.acct.usage.ws.model.ServiceItemTypesList;
import com.desolatetimelines.acct.workspace.data.service.AcctWorkspaceDataService;
import com.desolatetimelines.acct.workspace.exception.AcctWorkspaceServiceNotFoundException;
import com.desolatetimelines.acct.workspace.exception.AcctWorkspaceServiceSecurityException;
import com.desolatetimelines.acct.workspace.model.AcctWorkspace;
import com.desolatetimelines.acct.workspace.model.WorkspaceDetails;
import com.desolatetimelines.acct.workspace.privilegesprovider.model.WorkspaceServiceOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.desolatetimelines.acct.security.client.model.ResourceType.WORKSPACE;
import static com.desolatetimelines.acct.workspace.privilegesprovider.model.ResourceOwnership.*;
import static com.desolatetimelines.acct.workspace.privilegesprovider.model.WorkspacePrivilegeIds.getWorkspacePrivilegeId;
import static com.desolatetimelines.acct.workspace.privilegesprovider.model.WorkspaceServiceOperation.DELETE;
import static com.desolatetimelines.acct.workspace.privilegesprovider.model.WorkspaceServiceOperation.SAVE;

/**
 * Workspace services layer
 */
@Service
public class AcctWorkspaceService {

    private final RESTUsageEndpointClient usageEndpointClient;

    private final AcctSecurityClientService securityClientService;

    private final AcctWorkspaceErrorCodesRegistryService errors;

    private final AcctWorkspaceDataService dataService;

    private final String applicationName;

    private final String contextPath;

    public AcctWorkspaceService(
        RESTUsageEndpointClient usageEndpointClient,
        AcctSecurityClientService securityClientService,
        AcctWorkspaceErrorCodesRegistryService errors,
        AcctWorkspaceDataService dataService,
        @Value("${WORKSPACE_APPLICATION_NAME}") String applicationName,
        @Value("${WORKSPACE_SERVER_CONTEXT_PATH}") String contextPath
    ) {
        this.usageEndpointClient = usageEndpointClient;
        this.securityClientService = securityClientService;
        this.errors = errors;
        this.dataService = dataService;
        this.applicationName = applicationName;
        this.contextPath = contextPath;
    }

    /**
     * Registers in-use item types with the usage service upon startup
     */
    @SuppressWarnings("unused")
    @EventListener(ApplicationReadyEvent.class)
    protected void registerInUseObjectTypes() {
        usageEndpointClient.registerItemTypesForService(
            ServiceItemTypesList.builder()
                .withServiceName(applicationName)
                .withServiceContextPath(contextPath)
                .withItemType(List.of(
                    ObjectTypes.ICON.name(),
                    ObjectTypes.BANK.name(),
                    ObjectTypes.CURRENCY.name(),
                    ObjectTypes.USER.name(),
                    ObjectTypes.INCOME_OR_EXPENSE_ITEM.name()
                ))
                .build()
        );
    }

    /**
     * Returns the UUIDs of any used items of the given type and that can be found in the given list
     *
     * @param objectType the given type
     * @param itemUUIDs  the given list
     */
    public Collection<String> getInUseItemUUIDs(String objectType, Collection<String> itemUUIDs) {
        // If the object type is ICON then search workspaces and accounts for used icons
        if (Objects.equals(objectType, ObjectTypes.ICON.name())) {
            throw new UnsupportedOperationException("Not implemented"); // TODO: work here
        }

        // If the object type is BANK then search accounts and deposits for banks
        if (Objects.equals(objectType, ObjectTypes.BANK.name())) {
            throw new UnsupportedOperationException("Not implemented"); // TODO: work here
        }

        // If the object type is CURRENCY then search workspaces, accounts, and deposits for currencies
        if (Objects.equals(objectType, ObjectTypes.CURRENCY.name())) {
            throw new UnsupportedOperationException("Not implemented"); // TODO: work here
        }

        // If the object type is USER then search account records for users
        if (Objects.equals(objectType, ObjectTypes.USER.name())) {
            throw new UnsupportedOperationException("Not implemented"); // TODO: work here
        }

        // If the object type is INCOME_OR_EXPENSE_ITEM then search account records for income or expense items
        if (Objects.equals(objectType, ObjectTypes.INCOME_OR_EXPENSE_ITEM.name())) {
            throw new UnsupportedOperationException("Not implemented"); // TODO: work here
        }

        // If this point has been reached, it means that either the item type is not supported
        // or the code for handling the object type is missing from above
        throw new IllegalArgumentException("Object type [" + objectType + "] not supported");
    }

    /**
     * Saves the workspace data present in the given workspace details. If the given workspace
     * details are missing the {@link WorkspaceDetails#workspaceUUID() workspace UUID} then
     * a new workspace is created for the user for the given user UUID. If the workspace UUID
     * is not missing then the workspace identified by the workspace UUID is updated, given
     * that the user with the given user UUID has the proper access rights.
     *
     * @param workspaceDetails the given workspace details
     * @param userUUID         the given user UUID
     * @return a reference to the created or updated workspace entity
     */
    public AcctWorkspace saveWorkspace(
        String userUUID,
        WorkspaceDetails workspaceDetails,
        Collection<String> assignedPrivilegeNames
    ) {
        // If the workspace UUID was provided then get the workspace for the user
        // If the workspace UUID was not provided then create a new workspace
        final AcctWorkspace workspace =
            Optional.ofNullable(workspaceDetails.workspaceUUID())
                .map(workspaceUUID ->
                    findWorkspaceForUserAndOperation(
                        SAVE, userUUID, workspaceDetails.workspaceUUID(), assignedPrivilegeNames
                    )
                )
                .orElseGet(this::createNewWorkspace);

        // Update the workspace details
        workspace.setWorkspaceName(workspaceDetails.workspaceName());
        workspace.setWorkspaceDescription(workspaceDetails.workspaceDescription());
        workspace.setWorkspaceIconUUID(workspaceDetails.workspaceIconUUID());
        workspace.setDefaultCurrencyUUID(workspaceDetails.defaultCurrencyUUID());

        // Save the workspace
        final AcctWorkspace savedWorkspace = dataService.saveWorkspace(workspace);

        // If this is a new workspace, set the workspace ownership
        if (workspaceDetails.workspaceUUID() == null) {
            securityClientService.addWorkspaceOwner(
                WorkspaceOwner.builder()
                    .withOwnerType(OwnerType.USER)
                    .withOwnerUUID(userUUID)
                    .withWorkspaceUUID(savedWorkspace.getWorkspaceUUID())
                    .build()
            );
        }

        // Return a reference to the saved workspace
        return savedWorkspace;
    }

    /**
     * Deletes the workspace with the given workspace UUID, as long as it is accessible for deletion
     * by the user with the given user UUID, which is determined in part by the given privileges
     *
     * @param userUUID               the given user UUID
     * @param assignedPrivilegeNames the given privileges
     * @param workspaceUUID          the given workspace UUID
     */
    public void deleteWorkspace(String userUUID, Collection<String> assignedPrivilegeNames, String workspaceUUID) {
        // Find the workspace. Throw an exception if the workspace is not accessible to the uer for the delete operation
        // or if the workspace is not found.
        final AcctWorkspace workspace =
            findWorkspaceForUserAndOperation(DELETE, userUUID, workspaceUUID, assignedPrivilegeNames);

        // Delete the workspace
        dataService.deleteWorkspace(workspace);

        // Remove the ownership record
        securityClientService.deleteWorkspaceOwner(
            WorkspaceOwner.builder()
                .withOwnerType(OwnerType.USER)
                .withOwnerUUID(userUUID)
                .withWorkspaceUUID(workspaceUUID)
                .build()
        );
    }

    /**
     * Determines if the workspace with the given workspace UUID is accessible for the given operation
     * by the user with the given user UUID, based on the accessibility report fetched from the security
     * service and the provided collection of assigned user privileges.
     *
     * @param operation              the given operation
     * @param userUUID               the given user UUID
     * @param workspaceUUID          the given workspace UUID
     * @param assignedPrivilegeNames the provided collection of assigned user privileges
     */
    private boolean workspaceIsAccessibleToUserForOperation(
        WorkspaceServiceOperation operation,
        String userUUID,
        String workspaceUUID,
        Collection<String> assignedPrivilegeNames
    ) {
        return
            securityClientService.resourceIsAccessibleToUser(
                WORKSPACE,
                userUUID,
                workspaceUUID,
                createUserAccessRights(operation, assignedPrivilegeNames)
            );
    }

    private static UserResourceAccessRights createUserAccessRights(
        WorkspaceServiceOperation operation,
        Collection<String> assignedPrivilegeNames
    ) {
        return
            UserResourceAccessRights.builder()
                .withOwnResources(assignedPrivilegeNames.contains(getWorkspacePrivilegeId(operation, OWN_RESOURCES)))
                .withGroupResources(assignedPrivilegeNames.contains(getWorkspacePrivilegeId(operation, GROUP_RESOURCES)))
                .withAnyResources(assignedPrivilegeNames.contains(getWorkspacePrivilegeId(operation, ANY_RESOURCES)))
                .build();
    }

    /**
     * Creates a new {@link AcctWorkspace workspace} with a newly generated workspaceUUID
     *
     * @return a reference to the newly created entity
     */
    private AcctWorkspace createNewWorkspace() {
        final AcctWorkspace newWorkspace = dataService.createNewWorkspace();
        newWorkspace.setWorkspaceUUID(UUID.randomUUID().toString());
        return newWorkspace;
    }

    /**
     * Retrieves the workspace with the given workspaceUUID, as long as it is owned
     * by the user with the given userUID. If the ownership verification fails, an
     * exception is thrown.
     *
     * @param userUUID      the given userUUID
     * @param workspaceUUID the given workspaceUUID
     */
    private AcctWorkspace findWorkspaceForUserAndOperation(
        WorkspaceServiceOperation operation,
        String userUUID,
        String workspaceUUID,
        Collection<String> assignedPrivilegeNames
    ) {
        // If the user does not have access to perform the operation on the workspace then throw an exception
        if (!workspaceIsAccessibleToUserForOperation(operation, userUUID, workspaceUUID, assignedPrivilegeNames)) {
            throw new AcctWorkspaceServiceSecurityException(errors, ObjectTypes.WORKSPACE, workspaceUUID);
        }

        // If the workspace is accessible to the user then find it and return a reference
        return
            dataService.findWorkspaceByWorkspaceUUID(workspaceUUID)
                .orElseThrow(() -> new AcctWorkspaceServiceNotFoundException(
                    errors, ObjectTypes.WORKSPACE, workspaceUUID
                ));
    }


}
