import { Injectable, Predicate } from '@angular/core';
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
import { errorPipingObservableConsumer, errorPipingObservableOperation } from '../utils-reusalbe/rxjs-utils';
import { IconifiedIncomeOrExpenseItemSubcategory, IncomeOrExpenseItemSubcategory } from '../model-acct/income-or-expense-item-subcategory';
import { IconifiedIncomeOrExpenseItem, IncomeOrExpenseItem } from '../model-acct/income-or-expense-item';
import { BankProperties, IconifiedBankProperties } from '../model-acct/bank-properties';
import { AcctBanksRepository } from '../repositories-acct/banks-repository';
import { BankUUIDResponse } from '../model-acct/bank-uuid-response';
import { CurrencyProperties, IconifiedCurrencyProperties } from '../model-acct/currency-properties';
import { CurrencyUUIDResponse } from '../model-acct/currency-uuid-response';
import { AcctCurrenciesRepository } from '../repositories-acct/currencies-repository';

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
    private itemsRepository : AcctItemsRepository,
    private banksRepository : AcctBanksRepository,
    private currenciesRepository : AcctCurrenciesRepository
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

    throw new Error("The referenced income or expense item sub-category does not have an UUID")
  }

  public findIncomeOrExpenseItems(
    incomeOrExpenseItemSubcategoryUUID:string
  ) : Observable<IconifiedIncomeOrExpenseItem[]> {
    return errorPipingObservableOperation(
        this.itemsRepository.findIncomeOrExpenseItems(incomeOrExpenseItemSubcategoryUUID),
        (dataSet:IncomeOrExpenseItem[], mainSubscriber) => {
          // Convert to an iconified set and filter
          const iconifiedDataSet : IconifiedIncomeOrExpenseItem[] = 
            dataSet as IconifiedIncomeOrExpenseItem[]

          // Start loading the icons for each element in the data set that has an icon reference
          const iconLoadingTasks : ObservableInput<void>[] =
            iconifiedDataSet
              .filter(item => item.incomeOrExpenseItemIconUUID)
              .map(item =>
                this.applyIcon(
                  () => item.incomeOrExpenseItemIconUUID,
                  (imageData) => item.imageData = imageData
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
   * Saves the referenced income or expense item into the repository
   * 
   * @param incomeOrExpenseItemSubcategoryUUID   the UUID of the parent sub-category, under which the item is saved
   * @param incomeOrExpenseItem                  the referenced income or expense item
   */
  public saveIncomeOrExpenseItem(
    incomeOrExpenseItemSubcategoryUUID : string,
    incomeOrExpenseItem  :IncomeOrExpenseItem
  ) : Observable<void> {
    return this.itemsRepository.saveIncomeOrExpenseItem(
      incomeOrExpenseItemSubcategoryUUID,
      incomeOrExpenseItem
    )
  }

  /**
   * Deletes the referenced income or expense item from the catalog
   * 
   * @param item the referenced income or expense item
   */
  public deleteIncomeOrExpenseItem(item:IncomeOrExpenseItem) : Observable<void> {
    if (item?.incomeOrExpenseItemUUID) {
      const uuids : string[] = []
      uuids.push(item.incomeOrExpenseItemUUID)
      return this.itemsRepository.deleteIncomeOrExpenseItems(uuids)
    }

    throw new Error("The referenced income or expense item does not have an UUID")
  }

  /**
   * Returns an observable that produces an array of all the banks registered in the catalog,
   * enriched with the Base64-encoded contents of their icons
   */
  public findAllBanks() : Observable<IconifiedBankProperties[]> {
    return errorPipingObservableOperation(
        this.banksRepository.findAllBanks(),
        (dataSet:BankProperties[], mainSubscriber) => {
          // Convert to an iconified set and filter
          const iconifiedDataSet : IconifiedBankProperties[] = 
            dataSet as IconifiedBankProperties[]

          // Start loading the icons for each element in the data set that has an icon reference
          const iconLoadingTasks : ObservableInput<void>[] =
            iconifiedDataSet
              .filter(item => item.bankIconUUID)
              .map(item =>
                this.applyIcon(
                  () => item.bankIconUUID,
                  (imageData) => item.imageData = imageData
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
   * Saves the referenced bank into the catalog
   * 
   * @param bankProperties the referenced bank
   */
  public saveBank(bankProperties: BankProperties): Observable<BankUUIDResponse> {
    return this.banksRepository.saveBank(bankProperties)
  }

  /**
   * Deletes the referenced bank from the repository
   * 
   * @param bankProperties the referenced bank
   */
  public deleteBank(bankProperties: BankProperties): Observable<void> {
    if (bankProperties.bankUUID) {
      const uuids : string[] = []
      uuids.push(bankProperties.bankUUID)
      return this.banksRepository.deleteBanks(uuids)
    }

    throw new Error("The referenced bank does not have an UUID")
  }

  /**
   * Returns an observable that produces an array of all the currencies registered in the catalog
   * that match the given filter, enriched with the Base64-encoded contents of their icons. If the
   * filter is not given, then all currencies are returned.
   */
  public findAllCurrencies(filter?:Predicate<CurrencyProperties>) : Observable<IconifiedCurrencyProperties[]> {
    return errorPipingObservableOperation(
        this.currenciesRepository.findCurrencies(),
        (dataSet:CurrencyProperties[], mainSubscriber) => {
          // If a filter was provided, then apply it
          if (filter) {
            dataSet = dataSet.filter(filter)
          }

          // Convert to an iconified set and filter
          const iconifiedDataSet : IconifiedCurrencyProperties[] = 
            dataSet as IconifiedCurrencyProperties[]

          // Start loading the icons for each element in the data set that has an icon reference
          const iconLoadingTasks : ObservableInput<void>[] =
            iconifiedDataSet
              .filter(item => item.currencyIconUUID)
              .map(item =>
                this.applyIcon(
                  () => item.currencyIconUUID,
                  (imageData) => item.imageData = imageData
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
   * Returns an observable that produces an iconified currency properties object that contains the
   * properties of the currency identified by the given currencyUUID. If such a currency does not
   * exist, then an error is thrown.
   * 
   * @param currencyUUID the given currencyUUID
   */
  public findCurrencyByCurrencyUUID(currencyUUID:string) : Observable<IconifiedCurrencyProperties> {
    return new Observable<IconifiedCurrencyProperties>(subscriber => {
      this.findAllCurrencies(currency => currencyUUID == currency.currencyUUID).subscribe({
        next: currencies => {
          if (currencies.length == 0) {
            subscriber.error(new Error("Currency not found"))
          } else {
            subscriber.next(currencies[0])
          }
        },
        error: err => subscriber.error(err)
      })
    })
  }

  /**
   * Deletes the referenced currency from the catalog
   * 
   * @param currency the referenced currency
   */
  public deleteCurrency(currency:CurrencyProperties) : Observable<void> {
    return this.currenciesRepository.deleteCurrencies([currency.currencyUUID])
  }

  /**
   * Saves the referenced currency into the catalog
   * 
   * @param currency the referenced currency
   */
  public saveCurrency(currency: CurrencyProperties): Observable<CurrencyUUIDResponse> {
    return this.currenciesRepository.saveCurrency(currency)
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
