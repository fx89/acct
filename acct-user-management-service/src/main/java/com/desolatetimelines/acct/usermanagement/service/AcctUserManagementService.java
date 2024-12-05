package com.desolatetimelines.acct.usermanagement.service;

import com.desolatetimelines.acct.common.ObjectTypes;
import com.desolatetimelines.acct.usage.ws.client.RESTUsageEndpointClient;
import com.desolatetimelines.acct.usage.ws.model.ServiceItemTypesList;
import com.desolatetimelines.acct.usermanagement.data.model.AcctGroupDetails;
import com.desolatetimelines.acct.usermanagement.data.model.AcctUserCreationParameters;
import com.desolatetimelines.acct.usermanagement.data.model.AcctUserDetails;
import com.desolatetimelines.acct.usermanagement.data.model.AcctUserGroupCreationParameters;
import com.desolatetimelines.acct.usermanagement.data.service.AcctUserManagementDataService;
import com.desolatetimelines.acct.usermanagement.exception.AcctUserManagementBadParameterException;
import com.desolatetimelines.acct.usermanagement.exception.AcctUserManagementNotFoundException;
import com.desolatetimelines.acct.usermanagement.model.AcctUser;
import com.desolatetimelines.acct.usermanagement.model.AcctUsersGroup;
import com.desolatetimelines.acct.usermanagement.model.Page;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * Account Management services layer facade
 */
@Service
public class AcctUserManagementService {

    private final AcctUserManagementDataService dataService;

    private final UsersGroupSupplier usersGroupSupplier;

    private final RESTUsageEndpointClient usageEndpointClient;

    private final String applicationName;

    private final String contextPath;

    public AcctUserManagementService(
        AcctUserManagementDataService dataService,
        RESTUsageEndpointClient usageEndpointClient,
        @Value("${USER_MANAGEMENT_APPLICATION_NAME}") String applicationName,
        @Value("${USER_MANAGEMENT_SERVER_CONTEXT_PATH}") String contextPath
    ) {
        this.dataService = dataService;
        this.usersGroupSupplier = new UsersGroupSupplier(dataService);
        this.usageEndpointClient = usageEndpointClient;
        this.applicationName = applicationName;
        this.contextPath = contextPath;
    }

    /**
     * Registers in-use item types with the usage service upon startup
     */
    @SuppressWarnings("unused")
    @EventListener(ApplicationReadyEvent.class)
    protected void registerInUseObjectTypes() {
        usageEndpointClient.registerItemTypesForService(
            ServiceItemTypesList.builder()
                .withServiceName(applicationName)
                .withServiceContextPath(contextPath)
                .withItemType(List.of(
                    ObjectTypes.ICON.name(),
                    ObjectTypes.WORKSPACE.name()
                ))
                .build()
        );
    }

    /**
     * Find the user with the given login name and return the details, or throw an exception
     *
     * @param userLoginName the given login name
     */
    public AcctUserDetails findUserDetailsByUserLoginName(String userLoginName) {
        return
            dataService.retrieveUserDetailsByUserLoginName(userLoginName)
                .orElseThrow(() -> new AcctUserManagementNotFoundException("User not found"));
    }

    /**
     * Find the user with the given UUID and return the details, or throw an exception
     *
     * @param userUUID the given UUID
     */
    public AcctUserDetails findUserDetailsByUserUserUUID(String userUUID) {
        return
            dataService.retrieveUserDetailsByUserUUID(userUUID)
                .orElseThrow(() -> new AcctUserManagementNotFoundException("User not found"));
    }

    /**
     * Creates a user with the given parameters and maps it to the Users group
     *
     * @param userCreationParametersBuilder builder for the container of the given parameters
     * @return the UUID of the newly created user
     */
    @Transactional
    public String createUser(AcctUserCreationParameters.UserCreationParametersBuilder userCreationParametersBuilder) {
        // Create the user
        final AcctUser createdUser =
            dataService.createUser(
                userCreationParametersBuilder
                    .withUserUUID(UUID.randomUUID().toString())
                    .build()
            );

        // Get a reference to the "Users" group
        final AcctUsersGroup usersGroup = usersGroupSupplier.get();

        // Map the created user to the "Users" group
        dataService.createUserGroupMapping(createdUser, usersGroup);

        // Return the UUID of the created user
        return createdUser.getUserUUID();
    }

