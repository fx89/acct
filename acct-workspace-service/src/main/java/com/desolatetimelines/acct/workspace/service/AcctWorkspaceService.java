package com.desolatetimelines.acct.workspace.service;

import com.desolatetimelines.acct.common.model.ObjectTypes;
import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.security.client.data.AcctSecurityClientService;
import com.desolatetimelines.acct.security.client.model.UserResourceAccessRights;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnedWorkspacesGroup;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;
import com.desolatetimelines.acct.security.ws.endpoint.model.WorkspaceOwner;
import com.desolatetimelines.acct.usage.ws.client.RESTUsageEndpointClient;
import com.desolatetimelines.acct.usage.ws.model.ServiceItemTypesList;
import com.desolatetimelines.acct.workspace.AccountRecordExtendedDetailsMapper;
import com.desolatetimelines.acct.workspace.data.service.AcctWorkspaceDataService;
import com.desolatetimelines.acct.workspace.exception.*;
import com.desolatetimelines.acct.workspace.model.*;
import com.desolatetimelines.acct.workspace.privilegesprovider.model.WorkspaceServiceOperation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

import static com.desolatetimelines.acct.common.model.CommonUUIDs.INCOME_OR_EXPENSE_ITEM_UUID_FOR_DEPOSIT;
import static com.desolatetimelines.acct.common.model.CommonUUIDs.INCOME_OR_EXPENSE_ITEM_UUID_FOR_TRANSFER;
import static com.desolatetimelines.acct.security.client.model.ResourceType.WORKSPACE;
import static com.desolatetimelines.acct.workspace.privilegesprovider.model.ResourceOwnership.*;
import static com.desolatetimelines.acct.workspace.privilegesprovider.model.WorkspacePrivilegeIds.*;
import static com.desolatetimelines.acct.workspace.privilegesprovider.model.WorkspaceServiceOperation.*;
import static java.util.Collections.emptyList;

/**
 * Workspace services layer
 */
@Service
public class AcctWorkspaceService {

    private static final String ACCT_REC_TEXT_TRANSFER = "Transfer";

    private static final String ACCT_REC_TEXT_DEPOSIT_CREATION = "Deposit creation";

    private final RESTUsageEndpointClient usageEndpointClient;

    private final AcctSecurityClientService securityClientService;

    private final AcctWorkspaceErrorCodesRegistryService errors;

    private final AcctWorkspaceDataService dataService;

    private final String applicationName;

    private final String contextPath;

    public AcctWorkspaceService(
        RESTUsageEndpointClient usageEndpointClient,
        AcctSecurityClientService securityClientService,
        AcctWorkspaceErrorCodesRegistryService errors,
        AcctWorkspaceDataService dataService,
        @Value("${WORKSPACE_APPLICATION_NAME}") String applicationName,
        @Value("${WORKSPACE_SERVER_CONTEXT_PATH}") String contextPath
    ) {
        this.usageEndpointClient = usageEndpointClient;
        this.securityClientService = securityClientService;
        this.errors = errors;
        this.dataService = dataService;
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
                    ObjectTypes.BANK.name(),
                    ObjectTypes.CURRENCY.name(),
                    ObjectTypes.USER.name(),
                    ObjectTypes.INCOME_OR_EXPENSE_ITEM.name()
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
        // If the object type is ICON then search workspaces and accounts for used icons
        if (Objects.equals(objectType, ObjectTypes.ICON.name())) {
            throw new UnsupportedOperationException("Not implemented"); // TODO: work here
        }

        // If the object type is BANK then search accounts and deposits for banks
        if (Objects.equals(objectType, ObjectTypes.BANK.name())) {
            throw new UnsupportedOperationException("Not implemented"); // TODO: work here
        }

        // If the object type is CURRENCY then search workspaces, accounts, and deposits for currencies
        if (Objects.equals(objectType, ObjectTypes.CURRENCY.name())) {
            throw new UnsupportedOperationException("Not implemented"); // TODO: work here
        }

        // If the object type is USER then search account records for users
        if (Objects.equals(objectType, ObjectTypes.USER.name())) {
            throw new UnsupportedOperationException("Not implemented"); // TODO: work here
        }

        // If the object type is INCOME_OR_EXPENSE_ITEM then search account records for income or expense items
        if (Objects.equals(objectType, ObjectTypes.INCOME_OR_EXPENSE_ITEM.name())) {
            throw new UnsupportedOperationException("Not implemented"); // TODO: work here
        }

        // If this point has been reached, it means that either the item type is not supported
        // or the code for handling the object type is missing from above
        throw new IllegalArgumentException("Object type [" + objectType + "] not supported");
    }

