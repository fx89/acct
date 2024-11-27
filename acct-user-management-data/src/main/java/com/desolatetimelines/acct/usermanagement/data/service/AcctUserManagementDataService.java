package com.desolatetimelines.acct.usermanagement.data.service;

import com.desolatetimelines.acct.usermanagement.data.model.AcctGroupDetails;
import com.desolatetimelines.acct.usermanagement.data.model.AcctUserCreationParameters;
import com.desolatetimelines.acct.usermanagement.data.model.AcctUserDetails;
import com.desolatetimelines.acct.usermanagement.data.model.AcctUserGroupCreationParameters;
import com.desolatetimelines.acct.usermanagement.model.AcctUser;
import com.desolatetimelines.acct.usermanagement.model.AcctUserGroupMapping;
import com.desolatetimelines.acct.usermanagement.model.AcctUsersGroup;
import com.desolatetimelines.acct.usermanagement.repository.AcctUserGroupMappingsRepository;
import com.desolatetimelines.acct.usermanagement.repository.AcctUserGroupsRepository;
import com.desolatetimelines.acct.usermanagement.repository.AcctUsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Facade for the data layer of the user management service, allowing for the
 * loading and persisting of user management data objects
 */
@Service
public class AcctUserManagementDataService {

    private final AcctUsersRepository usersRepository;

    private final AcctUserGroupsRepository userGroupsRepository;

    private final AcctUserGroupMappingsRepository userGroupMappingsRepository;

    public AcctUserManagementDataService(
        AcctUsersRepository userAccountsRepository,
        AcctUserGroupsRepository userGroupsRepository,
        AcctUserGroupMappingsRepository userGroupMappingsRepository
    ) {
        this.usersRepository = userAccountsRepository;
        this.userGroupsRepository = userGroupsRepository;
        this.userGroupMappingsRepository = userGroupMappingsRepository;
    }

    /**
     * Retrieves {@link AcctUserDetails the details of the user} having the given user UUID.
     * If there is no such user, an empty Optional is returned.
     *
     * @param userUUID the given userUUID
     */
    public Optional<AcctUserDetails> retrieveUserDetailsByUserUUID(String userUUID) {
        return
            usersRepository
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
            usersRepository
                .findUserAccountByUserLoginName(userLoginName)
                .map(this::enrichUserAccountWithUsersGroups);
    }

    /**
     * Creates a user with the given parameters
     *
     * @param userCreationParameters container for the given parameters
     * @return the created user
     */
    public AcctUser createUser(AcctUserCreationParameters userCreationParameters) {
        // Create the user entity
        final AcctUser acctUser = usersRepository.createNew();

        // Set the properties of the user entity
        acctUser.setUserUUID(userCreationParameters.userUUID());
        acctUser.setUserName(userCreationParameters.userName());
        acctUser.setUserLoginName(userCreationParameters.userLoginName());
        acctUser.setUserEncryptedPassword(userCreationParameters.userEncryptedPassword());
        acctUser.setUserIconUUID(userCreationParameters.userIconUUID());
        acctUser.setDefaultWorkspaceUUID(userCreationParameters.defaultWorkspaceUUID());

        // Persist the user entity and return the UUID of the newly created user
        return usersRepository.save(acctUser);
    }

    /**
     * Returns the user with the given user UUID or an empty optional if such a user does not exist
     *
     * @param userUUID the given user UUID
     */
    public Optional<AcctUser> findUserByUserUUID(String userUUID) {
        return usersRepository.findUserAccountByUserUUID(userUUID);
    }

    /**
     * Saves the referenced user
     *
     * @param acctUser the referenced user
     * @return a reference to the persisted entity
     */
    public AcctUser saveUser(AcctUser acctUser) {
        return usersRepository.save(acctUser);
    }

    /**
     * Retrieves a users group having the given group UUID or returns an empty optional
     *
     * @param groupUUID the given group UUID
     */
    public Optional<AcctUsersGroup> findUsersGroupByGroupUUID(String groupUUID) {
        return userGroupsRepository.findFirstByGroupUUID(groupUUID);
    }

    /**
     * Creates a users group with the given details
     *
     * @param usersGroupCreationParameters container for the given parameters
     * @return a new reference to the saved object
     */
    public AcctUsersGroup createUsersGroup(AcctUserGroupCreationParameters usersGroupCreationParameters) {
        // Create the users group
        final AcctUsersGroup newGroup = userGroupsRepository.createNew();

        // Set the properties of the users group
        newGroup.setGroupUUID(usersGroupCreationParameters.groupUUID());
        newGroup.setGroupName(usersGroupCreationParameters.groupName());
        newGroup.setGroupDescription(usersGroupCreationParameters.groupDescription());
        newGroup.setGroupIconUUID(usersGroupCreationParameters.groupIconUUID());

        // Save the users group and return a reference
        return userGroupsRepository.save(newGroup);
    }

    /**
     * Creates a user / group mapping for the referenced user and group
     *
     * @param user       the referenced user
     * @param usersGroup the referenced group
     */
    public void createUserGroupMapping(AcctUser user, AcctUsersGroup usersGroup) {
        // Create the entity
        final AcctUserGroupMapping userGroupMapping = userGroupMappingsRepository.createNew();

        // Set the properties
        userGroupMapping.setUser(user);
        userGroupMapping.setGroup(usersGroup);

        // Save
        userGroupMappingsRepository.save(userGroupMapping);
    }

    /**
     * Removes the user / group mapping identified by the referenced user and group
     *
     * @param user       the referenced user
     * @param usersGroup the referenced group
     */
    public void deleteUserGroupMapping(AcctUser user, AcctUsersGroup usersGroup) {
        userGroupMappingsRepository.deleteByUserUUIDAndGroupUUID(user.getUserUUID(), usersGroup.getGroupUUID());
    }

    /**
     * Retrieves a set of {@link AcctGroupDetails groups} mapped to the user
     * identified by the given user UUID
     *
     * @param userUUID the given user UUID
     */
    public Set<AcctGroupDetails> findGroupsForUser(String userUUID) {
        return
            userGroupMappingsRepository.findAllByUserUserUUID(userUUID)
                .stream()
                .map(AcctUserGroupMapping::getGroup)
                .map(AcctUserManagementDataService::mapAcctUsersGroupToAcctGroupDetails)
                .collect(Collectors.toSet());
    }

    private AcctUserDetails enrichUserAccountWithUsersGroups(AcctUser userAccount) {
        return
            AcctUserDetails.builder()
                .withUserAccount(userAccount)
                .withUserGroups(userGroupsRepository.findUserGroupByUserUUID(userAccount.getUserUUID()))
                .build();
    }

    private static AcctGroupDetails mapAcctUsersGroupToAcctGroupDetails(AcctUsersGroup acctUserGroup) {
        return
            AcctGroupDetails.builder()
                .withGroupUUID(acctUserGroup.getGroupUUID())
                .withGroupName(acctUserGroup.getGroupName())
                .withGroupDescription(acctUserGroup.getGroupDescription())
                .withGroupIconUUID(acctUserGroup.getGroupIconUUID())
                .build();
    }
}
