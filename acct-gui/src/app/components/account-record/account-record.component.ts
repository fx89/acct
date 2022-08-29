import { Component, OnInit, Input } from '@angular/core';
import { IncomeOrExpenseItem } from 'src/app/model/income-or-expense-item';
import { StateService } from 'src/app/services/state-service/state.service';
import { AccountRecord } from 'src/app/model/account-record';
import * as uuid from 'uuid';

@Component({
  selector: 'app-account-record',
  templateUrl: './account-record.component.html',
  styleUrls: ['./account-record.component.css']
})
export class AccountRecordComponent implements OnInit {

    @Input() rec : AccountRecord;

    id : string = "_" + uuid.v4();

    private previouslyShownExchangeRateDetailsBoxId

    constructor(private state : StateService) {

    }

    ngOnInit() {
        this.state.loadIncomeOrExpenseItemCategories();
    }

    private getIncomeOrExpenseItemName(itemId : Number) {
        let ret : IncomeOrExpenseItem = itemId ? this.state.getIncomeOrExpenseItem(itemId) : null;

        if (ret == null || ret == undefined) {
            return "ERROR: Income or expense item not found";
        };

        return ret.name;
    }

    private getIncomeOrExpenseItemCategoryName(itemId : Number) {
        if (itemId && this.state.incomeOrExpenseItems[itemId.valueOf()]) {
            let catId : Number = this.state.getIncomeOrExpenseItem(itemId).incomeOrExpenseItemCategoryId;

            if (this.state.incomeOrExpenseItemCategories) {
                for(var c = 0 ; c < this.state.incomeOrExpenseItemCategories.length ; c++) {
                    if (this.state.incomeOrExpenseItemCategories[c].id == catId) {
                        return this.state.incomeOrExpenseItemCategories[c].name;
                    }
                }
            }
        }

        return null;
    }

    public isIncome(rec:AccountRecord) : boolean {
        return rec.value > 0;
    }

    getLastRecordedExchangeRate() {
        const currency = this.state.getMonitoiredCurrency(this.state.getAccount(this.rec.accountId).currencyId)

        if (currency) {
            return currency.lastCollectedValue
        }

        return 0
    }

    public isSafeToExchangeBack(rec:AccountRecord) : boolean {
        const currency = this.state.getMonitoiredCurrency(this.state.getAccount(rec.accountId).currencyId)

        if (currency) {
            let lastRecordedExchangeRate = currency.lastCollectedValue;
            return (rec.exchangeRate < lastRecordedExchangeRate);
        }

        return false;
    }

    public getExchangeRateTextboxId() {
        return this.id + "_exchangeRateTextbox"
    }

    public getExchangeRateDetailsBoxId() {
        return this.id + "_exchangeRateDetailsBox"
    }

    public showExchangeRateDetailBox(event, exchangeRateDetailsBoxId) {
        this.hideAllExchangeRateDetailsBoxes()

        if (this.isIncome(this.rec)) {
            const detailsBox = document.getElementById(exchangeRateDetailsBoxId)

            detailsBox.style.left = (event.x - 190) + "px"
            detailsBox.style.top = (event.y - 70) + "px"

            detailsBox.style.visibility = "visible"

            this.previouslyShownExchangeRateDetailsBoxId = exchangeRateDetailsBoxId
        }
    }

    public hideExchangeRateDetailsBox(exchangeRateDetailsBoxId) {
        const detailsBox = document.getElementById(exchangeRateDetailsBoxId)
        detailsBox.style.visibility = "hidden"
    }

    public hideAllExchangeRateDetailsBoxes() {
        const elements = document.getElementsByClassName("account-record-exchange-rate-popup")

        for (let i = 0 ; i < elements.length ; i++) {
            const element : any = elements.item(i)
            element.style.visibility = "hidden"
        }
    }

    public getRecValue() {
        return this.rec ? this.rec.value : 0
    }

    public getRecExchangeRate() {
        return this.rec ? this.rec.exchangeRate : 1
    }
}