    /**
     * Saves the workspace data present in the given workspace details. If the given workspace
     * details are missing the {@link WorkspaceDetails#workspaceUUID() workspace UUID} then
     * a new workspace is created for the user for the given user UUID. If the workspace UUID
     * is not missing then the workspace identified by the workspace UUID is updated, given
     * that the user with the given user UUID has the proper access rights.
     *
     * @param workspaceDetails the given workspace details
     * @param userUUID         the given user UUID
     * @return a reference to the created or updated workspace entity
     */
    @Transactional
    public AcctWorkspace saveWorkspace(
        String userUUID,
        WorkspaceDetails workspaceDetails,
        Collection<String> assignedPrivilegeNames
    ) {
        // If the workspace UUID was provided then get the workspace for the user
        // If the workspace UUID was not provided then create a new workspace
        final AcctWorkspace workspace =
            Optional.ofNullable(workspaceDetails.workspaceUUID())
                .map(workspaceUUID ->
                    findWorkspaceForUserAndOperation(
                        SAVE, userUUID, workspaceDetails.workspaceUUID(), assignedPrivilegeNames
                    )
                )
                .orElseGet(this::createNewWorkspace);

        // Update the workspace details
        workspace.setWorkspaceName(workspaceDetails.workspaceName());
        workspace.setWorkspaceDescription(workspaceDetails.workspaceDescription());
        workspace.setWorkspaceIconUUID(workspaceDetails.workspaceIconUUID());
        workspace.setDefaultCurrencyUUID(workspaceDetails.defaultCurrencyUUID());

        // Save the workspace
        final AcctWorkspace savedWorkspace = dataService.saveWorkspace(workspace);

        // If this is a new workspace, set the workspace ownership
        if (workspaceDetails.workspaceUUID() == null) {
            securityClientService.addWorkspaceOwner(
                WorkspaceOwner.builder()
                    .withOwnerType(OwnerType.USER)
                    .withOwnerUUID(userUUID)
                    .withWorkspaceUUID(savedWorkspace.getWorkspaceUUID())
                    .build()
            );
        }

        // Return a reference to the saved workspace
        return savedWorkspace;
    }

    /**
     * Deletes the workspace with the given workspace UUID, as long as it is accessible for deletion
     * by the user with the given user UUID, which is determined in part by the given privileges
     *
     * @param userUUID               the given user UUID
     * @param assignedPrivilegeNames the given privileges
     * @param workspaceUUID          the given workspace UUID
     */
    @Transactional
    public void deleteWorkspace(String userUUID, Collection<String> assignedPrivilegeNames, String workspaceUUID) {
        // Find the workspace. Throw an exception if the workspace is not accessible to the uer for the delete operation
        // or if the workspace is not found.
        final AcctWorkspace workspace =
            findWorkspaceForUserAndOperation(DELETE, userUUID, workspaceUUID, assignedPrivilegeNames);

        // Delete the workspace
        dataService.deleteWorkspace(workspace);

        // Remove the ownership record
        securityClientService.deleteWorkspaceOwner(
            WorkspaceOwner.builder()
                .withOwnerType(OwnerType.USER)
                .withOwnerUUID(userUUID)
                .withWorkspaceUUID(workspaceUUID)
                .build()
        );
    }

    /**
     * Returns the details of the workspaces accessible to the user with the given user UUID,
     * grouped by the accessibility mode. If the given list of user privilege names is missing
     * the privilege required for a given accessibility mode then the collection of workspaces
     * for that accessibility mode will be empty, even if there are user-reachable workspaces
     * within that group.
     *
     * @param userUUID               the given user UUID
     * @param assignedPrivilegeNames the given list of user privilege names
     */
    public AcctWorkspacesByOwnership retrieveUserAccessibleWorkspaces(
        String userUUID,
        Collection<String> assignedPrivilegeNames
    ) {
        // Get user-accessible workspace UUIDs
        final OwnedWorkspacesGroup workspaceUUIDs = securityClientService.getUserAccessibleWorkspaces(userUUID);

        // Combine the workspace UUID lists into one, while also taking the user's privileges into account...

        // Initialize the resulting collection
        final Collection<String> accessibleWorkspaceUUIDs =
            new ArrayList<>(
                workspaceUUIDs.userWorkspaces().size() +
                    workspaceUUIDs.groupWorkspaces().size() +
                    workspaceUUIDs.publicWorkspaces().size()
            );

        // Add workspace UUIDs to the list based on the user's privileges

        // The user must have the right to read own workspaces for the user workspaces to be returned
        if (assignedPrivilegeNames.contains(WORKSPACES_READ_OWN)) {
            accessibleWorkspaceUUIDs.addAll(workspaceUUIDs.userWorkspaces());
        }

        // The user must have the right to read group workspaces for the group workspaces to be returned
        if (assignedPrivilegeNames.contains(WORKSPACES_READ_GROUP)) {
            accessibleWorkspaceUUIDs.addAll(workspaceUUIDs.groupWorkspaces());
        }

        // The user may read public workspaces even without any special rights
        accessibleWorkspaceUUIDs.addAll(workspaceUUIDs.publicWorkspaces());

        // Retrieve the details of the workspaces in the previously-compiled list
        final Collection<AcctWorkspace> workspaces =
            dataService.findWorkspacesByWorkspaceUUIDIn(accessibleWorkspaceUUIDs);

        // Create response object, retrieve the workspaces, map and return
        return
            AcctWorkspacesByOwnership.builder()
                // User workspaces are the ones whose UUIDs are contained in the user workspace UUIDs list
                .withUserWorkspaces(
                    workspaces.stream()
                        .filter(workspace -> workspaceUUIDs.userWorkspaces().contains(workspace.getWorkspaceUUID()))
                        .toList()
                )
                // Group workspaces are the ones whose UUIDs are contained in the group workspace UUIDs list
                .withGroupWorkspaces(
                    workspaces.stream()
                        .filter(workspace -> workspaceUUIDs.groupWorkspaces().contains(workspace.getWorkspaceUUID()))
                        .toList()
                )
                // Public workspaces are the ones whose UUIDs are contained in the public workspace UUIDs list
                .withPublicWorkspaces(
                    workspaces.stream()
                        .filter(workspace -> workspaceUUIDs.publicWorkspaces().contains(workspace.getWorkspaceUUID()))
                        .toList()
                )
                .build();
    }

