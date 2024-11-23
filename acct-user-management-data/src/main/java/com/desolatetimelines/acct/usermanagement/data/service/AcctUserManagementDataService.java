package com.desolatetimelines.acct.usermanagement.data.service;

import com.desolatetimelines.acct.usermanagement.data.model.AcctUserDetails;
import com.desolatetimelines.acct.usermanagement.model.AcctUser;
import com.desolatetimelines.acct.usermanagement.repository.UserAccountsRepository;
import com.desolatetimelines.acct.usermanagement.repository.UserGroupsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Facade for the data layer of the user management service, allowing for the
 * loading and persisting of user management data objects
 */
@Service
public class AcctUserManagementDataService {

    private final UserAccountsRepository userAccountsRepository;

    private final UserGroupsRepository userGroupsRepository;

    public AcctUserManagementDataService(
        UserAccountsRepository userAccountsRepository,
        UserGroupsRepository userGroupsRepository
    ) {
        this.userAccountsRepository = userAccountsRepository;
        this.userGroupsRepository = userGroupsRepository;
    }

    /**
     * Retrieves {@link AcctUserDetails the details of the user} having the given user UUID.
     * If there is no such user, an empty Optional is returned.
     *
     * @param userUUID the given userUUID
     */
    public Optional<AcctUserDetails> retrieveUserDetailsByUserUUID(String userUUID) {
        return
            userAccountsRepository
                .findUserAccountByUserUUID(userUUID)
                .map(this::enrichUserAccountWithUsersGroups);
    }

    /**
     * Retrieves {@link AcctUserDetails the details of the user} having the given user login name.
     * If there is no such user, an empty Optional is returned.
     *
     * @param userLoginName the given user login name
     */
    public Optional<AcctUserDetails> retrieveUserDetailsByUserLoginName(String userLoginName) {
        return
            userAccountsRepository
                .findUserAccountByUserLoginName(userLoginName)
                .map(this::enrichUserAccountWithUsersGroups);
    }

    private AcctUserDetails enrichUserAccountWithUsersGroups(AcctUser userAccount) {
        return
            AcctUserDetails.builder()
                .withUserAccount(userAccount)
                .withUserGroups(userGroupsRepository.findUserGroupByUserUUID(userAccount.getUserUUID()))
                .build();
    }
}
