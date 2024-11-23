package com.desolatetimelines.acct.usage.model;

/**
 * Represents the mapping between a service and a used item type
 */
public interface AcctUsedItemType {
    AcctService getService();

    void setService(AcctService service);

    String getUsedItemTypeName();

    void setUsedItemTypeName(String usedItemTypeName);
}