    /**
     * Sets the encrypted password of the user identified by the given user UUID
     * to the given encrypted password
     *
     * @param userUUID                 the given user UUID
     * @param newUserEncryptedPassword the given encrypted password
     */
    public void setUserPassword(String userUUID, String newUserEncryptedPassword) {
        // Get the user. If the user does not exist, throw a "not found" exception.
        final AcctUser acctUser =
            dataService.findUserByUserUUID(userUUID)
                .orElseThrow(() -> new AcctUserManagementNotFoundException("User not found"));

        // Set the user's encrypted password
        acctUser.setUserEncryptedPassword(newUserEncryptedPassword);

        // Save the user
        dataService.saveUser(acctUser);
    }

    /**
     * Returns a set of {@link AcctGroupDetails entities} containing the details of all
     * the groups mapped to the user defined by the given user UUID
     *
     * @param userUUID the given user UUID
     */
    public Set<AcctGroupDetails> getUserGroups(String userUUID) {
        return dataService.findGroupsForUser(userUUID);
    }

    /**
     * Sets the {@code softDeleted} flag to {@code true} for the user
     * identified by the given user UUID
     *
     * @param userUUID the given user UUID
     */
    @Transactional
    public void softDeleteUserByUserUUID(String userUUID) {
        setSoftDeletedFlagForUserWithUserUUID(userUUID, true);
    }

    /**
     * Sets the {@code softDeleted} flag to {@code false} for the user
     * identified by the given user UUID
     *
     * @param userUUID the given user UUID
     */
    @Transactional
    public void undeleteUserByUserUUID(String userUUID) {
        setSoftDeletedFlagForUserWithUserUUID(userUUID, false);
    }

    /**
     * Sets the {@code softDeleted} flag to the given value for the user
     * identified by the given user UUID
     *
     * @param userUUID    the given user UUID
     * @param softDeleted the given value
     */
    private void setSoftDeletedFlagForUserWithUserUUID(String userUUID, boolean softDeleted) {
        // Find the user or throw an exception
        final AcctUser acctUser =
            dataService.findUserByUserUUID(userUUID)
                .orElseThrow(() -> new AcctUserManagementNotFoundException("User not found"));

        // Set the softDeleted flag to true
        acctUser.setSoftDeleted(softDeleted);

        // Save the user
        dataService.saveUser(acctUser);
    }

    /**
     * Returns a page of {@link AcctGroupDetails group records} having the given size and number,
     * for the groups for which the {@link AcctGroupDetails#groupName()}  group name} matches
     * the given pattern
     *
     * @param pattern    the given pattern
     * @param pageNumber the given number
     * @param pageSize   the given size
     */
    public Page<AcctUsersGroup> findGroupsByNamePattern(String pattern, int pageNumber, int pageSize) {
        // Verify the request
        verifyPageSearchRequest(pattern, pageNumber, pageSize);

        // If the parameters check out, run the operation
        return dataService.findGroupsByGroupNameLike(pattern, pageNumber, pageSize);
    }

    /**
     * Returns a page of user with the given number and of the given size,
     * containing user for which either the login name or human-readable
     * name contains the given pattern
     *
     * @param pattern    the given pattern
     * @param pageNumber the given number
     * @param pageSize   the given size
     */
    public Page<AcctUser> findUsersByNameOrLoginNamePattern(String pattern, int pageNumber, int pageSize) {
        // Verify the request
        verifyPageSearchRequest(pattern, pageNumber, pageSize);

        // If the parameters check out, run the operation
        return dataService.findUsersByUserLoginNameLikeOrUserNameLike(pattern, pageNumber, pageSize);
    }

