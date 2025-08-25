import { Injectable, Predicate } from '@angular/core';
import { AcctWorkspacesRepository } from '../repositories-acct/workspaces-repository';
import { concatAll, map, Observable, Subscriber } from 'rxjs';
import { WorkspaceCollections } from '../model-acct/workspace-collections';
import { IconifiedWorkspace, Workspace } from '../model-acct/workspace';
import { distinctElementsArray } from '../utils-reusalbe/array-utils';
import { errorPipingObservableTransform } from '../utils-reusalbe/rxjs-utils';
import { CatalogService } from './catalog.service';
import { WorkspaceUUIDResponse } from '../model-acct/workspace-uuid-response';
import { Account, IconifiedAccount } from '../model-acct/account';
import { AcctAccountsRepository } from '../repositories-acct/accounts-repository';
import { AccountUUIDResponse } from '../model-acct/account-uuid-response';
import { AccountRecord, AccountRecordInputData } from '../model-acct/account-record';
import { AccountRecordIdResponse } from '../model-acct/account-record-id-response';
import { isDefined } from '../utils-reusalbe/lang-utils';
import { AcctAccountRecordsRepository } from '../repositories-acct/account-records-repository';
import { AcctPage } from '../model-acct/acct-page';
import { CurrencyTransfer } from '../model-acct/currency-transfer';
import { CurrencyExchange } from '../model-acct/currency-exchange';
import { SortDirection } from '../model-acct/sort-direction';
import { AcctAutocompleteRepository } from '../repositories-acct/autocomplete-repository';
import { AutocompleteDataResponse } from '../model-acct/autocomplete-data-response';
import { IncomeOrExpenseItem } from '../model-acct/income-or-expense-item';
import { DepositProperties } from '../model-acct/deposit-modifiable-attributes';
import { AcctDepositsRepository } from '../repositories-acct/deposits-repository';
import { DepositUUIDResponse } from '../model-acct/deposit-uuid-response';
import { BankProperties } from '../model-acct/bank-properties';

/**
 * Container for the UUIDs of the workspace and account where an account record is found
 */
type AccountRecordContext = {
  workspaceUUID : string,
  accountUUID   : string
}

/**
 * Interface to the Workspace back-end service
 */
@Injectable({
  providedIn: 'root'
})
export class WorkspaceService {

  constructor(
    private workspacesRepository     : AcctWorkspacesRepository,
    private catalogService           : CatalogService,
    private accountsRepository       : AcctAccountsRepository,
    private accountRecordsRepository : AcctAccountRecordsRepository,
    private autocompleteRepository   : AcctAutocompleteRepository,
    private depositsRepository       : AcctDepositsRepository
  ) { 

  }

  /**
   * Returns an observable that produces an array of workspaces, complete with the image data of the related icons.
   * If a filter is given, then the array of workspaces will contain only workspaces that match the given filter.
   * 
   * @param filter an optional predicate that can be used to select a specific sub-set of workspaces
   */
  public findUserAccessibleWorkspaces(filter?:Predicate<Workspace>): Observable<IconifiedWorkspace[]> {
    // TODO: cache into session
    return this.workspacesRepository.findUserAccessibleWorkspaces().pipe(
      // Convert the workspaces collection into a workspaces array
      map(workspaceCollections => this.workspaceCollectionsToWorkspaceArray(workspaceCollections)),

      // Optionally apply the filter
      map(workspaces => filter ? workspaces.filter(filter) : workspaces),

      // Convert to iconified accounts
      map(workspaces => workspaces as IconifiedWorkspace[]),

      // Retrieve the icon for each iconified account
      map(workspaces =>
        this.catalogService.applyIconToItems(
          workspaces,
          workspace => workspace.workspaceIconUUID,
          (workspace, imageData) => workspace.imageData = imageData
        )
      ),

      // Flatten the Observable-of-Observables resulted from the icon applying operation
      concatAll()
    )
  }

  /**
   * Returns an observable that produces the workspace identified by the given workspaceUUID.
   * If the referenced workspace is not accessible by the user, or if the workspace does not
   * exist, then an error is thrown.
   * 
   * @param workspaceUUID the given workspace UUID
   */
  public findUserAccessibleWorkspace(workspaceUUID:string) : Observable<IconifiedWorkspace> {
    return errorPipingObservableTransform(
      this.findUserAccessibleWorkspaces(ws => workspaceUUID == ws.workspaceUUID),
      workspaces => {
        // If there is no workspace in the array, then throw the error
        if (workspaces.length == 0) {
          throw new Error("Workspace not found")
        }
        
        // If there's an workspace in the array, then return a reference
        return workspaces[0]
      }
    )
  }

