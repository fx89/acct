import { Observable } from "rxjs";
import { IncomeOrExpenseItemCategory } from "../../model-acct/income-or-expense-item-category";
import { AcctItemsRepository } from "../items-repository";
import { IncomeOrExpenseItemSubcategory } from "../../model-acct/income-or-expense-item-subcategory";
import { IncomeOrExpenseItem } from "../../model-acct/income-or-expense-item";

/**
 * Mock implementation of the items repository
 */
export class MockAcctItemsRepository extends AcctItemsRepository {

    override findAllIncomeOrExpenseItemCategories(): Observable<IncomeOrExpenseItemCategory[]> {
        throw new Error("Method not implemented.");
    }

    override saveIncomeOrExpenseItemCategory(incomeOrExpenseItemCategory:IncomeOrExpenseItemCategory) : Observable<void> {
        throw new Error("Method not implemented.");
    }

    override deleteIncomeOrExpenseItemCategories(incomeOrExpenseItemCategoryUUIDs:string[]) : Observable<void> {
        throw new Error("Method not implemented.");
    }

    override findIncomeOrExpenseItemSubcategories(incomeOrExpenseItemCategoryUUID:string) : Observable<IncomeOrExpenseItemSubcategory[]> {
        throw new Error("Method not implemented.");
    }

    override saveIncomeOrExpenseItemSubcategory(
        incomeOrExpenseItemCategoryUUID : string,
        incomeOrExpenseItemSubcategory  :IncomeOrExpenseItemSubcategory
    ) : Observable<void> {
        throw new Error("Method not implemented.");
    }

    override deleteIncomeOrExpenseItemSubcategories(incomeOrExpenseItemSubcategoryUUIDs:string[]) : Observable<void> {
        throw new Error("Method not implemented.");
    }

    override findIncomeOrExpenseItems(incomeOrExpenseItemSubcategoryUUID:string) : Observable<IncomeOrExpenseItem[]> {
        throw new Error("Method not implemented.");
    }

    override saveIncomeOrExpenseItem(
        incomeOrExpenseItemSubcategoryUUID : string,
        incomeOrExpenseItem  : IncomeOrExpenseItem
    ) : Observable<void> {
        throw new Error("Method not implemented.");
    }

    override deleteIncomeOrExpenseItems(incomeOrExpenseItemUUIDs:string[]) : Observable<void> {
        throw new Error("Method not implemented.");
    }

}