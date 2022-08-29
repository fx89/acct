import { EventEmitter, Injectable } from '@angular/core';
import { Account } from 'src/app/model/account';
import { AccountSummary } from 'src/app/model/account-summary';
import { DataService } from '../data-service/data.service';
import { Observable, BehaviorSubject } from 'rxjs';
import { AccountRecord } from 'src/app/model/account-record';
import { IncomeOrExpenseItem } from 'src/app/model/income-or-expense-item';
import { MatDialog } from '@angular/material';
import { DialogErrorComponent } from 'src/app/components/dialog-error/dialog-error.component';
import { DialogYesNoComponent } from 'src/app/components/dialog-yes-no/dialog-yes-no.component';
import { DialogService } from '../dialog-service/dialog.service';
import { ErrorResult } from 'src/app/model/error-result';
import { OkResult } from 'src/app/model/ok-result';
import { IncomeOrExpenseItemCategory } from 'src/app/model/income-or-expense-item-category';
import { Nameable } from 'src/app/model/nameable';
import { Identifiable } from 'src/app/model/identifiable';
import { Bank } from 'src/app/model/bank';
import { Deposit } from 'src/app/model/deposit';
import { MonthlyBalanceReportRecord } from 'src/app/model/monthly-balance-report-record';
import { ParamsBuilder } from 'src/app/reusable/params.builder';
import { MonitoredCurrency } from 'src/app/model/monitored-currency';
import { ExchangeRateHistoryRecord } from 'src/app/model/ExchangeRateHistoryRecord';
import { MonthlyExpensesReport } from 'src/app/model/monthly-expenses-report';

@Injectable({
  providedIn: 'root'
})
export class StateService {
    private RECS_PER_PAGE : number = 20;

    public accounts: Account[] = [];
    public selectedAccount : Account;
    public selectedAccountSummary : AccountSummary;
    public selectedAccountRecords : AccountRecord[];
    public selectedAccountRecord : AccountRecord;
    public selectedAccountCurrentPage : number = 0;

    public accountSummaries : AccountSummary[];
    public accountSummariesLoaded : boolean = false;

    public incomeOrExpenseItems : IncomeOrExpenseItem[];
    public incomeOrExpenseItemCategories : IncomeOrExpenseItemCategory[];
    public selectedIncomeOrExpenseItemCategory : IncomeOrExpenseItemCategory;

    // https://stackoverflow.com/questions/37587732/how-to-call-another-components-function-in-angular2
    private selectedRecordChangedMessageSource : BehaviorSubject<AccountRecord> = new BehaviorSubject(null);
    public selectedRecordChangedObservable : Observable<AccountRecord> = this.selectedRecordChangedMessageSource.asObservable();

    private selectedDepositChangedMessageSource : BehaviorSubject<Deposit> = new BehaviorSubject(null);
    public selectedDepositChangedObservable : Observable<Deposit> = this.selectedDepositChangedMessageSource.asObservable();

    private loadedDepositsMessageSource : BehaviorSubject<Deposit[]> = new BehaviorSubject(null);
    public loadedDepositsMessageSourceObservable : Observable<Deposit[]> = this.loadedDepositsMessageSource.asObservable();

    private loadedMonthlyAccountBalanceReportSource : BehaviorSubject<MonthlyBalanceReportRecord[]> = new BehaviorSubject(null);
    public loadedMonthlyAccountBalanceReportObservable : Observable<MonthlyBalanceReportRecord[]> = this.loadedMonthlyAccountBalanceReportSource.asObservable();


    public banks : Bank[];
    public selectedBank : Bank;

    public deposits : Deposit[];
    public selectedDeposit : Deposit;

    public monthlyBalanceReport : MonthlyBalanceReportRecord[] = [];
    public inlineHistoryReportMonths : number = 6;

    public monthlyDepositsBalanceReport : MonthlyBalanceReportRecord[] = [];

