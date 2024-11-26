package com.desolatetimelines.acct.usermanagement.service;

import com.desolatetimelines.acct.common.ObjectTypes;
import com.desolatetimelines.acct.usage.ws.client.RESTUsageEndpointClient;
import com.desolatetimelines.acct.usage.ws.model.ServiceItemTypesList;
import com.desolatetimelines.acct.usermanagement.data.model.AcctUserCreationParameters;
import com.desolatetimelines.acct.usermanagement.data.model.AcctUserDetails;
import com.desolatetimelines.acct.usermanagement.data.model.AcctUserGroupCreationParameters;
import com.desolatetimelines.acct.usermanagement.data.service.AcctUserManagementDataService;
import com.desolatetimelines.acct.usermanagement.exception.AcctUserManagementNotFoundException;
import com.desolatetimelines.acct.usermanagement.model.AcctUser;
import com.desolatetimelines.acct.usermanagement.model.AcctUsersGroup;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
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
