package com.desolatetimelines.acct.security.ws.mapper;

import com.desolatetimelines.acct.security.model.AcctWorkspaceOwner;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnedWorkspacesGroup;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

/**
 * Provides mapper methods for the {@link OwnedWorkspacesGroupsMapper} object
 */
public class OwnedWorkspacesGroupsMapper {

    public static OwnedWorkspacesGroup fromAcctWorkspaceOwnersCollection(
        Collection<AcctWorkspaceOwner> acctWorkspaceOwnerCollection
    ) {
        // Group by owner type name
        final Map<String, List<AcctWorkspaceOwner>> workspaceOwnersByOwnerType =
            acctWorkspaceOwnerCollection
                .stream()
                .collect(Collectors.groupingBy(wo -> wo.getOwnerType().name()));

        // Build and return the result
        return
            OwnedWorkspacesGroup.builder()
                .withUserWorkspaces(extractWorkspaceUUIDsByOwnerType(workspaceOwnersByOwnerType, OwnerType.USER))
                .withGroupWorkspaces(extractWorkspaceUUIDsByOwnerType(workspaceOwnersByOwnerType, OwnerType.GROUP))
                .withPublicWorkspaces(extractWorkspaceUUIDsByOwnerType(workspaceOwnersByOwnerType, OwnerType.PUBLIC))
                .build();
    }

    private static Collection<String> extractWorkspaceUUIDsByOwnerType(
        Map<String, List<AcctWorkspaceOwner>> workspaceOwnersByOwnerType,
        OwnerType ownerType
    ) {
        return
            workspaceOwnersByOwnerType.getOrDefault(ownerType.name(), emptyList())
                .stream()
                .map(AcctWorkspaceOwner::getWorkspaceUUID)
                .toList();
    }

}
