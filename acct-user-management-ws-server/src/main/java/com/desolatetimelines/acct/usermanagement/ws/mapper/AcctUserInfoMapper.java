package com.desolatetimelines.acct.usermanagement.ws.mapper;

import com.desolatetimelines.acct.usermanagement.model.AcctUser;
import com.desolatetimelines.acct.usermanagement.ws.model.AcctUserInfo;

/**
 * Provide mapper methods to and from the {@link AcctUserInfo} type
 */
public class AcctUserInfoMapper {

    public static AcctUserInfo fromAcctUser(AcctUser acctUser) {
        return
            new AcctUserInfo(
                acctUser.getUserUUID(),
                acctUser.getUserName(),
                acctUser.getUserLoginName()
            );
    }

}
