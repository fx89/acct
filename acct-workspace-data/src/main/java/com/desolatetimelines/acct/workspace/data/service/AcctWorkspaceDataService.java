package com.desolatetimelines.acct.workspace.data.service;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.workspace.model.AcctAccount;
import com.desolatetimelines.acct.workspace.model.AcctAccountRecord;
import com.desolatetimelines.acct.workspace.model.AcctCurrencyExchange;
import com.desolatetimelines.acct.workspace.model.AcctWorkspace;
import com.desolatetimelines.acct.workspace.repository.AccountRecordsRepository;
import com.desolatetimelines.acct.workspace.repository.AccountsRepository;
import com.desolatetimelines.acct.workspace.repository.AcctCurrencyExchangesRepository;
import com.desolatetimelines.acct.workspace.repository.AcctWorkspacesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Facade for the ACCT Workspace data layer
 */
@Service
public class AcctWorkspaceDataService {

    private final AcctWorkspacesRepository workspacesRepository;

    private final AccountsRepository accountsRepository;

    private final AccountRecordsRepository accountRecordsRepository;

    private final AcctCurrencyExchangesRepository currencyExchangesRepository;

    public AcctWorkspaceDataService(
        AcctWorkspacesRepository workspacesRepository,
        AccountsRepository accountsRepository,
        AccountRecordsRepository accountRecordsRepository,
        AcctCurrencyExchangesRepository currencyExchangesRepository
    ) {
        this.workspacesRepository = workspacesRepository;
        this.accountsRepository = accountsRepository;
        this.accountRecordsRepository = accountRecordsRepository;
        this.currencyExchangesRepository = currencyExchangesRepository;
    }

    /**
     * Creates a new {@link AcctWorkspace workspace} instance
     *
     * @return a reference to the newly created entity
     */
    public AcctWorkspace createNewWorkspace() {
        return workspacesRepository.createNew();
    }

    /**
     * Persists the referenced {@link AcctWorkspace workspace}
     *
     * @param workspace the referenced workspace
     * @return a reference to the persisted entity
     */
    public AcctWorkspace saveWorkspace(AcctWorkspace workspace) {
        return workspacesRepository.save(workspace);
    }

    /**
     * Returns a reference to the {@link AcctWorkspace workspace} entity with the given
     * workspace UUID or an empty optional if such an entity does not exist
     *
     * @param workspaceUUID the given workspace UUID
     */
    public Optional<AcctWorkspace> findWorkspaceByWorkspaceUUID(String workspaceUUID) {
        return workspacesRepository.findFirstByWorkspaceUUID(workspaceUUID);
    }

    /**
     * Retrieves a collection of {@link AcctWorkspace workspaces} for the UUIDs
     * in the given collection of workspace UUIDs
     *
     * @param workspaceUUIDs the given collection of workspace UUIDs
     */
    public Collection<AcctWorkspace> findWorkspacesByWorkspaceUUIDIn(Collection<String> workspaceUUIDs) {
        return workspacesRepository.findAllByWorkspaceUUIDIn(workspaceUUIDs);
    }

    /**
     * Deletes the referenced {@link AcctWorkspace workspace}
     */
    public void deleteWorkspace(AcctWorkspace workspace) {
        workspacesRepository.delete(workspace);
    }

    /**
     * Creates a new {@link AcctAccount account}
     *
     * @return a reference to the newly created entity
     */
    public AcctAccount createNewAccount() {
        return accountsRepository.createNew();
    }

    /**
     * Retrieves the account having the given account UUID or an empty optional
     * if such an account does not exist
     *
     * @param accountUUID the given account UUID
     */
    public Optional<AcctAccount> findAccountByAccountUUID(String accountUUID) {
        return accountsRepository.findFirstByAccountUUID(accountUUID);
    }

    /**
     * Persists the referenced {@link AcctAccount account}
     *
     * @param account the referenced account
     * @return a reference to the persisted entity
     */
    public AcctAccount saveAccount(AcctAccount account) {
        return accountsRepository.saveAccount(account);
    }

    /**
     * Retrieves a collection of {@link AcctAccount accounts} that are
     * contained by the referenced {@link AcctWorkspace workspace}
     *
     * @param workspace the referenced workspace
     */
    public Collection<AcctAccount> findAllAccountsInWorkspace(AcctWorkspace workspace) {
        return accountsRepository.findAllByWorkspace(workspace);
    }

    /**
     * Deletes the referenced {@link AcctAccount account}
     */
    public void deleteAccount(AcctAccount account) {
        accountsRepository.deleteAccount(account);
    }