    private static void verifyPageSearchRequest(String pattern, int pageNumber, int pageSize) {
        // Don't allow patterns smaller than 3 characters
        if (pattern == null || pattern.length() < 3) {
            throw new AcctUserManagementBadParameterException(
                "The pattern must be at least 3 characters long"
            );
        }

        // Don't allow negative page numbers
        if (pageNumber < 0) {
            throw new AcctUserManagementBadParameterException("The page number must be positive");
        }

        // Don't allow negative, empty or ludicrously large page sizes
        if (pageSize < 1 || pageSize > 200) {
            throw new AcctUserManagementBadParameterException(
                "The page size must be between 1 and 200"
            );
        }
    }

    /**
     * Saves the given {@link AcctUsersGroup users group} and returns the group's UUID.
     * If the given users group does not have a {@link AcctUsersGroup#getGroupUUID() UUID},
     * a new users group is created for the details of the given users group.
     */
    public String saveUsersGroup(AcctUsersGroup acctUsersGroup) {
        // If the Group UUID was not provided, create a new group for the given details
        if (acctUsersGroup.getGroupUUID() == null) {
            return
                dataService.createUsersGroup(
                    AcctUserGroupCreationParameters.builder()
                        .withGroupUUID(UUID.randomUUID().toString())
                        .withGroupName(acctUsersGroup.getGroupName())
                        .withGroupDescription(acctUsersGroup.getGroupDescription())
                        .withGroupIconUUID(acctUsersGroup.getGroupIconUUID())
                        .build()
                ).getGroupUUID();
        }

        // If the Group UUID was provided, get the group, update it and save
        else {
            final AcctUsersGroup usersGroup =
                dataService.findUsersGroupByGroupUUID(acctUsersGroup.getGroupUUID())
                    .orElseThrow(() -> new AcctUserManagementNotFoundException(
                        "Users group not found"
                    ));

            usersGroup.setGroupName(acctUsersGroup.getGroupName());
            usersGroup.setGroupDescription(acctUsersGroup.getGroupDescription());
            usersGroup.setGroupIconUUID(acctUsersGroup.getGroupIconUUID());

            final AcctUsersGroup savedUsersGroup = dataService.saveUsersGroup(usersGroup);

            return savedUsersGroup.getGroupUUID();
        }
    }

    /**
     * Supplies a reference to the "Users" group while making sure the group is created if it doesn't exist
     */
    private static final class UsersGroupSupplier implements Supplier<AcctUsersGroup> {

        /**
         * This is the UUID of the group that all users are mapped to upon creation
         */
        private static final String USERS_GROUP_UUID = "06f916c8-97c8-4c59-ac17-f858b8e3a186";

        /**
         * This is the name of the group that all users are mapped to upon creation
         */
        private static final String USERS_GROUP_NAME = "Users";

        /**
         * This is the description of the group that all users are mapped to upon creation
         */
        private static final String USERS_GROUP_DESCRIPTION = "Group for registered users without special privileges";

        /**
         * The data service is used for resolving the reference to the "Users" group
         */
        private final AcctUserManagementDataService dataService;

        /**
         * This is the group that all users are mapped to upon creation
         */
        private AcctUsersGroup usersGroup;

        private UsersGroupSupplier(AcctUserManagementDataService dataService) {
            this.dataService = dataService;
        }

        @Override
        @Transactional
        public synchronized AcctUsersGroup get() {
            // If the usersGroup reference is not set, resolve it
            if (usersGroup == null) {
                usersGroup =
                    dataService
                        // If the usersGroup is already registered then get a reference
                        .findUsersGroupByGroupUUID(USERS_GROUP_UUID)

                        // If the usersGroup is not already registered
                        // then register it at this time
                        // and get a reference to the newly registered group
                        .orElseGet(() -> dataService.createUsersGroup(
                            AcctUserGroupCreationParameters.builder()
                                .withGroupUUID(USERS_GROUP_UUID)
                                .withGroupName(USERS_GROUP_NAME)
                                .withGroupDescription(USERS_GROUP_DESCRIPTION)
                                .build()
                        ));
            }

            // In any case, return the users group reference
            return usersGroup;
        }
    }

}
