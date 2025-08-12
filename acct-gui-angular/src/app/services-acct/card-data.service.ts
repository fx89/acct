import { Injectable } from '@angular/core';
import { CatalogService } from './catalog.service';
import { concatAll, forkJoin, map, Observable } from 'rxjs';
import { CardData } from '../components-gui/cards-list/card-data';
import { IconifiedCurrencyProperties } from '../model-acct/currency-properties';
import { IconifiedBankProperties } from '../model-acct/bank-properties';
import { IconifiedIncomeOrExpenseItem } from '../model-acct/income-or-expense-item';
import { IconifiedIncomeOrExpenseItemSubcategory } from '../model-acct/income-or-expense-item-subcategory';
import { IconifiedIncomeOrExpenseItemCategory } from '../model-acct/income-or-expense-item-category';
import { complete } from '../utils-reusalbe/rxjs-utils';
import { flattenArrays } from '../utils-reusalbe/array-utils';

/**
 * Extends the CardData type with the IconifiedCurrencyProperties of a given currency
 */
export type CurrencyCardData = CardData & { currency : IconifiedCurrencyProperties }

/**
 * Extends the CardData type with the IconifiedBankProperties of a given currency
 */
export type BankCardData = CardData & { bank : IconifiedBankProperties }

/**
 * Extends the CardData type with the IconifiedIncomeOrExpenseItem that it represents,
 * along with the IconifiedIncomeOrExpenseItemSubcategory that the item is part of and
 * the IconifiedIncomeOrExpenseItemCategory that the subcategory is part of
 */
export type IncomeOrExpenseItemCardData = CardData & { 
  incomeOrExpenseItem            : IconifiedIncomeOrExpenseItem,
  incomeOrExpenseItemSubcategory : IconifiedIncomeOrExpenseItemSubcategory,
  incomeOrExpenseItemCategory    : IconifiedIncomeOrExpenseItemCategory
}

/**
 * Extends the CardData type with the IconifiedIncomeOrExpenseItemSubcategory that it represents,
 * along with the IconifiedIncomeOrExpenseItemCategory that the subcategory is part of
 */
export type IncomeOrExpenseItemSubcategoryCardData = CardData & {
  incomeOrExpenseItemSubcategory : IconifiedIncomeOrExpenseItemSubcategory,
  incomeOrExpenseItemCategory    : IconifiedIncomeOrExpenseItemCategory
}

/**
 * Extends the CardData type with the IconifiedIncomeOrExpenseItemCategory that it represents
 */
export type IncomeOrExpenseItemCategoryCardData = CardData & { incomeOrExpenseItemCategory : IconifiedIncomeOrExpenseItemCategory }

/**
 * Container for all the income or expense items, subcategories and categories registered within the catalog
 */
export type ItemsCatalog = {
  incomeOrExpenseItemCategories    : IncomeOrExpenseItemCategoryCardData[],
  incomeOrExpenseItemSubcategories : IncomeOrExpenseItemSubcategoryCardData[],
  incomeOrExpenseItems             : IncomeOrExpenseItemCardData[]
}

@Injectable({
  providedIn: 'root'
})
export class CardDataService {

  constructor(
    private catalogService : CatalogService
  ) { }

  /**
   * Returns an observable that produces an array of CurrencyCardData objects, which
   * contain the data to be set on a currency card and the iconified currency used as
   * source for the card data
   */
  public loadRegisteredCurrencies() : Observable<CurrencyCardData[]> {
    return this.catalogService.findAllCurrencies().pipe(
      map(currencies =>
        currencies.map(currency => {
          return {
            currency : currency,
            title    : currency.currencyCode,
            text     : currency.currencyName,
            imageRef : currency.imageData
          }
        })
      )
    )
  }

