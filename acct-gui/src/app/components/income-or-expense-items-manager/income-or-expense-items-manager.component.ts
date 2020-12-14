import { Component, OnInit, Input } from '@angular/core';
import { StateService } from 'src/app/services/state-service/state.service';
import { IncomeOrExpenseItemCategory } from 'src/app/model/income-or-expense-item-category';
import { IncomeOrExpenseItem } from 'src/app/model/income-or-expense-item';

@Component({
  selector: 'app-income-or-expense-items-manager',
  templateUrl: './income-or-expense-items-manager.component.html',
  styleUrls: ['./income-or-expense-items-manager.component.css']
})
export class IncomeOrExpenseItemsManagerComponent implements OnInit {

    @Input() private allowEditCategories : boolean;

    constructor(private state : StateService) { }

    private incomeOrExpenseItems : IncomeOrExpenseItem[];

    private getItemName : Function = (item => {return item.name; } );

    private newItem : Function = ( () => { 
        let ret : IncomeOrExpenseItem = new IncomeOrExpenseItem(0, "New Income or Expense Item"); 
        ret.incomeOrExpenseItemCategoryId = this.state.selectedIncomeOrExpenseItemCategory.id;
        return ret;
    });

    private newCategory : Function = ( () => {
        return new IncomeOrExpenseItemCategory(0, "New Category");
    });

    private selectedItem : IncomeOrExpenseItem;

    ngOnInit() {
        this.state.loadIncomeOrExpenseItemCategories();
        this.state.loadIncomeOrExpenseItems();
    }

    
    private selectItem(item : IncomeOrExpenseItem) {
        this.selectedItem = item;
    }

    private addItem(item: IncomeOrExpenseItem) {
        this.selectedItem = item;
    }

    private deleteItem(item: IncomeOrExpenseItem) {
        
    }

    private beginEditItem(item: IncomeOrExpenseItem) {
        this.selectedItem = item;
    }

    private endEditItem(itemName: string) {
        this.selectedItem.name = itemName;
        this.state.saveIncomeOrExpenseItem(this.selectedItem).subscribe(
            item => {
                this.selectedItem.id = item.id;
            }
        );
    }

    private selectCategory(category : IncomeOrExpenseItemCategory) {
        this.state.selectedIncomeOrExpenseItemCategory = category;
        this.loadIncomeOrExpenseItemsForCategory();
    }

    private addCategory(category: IncomeOrExpenseItemCategory) {
        this.state.selectedIncomeOrExpenseItemCategory = category;
    }

    private deleteCategory(category: IncomeOrExpenseItem) {
        
    }

    private beginEditCategory(category: IncomeOrExpenseItem) {
        this.state.selectedIncomeOrExpenseItemCategory = category;
    }

    private endEditCategory(categoryName: string) {
        this.state.selectedIncomeOrExpenseItemCategory.name = categoryName;
        this.state.saveIncomeOrExpenseItemCategory(
                this.state.selectedIncomeOrExpenseItemCategory
            ).subscribe(
                ret => {
                    this.state.selectedIncomeOrExpenseItemCategory.id = ret.id;
                }
            );
    }

    private loadIncomeOrExpenseItemsForCategory() {
        this.incomeOrExpenseItems = [];

        if (this.state.incomeOrExpenseItems) {
            for(let itm of this.state.incomeOrExpenseItems) {
                if (itm.incomeOrExpenseItemCategoryId == this.state.selectedIncomeOrExpenseItemCategory.id) {
                    this.incomeOrExpenseItems.push(itm);
                }
            }
        }
    }
}
