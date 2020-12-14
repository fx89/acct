import { Component, OnInit, Input } from '@angular/core';
import { IncomeOrExpenseItem } from 'src/app/model/income-or-expense-item';
import { StateService } from 'src/app/services/state-service/state.service';
import { AccountRecord } from 'src/app/model/account-record';

@Component({
  selector: 'app-account-record',
  templateUrl: './account-record.component.html',
  styleUrls: ['./account-record.component.css']
})
export class AccountRecordComponent implements OnInit {

    @Input() rec : AccountRecord;

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
}
