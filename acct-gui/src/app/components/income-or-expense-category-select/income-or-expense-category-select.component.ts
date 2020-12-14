import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { StateService } from 'src/app/services/state-service/state.service';
import { IncomeOrExpenseItem } from 'src/app/model/income-or-expense-item';

@Component({
  selector: 'app-income-or-expense-category-select',
  templateUrl: './income-or-expense-category-select.component.html',
  styleUrls: ['./income-or-expense-category-select.component.css']
})
export class IncomeOrExpenseCategorySelectComponent implements OnInit {
    @Input() cssClassName : string;

    /* Two way binding of selectedIncomeOrExpenseItemObj START */
    public selectedIncomeOrExpenseItemObj : IncomeOrExpenseItem;

    @Input()
    get selectedIncomeOrExpenseItem() : IncomeOrExpenseItem {
        return this.selectedIncomeOrExpenseItemObj;
    };

    @Output() selectedIncomeOrExpenseItemChange : EventEmitter<IncomeOrExpenseItem> = new EventEmitter();

    set selectedIncomeOrExpenseItem(val) {
        this.selectedIncomeOrExpenseItemObj = val;
    }

    onChange() {
      this.selectedIncomeOrExpenseItemChange.emit(this.selectedIncomeOrExpenseItemObj);
  }
    /* Two way binding of selectedIncomeOrExpenseItemObj END */

    


    constructor(private state : StateService) { }

    ngOnInit() {
        this.state.loadIncomeOrExpenseItems();
    }

    private getCatItemName(item : IncomeOrExpenseItem) : string {
        return this.getImpactOrExpenseItemCategoryName(item.incomeOrExpenseItemCategoryId) + " - " + item.name;
    }

    private getImpactOrExpenseItemCategoryName(catId : Number) : string {
        for (let cat of this.state.incomeOrExpenseItemCategories) {
            if (cat.id == catId) {
                return cat.name.valueOf();
            }
        }

        return "???";
    }
}
