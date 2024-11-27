package com.desolatetimelines.acct.security.model;

/**
 * Record of a user or group's ownership of a report
 */
public interface AcctReportOwner {

    String getReportUUID();

    void setReportUUID(String reportUUID);

    OwnerType getOwnerType();

    void setOwnerType(OwnerType ownerType);

    String getOwnerUUID();

    void setOwnerUUID(String ownerUUID);

}
