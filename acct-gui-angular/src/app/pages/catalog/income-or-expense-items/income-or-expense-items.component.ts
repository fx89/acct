import { Component } from '@angular/core';
import { ItemsManagerCardPropertyExtractor, ItemsManagerComponent, ItemsManagerDataItem, ItemsManagerDataSet, ItemsManagerNewItemFormDirective } from '../../../components-acct/items-manager/items-manager.component';
import { Observable } from 'rxjs';
import { InputComponent } from '../../../components-gui/input/input.component';
import { CatalogService } from '../../../services-acct/catalog.service';
import { IconifiedIncomeOrExpenseItemCategory, IncomeOrExpenseItemCategory } from '../../../model-acct/income-or-expense-item-category';
import { errorConsumingObservableOperation } from '../../../utils-reusalbe/rxjs-utils';
import { ButtonComponent } from '../../../components-gui/button/button.component';
import { DialogComponent } from '../../../components-gui/dialog/dialog.component';
import { IconsManagerComponent } from '../../../components-acct/icons-manager/icons-manager.component';
import { IconProperties } from '../../../model-acct/icon-properties';

@Component({
  selector: 'app-income-or-expense-items',
  imports: [
    ItemsManagerComponent,
    ItemsManagerNewItemFormDirective,
    InputComponent,
    ButtonComponent,
    DialogComponent,
    IconsManagerComponent
  ],
  templateUrl: './income-or-expense-items.component.html',
  styleUrl: './income-or-expense-items.component.less'
})
export class IncomeOrExpenseItemsComponent {

  constructor(
    private catalogService : CatalogService
  ) {

  }

  selectedIncomeOrExpenseItemCategory! : IconifiedIncomeOrExpenseItemCategory
 
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
    (item:ItemsManagerDataItem<IncomeOrExpenseItemCategory>) => errorConsumingObservableOperation(
      this.catalogService.deleteIncomeOrExpenseItemCategory(item),
      err => {
        // TODO: toast
        console.log(err)
      }
    )

  /**
   * Initializes a new, unsaved, income or expense item category for the items manager
   */
  newIncomeOrExpenseItemCategorySupplier : (() => IncomeOrExpenseItemCategory) = () => {
    // Create the category
    const category = {
      incomeOrExpenseItemCategoryName: "",
      incomeOrExpenseItemCategoryDescription: "",
      incomeOrExpenseItemCategoryIconUUID: ""
    }

    // Store the category as an iconified category
    this.selectedIncomeOrExpenseItemCategory = category as IconifiedIncomeOrExpenseItemCategory

    // Return a reference to the newly created category
    return category
  }

  /**
   * Saves an income or expense item category for the items manager
   */
  incomeOrExpenseItemCategorySavingConsumer : ((category:IncomeOrExpenseItemCategory) => Observable<void>) =
    (category:IncomeOrExpenseItemCategory) => errorConsumingObservableOperation(
      this.catalogService.createIncomeOrExpenseItemCategory(category),
      err => {
        // TODO: toast
        console.log(err)
      }
    )

  /**
   * Validates an income or expense item category for the items manager, before saving
   */
  incomeOrExpenseItemCategoryValidator : ((category:IncomeOrExpenseItemCategory) => boolean) =
    (category:IncomeOrExpenseItemCategory) => {
      if (category) {
        if (category.incomeOrExpenseItemCategoryName) {
          if (category.incomeOrExpenseItemCategoryDescription) {
            if (category.incomeOrExpenseItemCategoryIconUUID) {
              return true
            }
          }
        }
      }

      return false
    }

  /**
   * The selected income or expense item category
   */
  incomeOrExpenseItemCategoriesListSelectedItem? : IncomeOrExpenseItemCategory

  // Dialog visibility switches
  categoryIconsManagerVisible : boolean = false

  onChooseCategoryIconClick() : void {
    this.showCategoryIconsManager()
  }

  onCategoryIconSelected(icon:IconProperties) : void {
    // Set the icon UUID
    this.selectedIncomeOrExpenseItemCategory.incomeOrExpenseItemCategoryIconUUID = icon.iconUUID

    // Apply the icon
    this.catalogService.applyIcon(
      () => this.selectedIncomeOrExpenseItemCategory.incomeOrExpenseItemCategoryIconUUID,
      imageData => this.selectedIncomeOrExpenseItemCategory.imageData = imageData
    ).subscribe()

    // Hide the dialog
    this.hideCategoryIconsManager()
  }

  private showCategoryIconsManager() : void {
    this.categoryIconsManagerVisible = true
  }

  private hideCategoryIconsManager() : void {
    this.categoryIconsManagerVisible = false
  }

}
