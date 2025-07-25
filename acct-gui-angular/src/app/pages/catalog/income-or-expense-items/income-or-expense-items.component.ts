import { Component } from '@angular/core';
import { ItemsManagerCardPropertyExtractor, ItemsManagerComponent, ItemsManagerDataItem, ItemsManagerDataSet, ItemsManagerNewItemFormDirective } from '../../../components-acct/items-manager/items-manager.component';
import { Observable } from 'rxjs';
import { InputComponent } from '../../../components-gui/input/input.component';
import { CatalogService } from '../../../services-acct/catalog.service';
import { IconifiedIncomeOrExpenseItemCategory, IncomeOrExpenseItemCategory } from '../../../model-acct/income-or-expense-item-category';
import { errorConsumingObservableOperation } from '../../../utils-reusalbe/rxjs-utils';

@Component({
  selector: 'app-income-or-expense-items',
  imports: [
    ItemsManagerComponent,
    ItemsManagerNewItemFormDirective,
    InputComponent
  ],
  templateUrl: './income-or-expense-items.component.html',
  styleUrl: './income-or-expense-items.component.less'
})
export class IncomeOrExpenseItemsComponent {

  constructor(
    private catalogService : CatalogService
  ) {

  }
 
  /**
   * Produces the list of income of expense item categories for the items manager
   */
  incomeOrExpenseItemCategoriesListProducer : (() => Observable<ItemsManagerDataSet>) =
    () => errorConsumingObservableOperation(
      this.catalogService.findAllIncomeOrExpenseItemCategories(),
      err => {
        // TODO: toast
        console.log(err)
      }
    )

  incomeOrExpenseItemCategoryCardImageRefExtractor : ItemsManagerCardPropertyExtractor =
    (category:IconifiedIncomeOrExpenseItemCategory) => category.imageData

  /**
   * Extracts the card title for the income or expense item categories item manager
   */
  incomeOrExpenseItemCategoryCardTitleExtractor : ItemsManagerCardPropertyExtractor =
    (category:IncomeOrExpenseItemCategory) => category.incomeOrExpenseItemCategoryName

  /**
   * Extracts the card text for the income or expense item categories item manager
   */
  incomeOrExpenseItemCategoryCardTextExtractor : ItemsManagerCardPropertyExtractor = 
    (category:IncomeOrExpenseItemCategory) => category.incomeOrExpenseItemCategoryDescription

  /**
   * Deletes an income or expense item category for the income or expense item categories items manager
   */
  incomeOrExpenseItemCategoryDeletionConsumer : ((item:ItemsManagerDataItem<IncomeOrExpenseItemCategory>) => Observable<void>) =
    () => new Observable<void>()

  /**
   * Initializes a new, unsaved, income or expense item category for the items manager
   */
  newIncomeOrExpenseItemCategorySupplier : (() => IncomeOrExpenseItemCategory) = () => {
    return {
      incomeOrExpenseItemCategoryName: "",
      incomeOrExpenseItemCategoryDescription: "",
      incomeOrExpenseItemCategoryIconUUID: ""
    }
  }

  /**
   * Saves an income or expense item category for the items manager
   */
  incomeOrExpenseItemCategorySavingConsumer : ((category:IncomeOrExpenseItemCategory) => Observable<void>) =
    () => new Observable<void>()

  /**
   * Validates an income or expense item category for the items manager, before saving
   */
  incomeOrExpenseItemCategoryValidator : ((category:IncomeOrExpenseItemCategory) => boolean) =
    () => false

  /**
   * The selected income or expense item category
   */
  incomeOrExpenseItemCategoriesListSelectedItem? : IncomeOrExpenseItemCategory


}
