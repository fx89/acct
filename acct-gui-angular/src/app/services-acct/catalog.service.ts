import { Injectable } from '@angular/core';
import { AcctIconsRepository } from '../repositories-acct/icons-repository';
import { IconCreateRequest } from '../model-acct/icon-create-request';
import { forkJoin, Observable, ObservableInput } from 'rxjs';
import { IconUUIDResponse } from '../model-acct/icon-uuid-response';
import { IconQueryParams } from '../model-acct/icon-query-params';
import { IconsCountResponse } from '../model-acct/icons-count-response';
import { AcctPageRequest } from '../model-acct/page-request';
import { AcctPage } from '../model-acct/acct-page';
import { IconProperties } from '../model-acct/icon-properties';
import { AcctItemsRepository } from '../repositories-acct/items-repository';
import { IconifiedIncomeOrExpenseItemCategory, IncomeOrExpenseItemCategory } from '../model-acct/income-or-expense-item-category';
import { errorPipingObservableConsumer, errorPipingObservableOperation, errorPipingObservableTransform } from '../utils-reusalbe/rxjs-utils';
import { IconifiedIncomeOrExpenseItemSubcategory, IncomeOrExpenseItemSubcategory } from '../model-acct/income-or-expense-item-subcategory';

const ERROR_PLACEHOLDER_ICON_URL : string = "generic-icons/failed.png"

/**
 * Provides access to functions of the catalog service
 */
@Injectable({
  providedIn: 'root'
})
export class CatalogService {
  
  constructor(
    private iconsRepository : AcctIconsRepository,
    private itemsRepository : AcctItemsRepository
  ) { }

  /**
   * Creates a new icon with the details given in the request
   * 
   * @param request the request
   * @returns a container for the UUID of the newly created icon
   */
  public createIcon(request: IconCreateRequest): Observable<IconUUIDResponse> {
    return this.iconsRepository.createIcon(request)
  }

  public createIconCategory(iconCategoryName:string) : Observable<void> {
    return this.iconsRepository.createIconCategory(iconCategoryName)
  }

  /**
   * Returns an array of the names of all the registered icon categories
   */
  public findIconCategories(): Observable<string[]> {
    return this.iconsRepository.findIconCategories()
  }

  /**
   * Deletes the icon category with the given name
   * @param iconCategoryName the given name
   */
  public deleteIconCategory(iconCategoryName:string) : Observable<void> {
    return this.iconsRepository.deleteIconCategory(iconCategoryName)
  }

  /**
   * Returns a count of the icons that match the given name pattern and that belong to
   * the given category name. If a name pattern is not provided then the count includes
   * icons with any name. If the category name is not provided then the count includes
   * icons from all categories.
   * 
   * @param queryParams container for the query parameters of the icons count query
   */
  public countIcons(queryParams : IconQueryParams): Observable<IconsCountResponse> {
    return this.iconsRepository.countIcons(queryParams)
  }

  /**
   * Returns a page, with the given page number and of the given page size, of the icons
   * that match the given name pattern and that belong to the given category name. If a
   * name pattern is not provided then the count includes icons with any name. If the
   * category name is not provided then the count includes icons from all categories.
   * 
   * @param pageRequest the page request object
   */
  public findIcons(pageRequest: AcctPageRequest<IconQueryParams>): Observable<AcctPage<IconProperties>> {
    return this.iconsRepository.findIcons(pageRequest)
  }

  /**
   * Returns the base64-encoded bytes of the icon with the given icon UUID
   * 
   * @param iconUUID the given icon UUID
   */
  public loadIconBytesBase64(iconUUID: string): Observable<string> {
    return this.iconsRepository.loadIconBytesBase64(iconUUID)
  }

  /**
   * Deletes the icons identified by the UUIDs in the given collection of icon UUIDs
   * 
   * @param iconUUIDs the given collection of icon UUIDs
   */
  public deleteIcons(iconUUIDs : string[]): Observable<void> {
    return this.iconsRepository.deleteIcons(iconUUIDs)
  }

  /**
   * Returns an observable that produces a list of all the income or expense item categories in the catalog,
   * having the referenced icon added to each of them
   */
  public findAllIncomeOrExpenseItemCategories() : Observable<IconifiedIncomeOrExpenseItemCategory[]> {
    return errorPipingObservableOperation(
        this.itemsRepository.findAllIncomeOrExpenseItemCategories(),
        (dataSet:IncomeOrExpenseItemCategory[], mainSubscriber) => {
          // Convert to an iconified set and filter
          const iconifiedDataSet : IconifiedIncomeOrExpenseItemCategory[] = 
            dataSet as IconifiedIncomeOrExpenseItemCategory[]

          // Start loading the icons for each element in the data set that has an icon reference
          const iconLoadingTasks : ObservableInput<void>[] =
            iconifiedDataSet
              .filter(category => category.incomeOrExpenseItemCategoryIconUUID)
              .map(category =>
                this.applyIcon(
                  () => category.incomeOrExpenseItemCategoryIconUUID,
                  (imageData) => category.imageData = imageData
                )
              )

          // Wait for all the loading tasks to complete
          errorPipingObservableConsumer(
            forkJoin(iconLoadingTasks), // This is how we wait
            mainSubscriber,
            () => {
              mainSubscriber.next(iconifiedDataSet)
              mainSubscriber.complete()
            }
          )
        }
      )
  }