  /**
   * Deletes the referenced workspace
   */
  public deleteWorkspace(workspace:Workspace) : Observable<void> {
    if (workspace.workspaceUUID) {
      return this.workspacesRepository.deleteWorkspace(workspace.workspaceUUID)
    } else {
      throw new Error("Workspace UUID not provided")
    }
  }

  /**
   * Saves the referenced workspace
   * 
   * @param workspace the referenced workspace
   * @returns a container for the UUID of the saved workspace
   */
  public saveWorkspace(workspace:Workspace) : Observable<WorkspaceUUIDResponse> {
    return this.workspacesRepository.saveWorkspace(workspace, workspace.workspaceUUID)
  }

  /**
   * Returns an observable that produces an array containing the accounts defined
   * within the scope of the referenced workspace. If there are no such accounts,
   * then an empty array is produced.
   * 
   * @param workspace the referenced workspace
   */
  public findWorkspaceAccounts(workspace:Workspace) : Observable<IconifiedAccount[]> {
    if (workspace.workspaceUUID) {
      // Store the wokrspace UUID
      const workspaceUUID : string = workspace.workspaceUUID

      return this.accountsRepository.findAccountsByWorkspaceUUID(workspaceUUID).pipe(
        // Convert to iconified accounts
        map(accounts => accounts as IconifiedAccount[]),

        // Retrieve the icon for each iconified account
        map(accounts =>
          this.catalogService.applyIconToItems(
            accounts,
            account => account.accountIconUUID,
            (account, imageData) => account.imageData = imageData
          )
        ),

        // Flatten the Observable-of-Observables resulted from the icon applying operation
        concatAll()
      )
    }
    // If the workspace doesn't have an UUID, then throw an error
    else {
      throw new Error("Missing workspaceUUID")
    }
  }

  /**
   * Deletes the referenced account from the referenced workspace
   * @param workspace the referenced workspace
   * @param account   the referenced account
   * @returns an observable that tells the consumer when the operation has been completed
   */
  public deleteWorkspaceAccount(workspace:Workspace, account:Account) : Observable<void> {
    if (workspace.workspaceUUID) {
      if (account.accountUUID) {
        return this.accountsRepository.deleteAccount(workspace.workspaceUUID, account.accountUUID)
      }
      else {
        throw new Error("Missing account UUID")
      }
    }
    else {
      throw new Error("Missing workspace UUID")
    }
  }

  /**
   * Saves the referenced account into the referenced workspace by either creating a new
   * account entry, if the account UUID is not present, or updating an existing account
   * entry, if the account UUID is present.
   * 
   * @param workspace the referenced workspace
   * @param account   the referenced account
   * @returns an AccountUUIDResponse that contains the UUID of the persisted account
   */
  public saveAccount(workspace:Workspace, account:Account) : Observable<AccountUUIDResponse> {
    if (workspace.workspaceUUID) {
      return this.accountsRepository.saveAccount(workspace.workspaceUUID, account)
    }
    else {
      throw new Error("Missing workspace UUID")
    }
  }

  public findAccountBalance(workspace:Workspace, account:Account) : Observable<number> {
    // The workspace must have an UUID
    if (!isDefined(workspace.workspaceUUID)) {
      throw new Error("Missing workspace UUID")
    }

    // The account must have an UUID
    if (!isDefined(account.accountUUID)) {
      throw new Error("Missing account UUID")
    }

    // At this point, the UUIDs are both defined
    const workspaceUUID : string = workspace.workspaceUUID as string
    const accountUUID : string = account.accountUUID as string

    // Run the repository operation and, when it returns a response, extract the account balance from
    // the response and send it up the pipe.
    return this.accountsRepository.findAccountBalance(workspaceUUID, accountUUID)
      .pipe(map(response => response.accountBalance))
  }

  /**
     * Creates or updates an account record in the referenced account, within the referenced workspace.
     * The container for the data to be persisted may contain an accountRecordId, in which case, the
     * existing record having the said id is updated. If the accountRecordId is missing, then a new
     * record is created within the account.
     * 
     * @param workspace the referenced workspace
     * @param account   the referenced account
     * @param record    the container for the data to be persisted
     * 
     * @returns an observable that produces a container for the id of the persisted record
     */
  public saveAccountRecord(
    workspace:Workspace,
    account:Account,
    record: AccountRecordInputData
  ): Observable<AccountRecordIdResponse>
  {
    // Make sure the workspace and account UUIDs are provided and extract the account record context
    const context : AccountRecordContext = this.verifyAccountRecordContext(workspace, account)

    // Call the repository function
    return this.accountRecordsRepository.saveAccountRecord(context.workspaceUUID, context.accountUUID, record)
  }

