package com.desolatetimelines.acct.usermanagement.ws.mapper;

import com.desolatetimelines.acct.usermanagement.model.AcctUsersGroup;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUsersGroupCreationRequest;

/**
 * Provides mapping methods to and from the {@link AcctUsersGroupCreationRequest} type
 */
public class AcctUsersGroupCreationRequestsMapper {

    /**
     * Transforms the referenced {@link AcctUsersGroupCreationRequest creation request}
     * into a new {@link AcctUsersGroup data layer users group entity} having the
     * {@link AcctUsersGroup#getGroupUUID() group UUID} set to the given group UUID
     *
     * @param groupUUID       the given group UUID
     * @param creationRequest the given creation request
     */
    public static AcctUsersGroup toAcctUsersGroup(String groupUUID, AcctUsersGroupCreationRequest creationRequest) {
        return new AcctUsersGroup() {
            @Override
            public String getGroupUUID() {
                return groupUUID;
            }

            @Override
            public void setGroupUUID(String groupUUID) {
                throw new UnsupportedOperationException();
            }

            @Override
            public String getGroupName() {
                return creationRequest.groupName();
            }

            @Override
            public void setGroupName(String groupName) {
                throw new UnsupportedOperationException();
            }

            @Override
            public String getGroupDescription() {
                return creationRequest.groupDescription();
            }

            @Override
            public void setGroupDescription(String groupDescription) {
                throw new UnsupportedOperationException();
            }

            @Override
            public String getGroupIconUUID() {
                return creationRequest.groupIconUUID();
            }

            @Override
            public void setGroupIconUUID(String groupIconUUID) {
                throw new UnsupportedOperationException();
            }
        };
    }

}