    public supportedCurrencies : any[];
    public monitoredCurrencies : MonitoredCurrency[] = [];

    public exchageRatesHistory : Map<String, ExchangeRateHistoryRecord[]> = new Map<String, ExchangeRateHistoryRecord[]>();
    public exchangeRatesHistoryLengthDays : number = 200;
    public allExchangeRatesHistoryLoaded : boolean = false;

    public totalDepositsValue : number;
    public totalDepositsInterest : number;
    public firstDepositToReachMaturity : Deposit = null;

    public monthlyExpensesReports : MonthlyExpensesReport[];

    constructor(private dataService : DataService, private dialog: MatDialog, private dialogService : DialogService) { }

    public loadMonthlyExpensesReports() {
        if (this.monthlyExpensesReports) {
            return;
        }

        let params = new ParamsBuilder().withNumberParam("startMonth", this.computeMonthNumberMinusXMonths(this.inlineHistoryReportMonths)).build();

        this.dataService.get<MonthlyExpensesReport[]>("reports/accounts/monthlyExpenses", params).subscribe(
            response => {
                let errMsg : string = StateService.resultErrorMsg(response);

                if (errMsg) {
                    this.showErrDialog("ERROR: " + errMsg);
                } else {
                    this.monthlyExpensesReports = response;
                }
            }
        );
    }

    private clearExchangeRatesHistory() {
        this.exchageRatesHistory = new Map<String, ExchangeRateHistoryRecord[]>();
        this.allExchangeRatesHistoryLoaded = false;
    }

    public loadExchangeRatesHistory() {
        this.allExchangeRatesHistoryLoaded = false;

        if (this.monitoredCurrencies) {
            this.loadExchangeRatesHistoryForAllMonitoredCurrencies();
        } else {
            this.loadMonitoredCurrency().subscribe(
                result => {
                    if (this.monitoredCurrencies) {
                        this.loadExchangeRatesHistoryForAllMonitoredCurrencies();
                    }
                }
            );
        }
    }

    public monitoredCurrencyHashName(c:MonitoredCurrency) : string {
        return c.currencyTypeName + " - " + this.getBank(c.bankId).name
    }

    private updateExchangeRatesHistoryRecordsState() {
        this.allExchangeRatesHistoryLoaded = false;

        for (let c of this.monitoredCurrencies) {
            if (!(this.exchageRatesHistory.get(this.monitoredCurrencyHashName(c)))) {
                return;
            }
        }

        this.allExchangeRatesHistoryLoaded = true;
    }

    private loadExchangeRatesHistoryForAllMonitoredCurrencies() {
        for (let cr of this.monitoredCurrencies) {
            this.loadExchangeRatesHistoryForCurrency(cr);
        }
    }

    public loadExchangeRatesHistoryForCurrency(currency : MonitoredCurrency) {
        if (this.exchageRatesHistory.get(this.monitoredCurrencyHashName(currency))) {
            this.updateExchangeRatesHistoryRecordsState();
            return;
        }

        let params = new ParamsBuilder()
                            .withNumberParam("currencyId", currency.id)
                            .withStringParam("sinceDate", this.computeDateMinusXDays(this.exchangeRatesHistoryLengthDays))
                        .build();

        this.dataService.get<ExchangeRateHistoryRecord[]>("currency/listHistory", params).subscribe(
            result => {
                let errMsg : string = StateService.resultErrorMsg(result);

                if (errMsg) {
                    this.showErrDialog("ERROR: " + errMsg);
                } else {
                    this.exchageRatesHistory.set(this.monitoredCurrencyHashName(currency), result);
                    this.updateExchangeRatesHistoryRecordsState();
                }
            }
        );
    }

    public computeMonitoredCurrenciesForSelectedBank() : MonitoredCurrency[] {
        const ret : MonitoredCurrency[] = []

        if (this.selectedBank) {
            for(let mc of this.monitoredCurrencies) {
                if (mc.bankId == this.selectedBank.id) {
                    ret.push(mc)
                }
            }
        }

        return ret
    }