  /**
     * Deletes the referenced account record from the referenced account,
     * which is part of the referenced workspace.
     * 
     * @param workspace the referenced workspace
     * @param account   the referenced account
     * @param record    the referenced account record
     * 
     * @returns an observable that lets consumers know when the operation has ended
     */
  public deleteAccountRecord(
    workspace : Workspace,
    account   : Account,
    record    : AccountRecordInputData
  ) : Observable<void> {
    // Make sure the workspace and account UUIDs are provided and extract the account record context
    const context : AccountRecordContext = this.verifyAccountRecordContext(workspace, account)

    // Call the repository function
    return this.accountRecordsRepository.deleteAccountRecord(context.workspaceUUID, context.accountUUID, record)
  }

  /**
     * Returns an observable that produces a page of account records, optionally filtered by the given
     * text pattern, and sorted by record date in ascending order. The page of records is taken from
     * the referenced account, which must be part of the referenced workspace. The returned page is as
     * large as the given page size and has the given page number.
     * 
     * @param workspace     the workspace where the account resides
     * @param account       the account that owns the sought records
     * @param pageNumber    the number of the page to fetch
     * @param pageSize      the maximum number of elements to be contained in the returned page
     * @param pattern       optional text pattern against which to match the account record text
     * @param sortDirection the order in which the records are sorted within the context of the page request
     */
  public findSortedPageOfAccountRecordsByTextPattern(
    workspace     : Workspace,
    account       : Account,
    pageNumber    : number,
    pageSize      : number,
    sortDirection : SortDirection,
    pattern?      : string
  ): Observable<AcctPage<AccountRecord>>
  {
    // Make sure the workspace and account UUIDs are provided and extract the account record context
    const context : AccountRecordContext = this.verifyAccountRecordContext(workspace, account)

    // Call the repository function
    return this.accountRecordsRepository.findSortedPageOfAccountRecordsByTextPattern(
      context.workspaceUUID,
      context.accountUUID,
      pageNumber,
      pageSize,
      sortDirection,
      pattern
    )
  }

  /**
   * Transfers a given amount from a source account to a target account of the same currency.
   * The transfer parameters are contained by the given currency transfer record.
   * 
   * @param workspace        the workspace that owns the two accounts
   * @param currencyTransfer Container for the transfer parameters
   * 
   * @returns an observable that lets the consumer know when the transfer is complete
   */
  public saveCurrencyTransfer(workspace:Workspace, currencyTransfer:CurrencyTransfer): Observable<void> {
    // Make sure the workspace UUID is provided and extract the account record context
    const context : AccountRecordContext = this.verifyAccountRecordContext(workspace)

    // Call the repository function
    return this.accountRecordsRepository.saveCurrencyTransfer(
      context.workspaceUUID,
      currencyTransfer.sourceAccount.accountUUID ?? "",
      currencyTransfer.targetAccount.accountUUID ?? "",
      currencyTransfer.amount
    )
  }

  /**
   * Registers a purchase of a given amount of currency in the target account with a computed
   * amount of currency from the source account. The source account amount is computed based on
   * the amount purchased in the target account and the given exchange rate. Both accounts must
   * be part of the workspace referenced by the given workspace UUID.
   * 
   * @param workspace        The workspace where the two accounts reside
   * @param currencyExchange The currency exchange to be registered
   * 
   * @returns an observable that lets the consumer know when the transfer is complete
   */
  public saveCurrencyExchange(workspace:Workspace, currencyExchange:CurrencyExchange): Observable<void> {
    // Make sure the workspace UUID is provided and extract the account record context
    const context : AccountRecordContext = this.verifyAccountRecordContext(workspace)

    // Call the repository function
    return this.accountRecordsRepository.saveCurrencyExchange(
      context.workspaceUUID,
      currencyExchange.currencyTransfer.sourceAccount.accountUUID ?? "",
      currencyExchange.currencyTransfer.targetAccount.accountUUID ?? "",
      currencyExchange.currencyTransfer.amount,
      currencyExchange.exchangeRate,
      currencyExchange.originalAccountRecordId
    )
  }

