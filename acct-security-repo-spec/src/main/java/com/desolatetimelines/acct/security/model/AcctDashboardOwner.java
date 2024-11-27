package com.desolatetimelines.acct.security.model;

/**
 * Record of a user or group's ownership of a dashboard
 */
public interface AcctDashboardOwner {

    String getDashboardUUID();

    void setDashboardUUID(String dashboardUUID);

    OwnerType getOwnerType();

    void setOwnerType(OwnerType ownerType);

    String getOwnerUUID();

    void setOwnerUUID(String ownerUUID);

}