    public loadMonitoredCurrency() : Observable<MonitoredCurrency[]> {
        const ret : EventEmitter<MonitoredCurrency[]> = new EventEmitter<MonitoredCurrency[]>()

        this.loadBanks().subscribe(() => {
            const obs : Observable<MonitoredCurrency[]> = this.dataService.get<MonitoredCurrency[]>("currency/list")

            obs.subscribe(monCr => {
                this.monitoredCurrencies = monCr;

                this.dataService.get<String[]>("currency/listSupportedCurrencies").subscribe(
                    supCr => {
                        this.supportedCurrencies = [];

                        for(let bnk of this.banks) {
                            this.supportedCurrencies[bnk.id] = []

                            for (let sc of supCr) {
                                if (!(this.isCurrencyMonitored(sc, bnk.id))) {
                                    this.supportedCurrencies[bnk.id].push(sc);
                                }
                            }
                        }

                        this.clearExchangeRatesHistory();

                        ret.emit(monCr)
                    }
                );
            });
        })

        return ret;
    }

    public monitorCurrency(currencyName : String, bankId : number) {
        let params = new ParamsBuilder()
                            .withStringParam("currencyName", currencyName)
                            .withNumberParam("bankId", bankId)
                        .build();
 
        this.dataService.post<MonitoredCurrency>("currency/monitorCurrency", params)
        .subscribe(
            mCr => {
                this.monitoredCurrencies.push(mCr);
                this.popSupportedCurrency(currencyName);
                this.clearExchangeRatesHistory();
            }
        );
        
    }

    public unMonitorCurrency(cr : MonitoredCurrency) {
        this.dataService.delete<MonitoredCurrency>("currency", cr).subscribe(
            result => {
                let errMsg : string = StateService.resultErrorMsg(result);

                if (errMsg) {
                    this.showErrDialog("ERROR: " + errMsg);
                } else {
                    this.supportedCurrencies.push(cr.currencyTypeName);
                    this.popMonitoredCurrency(cr);
                    this.clearExchangeRatesHistory();
                }
            }
        );
    }

    public updateCurrenciesFromSource() {
        const ret : EventEmitter<any> = new EventEmitter<any>()

        if (this.selectedBank) {
            this.dataService.get<String>(
                "currency/updateCurrenciesFromSource", 
                new ParamsBuilder().withNumberParam("bankId", this.selectedBank.id).build()
            )
            .subscribe(
                result => {
                    let errMsg : string = StateService.resultErrorMsg(result)

                    if (errMsg) {
                        this.showErrDialog("ERROR: " + errMsg);
                    } else {
                        this.loadMonitoredCurrency();
                        ret.emit(result)
                    }
                }
            )
        } else {
            this.showErrDialog("Please select a bank")
            ret.emit("Bank not selected")
        }

        return ret;
    }

    private popSupportedCurrency(spCrName : String) {
        let oldArr : String[] = this.supportedCurrencies;
        this.supportedCurrencies = [];

        for (let c of oldArr) {
            if (c != spCrName) {
                this.supportedCurrencies.push(c);
            }
        }
    }

    private popMonitoredCurrency(mc : MonitoredCurrency) {
        let oldArr : MonitoredCurrency[] = this.monitoredCurrencies;
        this.monitoredCurrencies = [];

        for (let amc of oldArr) {
            if (amc.id != mc.id) {
                this.monitoredCurrencies.push(amc);
            }
        }
    }

    private isCurrencyMonitored(crName : String, bankId : number) : boolean {
        for (let mCr of this.monitoredCurrencies) {
            if (mCr.currencyTypeName == crName && mCr.bankId == bankId) {
                return true;
            }
        }
        return false;
    }