  public findAutocompleteData(
    workspace           : Workspace,
    account             : Account,
    incomeOrExpenseItem : IncomeOrExpenseItem,
    textPattern         : string
  ) : Observable<AutocompleteDataResponse[]> {
    // Make sure the workspace UUID is provided and extract the account record context
    const context : AccountRecordContext = this.verifyAccountRecordContext(workspace, account)

    // Make sure the income or expense item UUID is provided
    if (!isDefined(incomeOrExpenseItem.incomeOrExpenseItemUUID)) {
      throw new Error("Income or expense item UUID not provided")
    }

    // Extract the income or expense item UUID
    const incomeOrExpenseItemUUID : string = incomeOrExpenseItem.incomeOrExpenseItemUUID ?? ""

    // Call the repository function
    return this.autocompleteRepository.findAutocompleteData(
      context.workspaceUUID,
      context.accountUUID,
      incomeOrExpenseItemUUID,
      textPattern
    )
  }

  /**
   * Saves the referenced deposit in the referenced workspace
   * 
   * @param workspace the referenced workspace
   * @param deposit   the referenced deposit
   * 
   * @returns an observable that produces a container for the
   *          depositUUID of the saved deposit
   */
  public saveDeposit(workspace:Workspace, deposit:DepositProperties) : Observable<DepositUUIDResponse> {
    // Make sure the workspace UUID is provided and extract the account record context
    const context : AccountRecordContext = this.verifyAccountRecordContext(workspace)

    // Call the repository function
    return this.depositsRepository.saveDeposit(context.workspaceUUID, deposit)
  }

  /**
   * Capitalizes the referenced deposit with the given return value within the scope of the referenced
   * workspace
   * 
   * @param workspace          the referenced workspace
   * @param deposit            the referenced deposit
   * @param depositReturnValue the given return value
   * 
   * @returns an observable that lets the consumer know when the operation is complete
   */
  public capitalizeDeposit(workspace:Workspace, deposit:DepositProperties, depositReturnValue:number) : Observable<void> {
    // Make sure the workspace UUID is provided and extract the account record context
    const context : AccountRecordContext = this.verifyAccountRecordContext(workspace)

    // Call the repository function
    return this.depositsRepository.capitalizeDeposit(context.workspaceUUID, deposit, depositReturnValue)
  }

  /**
     * Returns an observable that produces a page of deposits within the referenced
     * workspace, sorted by the projected end date in ascending order. Only the
     * deposits at the referenced bank are fetched. 
     * 
     * @param workspace    the referenced workspace
     * @param bank         the referenced bank
     * @param pageNumber   the zero-based index of the page to be returned
     * @param pageSize     the number of elements to be contained by any given page
     */
  public findSortedPageOfDepositsByWorkspaceAndBank(
    workspace  : Workspace,
    bank       : BankProperties,
    pageNumber : number,
    pageSize   : number
  ) : Observable<AcctPage<DepositProperties>> {
    // Make sure the workspace UUID is provided and extract the account record context
    const context : AccountRecordContext = this.verifyAccountRecordContext(workspace)

    // Call the repository function
    return this.depositsRepository.findSortedPageOfDepositsByWorkspaceUUIDAndOptionalBankUUID(
      context.workspaceUUID,
      bank.bankUUID ?? "",
      pageNumber,
      pageSize
    )
  }

  public findSortedPageOfDepositsToCapitalizeByWorkspaceAndBank(
    workspace  : Workspace,
    bank       : BankProperties,
    pageNumber : number,
    pageSize   : number
  ) : Observable<AcctPage<DepositProperties>> {
    // Make sure the workspace UUID is provided and extract the account record context
    const context : AccountRecordContext = this.verifyAccountRecordContext(workspace)

    // Call the repository function
    return this.depositsRepository.findSortedPageOfDepositsToCapitalizeByWorkspaceUUIDAndBankUUID(
      context.workspaceUUID,
      bank.bankUUID ?? "",
      pageNumber,
      pageSize
    )
  }

  private verifyAccountRecordContext(workspace:Workspace, account?:Account) : AccountRecordContext {
    // The workspace UUID is mandatory
    if (!isDefined(workspace?.workspaceUUID)) {
      throw new Error("Missing workspace UUID")
    }

    // The account UUID is mandatory
    if (isDefined(account) && !isDefined(account?.accountUUID)) {
      throw new Error("Missing account UUID")
    }

    return {
      workspaceUUID : workspace?.workspaceUUID ?? "",
      accountUUID   : account?.accountUUID ?? ""
    }
  }

  private workspaceCollectionsToWorkspaceArray(workspaceCollections:WorkspaceCollections) : Workspace[] {
    return distinctElementsArray(
      workspaceCollections.groupWorkspaces.concat(
        workspaceCollections.publicWorkspaces,
        workspaceCollections.userWorkspaces
      ),
      // Workspaces are uniquely identified by the workspaceUUID
      (workspace:Workspace) => workspace.workspaceUUID
    )
  }

}
