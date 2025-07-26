import { Observable } from "rxjs";
import { IncomeOrExpenseItemCategory } from "../../model-acct/income-or-expense-item-category";
import { AcctItemsRepository } from "../items-repository";

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

}