  /**
   * Returns an observable that produces an array of BankCardData objects, which contain the
   * data to be set on a bank card and the iconified bank used as source for the card data
   */
  public loadRegisteredBanks() : Observable<BankCardData[]> {
    return this.catalogService.findAllBanks().pipe(
      map(banks => 
        banks.map(bank => {
          return {
            bank     : bank,
            title    : bank.bankCode,
            text     : bank.bankName,
            imageRef : bank.imageData
          }
        })
      )
    )
  }

  /**
   * Returns an observable that produces an ItemsCatalog, which contain the data to be set
   * on cards describing income or expense items, income or expense item subcategories and
   * income or expense item categories.
   */
  public loadRegisteredItemsCatalog() : Observable<ItemsCatalog> {
    return new Observable<ItemsCatalog>(subscriber => {
      // Create an empty catalog
      const itemsCatalog : ItemsCatalog = {
        incomeOrExpenseItemCategories    : [],
        incomeOrExpenseItemSubcategories : [],
        incomeOrExpenseItems             : []
      }

      // Start loading the income or expense item categories
      this.catalogService.findAllIncomeOrExpenseItemCategories().pipe(
        map(categories =>
          categories.map(category => {
            return {
              incomeOrExpenseItemCategory : category,
              title                       : category.incomeOrExpenseItemCategoryName,
              text                        : category.incomeOrExpenseItemCategoryDescription,
              imageRef                    : category.imageData
            }
          })
        )
      ).subscribe({
        // When the categories are loaded
        next: categories => {
          // Assign the categories
          itemsCatalog.incomeOrExpenseItemCategories = categories

          const observables : Observable<IncomeOrExpenseItemSubcategoryCardData[]>[] =
            categories
              .map(category => 
                this.catalogService.findIncomeOrExpenseItemSubcategories(
                  category?.incomeOrExpenseItemCategory?.incomeOrExpenseItemCategoryUUID ?? ""
                ).pipe(
                  map(subcategories => 
                    subcategories.map(subcategory => {
                      return {
                        incomeOrExpenseItemCategory    : category.incomeOrExpenseItemCategory,
                        incomeOrExpenseItemSubcategory : subcategory,
                        title                          : subcategory.incomeOrExpenseItemSubcategoryName,
                        text                           : subcategory.incomeOrExpenseItemSubcategoryDescription,
                        imageRef                       : subcategory.imageData
                      }
                    })
                  )
                )
              )

          // Start loading the subcategories of all the fetched categories
          forkJoin(observables)
          .subscribe({
            // When the subcategories are loaded
            next: subcategories => {
              // Assign the subcategories
              itemsCatalog.incomeOrExpenseItemSubcategories = flattenArrays(subcategories)

              // Start loading income or expense items for all the loaded subcategories
              forkJoin(
                itemsCatalog.incomeOrExpenseItemSubcategories.map(subcategory =>
                  this.catalogService.findIncomeOrExpenseItems(
                    subcategory.incomeOrExpenseItemSubcategory.incomeOrExpenseItemSubcategoryUUID ?? ""
                  ).pipe(
                    map(items => 
                      items.map(item => {
                        return {
                          incomeOrExpenseItem            : item,
                          incomeOrExpenseItemSubcategory : subcategory.incomeOrExpenseItemSubcategory,
                          incomeOrExpenseItemCategory    : subcategory.incomeOrExpenseItemCategory,
                          title                          : item.incomeOrExpenseItemName,
                          text                           : item.incomeOrExpenseItemDescription,
                          imageRef                       : item.imageData
                        }
                      })
                    )
                  )
                )
              )
              .subscribe({
                // When the items are done loading
                next: items => {
                  // Assign the items
                  itemsCatalog.incomeOrExpenseItems = flattenArrays(items)

                  // Push the items up the pipe
                  complete(subscriber, itemsCatalog)
                },
                error: err => subscriber.error(err)
              })
            },
            error: err => {
              subscriber.error(err)
            }
          })
        },
        error: err => subscriber.error(err)
      })
    })
  }
}
