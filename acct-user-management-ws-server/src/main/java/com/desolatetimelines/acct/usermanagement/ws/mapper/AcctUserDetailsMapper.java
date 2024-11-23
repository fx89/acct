package com.desolatetimelines.acct.usermanagement.ws.mapper;

import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserDetails;

/**
 * Provides mapper methods to and from {@link AcctUserDetails}
 */
public abstract class AcctUserDetailsMapper {

    /**
     * Maps the referenced
     * {@link com.desolatetimelines.acct.usermanagement.data.model.AcctUserDetails data layer user details}
     * to a new {@link AcctUserDetails presentation layer user details} object
     *
     * @param dataLayerUserDetails the referenced data layer user detils
     */
    public static AcctUserDetails fromDataLayerAcctUserDetails(
        com.desolatetimelines.acct.usermanagement.data.model.AcctUserDetails dataLayerUserDetails
    ) {
        // Add the user details
        final AcctUserDetails.AcctUserDetailsBuilder builder =
            AcctUserDetails.builder()
                .withUserUUID(dataLayerUserDetails.userAccount().getUserUUID())
                .withUserName(dataLayerUserDetails.userAccount().getUserName())
                .withUserLoginName(dataLayerUserDetails.userAccount().getUserLoginName())
                .withUserIconUUID(dataLayerUserDetails.userAccount().getUserIconUUID())
                .withUserEncryptedPasswordName(dataLayerUserDetails.userAccount().getUserEncryptedPassword())
                .withDefaultWorkspaceUUID(dataLayerUserDetails.userAccount().getDefaultWorkspaceUUID());

        // Add the group details
        dataLayerUserDetails.userGroups().forEach(userGroup ->
            builder.withUserGroup(AcctGroupInfoMapper.fromDataLayerUserGroup(userGroup))
        );

        // Build
        return builder.build();
    }

}
