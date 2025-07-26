import { Observable } from "rxjs";
import { IncomeOrExpenseItemCategory } from "../../model-acct/income-or-expense-item-category";
import { AcctItemsRepository } from "../items-repository";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector } from "../../services-reusable/http-connectors.service";

/**
 * Implementation of the items repository that uses the HTTP client abstraction layer to
 * work with the ACCT back-end services
 */
export class HttpAcctItemsRepository extends AcctItemsRepository {

    constructor(
        private httpConnector : HttpConnector
    ) {
        super()
    }

    override findAllIncomeOrExpenseItemCategories(): Observable<IncomeOrExpenseItemCategory[]> {
        return new Observable<IncomeOrExpenseItemCategory[]>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/items/categories"
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:IncomeOrExpenseItemCategory[]) => responseBody,
                    "Income or expense item categories not found."
                )
            )
        })
    }

    override saveIncomeOrExpenseItemCategory(
        incomeOrExpenseItemCategory:IncomeOrExpenseItemCategory
    ) : Observable<void> {
        return new Observable<void>(subscriber => {
            // Create parameters object
            const params : Record<string, string | number | boolean | ReadonlyArray<string | number | boolean>> = {}

            // If the category has an UUID, then add it to the parameters object
            if (incomeOrExpenseItemCategory.incomeOrExpenseItemCategoryUUID) {
                params["incomeOrExpenseItemCategoryUUID"] = incomeOrExpenseItemCategory.incomeOrExpenseItemCategoryUUID
            }

            this.httpConnector.put(
                {
                    url: "/items/categories",
                    data: {
                        params: params,
                        body: {
                            incomeOrExpenseItemCategoryName        : incomeOrExpenseItemCategory.incomeOrExpenseItemCategoryName,
                            incomeOrExpenseItemCategoryDescription : incomeOrExpenseItemCategory.incomeOrExpenseItemCategoryDescription,
                            incomeOrExpenseItemCategoryIconUUID    : incomeOrExpenseItemCategory.incomeOrExpenseItemCategoryIconUUID
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:IncomeOrExpenseItemCategory[]) => responseBody,
                    "Income or expense item categories not created."
                )
            )
        })
    }
    
}