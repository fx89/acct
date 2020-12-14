import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AccountsListComponent } from './components/accounts-list/accounts-list.component';
import { MainSectionComponent } from './components/section-main/main.component';
import { DashboardSectionComponent } from './components/section-dashboard/dashboard.component';
import { AccountsSectionComponent } from './components/section-accounts/accounts.component';
import { DepositsSectionComponent } from './components/section-deposits/deposits.component';
import { SlidingMenuComponent } from './components/sliding-menu/sliding-menu.component';

// https://stackoverflow.com/questions/38892771/cant-bind-to-ngmodel-since-it-isnt-a-known-property-of-input
import { FormsModule } from '@angular/forms';

// https://material.angular.io/components/datepicker/api
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatNativeDateModule, MAT_DATE_FORMATS, DateAdapter, MAT_DATE_LOCALE} from '@angular/material';
import {MatInputModule} from '@angular/material';
import {MatDialogModule} from '@angular/material/dialog';

// https://stackoverflow.com/questions/53359598/how-to-change-angular-material-datepicker-format
import { MY_FORMATS } from './reusable/formats';
import { MomentDateAdapter } from '@angular/material-moment-adapter';

// Google charts - https://www.tutorialspoint.com/angular_googlecharts/angular_googlecharts_quick_guide.htm
import { GoogleChartsModule } from 'angular-google-charts';

import { IncomeOrExpenseCategorySelectComponent } from './components/income-or-expense-category-select/income-or-expense-category-select.component';
import { AccountRecordComponent } from './components/account-record/account-record.component';
import { AccountSummaryComponent } from './components/account-summary/account-summary.component';
import { AccountRecordsComponent } from './components/account-records/account-records.component';
import { AccountRecordsInputFormComponent } from './components/account-records-input-form/account-records-input-form.component';
import { DialogErrorComponent } from './components/dialog-error/dialog-error.component';
import { DialogYesNoComponent } from './components/dialog-yes-no/dialog-yes-no.component';
import { IncomeOrExpenseItemsManagerComponent } from './components/income-or-expense-items-manager/income-or-expense-items-manager.component';
import { EditableNamesListComponent } from './components/editable-names-list/editable-names-list.component';
import { AccountsManagerComponent } from './components/accounts-manager/accounts-manager.component';
import { BanksListComponent } from './components/banks-list/banks-list.component';
import { DepositsTableComponent } from './components/deposits-table/deposits-table.component';
import { DepositsInputFormComponent } from './components/deposits-input-form/deposits-input-form.component';
import { IsoDatePipe } from './pipes/iso-date.pipe';
import { ProgressBarComponent } from './components/progress-bar/progress-bar.component'; // don't forget [npm i @angular/material-moment-adapter] and [npm i moment]
import { MonthlyBalanceReportComponent } from './components/monthly-balance-report/monthly-balance-report.component';
import { MonthlySavingsReportComponent } from './components/monthly-savings-report/monthly-savings-report.component';
import { CurrenciesManagerComponent } from './components/currencies-manager/currencies-manager.component';
import { SectionExchangeRatesComponent } from './components/section-exchange-rates/section-exchange-rates.component';
import { MoneyTransferFormComponent } from './components/money-transfer-form/money-transfer-form.component';


@NgModule({
  declarations: [
    AppComponent,
    AccountsListComponent,
    MainSectionComponent,
    DashboardSectionComponent,
    AccountsSectionComponent,
    DepositsSectionComponent,
    SlidingMenuComponent,
    IncomeOrExpenseCategorySelectComponent,
    AccountRecordComponent,
    AccountSummaryComponent,
    AccountRecordsComponent,
    AccountRecordsInputFormComponent,
    DialogErrorComponent,
    DialogYesNoComponent,
    IncomeOrExpenseItemsManagerComponent,
    EditableNamesListComponent,
    AccountsManagerComponent,
    BanksListComponent,
    DepositsTableComponent,
    DepositsInputFormComponent,
    IsoDatePipe,
    ProgressBarComponent,
    MonthlyBalanceReportComponent,
    MonthlySavingsReportComponent,
    CurrenciesManagerComponent,
    SectionExchangeRatesComponent,
    MoneyTransferFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatInputModule,
    MatDialogModule,
    GoogleChartsModule
  ],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS}
  ],
  bootstrap: [AppComponent],
  entryComponents: [DialogErrorComponent, DialogYesNoComponent, IncomeOrExpenseItemsManagerComponent, AccountsManagerComponent, MoneyTransferFormComponent]
})
export class AppModule { }
