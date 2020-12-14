import { Component, OnInit } from '@angular/core';
import { IncomeOrExpenseItem } from 'src/app/model/income-or-expense-item';
import { StateService } from 'src/app/services/state-service/state.service';
import { AccountRecord } from 'src/app/model/account-record';
import { IncomeOrExpenseItemsManagerComponent } from '../income-or-expense-items-manager/income-or-expense-items-manager.component';
import { MatDialogRef, MatDialog } from '@angular/material';

@Component({
  selector: 'app-account-records-input-form',
  templateUrl: './account-records-input-form.component.html',
  styleUrls: ['./account-records-input-form.component.css']
})
export class AccountRecordsInputFormComponent implements OnInit {
    public selectedIncomeOrExpenseItem : IncomeOrExpenseItem;
    public selectedAccountRecordDateStr : string;

    private itemsManagerDialogRef : MatDialogRef<IncomeOrExpenseItemsManagerComponent>;

    constructor(private state : StateService, private dialog: MatDialog) { }

    ngOnInit() {
        // https://stackoverflow.com/questions/37587732/how-to-call-another-components-function-in-angular2
        this.state.selectedRecordChangedObservable.subscribe(rec => {
            this.selectedAccountRecordToEditFormVariables(this.state.selectedAccountRecord);
        })
    }

    public selectedAccountRecordToEditFormVariables(rec : AccountRecord) {
        this.selectedIncomeOrExpenseItem = this.state.getIncomeOrExpenseItem(rec.incomeOrExpenseItemId);
        this.selectedAccountRecordDateStr = new Date(this.state.selectedAccountRecord.date.toString()).toISOString().split("T")[0];
    }

    private editFormVariablesToSelectedAccountRecord() {
        console.log(this.selectedIncomeOrExpenseItem);
        this.state.selectedAccountRecord.incomeOrExpenseItemId = this.selectedIncomeOrExpenseItem.id;
        this.state.selectedAccountRecord.date = new Date(this.selectedAccountRecordDateStr);
        this.selectedAccountRecordDateStr = null;
    }

    public onValueKeyUp(event) {
        if (event.key == "Enter") {
            this.editFormVariablesToSelectedAccountRecord();
            this.state.saveSelectedAccountRecord();
        }
    }

    public onItemChange(event) {
        this.state.selectedAccountRecord.value = this.selectedIncomeOrExpenseItem.lastUsedValue;

        if (!(this.state.selectedAccountRecord.value)) {
            this.state.selectedAccountRecord.value = 0;
        }
    }

    public showIncomeOrExpenseItemsManager() {
        this.itemsManagerDialogRef = this.dialog.open(IncomeOrExpenseItemsManagerComponent, {
            width: '400px',
            height: '300px'
          });
    }

    public getCatItemName(item : IncomeOrExpenseItem) : string {
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