    /**
     * Retrieves a collection of workspaces that are directly owned by the user with the given user UUID
     *
     * @param userUUID the given user UUID
     */
    public Collection<AcctWorkspace> retrieveWorkspacesOwnedByUser(String userUUID) {
        // Get a collection of UUIDs for the workspaces owned by the user from the security service
        final Collection<String> workspaceUUIDs =
            securityClientService.getWorkspacesOwnedByOwnerOfType(OwnerType.USER, userUUID);

        // Retrieve and return the workspaces with the aforementioned UUIDs from the data store
        return dataService.findWorkspacesByWorkspaceUUIDIn(workspaceUUIDs);
    }

    /**
     * Retrieves a collection of workspaces that are owned by the group with the given group UUID
     *
     * @param groupUUID the given group UUID
     */
    public Collection<AcctWorkspace> retrieveWorkspacesOwnedByGroup(String groupUUID) {
        // Get a collection of UUIDs for the workspaces owned by the group from the security service
        final Collection<String> workspaceUUIDs =
            securityClientService.getWorkspacesOwnedByOwnerOfType(OwnerType.GROUP, groupUUID);

        // Retrieve and return the workspaces with the aforementioned UUIDs from the data store
        return dataService.findWorkspacesByWorkspaceUUIDIn(workspaceUUIDs);
    }

    /**
     * Persists the given {@link AccountDetails account details} in the workspace with the give workspace
     * UUID. If the given account details do not contain an {@link AccountDetails#accountUUID() account UUID}
     * then a new account is created for the given details. If the UUID is not missing then the account that's
     * identified by the given account UUID is updated. Workspace accessibility is decided based on the given
     * user UUID and the given collection of privileges.
     *
     * @param userUUID               the given user UUID
     * @param workspaceUUID          the given workspace UUID
     * @param accountDetails         the given account details
     * @param assignedPrivilegeNames the given collection of privileges
     * @return the UUID of the account for which the details have been persisted
     */
    @Transactional
    public String saveAccount(
        String userUUID,
        String workspaceUUID,
        AccountDetails accountDetails,
        Collection<String> assignedPrivilegeNames
    ) {
        // Retrieve the workspace for writing (altering accounts within the workspace means modifying the workspace)
        final AcctWorkspace workspace =
            findWorkspaceForUserAndOperation(SAVE, userUUID, workspaceUUID, assignedPrivilegeNames);

        // Get a reference to the account (either an existing account, if the account UUID was provided,
        // or a new account, if the account UUID was not provided)
        final AcctAccount account =
            Optional.ofNullable(accountDetails.accountUUID())
                .map(accountUUID -> findAccountByAccountUUIDForWorkspace(workspace, accountUUID))
                .orElseGet(() -> createNewAccountWithinWorkspace(workspace));

        // Set the account's properties
        account.setAccountName(accountDetails.accountName());
        account.setAccountNumber(accountDetails.accountNumber());
        account.setAccountIconUUID(accountDetails.accountIconUUID());
        account.setBankUUID(accountDetails.bankUUID());
        account.setCurrencyUUID(accountDetails.currencyUUID());

        // Persist the account and return its uuid
        return dataService.saveAccount(account).getAccountUUID();
    }

    /**
     * Retrieves a collection of {@link AcctAccount accounts} contained by the
     * {@link AcctWorkspace workspace} having the given workspace UUID. If the
     * user with the given user UUID cannot reach the workspace, or if the
     * permissions stated in the given collection of privilege names are not
     * sufficient, then exceptions are thrown.
     *
     * @param userUUID               the given user UUID
     * @param workspaceUUID          the given workspace UUID
     * @param assignedPrivilegeNames the given collection of privilege names
     */
    public Collection<AcctAccount> getAccountsInWorkspace(
        String userUUID,
        String workspaceUUID,
        Collection<String> assignedPrivilegeNames
    ) {
        // Retrieve the workspace for writing (altering accounts within the workspace means modifying the workspace)
        final AcctWorkspace workspace =
            findWorkspaceForUserAndOperation(READ, userUUID, workspaceUUID, assignedPrivilegeNames);

        // Retrieve and return a reference to the collection of accounts in the workspace
        return dataService.findAllAccountsInWorkspace(workspace);
    }

