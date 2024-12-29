package com.desolatetimelines.acct.workspace.service;

import com.desolatetimelines.acct.common.model.ObjectTypes;
import com.desolatetimelines.acct.security.ws.client.RESTWorkspaceOwnershipEndpointClient;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;
import com.desolatetimelines.acct.security.ws.endpoint.model.WorkspaceAccessibilityReport;
import com.desolatetimelines.acct.security.ws.endpoint.model.WorkspaceOwner;
import com.desolatetimelines.acct.usage.ws.client.RESTUsageEndpointClient;
import com.desolatetimelines.acct.usage.ws.model.ServiceItemTypesList;
import com.desolatetimelines.acct.workspace.data.service.AcctWorkspaceDataService;
import com.desolatetimelines.acct.workspace.exception.AcctWorkspaceServiceNotFoundException;
import com.desolatetimelines.acct.workspace.exception.AcctWorkspaceServiceSecurityException;
import com.desolatetimelines.acct.workspace.model.AcctWorkspace;
import com.desolatetimelines.acct.workspace.model.WorkspaceDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Workspace services layer
 */
@Service
public class AcctWorkspaceService {

    private final RESTUsageEndpointClient usageEndpointClient;

    private final RESTWorkspaceOwnershipEndpointClient workspaceOwnershipEndpointClient;

    private final AcctWorkspaceErrorCodesRegistryService errors;

    private final AcctWorkspaceDataService dataService;

    private final String applicationName;

    private final String contextPath;

    public AcctWorkspaceService(
        RESTUsageEndpointClient usageEndpointClient,
        RESTWorkspaceOwnershipEndpointClient workspaceOwnershipEndpointClient,
        AcctWorkspaceErrorCodesRegistryService errors,
        AcctWorkspaceDataService dataService,
        @Value("${WORKSPACE_APPLICATION_NAME}") String applicationName,
        @Value("${WORKSPACE_SERVER_CONTEXT_PATH}") String contextPath
    ) {
        this.usageEndpointClient = usageEndpointClient;
        this.workspaceOwnershipEndpointClient = workspaceOwnershipEndpointClient;
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
    public AcctWorkspace saveWorkspace(String userUUID, WorkspaceDetails workspaceDetails) {
        // If the workspace UUID was provided then get the workspace for the user
        // If the workspace UUID was not provided then create a new workspace
        final AcctWorkspace workspace =
            Optional.ofNullable(workspaceDetails.workspaceUUID())
                .map(workspaceUUID -> findWorkspaceForUser(userUUID, workspaceDetails.workspaceUUID()))
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
            workspaceOwnershipEndpointClient.addWorkspaceOwner(
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
    private AcctWorkspace findWorkspaceForUser(String userUUID, String workspaceUUID) {
        // Get the accessibility report for the referenced user and workspace
        final WorkspaceAccessibilityReport accessibilityReport =
            workspaceOwnershipEndpointClient.isUserAccessibleWorkspace(userUUID, workspaceUUID);

        // If the workspace is not directly accessible to the user then throw an exception
        if (!accessibilityReport.accessible() || accessibilityReport.isGroupWorkspace()) {
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
