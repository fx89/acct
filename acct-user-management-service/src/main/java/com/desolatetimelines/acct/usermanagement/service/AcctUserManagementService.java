package com.desolatetimelines.acct.usermanagement.service;

import com.desolatetimelines.acct.common.model.ObjectTypes;
import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.common.utils.Streams;
import com.desolatetimelines.acct.security.ws.client.RESTWorkspaceOwnershipEndpointClient;
import com.desolatetimelines.acct.security.ws.endpoint.model.WorkspaceAccessibilityReport;
import com.desolatetimelines.acct.usage.ws.client.RESTUsageEndpointClient;
import com.desolatetimelines.acct.usage.ws.model.ServiceItemTypesList;
import com.desolatetimelines.acct.usermanagement.data.model.AcctGroupDetails;
import com.desolatetimelines.acct.usermanagement.data.model.AcctUserCreationParameters;
import com.desolatetimelines.acct.usermanagement.data.model.AcctUserDetails;
import com.desolatetimelines.acct.usermanagement.data.model.AcctUserGroupCreationParameters;
import com.desolatetimelines.acct.usermanagement.data.service.AcctUserManagementDataService;
import com.desolatetimelines.acct.usermanagement.exception.AcctUserManagementBadParameterException;
import com.desolatetimelines.acct.usermanagement.exception.AcctUserManagementIllegalAccessException;
import com.desolatetimelines.acct.usermanagement.exception.AcctUserManagementNotFoundException;
import com.desolatetimelines.acct.usermanagement.model.AcctUser;
import com.desolatetimelines.acct.usermanagement.model.AcctUsersGroup;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.Supplier;

/**
 * Account Management services layer facade
 */
@Service
public class AcctUserManagementService {

    private final AcctUserManagementDataService dataService;

    private final UsersGroupSupplier usersGroupSupplier;

    private final RESTUsageEndpointClient usageEndpointClient;

    private final RESTWorkspaceOwnershipEndpointClient workspaceOwnershipEndpointClient;

    private final String applicationName;

    private final String contextPath;

    public AcctUserManagementService(
        AcctUserManagementDataService dataService,
        RESTUsageEndpointClient usageEndpointClient,
        RESTWorkspaceOwnershipEndpointClient workspaceOwnershipEndpointClient,
        @Value("${USER_MANAGEMENT_APPLICATION_NAME}") String applicationName,
        @Value("${USER_MANAGEMENT_SERVER_CONTEXT_PATH}") String contextPath
    ) {
        this.dataService = dataService;
        this.usersGroupSupplier = new UsersGroupSupplier(dataService);
        this.usageEndpointClient = usageEndpointClient;
        this.workspaceOwnershipEndpointClient = workspaceOwnershipEndpointClient;
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
     * Returns the UUIDs of any used items of the given type and that can be found in the given list
     *
     * @param objectType the given type
     * @param itemUUIDs  the given list
     */
    public Collection<String> getInUseItemUUIDs(String objectType, Collection<String> itemUUIDs) {
        // If the object type is ICON then search users and groups for used icons
        if (Objects.equals(objectType, ObjectTypes.ICON.name())) {
            return
                Streams.multiConcat(
                    dataService.findUsersByUserIconUUIDIn(itemUUIDs).stream().map(AcctUser::getUserIconUUID),
                    dataService.findUserGroupsByGroupIconUUIDIn(itemUUIDs).stream().map(AcctUsersGroup::getGroupIconUUID)
                ).toList();

        }

        // If the object type is WORKSPACE then search users used workspaces
        if (Objects.equals(objectType, ObjectTypes.WORKSPACE.name())) {
            return
                dataService.findUsersByWorkspaceUUIDIn(itemUUIDs)
                    .stream()
                    .map(AcctUser::getDefaultWorkspaceUUID)
                    .toList();
        }

        // If this point has been reached, it means that either the item type is not supported
        // or the code for handling the object type is missing from above
        throw new IllegalArgumentException("Object type [" + objectType + "] not supported");
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
    @Transactional
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
     * Sets the human-readable name of the user with the given user UUID to the given userName
     *
     * @param userUUID    the given user UUID
     * @param newUserName the given userName
     */
    @Transactional
    public void setUserName(String userUUID, String newUserName) {
        // Get the user. If the user does not exist, throw a "not found" exception.
        final AcctUser acctUser =
            dataService.findUserByUserUUID(userUUID)
                .orElseThrow(() -> new AcctUserManagementNotFoundException("User not found"));

        // Set the user's encrypted password
        acctUser.setUserName(newUserName);

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
        acctUser.setSoftDeletedDate(Instant.now());

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
     * Deletes the group with the given group UUID
     *
     * @param groupUUID the given group UUID
     */
    public void deleteUsersGroup(String groupUUID) {
        // Find the users group or throw an exception
        final AcctUsersGroup usersGroup =
            dataService.findUsersGroupByGroupUUID(groupUUID)
                .orElseThrow(() -> new AcctUserManagementNotFoundException(
                    "Group not found"
                ));

        // Delete the users group
        dataService.deleteUsersGroup(usersGroup);
    }

    /**
     * Sets the default workspace of the user with the given user UUID to the given workspace UUID
     *
     * @param userUUID      the given user UUID
     * @param workspaceUUID the given workspace UUID
     */
    public void setUserDefaultWorkspace(String userUUID, String workspaceUUID) {
        // Get the user or throw an exception
        final AcctUser user =
            dataService.findUserByUserUUID(userUUID)
                .orElseThrow(() -> new AcctUserManagementNotFoundException("User not found"));

        // Check if the workspace is accessible to the user
        final WorkspaceAccessibilityReport workspaceAccessibilityReport =
            workspaceOwnershipEndpointClient.isUserAccessibleWorkspace(userUUID, workspaceUUID);

        // If the workspace is not accessible to the user, throw an exception
        if (!workspaceAccessibilityReport.accessible()) {
            throw new AcctUserManagementIllegalAccessException(
                "User does not have access to the workspace"
            );
        }

        // Set the new default workspace
        user.setDefaultWorkspaceUUID(workspaceUUID);

        // Save the user
        dataService.saveUser(user);
    }

    /**
     * Returns a collection of {@link AcctUser users} that have been
     * {@link AcctUserManagementService#softDeleteUserByUserUUID(String) soft-deleted}
     * more than a given number of days ago
     *
     * @param nbrDaysAgo the given number of days
     */
    public Collection<AcctUser> findSoftDeletedUsersDeletedDaysAgo(int nbrDaysAgo) {
        // Compute the reference date
        final Instant referenceDate = Instant.now().minus(Duration.ofDays(nbrDaysAgo));

        // Get the users that have been soft-deleted before the reference date
        return dataService.findUsersBySoftDeletedTrueAndSoftDeletedDateLessThan(referenceDate);
    }

    /**
     * Deletes the users in the given collection. This differs from the
     * {@link AcctUserManagementService#softDeleteUserByUserUUID(String) soft-deletion}
     * in that, in this case, the users are actually removed from the database
     *
     * @param users the given collection
     */
    public void permanentlyDeleteUsers(Collection<AcctUser> users) {
        dataService.deleteUsers(users);
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