    /**
     * Deletes the {@link AcctAccount account} with the given account UUID
     * from the {@link AcctWorkspace workspace} with the given workspace UUID.
     * Raises exceptions if the user with the given user UUID does not have
     * access to the workspace or does not have the privilege to update own
     * and / or group workspaces, which is determined from the given list
     * of privilege names.
     *
     * @param userUUID               the given user UUID
     * @param workspaceUUID          the given workspace UUID
     * @param accountUUID            the given account UUID
     * @param assignedPrivilegeNames the given list of privilege names
     */
    @Transactional
    public void deleteAccountFromWorkspace(
        String userUUID,
        String workspaceUUID,
        String accountUUID,
        Collection<String> assignedPrivilegeNames
    ) {
        // Retrieve the account from the workspace
        final AcctAccount account =
            retrieveAccountFromWorkspaceForWorkspaceOperation(
                SAVE, userUUID, workspaceUUID, assignedPrivilegeNames, accountUUID
            );

        // Delete the account
        dataService.deleteAccount(account);
    }

    /**
     * Creates or updates an {@link AcctAccountRecord account record} for the given
     * account record details, belonging to the {@link AcctAccount account} wit the
     * given account UUID, residing in the workspace with the given workspace UUID.
     * If the user with the given user UUID does not have access to the workspace,
     * or if the proper privileges are not contained by the given privilege names
     * collection, then an exception is thrown.
     *
     * @param userUUID               the given user UUID
     * @param workspaceUUID          the given workspace UUID
     * @param accountUUID            the given account UUID
     * @param accountRecordDetails   the given account record details
     * @param assignedPrivilegeNames the given privilege names collection
     * @return the {@link AcctAccountRecord#getAccountRecordId() account record ID}
     * of the created or updated entity
     */
    @Transactional
    public Long saveAccountRecord(
        String userUUID,
        String workspaceUUID,
        String accountUUID,
        AccountRecordDetails accountRecordDetails,
        Collection<String> assignedPrivilegeNames
    ) {
        // Retrieve the account from the workspace
        final AcctAccount account =
            retrieveAccountFromWorkspaceForWorkspaceOperation(
                SAVE, userUUID, workspaceUUID, assignedPrivilegeNames, accountUUID
            );

        // Get a reference to the record to be updated (either a new record or existing record,
        // based on the existence of the account record ID)
        final AcctAccountRecord record =
            accountRecordDetails.accountRecordId() == null
                ? createNewAccountRecordWithinAccount(account, userUUID)
                : retrieveAccountRecord(accountRecordDetails.accountRecordId());

        // Populate the account record
        record.setIncomeOrExpenseItemUUID(accountRecordDetails.incomeOrExpenseItemUUID());
        record.setAccountRecordText(accountRecordDetails.accountRecordText());
        record.setAccountRecordValue(accountRecordDetails.accountRecordValue());

        // Persist the account record and return the account record id
        return saveAccountRecord(userUUID, record).getAccountRecordId();
    }

    /**
     * Retrieves a page of {@link AccountRecordExtendedDetails extended account records},
     * which include all the properties an {@link AcctAccountRecord account record} has
     * and add some more additional information such as the purchase price for foreign
     * currencies when it comes to currency exchange records. The page of records is taken
     * from the account with the given account UUID within the workspace with the given
     * workspace UUID, as seen by the user with the given user UUID and the given collection
     * of assigned privileges. If the pattern is provided then a filter on the record's
     * {@link AcctAccountRecord#getAccountRecordText() text} applies. The returned page
     * is of the given page number and page size and the data set is sorted in ascending
     * order by {@link AcctAccountRecord#getAccountRecordDate() account record date}.
     *
     * @param userUUID               the given user UUID
     * @param workspaceUUID          the given workspace UUID
     * @param accountUUID            the given account UUID
     * @param pattern                the pattern
     * @param pageNumber             the given page number
     * @param pageSize               the given page size
     * @param assignedPrivilegeNames the given collection of assigned privileges
     */
    public Page<AccountRecordExtendedDetails> findSortedPageOfAccountRecordsByTextPattern(
        String userUUID,
        String workspaceUUID,
        String accountUUID,
        String pattern,
        int pageNumber,
        int pageSize,
        Collection<String> assignedPrivilegeNames
    ) {
        // Retrieve the account from the workspace
        final AcctAccount account =
            retrieveAccountFromWorkspaceForWorkspaceOperation(
                SAVE, userUUID, workspaceUUID, assignedPrivilegeNames, accountUUID
            );

        // Retrieve the page of account records using the proper method, which is
        // chosen based on the existence of the pattern
        final Page<AcctAccountRecord> accountRecordsPage =
            (pattern == null || pattern.isBlank())
                ? dataService.findAccountRecordsByAccount(account, pageNumber, pageSize)
                : dataService.findAccountRecordsByAccountAndTextLike(account, pattern, pageNumber, pageSize);

        // If this is a foreign currency account then fetch currency exchange records for the page
        final Collection<AcctCurrencyExchange> currencyExchangeRecords =
            Objects.equals(account.getCurrencyUUID(), account.getWorkspace().getDefaultCurrencyUUID())
                ? emptyList()
                : dataService.findCurrencyExchangesByTargetAccountRecordIn(accountRecordsPage.data());

        // Turn the retrieved page into a page of AccountRecordExtendedDetails
        // and return a reference
        return
            AccountRecordExtendedDetailsMapper.fromPageOfAcctAccountRecords(
                accountRecordsPage,
                currencyExchangeRecords
            );
    }

