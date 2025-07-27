import { Observable } from "rxjs";
import { IncomeOrExpenseItemCategory } from "../model-acct/income-or-expense-item-category";
import { IncomeOrExpenseItemSubcategory } from "../model-acct/income-or-expense-item-subcategory";
import { IncomeOrExpenseItem } from "../model-acct/income-or-expense-item";

/**
 * Allows creating, reading, updating and deleting income or expense item categories and subcategories
 */
export abstract class AcctItemsRepository {

    /**
     * Returns an observable that produces a list of all income or expense item categories in the catalog
     */
    abstract findAllIncomeOrExpenseItemCategories() : Observable<IncomeOrExpenseItemCategory[]>

    /**
     * Saves the referenced income or expense item category into the repository
     * 
     * @param incomeOrExpenseItemCategory the referenced income or expense item category
     */
    abstract saveIncomeOrExpenseItemCategory(incomeOrExpenseItemCategory:IncomeOrExpenseItemCategory) : Observable<void>

    /**
     * Deletes the income or expense item categories having the UUIDs in the referenced list of
     * income or expense iten category UUIDs
     * 
     * @param incomeOrExpenseItemCategoryUUIDs the referenced list of income or expense iten category UUIDs
     */
    abstract deleteIncomeOrExpenseItemCategories(incomeOrExpenseItemCategoryUUIDs:string[]) : Observable<void>

    /**
     * Returns an observable that produces a list of all the income or expense item sub-categories registered
     * in the catalog under the category with the given income or expense item category UUID
     * 
     * @param incomeOrExpenseItemCategoryUUID the given income or expense item category UUID
     */
    abstract findIncomeOrExpenseItemSubcategories(incomeOrExpenseItemCategoryUUID:string) : Observable<IncomeOrExpenseItemSubcategory[]>

    /**
     * Saves the referenced income or expense item sub-category into the repository, under category referenced
     * using the given incomeOrExpenseItemCategoryUUID
     * 
     * @param incomeOrExpenseItemCategoryUUID the given incomeOrExpenseItemCategoryUUID
     * @param incomeOrExpenseItemSubcategory  the referenced income or expense item sub-category
     */
    abstract saveIncomeOrExpenseItemSubcategory(
        incomeOrExpenseItemCategoryUUID : string,
        incomeOrExpenseItemSubcategory  : IncomeOrExpenseItemSubcategory
    ) : Observable<void>

    /**
     * Deletes the income or expense item sub-categories having the UUIDs in the referenced list of
     * income or expense iten sub-category UUIDs
     * 
     * @param incomeOrExpenseItemSubcategoryUUIDs the referenced list of income or expense iten sub-category UUIDs
     */
    abstract deleteIncomeOrExpenseItemSubcategories(incomeOrExpenseItemSubcategoryUUIDs:string[]) : Observable<void>

    /**
     * Returns an observable that produces a list of all the income or expense items registered in
     * the catalog under the sub-category with the given income or expense item subcategory UUID
     * 
     * @param incomeOrExpenseItemSubcategoryUUID the given income or expense item subcategory UUID
     */
    abstract findIncomeOrExpenseItems(incomeOrExpenseItemSubcategoryUUID:string) : Observable<IncomeOrExpenseItem[]>

    /**
     * Saves the referenced income or expense item under the sub-category having the given income or
     * expense item sub-category UUID
     * 
     * @param incomeOrExpenseItemSubcategoryUUID   he given income orexpense item sub-category UUID
     * @param incomeOrExpenseItem                  the refernced income or expense item
     */
    abstract saveIncomeOrExpenseItem(
        incomeOrExpenseItemSubcategoryUUID : string,
        incomeOrExpenseItem  : IncomeOrExpenseItem
    ) : Observable<void>

    /**
     * Deletes the income or expense items identified by the UUIDs in the referenced UUIDs array
     * 
     * @param incomeOrExpenseItemUUIDs the referenced UUIDs array
     */
    abstract deleteIncomeOrExpenseItems(incomeOrExpenseItemUUIDs:string[]) : Observable<void>
}