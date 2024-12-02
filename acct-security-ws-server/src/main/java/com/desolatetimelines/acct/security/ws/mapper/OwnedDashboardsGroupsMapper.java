package com.desolatetimelines.acct.security.ws.mapper;

import com.desolatetimelines.acct.security.model.AcctDashboardOwner;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnedDashboardsGroup;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

/**
 * Provides mapper methods for the {@link OwnedDashboardsGroup} object
 */
public class OwnedDashboardsGroupsMapper {

    public static OwnedDashboardsGroup fromAcctDashboardOwnersCollection(
        Collection<AcctDashboardOwner> acctDashboadOwnerCollection
    ) {
        // Group by owner type name
        final Map<String, List<AcctDashboardOwner>> dashboardOwnersByOwnerType =
            acctDashboadOwnerCollection
                .stream()
                .collect(Collectors.groupingBy(o -> o.getOwnerType().name()));

        // Build and return the result
        return
            OwnedDashboardsGroup.builder()
                .withUserDashboards(extractDashboardUUIDsByOwnerType(dashboardOwnersByOwnerType, OwnerType.USER))
                .withGroupDashboards(extractDashboardUUIDsByOwnerType(dashboardOwnersByOwnerType, OwnerType.GROUP))
                .withPublicDashboards(extractDashboardUUIDsByOwnerType(dashboardOwnersByOwnerType, OwnerType.PUBLIC))
                .build();
    }

    private static Collection<String> extractDashboardUUIDsByOwnerType(
        Map<String, List<AcctDashboardOwner>> dashboardOwnersByOwnerType,
        OwnerType ownerType
    ) {
        return
            dashboardOwnersByOwnerType.getOrDefault(ownerType.name(), emptyList())
                .stream()
                .map(AcctDashboardOwner::getResourceUUID)
                .toList();
    }

}