    /**
     * Transfers the given amount from the account referenced by the given source account UUID
     * into the account referenced by the given target account UUID, provided that both accounts
     * are part of the same workspace, referenced by the given workspace UUID, and have the same
     * currency. To secure the operation, the user referenced by the given user UUID has to have
     * access to the workspace and the given collection of privileges must contain the proper
     * privileges for to allow the user to update the workspace.
     *
     * @param userUUID               the given user UUID
     * @param workspaceUUID          the given workspace UUID
     * @param sourceAccountUUID      the given source account UUID
     * @param targetAccountUUID      the given target account UUID
     * @param amount                 the given amount
     * @param assignedPrivilegeNames the given collection of privileges
     */
    @Transactional
    public void transferAmountBetweenAccountsWithSameCurrency(
        String userUUID,
        String workspaceUUID,
        String sourceAccountUUID,
        String targetAccountUUID,
        Double amount,
        Collection<String> assignedPrivilegeNames
    ) {
        // Retrieve the workspace for the save operation
        final AcctWorkspace workspace =
            findWorkspaceForUserAndOperation(SAVE, userUUID, workspaceUUID, assignedPrivilegeNames);

        // Retrieve the source account from the workspace
        final AcctAccount sourceAccount =
            findAccountByAccountUUIDForWorkspace(workspace, sourceAccountUUID);

        // Retrieve the target account from the workspace
        final AcctAccount targetAccount =
            findAccountByAccountUUIDForWorkspace(workspace, targetAccountUUID);

        // Make sure the source and target accounts have the same currency
        if (!Objects.equals(sourceAccount.getCurrencyUUID(), targetAccount.getCurrencyUUID())) {
            throw new AcctWorkspaceServiceMismatchedCurrenciesException(
                errors,
                sourceAccount.getCurrencyUUID(),
                targetAccount.getCurrencyUUID()
            );
        }

        // Make sure the source account has enough currency for the transfer
        verifyMinimumAccountBalance(sourceAccount, amount);

        // Get the current date
        final Instant currentDate = Instant.now();

        // Add a record for subtracting the amount from the source account
        final AcctAccountRecord sourceAccountRecord = createNewAccountRecordWithinAccount(sourceAccount, userUUID);
        sourceAccountRecord.setAccountRecordText(ACCT_REC_TEXT_TRANSFER);
        sourceAccountRecord.setAccountRecordValue(-amount);
        sourceAccountRecord.setIncomeOrExpenseItemUUID(INCOME_OR_EXPENSE_ITEM_UUID_FOR_TRANSFER);
        saveAccountRecord(userUUID, currentDate, sourceAccountRecord);

        // Add a record for adding the amount to the target account
        final AcctAccountRecord targetAccountRecord = createNewAccountRecordWithinAccount(targetAccount, userUUID);
        targetAccountRecord.setAccountRecordText(ACCT_REC_TEXT_TRANSFER);
        targetAccountRecord.setAccountRecordValue(amount);
        targetAccountRecord.setIncomeOrExpenseItemUUID(INCOME_OR_EXPENSE_ITEM_UUID_FOR_TRANSFER);
        saveAccountRecord(userUUID, currentDate, targetAccountRecord);
    }

