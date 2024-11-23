package com.desolatetimelines.acct.security.model;

/**
 * Maps a privilege name to a group UUID
 */
public interface AcctGroupPrivilege {

    String getGroupUUID();

    void setGroupUUID(String groupUUID);

    String getPrivilegeName();

    void setPrivilegeName(String privilegeName);

}