    public loadMonthlyDepositsBalanceReport() {
        this.loadMonthlyBalanceReport(null, "deposits/monthlyBalance", ret => { this.monthlyDepositsBalanceReport = ret });
    }

    public loadMonthlyBalanceReportForCurrentAccount() {
        if (!(this.selectedAccount)) {
            return;
        }

        this.loadMonthlyBalanceReport(this.selectedAccount.id, "accounts/monthlyBalance", ret => {this.monthlyBalanceReport = ret;});
    }

    private loadMonthlyBalanceReport(accountId : Number, reportPath : string, callback : (ret : MonthlyBalanceReportRecord[]) => void) {
        // Build the params map for the request
        let startMonth : number = this.computeMonthNumberMinusXMonths(this.inlineHistoryReportMonths);
        let pBbuilder = new ParamsBuilder().withNumberParam("startMonth", startMonth);
        if (accountId) {
            pBbuilder = pBbuilder.withNumberParam("accountId", accountId);
        }
        let params = pBbuilder.build();

        // Send the requeest and process the result upon response arrival
        this.dataService.get<MonthlyBalanceReportRecord[]>("reports/" + reportPath, params)
        .subscribe(ret => {
            let targetArray : MonthlyBalanceReportRecord[] = [];

            for(let rec of ret) {
                targetArray.push(
                    new MonthlyBalanceReportRecord(
                        rec.recordYearMonth,
                        rec.startingBalance,
                        rec.incomingBalance,
                        rec.outgoingBalance
                    )
                );
            }
            callback(targetArray);
        });
    }

    private computeMonthNumberMinusXMonths(x : number) : number {
        let today : Date = new Date();
        let xMonthsAgo = new Date(); xMonthsAgo.setTime(today.getTime() - (x * 20 * 24*60*60*1000));

        let year : number = xMonthsAgo.getUTCFullYear();
        let month : number = xMonthsAgo.getUTCMonth();

        return (year * 100) + month;
    }

    private computeDateMinusXDays(x : number) : String {
        let d : Date = new Date(new Date().getDate() - x);
        return this.formatDate(d);
    }

    public formatDate(d : Date) : String {
        return d.getUTCFullYear() + "-" + this.twoDigits(d.getMonth()) + "-" + this.twoDigits(d.getUTCDay());
    }

    private twoDigits(n : number) : String {
        if (n < 10) {
            return "0" + n;
        }

        return "" + n;
    }

    public loadDeposits() {
        if (this.deposits == null || this.deposits == undefined) {
            this.dataService.list<Deposit[]>("deposits").subscribe(
                deposits => {
                    let errMsg : string = StateService.resultErrorMsg(deposits);

                    if (errMsg) {
                        this.showErrDialog("ERROR : " + errMsg);
                        this.selectDeposit(this.selectedDeposit); // Select again to make sure the edit form is updated
                    } else {
                        this.deposits = deposits;
                        this.selectedDeposit = null;
                        this.loadedDepositsMessageSource.next(this.deposits);

                        this.loadMonthlyDepositsBalanceReport();
                        this.cacheDepositsStatistics();
                    }
                }
            );
        }
    }

    private cacheDepositsStatistics() {
        this.totalDepositsValue = 0;
        this.totalDepositsInterest = 0;

        if (this.deposits && this.deposits.length > 0) {
            this.firstDepositToReachMaturity = null;

            for (let dep of this.deposits) {
                // Cache the counters
                this.totalDepositsValue += dep.value ? dep.value.valueOf() : 0;
                this.totalDepositsInterest += dep.value && dep.interestPercent ? (dep.value.valueOf() * dep.interestPercent.valueOf() / 100) : 0;

                // Find the first deposit to reach matureity
                if (this.firstDepositToReachMaturity == null || this.firstDepositToReachMaturity.endDate > dep.endDate) {
                    this.firstDepositToReachMaturity = dep;
                }
            }
        }
    }

