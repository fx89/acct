import { Observable } from "rxjs";
import { AccountRecordInputData, AccountRecord } from "../../model-acct/account-record";
import { AccountRecordIdResponse } from "../../model-acct/account-record-id-response";
import { AcctPage } from "../../model-acct/acct-page";
import { CurrencyExchange } from "../../model-acct/currency-exchange";
import { CurrencyTransfer } from "../../model-acct/currency-transfer";
import { AcctAccountRecordsRepository } from "../account-records-repository";
import { SortDirection } from "../../model-acct/sort-direction";

/**
 * Mock implementation of the AcctAccountRecordsRepository
 */
export class MockAcctAccountRecordsRepository extends AcctAccountRecordsRepository {

    override saveAccountRecord(
        workspaceUUID : string,
        accountUUID   : string,
        record        : AccountRecordInputData
    ): Observable<AccountRecordIdResponse> {
        throw new Error("Method not implemented.");
    }

    override deleteAccountRecord(
        workspaceUUID : string,
        accountUUID   : string,
        record        : AccountRecordInputData
    ): Observable<void> {
        throw new Error("Method not implemented.");
    }

    override findSortedPageOfAccountRecordsByTextPattern(
        workspaceUUID : string,
        accountUUID   : string,
        pageNumber    : number,
        pageSize      : number,
        sortDirection : SortDirection,
        pattern?      : string
    ): Observable<AcctPage<AccountRecord>> {
        throw new Error("Method not implemented.");
    }

    override saveCurrencyTransfer(
        workspaceUUID     : string,
        sourceAccountUUID : string,
        targetAccountUUID : string,
        amount            : number
    ): Observable<void> {
        throw new Error("Method not implemented.");
    }

    override saveCurrencyExchange(
        workspaceUUID            : string,
        sourceAccountUUID        : string,
        targetAccountUUID        : string,
        amount                   : number,
        exchangeRate             : number,
        originalAccountRecordId? : number
    ): Observable<void> {
        throw new Error("Method not implemented.");
    }
    
}