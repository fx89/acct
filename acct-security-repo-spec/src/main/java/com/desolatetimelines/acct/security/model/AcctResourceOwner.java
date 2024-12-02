package com.desolatetimelines.acct.security.model;

/**
 * Specification for the generic resource owner entity. Regardless of the resource type,
 * this resource owner entity contains: <ul>
 * <li>a resource UUID that uniquely identifies the resource across the ACCT ecosystem</li>
 * <li>an owner UUID that uniquely identifies the owner across the ACCT ecosystem</li>
 * <li>an {@link OwnerType owner type} that identifies the type of owner that owns the resource</li>
 * </ul>
 */
public interface AcctResourceOwner {

    String getResourceUUID();

    void setResourceUUID(String reportUUID);

    OwnerType getOwnerType();

    void setOwnerType(OwnerType ownerType);

    String getOwnerUUID();

    void setOwnerUUID(String ownerUUID);

}