    @Transactional
    public void currencyExchange(
        String userUUID,
        String workspaceUUID,
        String sourceAccountUUID,
        String targetAccountUUID,
        Double amount,
        Double exchangeRate,
        Long originalAccountRecordId,
        Collection<String> assignedPrivilegeNames
    ) {
        // Retrieve the workspace for the save operation
        final AcctWorkspace workspace =
            findWorkspaceForUserAndOperation(SAVE, userUUID, workspaceUUID, assignedPrivilegeNames);

        // Retrieve the source account from the workspace
        final AcctAccount sourceAccount =
            findAccountByAccountUUIDForWorkspace(workspace, sourceAccountUUID);

        // Retrieve the target account from the workspace
        final AcctAccount targetAccount =
            findAccountByAccountUUIDForWorkspace(workspace, targetAccountUUID);

        // Make sure the source and target accounts have different currencies
        if (Objects.equals(sourceAccount.getCurrencyUUID(), targetAccount.getCurrencyUUID())) {
            throw new AcctWorkspaceServiceSameCurrencyException(
                errors,
                sourceAccount.getAccountUUID(),
                targetAccount.getAccountUUID()
            );
        }

        // Compute the amount required to be available in the source account
        // for the transfer to take place
        final Double sourceAmount = amount * exchangeRate;

        // Make sure the source account has enough currency for the transfer
        verifyMinimumAccountBalance(sourceAccount, sourceAmount);

        // Get the current date
        final Instant currentDate = Instant.now();

        // Add a record for subtracting the amount from the source account
        final AcctAccountRecord sourceAccountRecord = createNewAccountRecordWithinAccount(sourceAccount, userUUID);
        sourceAccountRecord.setAccountRecordText(ACCT_REC_TEXT_TRANSFER);
        sourceAccountRecord.setAccountRecordValue(-sourceAmount);
        sourceAccountRecord.setIncomeOrExpenseItemUUID(INCOME_OR_EXPENSE_ITEM_UUID_FOR_TRANSFER);
        final AcctAccountRecord savedSourceAccountRecord = saveAccountRecord(userUUID, currentDate, sourceAccountRecord);

        // Add a record for adding the amount to the target account
        final AcctAccountRecord targetAccountRecord = createNewAccountRecordWithinAccount(targetAccount, userUUID);
        targetAccountRecord.setAccountRecordText(ACCT_REC_TEXT_TRANSFER);
        targetAccountRecord.setAccountRecordValue(amount);
        targetAccountRecord.setIncomeOrExpenseItemUUID(INCOME_OR_EXPENSE_ITEM_UUID_FOR_TRANSFER);
        final AcctAccountRecord savedTargetAccountRecord = saveAccountRecord(userUUID, currentDate, targetAccountRecord);

        // Create a currency exchange record
        final AcctCurrencyExchange currencyExchange = dataService.createNewCurrencyExchange();
        currencyExchange.setCurrencyExchangeRate(exchangeRate);
        currencyExchange.setPurchasePrice(sourceAmount);
        currencyExchange.setCurrencyExchangeSourceAccountRecord(savedSourceAccountRecord);
        currencyExchange.setCurrencyExchangeTargetAccountRecord(savedTargetAccountRecord);

        // If an original exchange record was referenced then find the reverse
        // currency exchange record and add it to the previously-created currency
        // exchange record
        if (originalAccountRecordId != null) {
            // Find the currency exchange record that has the source account as target
            final Optional<AcctCurrencyExchange> optionalOriginalCurrencyExchangeRecord =
                dataService.findCurrencyExchangeByTargetAccountRecordId(originalAccountRecordId);

            // If an original currency exchange record is found then reference it from the
            // newly created currency e4xchange record
            optionalOriginalCurrencyExchangeRecord.ifPresent(currencyExchange::setOptionalOriginalCurrencyExchange);
        }

        // Persist the currency exchange record
        dataService.saveCurrencyExchange(currencyExchange);
    }

    /**
     * Computes the balance of the account referenced by the given account UUID,
     * within the workspace with the given workspace UUID. Raises exceptions if
     * the workspace is not accessible to the user with the given user UUID given
     * the referenced collection of user privileges.
     *
     * @param userUUID               the given user UUID
     * @param workspaceUUID          the given workspace UUID
     * @param accountUUID            the given account UUID
     * @param assignedPrivilegeNames the referenced collection of user privileges
     */
    public double computeAccountBalance(
        String userUUID,
        String workspaceUUID,
        String accountUUID,
        Collection<String> assignedPrivilegeNames
    ) {
        // Retrieve the account from the workspace
        final AcctAccount account =
            retrieveAccountFromWorkspaceForWorkspaceOperation(
                READ, userUUID, workspaceUUID, assignedPrivilegeNames, accountUUID
            );

        // Compute the balance and return the result
        return computeAccountBalance(account);
    }

    /**
     * Creates a new {@link AcctDeposit deposit} with the given amount of currency from the
     * source account referenced by the given source account UUID that can be found in the
     * workspace with the given workspace UUID. The following properties are set on the newly
     * created deposit: <ul>
     * <li>{@link AcctDeposit#getDepositAccountNumber() account number} =  the given deposit account number</li>
     * <li>{@link AcctDeposit#getDepositProjectedEndDate() projected end date} = the given projected end date</li>
     * <li>{@link AcctDeposit#getDepositInterestPercent() interest percent} = the given interest percent</li>
     * </ul>
     * The operation mail not be allowed if the user with the given user UUID does not have
     * access to the workspace or if the given collection of privileges does not contain the
     * proper access rights.
     *
     * @param userUUID               the given user UUID
     * @param workspaceUUID          the given workspace UUID
     * @param sourceAccountUUID      the given source account UUID
     * @param depositAccountNumber   the given deposit account number
     * @param amount                 the given amount of currency
     * @param projectedEndDate       the given projected end date
     * @param interestPct            the given interest percent
     * @param assignedPrivilegeNames the given collection of privileges
     * @return the {@link AcctDeposit#getDepositUUID() UUID} of the newly created deposit
     */
    public String createDeposit(
        String userUUID,
        String workspaceUUID,
        String sourceAccountUUID,
        String depositAccountNumber,
        Double amount,
        Instant projectedEndDate,
        Double interestPct,
        Collection<String> assignedPrivilegeNames
    ) {
        // Retrieve the source account from the workspace
        final AcctAccount sourceAccount =
            retrieveAccountFromWorkspaceForWorkspaceOperation(
                SAVE, userUUID, workspaceUUID, assignedPrivilegeNames, sourceAccountUUID
            );

        // Make sure the source account has enough currency for the transfer
        verifyMinimumAccountBalance(sourceAccount, amount);

        // Create a new account record for subtracting the amount for the deposit from the source account
        final AcctAccountRecord depositCreationRecord = createNewAccountRecordWithinAccount(sourceAccount, userUUID);
        depositCreationRecord.setAccountRecordText(ACCT_REC_TEXT_DEPOSIT_CREATION);
        depositCreationRecord.setAccountRecordValue(-amount);
        depositCreationRecord.setIncomeOrExpenseItemUUID(INCOME_OR_EXPENSE_ITEM_UUID_FOR_DEPOSIT);
        final AcctAccountRecord savedDepositCreationRecord = saveAccountRecord(userUUID, depositCreationRecord);

        // Create the new deposit
        final AcctDeposit deposit = dataService.createNewDeposit();

        // Populate the newly created deposit with the properties inherited from the source account
        deposit.setBankUUID(sourceAccount.getBankUUID());
        deposit.setCurrencyUUID(sourceAccount.getCurrencyUUID());

        // Populate the newly created deposit with the properties received as input
        deposit.setDepositAccountNumber(depositAccountNumber);
        deposit.setDepositInterestPercent(interestPct);
        deposit.setDepositProjectedEndDate(projectedEndDate);
        deposit.setDepositValue(amount);

        // Populate the workflow-related properties of the newly created deposit
        deposit.setDepositCreationAccountRecord(savedDepositCreationRecord);
        deposit.setDepositUUID(UUID.randomUUID().toString());

        // Save and return the UUID
        return dataService.saveDeposit(deposit).getDepositUUID();
    }

