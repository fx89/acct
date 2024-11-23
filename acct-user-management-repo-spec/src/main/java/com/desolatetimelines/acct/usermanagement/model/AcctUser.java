package com.desolatetimelines.acct.usermanagement.model;

import java.time.Instant;

/**
 * Uniquely identifies a user account in the ACCT ecosystem
 */
public interface AcctUser {

    String getUserUUID();

    void setUserUUID(String userUUID);

    String getUserLoginName();

    void setUserLoginName(String userLoginName);

    String getUserEncryptedPassword();

    void setUserEncryptedPassword(String userEncryptedPassword);

    Instant getUserPasswordExpiryDate();

    void setUserPasswordExpiryDate(Instant userPasswordExpiryDate);

    String getUserName();

    void setUserName(String userName);

    String getUserIconUUID();

    void setUserIconUUID(String userIconUUID);

    String getDefaultWorkspaceUUID();

    void setDefaultWorkspaceUUID(String defaultWorkspaceUUID);

    Boolean getSoftDeleted();

    void setSoftDeleted(Boolean softDeleted);
}


