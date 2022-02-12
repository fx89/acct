import { Component, OnInit } from '@angular/core';
import { StateService } from 'src/app/services/state-service/state.service';
import { Account } from 'src/app/model/account';
import { IncomeOrExpenseItemCategory } from 'src/app/model/income-or-expense-item-category';
import { IncomeOrExpenseItem } from 'src/app/model/income-or-expense-item';
import { MonthlyReportSimpleRecord } from 'src/app/model/monthly-report-simple-record';
import { MonthlyExpensesReport } from 'src/app/model/monthly-expenses-report';

@Component({
  selector: 'app-section-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardSectionComponent implements OnInit {

    private sourceXValueMapper : (rec) => any = (rec) => { return rec.recordYearMonth.toString(); };
    private sourceYValueMappers : [] = [];

    private selectedAccount : Account = null;
    private selectedCategory : IncomeOrExpenseItemCategory = null;
    private selectedItem : IncomeOrExpenseItem = null;

    private contextItems : IncomeOrExpenseItem[] = null;

    private monthlyExpensesReportChartData : MonthlyReportSimpleRecord[] = null;

    private monthlyExpensesReportChartXValueMapper : (rec) => any = (rec) => {return rec.month; }
    private monthlyExpensesReportChartYValueMapper : (rec) => any = (rec) => {return rec.value; }

    constructor(private state : StateService) { }

    ngOnInit() {
        this.state.loadIncomeOrExpenseItems();
        this.state.loadIncomeOrExpenseItemCategories();
        this.state.loadMonthlyExpensesReports();

        this.state.loadMonitoredCurrency().subscribe(mnCr => {
            for(let mc of mnCr) {
                const den = mc.lastCollectedValue ? mc.lastCollectedValue.valueOf() : 1
                this.sourceYValueMappers[mc.currencyTypeName.valueOf()]
                    = (rec) => { return rec.remainingBalance / den };
            }
            
        });

        this.state.loadDeposits();
        this.state.loadSummaryOfAllAccounts();
    }

    private getAccountValue(accId : Number) {
        let acSum = this.state.accountSummaries[accId.valueOf()];
        return acSum.runningSumIncoming + acSum.runningSumOutgoing;
    }

    private onAccountChanged() {
        this.monthlyExpensesReportChartData = null;
        this.computeMonthlyExpensesReportChartData();
    }

    private onCategoryChanged() {
        this.monthlyExpensesReportChartData = null;
        this.contextItems = null;
        this.selectedItem = null;
        this.computeMonthlyExpensesReportChartData();
    }

    private onItemChanged() {
        this.monthlyExpensesReportChartData = null;
        this.computeMonthlyExpensesReportChartData();
    }

    private isItemValidInContext(item : IncomeOrExpenseItem) : boolean {
        return (!(this.selectedCategory)) || item.incomeOrExpenseItemCategoryId == this.selectedCategory.id;
    }

    private getValidIncomeOrExpenseItems() {
        if (this.contextItems) {
            return this.contextItems;
        }

        let ret = [];

        for (let itm of this.state.incomeOrExpenseItems) {
            if (this.isItemValidInContext(itm)) {
                ret.push(itm);
            }
        }

        return ret;
    }

    private computeMonthlyExpensesReportChartData() {
        this.monthlyExpensesReportChartData = [];

        if (this.selectedAccount) {
            this.addMonthlyExpensesReportDataForAccount(this.selectedAccount.id);
        } else {
            for (let acct of this.state.accounts) {
                this.addMonthlyExpensesReportDataForAccount(acct.id);
            }
        }
    }

    private addMonthlyExpensesReportDataForAccount(accountId : Number) {
        let acctReport : MonthlyExpensesReport = this.findMonthlyExpensesAccountReport(accountId);

        if (acctReport) {
            for (let month of acctReport.months) {
                let repMonth : MonthlyReportSimpleRecord = this.findReportMonthRecord(month.monthNumber);

                for (let cat of month.categories) {
                    if (!(this.selectedCategory) || this.selectedCategory.id == cat.categoryId) {

                        for (let itm of cat.items) {
                            if (!(this.selectedItem) || this.selectedItem.id == itm.itemId) {
                                if (repMonth.value) {
                                    repMonth.value += itm.totalExpenses.valueOf();
                                } else {
                                    repMonth.value = itm.totalExpenses.valueOf();
                                }
                            }
                        }

                    }
                }
            }
        }

    }

    private findMonthlyExpensesAccountReport(accountId : Number) : MonthlyExpensesReport {
        for (let rep of this.state.monthlyExpensesReports) {
            if (rep.accountId == accountId) {
                return rep;
            }
        }

        return null;
    }

    private findReportMonthRecord(monthNumber : Number) : MonthlyReportSimpleRecord {
        for (let rec of this.monthlyExpensesReportChartData) {
            if (rec.month == monthNumber.toString()) {
                return rec;
            }
        }

        let rec : MonthlyReportSimpleRecord = new MonthlyReportSimpleRecord();
        rec.month = monthNumber.toString();
        rec.value = 0;
        this.monthlyExpensesReportChartData.push(rec);
        return rec;
    }
}