    private AcctAccount retrieveAccountFromWorkspaceForWorkspaceOperation(
        WorkspaceServiceOperation operation,
        String userUUID,
        String workspaceUUID,
        Collection<String> assignedPrivilegeNames,
        String accountUUID
    ) {
        // Retrieve the workspace for the save operation
        final AcctWorkspace workspace =
            findWorkspaceForUserAndOperation(operation, userUUID, workspaceUUID, assignedPrivilegeNames);

        // Retrieve the source account from the workspace
        return findAccountByAccountUUIDForWorkspace(workspace, accountUUID);
    }

    /**
     * Throws an exception if the balance of the referenced {@link AcctAccount account}
     * is lower than the given required amount
     *
     * @param account        the referenced account
     * @param requiredAmount the given required amount
     */
    private void verifyMinimumAccountBalance(AcctAccount account, Double requiredAmount) {
        if (computeAccountBalance(account) < requiredAmount) {
            throw new AcctWorkspaceServiceInsufficientFundsException(errors, account.getAccountUUID());
        }
    }

    /**
     * Computes the balance of the referenced {@link AcctAccount account}
     *
     * @param account the referenced account
     * @return the computed balance
     */
    private Double computeAccountBalance(AcctAccount account) {
        return dataService.sumAccountRecordValuesByAccount(account);
    }

    /**
     * Creates a new instance of {@link AcctAccountRecord} and populates it with the
     * referenced parent account, while also setting the workflow-related properties
     * that include the given user UUID. The entity is created in-memory and not
     * persisted.
     *
     * @param account            the referenced parent account
     * @param recordedByUserUUID the given user UUID
     * @return a reference to the newly created entity
     */
    private AcctAccountRecord createNewAccountRecordWithinAccount(AcctAccount account, String recordedByUserUUID) {
        // Create a new account record entity
        final AcctAccountRecord newRecord = dataService.createNewAccountRecord();

        // Set the parent account of the new account record entity
        newRecord.setAccount(account);

        // Populate the workflow-related properties
        newRecord.setAccountRecordDate(Instant.now());
        newRecord.setRecordedByUserUUID(recordedByUserUUID);

        // Return a reference to the newly created entity
        return newRecord;
    }

    /**
     * Retrieves the {@link AcctAccountRecord account record} with the given
     * {@link AcctAccountRecord#getAccountRecordId() account record id} or
     * throws an exception.
     *
     * @param accountRecordId the given account record id
     * @return a reference to the retrieved entity
     */
    private AcctAccountRecord retrieveAccountRecord(Long accountRecordId) {
        return
            dataService.findAccountRecordByAccountRecordId(accountRecordId)
                .orElseThrow(() -> new AcctWorkspaceServiceNotFoundException(
                    errors, ObjectTypes.ACCOUNT_RECORD, Long.toString(accountRecordId))
                );
    }

    /**
     * Persists the referenced {@link AcctAccountRecord account record} while also setting
     * the {@link AcctAccountRecord#getLastModifiedDate() last modified date} to the current
     * date and the {@link AcctAccountRecord#getLastModifiedByUserUUID() last modified by user UUID}
     * to the given user UUID
     *
     * @param userUUID the given user UUID
     * @param record   the referenced account record
     * @return a reference to the persisted entity
     */
    private AcctAccountRecord saveAccountRecord(String userUUID, AcctAccountRecord record) {
        return saveAccountRecord(userUUID, Instant.now(), record);
    }

