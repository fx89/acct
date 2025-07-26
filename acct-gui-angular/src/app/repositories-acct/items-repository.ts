import { Observable } from "rxjs";
import { IncomeOrExpenseItemCategory } from "../model-acct/income-or-expense-item-category";

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
}