    public newDeposit() {
        if (this.accounts == null || this.accounts.length == 0) {
            this.showErrDialog("At least one account must be registered before creating a deposit");
            return;
        }

        if (this.banks == null || this.banks.length == 0) {
            this.showErrDialog("At least one bank must be registered before creating a deposit");
            return;
        }

        this.selectedDeposit = new Deposit(0);

        this.selectedDeposit.sourceAccountId = this.accounts[0].id;
        this.selectedDeposit.bankId = this.banks[0].id;
        this.selectedDeposit.accountNumber = "New deposit";
        this.selectedDeposit.startDate = new Date();
        this.selectedDeposit.endDate = this.addYears(this.selectedDeposit.startDate, 1);

        this.deposits.push(this.selectedDeposit);

        this.resetCatalogues();
    }

    private resetCatalogues() {
        this.incomeOrExpenseItemCategories = null;
        this.incomeOrExpenseItems = null;
    }

    private addYears(date : Date, nYears : number) : Date {
        let year  = date.getFullYear();
        let month = date.getMonth();
        let day   = date.getDay();

        return new Date(year + nYears, month, day);
    }

    public selectDeposit(deposit : Deposit) {
        this.selectedDeposit = deposit;
        this.selectedDepositChangedMessageSource.next(this.selectedDeposit); // propagate the message
    }

    public saveSelectedDeposit(saveOnlyAccountNumber : boolean = false) {
        this.dataService.save<Deposit>("deposits", this.selectedDeposit, (saveOnlyAccountNumber ? "saveAccountNumber" : null)).subscribe(
            deposit => {
                let errMsg : string = StateService.resultErrorMsg(deposit);

                if (errMsg) {
                    this.showErrDialog("ERROR : " + errMsg);
                    this.selectDeposit(this.selectedDeposit); // Select again to make sure the edit form is updated
                } else {
                    this.selectedDeposit.id = deposit.id;
                    this.integrateIdentifiable(deposit, this.deposits);
                    this.selectedDeposit = null;
                }
            }
        );
    }

    public loadBanks() : Observable<any> {
        const ret = new EventEmitter<any>()

        if (this.banks == null || this.banks == undefined) {
            this.dataService.list<Bank[]>("banks").subscribe(
                banks => {
                    this.banks = banks
                    ret.emit()
                }
            );
        } else {
            setTimeout(() => ret.emit(), 500)
        }

        return ret;
    }

    public saveBank(bank : Bank) {
        this.dataService.save<Bank>("banks", bank).subscribe(
            bnk => {
                bank.id = bnk.id;
                this.integrateIdentifiable(bnk, this.banks);
            }
        );
    }

    public getBank(id : Number) {
        return this.getIdentifiable<Bank>(this.banks, id);
    }

    private getIdentifiable<T extends Identifiable>(itemsArray : T[], id : Number) : T {
        for(let item of itemsArray) { if (item.id == id) { return item; } }
        return null;
    }

    public loadAccounts() : Observable<Account[]> {
        let ret : Observable<Account[]> = this.dataService.list<Account[]>("accounts");

        ret.subscribe((result : Account[]) => {
            this.accounts = result;
            this.selectedAccount = (result[0] == null || result[0] == undefined) ? null : result[0];
            this.selectedAccount.currencyId = this.selectedAccount.currencyId ? this.selectedAccount.currencyId : 0;
        });

        return ret;
    }

    public selectAccountId(accountId : Number){
        for (let acc of this.accounts) {
            if (acc.id == accountId) {
                this.selectedAccount = acc;
                this.clearSelectedAccountRecord();
                break;
            }
        }

        this.loadSelectedAccountSummary().subscribe(ret => {
            this.loadSelectedAccountRecords();
        });

        this.loadMonthlyBalanceReportForCurrentAccount();
    }

    public saveSelectedAccount() {
        this.saveAccount(this.selectedAccount);
    }

