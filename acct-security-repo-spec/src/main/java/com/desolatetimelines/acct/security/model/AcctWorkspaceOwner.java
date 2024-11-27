package com.desolatetimelines.acct.security.model;

/**
 * Record of a user or group's ownership of a workspace
 */
public interface AcctWorkspaceOwner {

    String getWorkspaceUUID();

    void setWorkspaceUUID(String workspaceUUID);

    OwnerType getOwnerType();

    void setOwnerType(OwnerType ownerType);

    String getOwnerUUID();

    void setOwnerUUID(String ownerUUID);

}
