import { Observable } from "rxjs";
import { AccountRecord, AccountRecordInputData } from "../model-acct/account-record";
import { AccountRecordIdResponse } from "../model-acct/account-record-id-response";
import { AcctPage } from "../model-acct/acct-page";
import { CurrencyTransfer } from "../model-acct/currency-transfer";
import { CurrencyExchange } from "../model-acct/currency-exchange";
import { SortDirection } from "../model-acct/sort-direction";

/**
 * Allows creating, reading, updating and deleting account records
 */
export abstract class AcctAccountRecordsRepository {

    /**
     * Creates or updates an account record in the referenced account, within the referenced workspace.
     * The container for the data to be persisted may contain an accountRecordId, in which case, the
     * existing record having the said id is updated. If the accountRecordId is missing, then a new
     * record is created within the account.
     * 
     * @param workspaceUUID the UUID of the referenced workspace
     * @param accountUUID   the UUID of the referenced account
     * @param record        the container for the data to be persisted
     * 
     * @returns an observable that produces a container for the id of the persisted record
     */
    abstract saveAccountRecord(
        workspaceUUID : string,
        accountUUID   : string,
        record        : AccountRecordInputData
    ) : Observable<AccountRecordIdResponse>

    /**
     * Deletes the referenced account record from the account referenced via the given account UUID,
     * which is part of the workspace wirth the given workspace UUID.
     * 
     * @param workspaceUUID the given workspace UUID
     * @param accountUUID   the given account UUID
     * @param record        the referenced account record
     * 
     * @returns an observable that lets consumers know when the operation has ended
     */
    abstract deleteAccountRecord(
        workspaceUUID : string,
        accountUUID   : string,
        record        : AccountRecordInputData
    ) : Observable<void>

    /**
     * Returns an observable that produces a page of account records, optionally filtered by the given
     * text pattern, and sorted by record date in ascending order. The page of records is taken from
     * the account with the given account UUID , which must be part of the workspace with the given
     * workspace UUID. The returned page is as large as the given page size and has the given page number.
     * 
     * @param workspaceUUID UUID that identifies the workspace where the account resides
     * @param accountUUID   UUID that identifies the account that owns the sought records
     * @param pageNumber    the number of the page to fetch
     * @param pageSize      the maximum number of elements to be contained in the returned page
     * @param sortDirection the order in which the records are sorted within the context of the page request
     * @param pattern       optional text pattern against which to match the account record text
     */
    abstract findSortedPageOfAccountRecordsByTextPattern(
        workspaceUUID : string,
        accountUUID   : string,
        pageNumber    : number,
        pageSize      : number,
        sortDirection : SortDirection,
        pattern?      : string
    ) : Observable<AcctPage<AccountRecord>>

    /**
     * Transfers a given amount from a source account to a target account of the same currency.
     * The transfer parameters are contained by the given currency transfer record.
     * 
     * @param workspaceUUID     UUID of the workspace that owns the two accounts
     * @param sourceAccountUUID UUID of the account from where the amount is transferred
     * @param targetAccountUUID UUID of the account to which the amount is transferred
     * @param amount            the amount that is transferred
     * 
     * @returns an observable that lets the consumer know when the transfer is complete
     */
    abstract saveCurrencyTransfer(
        workspaceUUID     : string,
        sourceAccountUUID : string,
        targetAccountUUID : string,
        amount            : number
    ) : Observable<void>

    /**
     * Registers a purchase of a given amount of currency in the target account with a computed
     * amount of currency from the source account. The source account amount is computed based on
     * the amount purchased in the target account and the given exchange rate. Both accounts must
     * be part of the workspace referenced by the given workspace UUID.
     * 
     * @param workspaceUUID           UUID of the workspace that owns the two accounts
     * @param sourceAccountUUID       UUID of the account from where the amount is transferred
     * @param targetAccountUUID       UUID of the account to which the amount is transferred
     * @param amount                  the amount that is transferred
     * @param exchangeRate            The exchange rate at which the currency of the target account is purchased against the currency of the source account
     * @param originalAccountRecordId Optional id of the account record that describes the original exchange of currency for which this currency exchange is a buy-back.
     * 
     * @returns an observable that lets the consumer know when the transfer is complete
     */
    abstract saveCurrencyExchange(
        workspaceUUID            : string,
        sourceAccountUUID        : string,
        targetAccountUUID        : string,
        amount                   : number,
        exchangeRate             : number,
        originalAccountRecordId? : number
    ) : Observable<void>

}