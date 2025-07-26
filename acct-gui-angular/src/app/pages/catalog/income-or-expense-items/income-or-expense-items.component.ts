import { Component, EventEmitter } from '@angular/core';
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
import { IconifiedIncomeOrExpenseItemSubcategory, IncomeOrExpenseItemSubcategory } from '../../../model-acct/income-or-expense-item-subcategory';

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

  selectedIncomeOrExpenseItemSubcategory! : IconifiedIncomeOrExpenseItemSubcategory
 
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
      this.catalogService.saveIncomeOrExpenseItemCategory(category),
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
   * Lets the subcategory manager know that the selected category has changed
   */
  incomeOrExpenseItemCategoriesListSelectionChanged() : void {
    this.subcategoriesListForceReloadEventEmitter.emit()
  }

  /**
   * The selected income or expense item category
   */
  incomeOrExpenseItemCategoriesListSelectedItem? : IncomeOrExpenseItemCategory

  /**
   * The selected income or expense item sub-category
   */
  incomeOrExpenseItemSubcategoriesListSelectedItem? : IncomeOrExpenseItemSubcategory

  /**
   * Event emitter that triggers the reload of the sub-categories list when a category is selected
   */
  subcategoriesListForceReloadEventEmitter : EventEmitter<void> = new EventEmitter<void>()

  /**
   * Returns an observable that produces the list of sub-categories registered in the catalog under
   * the selected category
   */
  incomeOrExpenseItemSubcategoriesListProducer : (() => Observable<ItemsManagerDataSet>) =
    () => new Observable<ItemsManagerDataSet>(subscriber => {
      if (this.incomeOrExpenseItemCategoriesListSelectedItem) {
        if (this.incomeOrExpenseItemCategoriesListSelectedItem.incomeOrExpenseItemCategoryUUID) {
          this.catalogService.findIncomeOrExpenseItemSubcategories(
            this.incomeOrExpenseItemCategoriesListSelectedItem.incomeOrExpenseItemCategoryUUID
          ).subscribe({
            next: data => {
              subscriber.next(data)
              subscriber.complete()
            },
            error: err => {
              // TODO: toast
              console.log(err)
            }
          })
        }
      } else {
        subscriber.next([])
        subscriber.complete()
      }
    })

  /**
   * Extracts the card image for the income or expense item sub-categories item manager
   */
  incomeOrExpenseItemSubcategoryCardImageRefExtractor : ItemsManagerCardPropertyExtractor =
    (subucategory:IconifiedIncomeOrExpenseItemSubcategory) => subucategory.imageData

  /**
   * Extracts the card title for the income or expense item sub-categories item manager
   */
  incomeOrExpenseItemSubcategoryCardTitleExtractor : ItemsManagerCardPropertyExtractor =
    (subucategory:IconifiedIncomeOrExpenseItemSubcategory) => subucategory.incomeOrExpenseItemSubcategoryName

  /**
   * Extracts the card text for the income or expense item sub-categories item manager
   */
  incomeOrExpenseItemSubcategoryCardTextExtractor : ItemsManagerCardPropertyExtractor = 
    (subucategory:IconifiedIncomeOrExpenseItemSubcategory) => subucategory.incomeOrExpenseItemSubcategoryDescription

  /**
   * Validates an income or expense item sub-category for the items manager, before saving
   */
  incomeOrExpenseItemSubcategoryValidator : ((subcategory:IncomeOrExpenseItemSubcategory) => boolean) =
    (subcategory:IncomeOrExpenseItemSubcategory) => {
      if (subcategory) {
        if (subcategory.incomeOrExpenseItemSubcategoryName) {
          if (subcategory.incomeOrExpenseItemSubcategoryDescription) {
            if (subcategory.incomeOrExpenseItemSubcategoryIconUUID) {
              return true
            }
          }
        }
      }

      return false
    }

  /**
   * Initializes a new, unsaved, income or expense item sub-category for the items manager
   */
  newIncomeOrExpenseItemSubcategorySupplier : (() => IncomeOrExpenseItemSubcategory) = () => {
    // Create the category
    const subcategory = {
      incomeOrExpenseItemSubcategoryName: "",
      incomeOrExpenseItemSubcategoryDescription: "",
      incomeOrExpenseItemSubcategoryIconUUID: ""
    }

    // Store the sub-category as an iconified sub-category
    this.selectedIncomeOrExpenseItemSubcategory = subcategory as IconifiedIncomeOrExpenseItemSubcategory

    // Return a reference to the newly created sub-category
    return subcategory
  }

  /**
   * Saves an income or expense item sub-category for the items manager
   */
  incomeOrExpenseItemSubcategorySavingConsumer : ((subcategory:IncomeOrExpenseItemSubcategory) => Observable<void>) =
    (subcategory:IncomeOrExpenseItemSubcategory) => errorConsumingObservableOperation(
      this.catalogService.saveIncomeOrExpenseItemSubcategory(
        this.incomeOrExpenseItemCategoriesListSelectedItem?.incomeOrExpenseItemCategoryUUID ?? "",
        subcategory
      ),
      err => {
        // TODO: toast
        console.log(err)
      }
    )

  /**
   * Deletes an income or expense item category for the income or expense item categories items manager
   */
  incomeOrExpenseItemSubcategoryDeletionConsumer : ((item:ItemsManagerDataItem<IncomeOrExpenseItemSubcategory>) => Observable<void>) =
    (item:ItemsManagerDataItem<IncomeOrExpenseItemSubcategory>) => errorConsumingObservableOperation(
      this.catalogService.deleteIncomeOrExpenseItemSubcategory(item),
      err => {
        // TODO: toast
        console.log(err)
      }
    )

  // Dialog visibility switches
  categoryIconsManagerVisible : boolean = false
  subcategoryIconsManagerVisible : boolean = false

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

  onChooseSubcategoryIconClick() : void {
    this.showSubcategoryIconsManager()
  }

  onSubcategoryIconSelected(icon:IconProperties) : void {
    // Set the icon UUID
    this.selectedIncomeOrExpenseItemSubcategory.incomeOrExpenseItemSubcategoryIconUUID = icon.iconUUID

    // Apply the icon
    this.catalogService.applyIcon(
      () => this.selectedIncomeOrExpenseItemSubcategory.incomeOrExpenseItemSubcategoryIconUUID,
      imageData => this.selectedIncomeOrExpenseItemSubcategory.imageData = imageData
    ).subscribe()

    // Hide the dialog
    this.hideSubcategoryIconsManager()
  }

  private showCategoryIconsManager() : void {
    this.categoryIconsManagerVisible = true
  }

  private hideCategoryIconsManager() : void {
    this.categoryIconsManagerVisible = false
  }

  private showSubcategoryIconsManager() : void {
    this.subcategoryIconsManagerVisible = true
  }

  private hideSubcategoryIconsManager() : void {
    this.subcategoryIconsManagerVisible = false
  }

}
