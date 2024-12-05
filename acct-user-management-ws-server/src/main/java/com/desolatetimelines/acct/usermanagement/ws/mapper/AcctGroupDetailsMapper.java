package com.desolatetimelines.acct.usermanagement.ws.mapper;

import com.desolatetimelines.acct.usermanagement.model.AcctUsersGroup;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctGroupDetails;

/**
 * Provides mapper methods to and from {@link AcctGroupDetails}
 */
public class AcctGroupDetailsMapper {

    /**
     * Maps the referenced
     * {@link AcctUsersGroup data layer group details}
     * to a new {@link AcctGroupDetails presentation layer group details} object
     *
     * @param dataLayerGroupDetails the referenced data layer user details
     */
    public static AcctGroupDetails fromDataLayerAcctUserDetails(
        AcctUsersGroup dataLayerGroupDetails
    ) {
        // Add the user details
        return
            AcctGroupDetails.builder()
                .withGroupUUID(dataLayerGroupDetails.getGroupUUID())
                .withGroupName(dataLayerGroupDetails.getGroupName())
                .withGroupDescription(dataLayerGroupDetails.getGroupDescription())
                .withGroupIconUUID(dataLayerGroupDetails.getGroupIconUUID())
                .build();
    }

}
