package com.desolatetimelines.acct.usermanagement.model;

/**
 * Uniquely identifies a group of users within the ACCT ecosystem
 */
public interface AcctUsersGroup {

    String getGroupUUID();

    void setGroupUUID(String groupUUID);

     String getGroupName();

    void setGroupName(String groupName);

    String getGroupDescription();

    void setGroupDescription(String groupDescription);

    String getGroupIconUUID();

    void setGroupIconUUID(String groupIconUUID);
}
