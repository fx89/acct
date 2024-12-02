package com.desolatetimelines.acct.security.ws.mapper;

import com.desolatetimelines.acct.security.model.AcctReportOwner;
import com.desolatetimelines.acct.security.model.AcctResourceOwner;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnedReportsGroup;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

/**
 * Provides mapper methods for the {@link OwnedReportsGroup} object
 */
public class OwnedReportsGroupsMapper {

    public static OwnedReportsGroup fromAcctReportOwnersCollection(
        Collection<AcctReportOwner> acctReportOwnerCollection
    ) {
        // Group by owner type name
        final Map<String, List<AcctReportOwner>> reportOwnersByOwnerType =
            acctReportOwnerCollection
                .stream()
                .collect(Collectors.groupingBy(o -> o.getOwnerType().name()));

        // Build and return the result
        return
            OwnedReportsGroup.builder()
                .withUserReports(extractReportUUIDsByOwnerType(reportOwnersByOwnerType, OwnerType.USER))
                .withGroupReports(extractReportUUIDsByOwnerType(reportOwnersByOwnerType, OwnerType.GROUP))
                .withPublicReports(extractReportUUIDsByOwnerType(reportOwnersByOwnerType, OwnerType.PUBLIC))
                .build();
    }

    private static Collection<String> extractReportUUIDsByOwnerType(
        Map<String, List<AcctReportOwner>> reportOwnersByOwnerType,
        OwnerType ownerType
    ) {
        return
            reportOwnersByOwnerType.getOrDefault(ownerType.name(), emptyList())
                .stream()
                .map(AcctResourceOwner::getResourceUUID)
                .toList();
    }

}