    /**
     * Persists the referenced {@link AcctAccountRecord account record} while also setting
     * the {@link AcctAccountRecord#getLastModifiedDate() last modified date} to the given
     * date and the {@link AcctAccountRecord#getLastModifiedByUserUUID() last modified by user UUID}
     * to the given user UUID
     *
     * @param userUUID    the given user UUID
     * @param currentDate the given date
     * @param record      the referenced account record
     * @return a reference to the persisted entity
     */
    private AcctAccountRecord saveAccountRecord(String userUUID, Instant currentDate, AcctAccountRecord record) {
        // Set the workflow-related properties
        record.setLastModifiedDate(currentDate);
        record.setLastModifiedByUserUUID(userUUID);

        // Persist the account record and return
        return dataService.saveAccountRecord(record);
    }

    /**
     * Retrieves the account with the given account UUID or throws an exception if the
     * account is not found.
     *
     * @param accountUUID the given account UUID
     */
    private AcctAccount findAccountByAccountUUIDForWorkspace(AcctWorkspace workspace, String accountUUID) {
        // Find the account or throw a not found exception
        final AcctAccount account =
            dataService.findAccountByAccountUUID(accountUUID)
                .orElseThrow(() -> new AcctWorkspaceServiceNotFoundException(errors, ObjectTypes.ACCOUNT, accountUUID));

        // If the account is found and the workspace UUID of the workspace within which the account
        // exists does not match the given one then throw a ot found exception (no special exception
        // is thrown to minimize the chance of successful hacking)
        if (!Objects.equals(account.getWorkspace().getWorkspaceUUID(), workspace.getWorkspaceUUID())) {
            throw new AcctWorkspaceServiceNotFoundException(errors, ObjectTypes.ACCOUNT, accountUUID);
        }

        // If everything is well, return a reference to the retrieved account entity
        return account;
    }

    /**
     * Creates a new {@link AcctAccount account} within the referenced {@link AcctWorkspace workspace}
     * and gives it a random account UUID. The entity is not persisted.
     *
     * @param workspace the referenced workspace
     */
    private AcctAccount createNewAccountWithinWorkspace(AcctWorkspace workspace) {
        final AcctAccount account = dataService.createNewAccount();

        account.setWorkspace(workspace);
        account.setAccountUUID(UUID.randomUUID().toString());

        return account;
    }

    /**
     * Determines if the workspace with the given workspace UUID is accessible for the given operation
     * by the user with the given user UUID, based on the accessibility report fetched from the security
     * service and the provided collection of assigned user privileges.
     *
     * @param operation              the given operation
     * @param userUUID               the given user UUID
     * @param workspaceUUID          the given workspace UUID
     * @param assignedPrivilegeNames the provided collection of assigned user privileges
     */
    private boolean workspaceIsAccessibleToUserForOperation(
        WorkspaceServiceOperation operation,
        String userUUID,
        String workspaceUUID,
        Collection<String> assignedPrivilegeNames
    ) {
        return
            securityClientService.resourceIsAccessibleToUser(
                WORKSPACE,
                userUUID,
                workspaceUUID,
                createUserAccessRights(operation, assignedPrivilegeNames)
            );
    }

    private static UserResourceAccessRights createUserAccessRights(
        WorkspaceServiceOperation operation,
        Collection<String> assignedPrivilegeNames
    ) {
        return
            UserResourceAccessRights.builder()
                .withOwnResources(assignedPrivilegeNames.contains(getWorkspacePrivilegeId(operation, OWN_RESOURCES)))
                .withGroupResources(assignedPrivilegeNames.contains(getWorkspacePrivilegeId(operation, GROUP_RESOURCES)))
                .withAnyResources(assignedPrivilegeNames.contains(getWorkspacePrivilegeId(operation, ANY_RESOURCES)))
                .build();
    }

    /**
     * Creates a new {@link AcctWorkspace workspace} with a newly generated workspaceUUID
     *
     * @return a reference to the newly created entity
     */
    private AcctWorkspace createNewWorkspace() {
        final AcctWorkspace newWorkspace = dataService.createNewWorkspace();
        newWorkspace.setWorkspaceUUID(UUID.randomUUID().toString());
        return newWorkspace;
    }

    /**
     * Retrieves the workspace with the given workspaceUUID, as long as it is owned
     * by the user with the given userUID. If the ownership verification fails, an
     * exception is thrown.
     *
     * @param userUUID      the given userUUID
     * @param workspaceUUID the given workspaceUUID
     */
    private AcctWorkspace findWorkspaceForUserAndOperation(
        WorkspaceServiceOperation operation,
        String userUUID,
        String workspaceUUID,
        Collection<String> assignedPrivilegeNames
    ) {
        // If the user does not have access to perform the operation on the workspace then throw an exception
        if (!workspaceIsAccessibleToUserForOperation(operation, userUUID, workspaceUUID, assignedPrivilegeNames)) {
            throw new AcctWorkspaceServiceSecurityException(errors, ObjectTypes.WORKSPACE, workspaceUUID);
        }

        // If the workspace is accessible to the user then find it and return a reference
        return
            dataService.findWorkspaceByWorkspaceUUID(workspaceUUID)
                .orElseThrow(() -> new AcctWorkspaceServiceNotFoundException(
                    errors, ObjectTypes.WORKSPACE, workspaceUUID
                ));
    }


}