  /**
   * Saves the referenced income or expense item category into the repository
   * 
   * @param incomeOrExpenseItemCategory the referenced income or expense item category
   */
  public saveIncomeOrExpenseItemCategory(incomeOrExpenseItemCategory:IncomeOrExpenseItemCategory) : Observable<void> {
    return this.itemsRepository.saveIncomeOrExpenseItemCategory(incomeOrExpenseItemCategory)
  }

  /**
   * Deletes the referenced category from the catalog
   * 
   * @param incomeOrExpenseItemCategory the referenced category
   */
  public deleteIncomeOrExpenseItemCategory(incomeOrExpenseItemCategory:IncomeOrExpenseItemCategory) : Observable<void> {
    if (incomeOrExpenseItemCategory?.incomeOrExpenseItemCategoryUUID) {
      const uuids : string[] = []
      uuids.push(incomeOrExpenseItemCategory.incomeOrExpenseItemCategoryUUID)
      return this.itemsRepository.deleteIncomeOrExpenseItemCategories(uuids)
    }

    throw new Error("The referenced income or expense item category does not have an UUID")
  }

  public findIncomeOrExpenseItemSubcategories(
    incomeOrExpenseItemCategoryUUID:string
  ) : Observable<IconifiedIncomeOrExpenseItemSubcategory[]> {
    return errorPipingObservableOperation(
        this.itemsRepository.findIncomeOrExpenseItemSubcategories(incomeOrExpenseItemCategoryUUID),
        (dataSet:IncomeOrExpenseItemSubcategory[], mainSubscriber) => {
          // Convert to an iconified set and filter
          const iconifiedDataSet : IconifiedIncomeOrExpenseItemSubcategory[] = 
            dataSet as IconifiedIncomeOrExpenseItemSubcategory[]

          // Start loading the icons for each element in the data set that has an icon reference
          const iconLoadingTasks : ObservableInput<void>[] =
            iconifiedDataSet
              .filter(subcategory => subcategory.incomeOrExpenseItemSubcategoryIconUUID)
              .map(subcategory =>
                this.applyIcon(
                  () => subcategory.incomeOrExpenseItemSubcategoryIconUUID,
                  (imageData) => subcategory.imageData = imageData
                )
              )

          // Add a dummy operation so that the iconLoadingTasks array still has something to execute
          // even if the iconifiedDataSet is empty
          iconLoadingTasks.push(new Observable<void>(subscriber => {
            subscriber.next()
            subscriber.complete()
          }))

          // Wait for all the loading tasks to complete
          errorPipingObservableConsumer(
            forkJoin(iconLoadingTasks), // This is how we wait
            mainSubscriber,
            () => {
              mainSubscriber.next(iconifiedDataSet)
              mainSubscriber.complete()
            }
          )
        }
      )
  }

  /**
   * Saves the referenced income or expense item sub-category into the repository
   * 
   * @param incomeOrExpenseItemCategoryUUID the UUID of the parent category, under which the sub-category is saved
   * @param incomeOrExpenseItemSubcategory  the referenced income or expense item sub-category
   */
  public saveIncomeOrExpenseItemSubcategory(
    incomeOrExpenseItemCategoryUUID : string,
    incomeOrExpenseItemSubcategory  :IncomeOrExpenseItemSubcategory
  ) : Observable<void> {
    return this.itemsRepository.saveIncomeOrExpenseItemSubcategory(
      incomeOrExpenseItemCategoryUUID,
      incomeOrExpenseItemSubcategory
    )
  }

  /**
   * Deletes the referenced sub-category from the catalog
   * 
   * @param incomeOrExpenseItemSubcategory the referenced sub-category
   */
  public deleteIncomeOrExpenseItemSubcategory(incomeOrExpenseItemSubcategory:IncomeOrExpenseItemSubcategory) : Observable<void> {
    if (incomeOrExpenseItemSubcategory?.incomeOrExpenseItemSubcategoryUUID) {
      const uuids : string[] = []
      uuids.push(incomeOrExpenseItemSubcategory.incomeOrExpenseItemSubcategoryUUID)
      return this.itemsRepository.deleteIncomeOrExpenseItemSubcategories(uuids)
    }

    throw new Error("The referenced income or expense item category does not have an UUID")
  }

  /**
   * Loads the icon identified by the UUID given by the referenced UUID extractor function.
   * When the loading is done, the icon data is fed into the referenced data setter. An
   * observable is returned, to let the consumer know when the operation is finished.
   * @param iconUUIDExtractor the referenced UUID extractor function
   * @param iconDataSetter    the referenced data setter
   */
  public applyIcon(
    iconUUIDExtractor:(() => string | undefined),
    iconDataSetter:((data:string) => void)
  ) : Observable<void> {
    return new Observable<void>(subscriber => {
      this.loadIconBytesBase64(iconUUIDExtractor() ?? "").subscribe({
        // If the icon was loaded successfully, then set the icon data
        next: imageData => {
          iconDataSetter(imageData)
          subscriber.next()
          subscriber.complete()
        },
        // If the icon was not loaded successfully, then set the placeholder icon
        error: () => {
          iconDataSetter(ERROR_PLACEHOLDER_ICON_URL)
          subscriber.next()
          subscriber.complete()
        }
      })
    })
  }

}
