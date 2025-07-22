package com.desolatetimelines.acct.catalog.model;

/**
 * Uniquely identifies an icon in the ACCT catalog
 */
public interface AcctIcon {

    String getIconUUID();

    void setIconUUID(String iconUUID);

    String getIconName();

    void setIconName(String iconName);

    String getMimeType();

    void setMimeType(String mimeType);

    String getIconBytesBase64();

    void setIconBytesBase64(String iconBytesBase64);

    AcctIconCategory getIconCategory();

    void setIconCategory(AcctIconCategory category);

}
