package com.desolatetimelines.acct.workspace.ws.service;

import com.desolatetimelines.acct.usage.ws.redist.service.InUseItemsService;
import com.desolatetimelines.acct.workspace.service.AcctWorkspaceService;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementation of the {@link InUseItemsService} for the ACCT Usage service
 */
@Service
public class AcctWorkspaceInUseItemsService implements InUseItemsService {

    private final AcctWorkspaceService workspaceService;

    public AcctWorkspaceInUseItemsService(AcctWorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @Override
    public Collection<String> getInUseItemUUIDs(String objectType, Collection<String> itemUUIDs) {
        return workspaceService.getInUseItemUUIDs(objectType, itemUUIDs);
    }
}
