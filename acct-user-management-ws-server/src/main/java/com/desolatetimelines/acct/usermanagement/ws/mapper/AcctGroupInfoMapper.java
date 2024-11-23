package com.desolatetimelines.acct.usermanagement.ws.mapper;

import com.desolatetimelines.acct.usermanagement.model.AcctUsersGroup;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctGroupInfo;

/**
 * Provides mapper methods to and from {@link AcctGroupInfo}
 */
public abstract class AcctGroupInfoMapper {

    /**
     * Maps the referenced {@link AcctUsersGroup data layer users group} to a new
     * {@link AcctGroupInfo presentation layer group info}
     *
     * @param dataLayerUserGroup the referenced data layer user group
     */
    public static AcctGroupInfo fromDataLayerUserGroup(AcctUsersGroup dataLayerUserGroup) {
        return
            AcctGroupInfo.builder()
                .withGroupUUID(dataLayerUserGroup.getGroupUUID())
                .withGroupName(dataLayerUserGroup.getGroupName())
                .build();
    }

}