    public saveAccount(account : Account) {
        this.dataService.save<Account>("accounts", account).subscribe(
            acc => {
                account.id = acc.id;
                account.foreignCurrencyAccount = acc.foreignCurrencyAccount;
                account.currencyId = acc.currencyId ? acc.currencyId : 0;
                this.integrateIdentifiable(account, this.accounts);
            }
        );
    }

    public getAccount(id : Number) : Account {
        return this.getIdentifiable<Account>(this.accounts, id);
    }

    public selectAccountRecord(rec : AccountRecord) {
        this.selectedAccountRecord = rec; // set the selected record
        this.selectedRecordChangedMessageSource.next(this.selectedAccountRecord); // propagate the message

    }

    public saveSelectedAccountRecord() {
        this.dataService.save<AccountRecord>("records", this.selectedAccountRecord)
            .subscribe(rec => {
                let errMsg : string = StateService.resultErrorMsg(rec);
                if (errMsg) {
                    this.showErrDialog("ERROR : " + errMsg);
                    this.selectAccountRecord(this.selectedAccountRecord); // Select again to make sure the edit form is updated
                } else {
                    this.monthlyExpensesReports = null;

                    this.selectedAccountRecord.id = rec.id;

                    // Reload the account summary since:
                    //    1) applying delta involves having a lot of code to maintain
                    //    2) the load on the server is not that high when fetching the summary
                    this.loadSelectedAccountSummary();

                    // Clear the selected account record
                    this.clearSelectedAccountRecord();
                }
            });
    }

    public newAccountRecord() {
        this.clearSelectedAccountRecord();
        let rec : AccountRecord = this.createNewAccountRecord();
        this.selectedAccountRecords.push(rec);
        this.selectAccountRecord(rec);
    }

    public deleteAccountRecord(rec : AccountRecord) {
        this.clearSelectedAccountRecord();

        this.dialogService.dialogYesNoResponseObservable.subscribe(rsp => {
            if (rsp) {
                this.dataService.delete<AccountRecord>("records", rec).subscribe(result => {
                    if (StateService.resultOk(result)) {
                        this.popAccountRecord(rec);
                    } else {
                        let errResult : ErrorResult = <ErrorResult><unknown>result;
                        this.showErrDialog("ERROR: " + errResult.message);
                    }
                });
            }
        });

        this.showYesNoDialog("Really delete account record ?");
    }

    public prevAcctRecsPage() {
        if (this.selectedAccountCurrentPage > 0) {
            this.selectedAccountCurrentPage--;
            this.loadSelectedAccountRecords();
        }
    }

    public nextAcctRecsPage() {
        const prevRecs = this.selectedAccountRecords;
        this.selectedAccountCurrentPage++;
        this.loadSelectedAccountRecords(() => {
            if (!this.selectedAccountRecords || !this.selectedAccountRecords[0]) {
                this.selectedAccountCurrentPage--;
                this.selectedAccountRecords = prevRecs;
            }
        })
    }

    public getMonitoiredCurrency(currencyId : Number) : MonitoredCurrency {
        for(let mCur of this.monitoredCurrencies) {
            if (mCur.id == currencyId) {
                return mCur;
            }
        }

        return null;
    }

    public saveIncomeOrExpenseItem(item : IncomeOrExpenseItem) : Observable<IncomeOrExpenseItem> {
        let obs = this.dataService.save<IncomeOrExpenseItem>("items", item);
        
        obs.subscribe(
            ret => {
                this.incomeOrExpenseItems = null;
                this.loadIncomeOrExpenseItems();
            }
        );

        return obs;
    }