    /**
     * Creates a new instance of {@link AcctAccountRecord}
     */
    public AcctAccountRecord createNewAccountRecord() {
        return accountRecordsRepository.createNew();
    }

    /**
     * Persists the referenced {@link AcctAccountRecord account record}
     *
     * @param accountRecord the referenced account record
     * @return a reference to the persisted entity
     */
    public AcctAccountRecord saveAccountRecord(AcctAccountRecord accountRecord) {
        return accountRecordsRepository.save(accountRecord);
    }

    /**
     * Retrieves the {@link AcctAccountRecord account record} with the given
     * {@link AcctAccountRecord#getAccountRecordId() account record ID} or an
     * empty optional in case the account record is not found
     *
     * @param accountRecordId the given account record ID
     * @return a reference to the retrieved entity
     */
    public Optional<AcctAccountRecord> findAccountRecordByAccountRecordId(Long accountRecordId) {
        return accountRecordsRepository.findFirstByAccountRecordId(accountRecordId);
    }

    /**
     * Retrieves a page of the given page size and with the given page number
     * of {@link AcctAccountRecord account records} belonging to the referenced
     * {@link AcctAccount account} and for which the
     * {@link AcctAccountRecord#getAccountRecordText() text} matches the given
     * pattern
     *
     * @param account     the referenced account
     * @param textPattern the given pattern
     * @param pageNumber  the given page number
     * @param pageSize    the given page size
     */
    public Page<AcctAccountRecord> findAccountRecordsByAccountAndTextLike(
        AcctAccount account,
        String textPattern,
        int pageNumber,
        int pageSize
    ) {
        return
            accountRecordsRepository.findAllByAccountAndTextLike(
                account, textPattern, pageNumber, pageSize
            );
    }

    /**
     * Retrieves a page of the given page size and with the given page number
     * of {@link AcctAccountRecord account records} belonging to the referenced
     * {@link AcctAccount account}
     *
     * @param account    the referenced account
     * @param pageNumber the given page number
     * @param pageSize   the given page size
     */
    public Page<AcctAccountRecord> findAccountRecordsByAccount(
        AcctAccount account,
        int pageNumber,
        int pageSize
    ) {
        return accountRecordsRepository.findAllByAccount(account, pageNumber, pageSize);
    }

    /**
     * Retrieves a collection of {@link AcctCurrencyExchange currency exchange records} for which the
     * {@link AcctCurrencyExchange#getCurrencyExchangeTargetAccountRecord() target account record} is
     * one of the {@link AcctAccountRecord account records} in the given collection
     *
     * @param accountRecords the given collection
     */
    public Collection<AcctCurrencyExchange> findCurrencyExchangesByTargetAccountRecordIn(
        Collection<AcctAccountRecord> accountRecords
    ) {
        return currencyExchangesRepository.findAllByTargetAccountRecordIn(accountRecords);
    }

    /**
     * Retrieves the {@link AcctCurrencyExchange currency exchange record} for which the
     * {@link AcctCurrencyExchange#getCurrencyExchangeTargetAccountRecord() target account record}
     * has the given {@link AcctAccountRecord#getAccountRecordId() account record id}.
     * If such a currency exchange record does not exist, an empty optional is returned.
     *
     * @param accountRecordId the given account record id
     */
    public Optional<AcctCurrencyExchange> findCurrencyExchangeByTargetAccountRecordId(Long accountRecordId) {
        return currencyExchangesRepository.findFirstByTargetAccountRecordId(accountRecordId);
    }

    /**
     * Returns the sum of the {@link AcctAccountRecord#getAccountRecordValue()} property of all
     * {@link AcctAccountRecord records} belonging to the referenced {@link AcctAccount account}
     *
     * @param account the referenced account
     */
    public Double sumAccountRecordValuesByAccount(AcctAccount account) {
        return
            Optional
                .ofNullable(accountRecordsRepository.sumAccountRecordValueByAccount(account))
                .orElse(0d); // In case there are no records and the response is null
    }

    /**
     * Creates a new instance of {@link AcctCurrencyExchange}
     *
     * @return the newly created instance
     */
    public AcctCurrencyExchange createNewCurrencyExchange() {
        return currencyExchangesRepository.createNew();
    }

    /**
     * Persists the referenced {@link AcctCurrencyExchange currency exchange record}
     *
     * @return a reference to the persisted entity
     */
    public AcctCurrencyExchange saveCurrencyExchange(AcctCurrencyExchange currencyExchange) {
        return currencyExchangesRepository.save(currencyExchange);
    }

}