    public moneyTransfer(fromAccountId : Number, toAccountId : Number, amount : Number) {
        let params = new ParamsBuilder()
                            .withNumberParam("fromAccountId", fromAccountId)
                            .withNumberParam("toAccountId", toAccountId)
                            .withNumberParam("amount", amount)
                        .build();

        this.dataService.get<String>("accounts/transfer", params).subscribe(
            ret => {
                let err : ErrorResult = <ErrorResult><unknown>ret;

                let errMsg : string = StateService.resultErrorMsg(err);

                if (errMsg) {
                    this.showErrDialog("ERROR: " + errMsg);
                } else {
                    this.incomeOrExpenseItems = null;
                    this.incomeOrExpenseItemCategories = null;
                    this.loadIncomeOrExpenseItemCategories();
                    this.loadIncomeOrExpenseItems();

                    if (this.selectedAccount) {
                        if (this.selectedAccount.id == fromAccountId || this.selectedAccount.id == toAccountId) {
                            this.selectAccountId(this.selectedAccount.id);
                        }
                    }
                }
            }
        );
    }

    private static resultOk(result: any) : boolean {
        return result && (<OkResult><unknown>result).text == "OK";
    }

    private static resultErrorMsg(result: any) : string {
        let err : ErrorResult = <ErrorResult><unknown>result;

        if (err && err.message) {
            return err.message;
        } else {
            return null;
        }
    }

    public showErrDialog(errStr : string) {
        this.dialogService.dialogMessage = errStr;
        this.dialogService.errDialogRef = this.dialog.open(DialogErrorComponent, {
            height: '150px',
            width: '300px'
          });
    }

    private showYesNoDialog(msgStr : string) {
        this.dialogService.dialogMessage = msgStr;
        this.dialogService.yesNoDialogRef = this.dialog.open(DialogYesNoComponent, {
            height: '150px',
            width: '300px'
          });
    }

    private popAccountRecord(rec : AccountRecord) {
        for(let r = 0 ; r < this.selectedAccountRecords.length ; r++) {
            if (this.selectedAccountRecords[r].id == rec.id) {
                for(let rr = r ; rr < this.selectedAccountRecords.length ; rr++) {
                    this.selectedAccountRecords[rr] = this.selectedAccountRecords[rr+1];
                }
                this.selectedAccountRecords.pop();
                break;
            }
        }
    }

    private createNewAccountRecord() : AccountRecord {
        let rec : AccountRecord = new AccountRecord(null);
        rec.accountId = this.selectedAccount.id;
        rec.date = new Date();
        rec.incomeOrExpenseItemId = 0;
        rec.value = 0;
        return rec;
    }

    private clearSelectedAccountRecord() {
        this.selectedAccountRecord = null;
        this.monthlyBalanceReport = [];
        this.selectedAccountCurrentPage = 0;
    }

    public loadSummaryOfAllAccounts() {
        if (this.accounts.length > 0) {
            this.loadSummaryOfAllAccountsPresumingAccountsHaveBeenLoaded();
        } else {
            this.loadAccounts().subscribe(result => {
                this.loadSummaryOfAllAccountsPresumingAccountsHaveBeenLoaded();
            });
        }
    }

    private loadSummaryOfAllAccountsPresumingAccountsHaveBeenLoaded() {
        this.accountSummaries = [];
        this.accountSummariesLoaded = false;

        for(let acc of this.accounts) {
            this.loadAccountSummary(acc.id).subscribe(acSum => {
                this.accountSummaries[acc.id.valueOf()] = acSum;
                this.updateAccountSummariesStatus();
            })
        }
    }

    private updateAccountSummariesStatus() {
        this.accountSummariesLoaded = false;

        for (let acc of this.accounts) {
            if (!(this.accountSummaries[acc.id.valueOf()])) {
                return;
            }
        }

        this.accountSummariesLoaded = true;
    }

    private loadSelectedAccountSummary() : Observable<AccountSummary> {
        let ret = this.loadAccountSummary(this.selectedAccount.id);

        ret.subscribe(accountSummary => {
            this.selectedAccountSummary = accountSummary;
        });

        return ret;
    }

    private loadAccountSummary(accountId : Number) : Observable<AccountSummary> {
        return this.dataService.get<AccountSummary>("accounts/summary", StateService.makeIdParam("accountId", accountId));
    }

    private loadSelectedAccountRecords(then?:Function) {
        const params = new Map([
            ["accountId"  , this.selectedAccount.id.toFixed()],
            ["pageNumber" , this.selectedAccountCurrentPage.toFixed()],
            ["rowsPerPage", this.RECS_PER_PAGE.toFixed()]
        ]);

        this.dataService.get<AccountRecord[]>("records/page", params)
          .subscribe(ret => {
              this.selectedAccountRecords = ret;
              if (then) {
                  then()
              }
          });
    }

    public loadIncomeOrExpenseItems() {
        if (this.incomeOrExpenseItems == null || this.incomeOrExpenseItems == undefined) {
            this.loadList<IncomeOrExpenseItem>("items", (result => {
                this.incomeOrExpenseItems = this.sortIncomeOrExpenseItems(result);
                
            }));
        }
    }

    public getIncomeOrExpenseItem(itemId : Number) : IncomeOrExpenseItem {
        for (let item of this.incomeOrExpenseItems) {
            if (item.id == itemId) {
                return item;
            }
        }

        return null;
    }

    private sortIncomeOrExpenseItems(items : IncomeOrExpenseItem[]) : IncomeOrExpenseItem[] {
        return items.sort( (a, b) => { 
            if (a.incomeOrExpenseItemCategoryId == b.incomeOrExpenseItemCategoryId) {
                return (a.name < b.name ? -1 : 1);
            } else {
                if (a.incomeOrExpenseItemCategoryId) {
                    return a.incomeOrExpenseItemCategoryId.valueOf() - b.incomeOrExpenseItemCategoryId.valueOf();
                } else {
                    return -1;
                }
            }
         } )
    }

    public loadIncomeOrExpenseItemCategories() {
        if (this.incomeOrExpenseItemCategories == null || this.incomeOrExpenseItemCategories == undefined) {
            this.loadList<IncomeOrExpenseItemCategory>(
                "categories",
                result => {
                    this.incomeOrExpenseItemCategories = result;
                    this.selectedIncomeOrExpenseItemCategory = this.incomeOrExpenseItemCategories[0];
                }
            );
        }
    }

    public saveIncomeOrExpenseItemCategory(category : IncomeOrExpenseItemCategory) : Observable<IncomeOrExpenseItemCategory> {
        let obs = this.dataService.save<IncomeOrExpenseItemCategory>("categories", category);
        
        obs.subscribe(
            cat => {
                category.id = cat.id;
                this.integrateIdentifiable<IncomeOrExpenseItemCategory>(cat, this.incomeOrExpenseItemCategories);
            }
        );

        return obs;
    }

    private integrateIdentifiable<T extends Identifiable>(item : T, array : T[]) {
        for(let c = 0 ; c < array.length ; c++) {
            if (array[c].id == item.id) {
                array[c] = item;
                return;
            }
        }

        array.push(item);
    }

    private loadList<T extends Nameable>(wsPath: string, callback:Function) {
        this.dataService.list<T[]>(wsPath).subscribe(items => {
            let newList : T[] = [];

            for(let item of items) {
                newList[item.id.valueOf()] = item;
            }

            newList[0] = <T>(new Nameable(0, "Not selected"));

            callback(newList);
      });
    }

    private static makeIdParam(paramName : string, paramValue : Number) : Map<string,string> {
        return new Map([[paramName, paramValue.toFixed()]]);
    }

    public selectMonitoredCurrency(currencyId : Number){
        for (let crr of this.monitoredCurrencies) {
            if (crr.id == currencyId) {
                this.selectedAccount.currencyId = crr.id;
                break;
            }
        }
        this.saveSelectedAccount();
    }
}
