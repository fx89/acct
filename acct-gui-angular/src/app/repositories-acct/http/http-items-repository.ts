import { Observable } from "rxjs";
import { IncomeOrExpenseItemCategory } from "../../model-acct/income-or-expense-item-category";
import { AcctItemsRepository } from "../items-repository";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector } from "../../services-reusable/http-connectors.service";
import { IncomeOrExpenseItemSubcategory } from "../../model-acct/income-or-expense-item-subcategory";

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
                    (responseBody) => responseBody,
                    "Income or expense item category not created."
                )
            )
        })
    }

    override deleteIncomeOrExpenseItemCategories(incomeOrExpenseItemCategoryUUIDs:string[]) : Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.delete(
                {
                    url: "/items/categories",
                    data: {
                        params: {
                            categories: incomeOrExpenseItemCategoryUUIDs
                        }
                    }
                },
                {
                    responseHandler: () => {
                        subscriber.next()
                        subscriber.complete()
                    },
                    errorHandler: err => subscriber.error(err)
                }
            )
        })
    }

    override findIncomeOrExpenseItemSubcategories(
        incomeOrExpenseItemCategoryUUID:string
    ) : Observable<IncomeOrExpenseItemSubcategory[]> {
        return new Observable<IncomeOrExpenseItemSubcategory[]>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/items/subcategories",
                    data: {
                        params: {
                            incomeOrExpenseItemCategoryUUID: incomeOrExpenseItemCategoryUUID
                        }
                    }
                },
                {
                    responseHandler: (response:any) => {    
                        subscriber.next(response.body)
                        subscriber.complete()
                    },
                    errorHandler: err => subscriber.error(err)
                }
            )
        })
    }

    override saveIncomeOrExpenseItemSubcategory(
        incomeOrExpenseItemCategoryUUID : string,
        incomeOrExpenseItemSubcategory : IncomeOrExpenseItemSubcategory
    ) : Observable<void> {
        return new Observable<void>(subscriber => {
            // Create parameters object
            const params : Record<string, string | number | boolean | ReadonlyArray<string | number | boolean>> = {}

            // Add the category UUID parameter
            params["incomeOrExpenseItemCategoryUUID"] = incomeOrExpenseItemCategoryUUID

            // If the sub-category has an UUID, then add it to the parameters object
            if (incomeOrExpenseItemSubcategory.incomeOrExpenseItemSubcategoryUUID) {
                params["incomeOrExpenseItemSubcategoryUUID"] = incomeOrExpenseItemSubcategory.incomeOrExpenseItemSubcategoryUUID
            }

            this.httpConnector.put(
                {
                    url: "/items/subcategories",
                    data: {
                        params: params,
                        body: {
                            incomeOrExpenseItemSubcategoryName        : incomeOrExpenseItemSubcategory.incomeOrExpenseItemSubcategoryName,
                            incomeOrExpenseItemSubcategoryDescription : incomeOrExpenseItemSubcategory.incomeOrExpenseItemSubcategoryDescription,
                            incomeOrExpenseItemSubcategoryIconUUID    : incomeOrExpenseItemSubcategory.incomeOrExpenseItemSubcategoryIconUUID
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody) => responseBody,
                    "Income or expense item sub-category not created."
                )
            )
        })
    }

    override deleteIncomeOrExpenseItemSubcategories(incomeOrExpenseItemSubcategoryUUIDs:string[]) : Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.delete(
                {
                    url: "/items/subcategories",
                    data: {
                        params: {
                            incomeOrExpenseItemSubcategoryUUIDs: incomeOrExpenseItemSubcategoryUUIDs
                        }
                    }
                },
                {
                    responseHandler: () => {
                        subscriber.next()
                        subscriber.complete()
                    },
                    errorHandler: err => subscriber.error(err)
                }
            )
        })
    }
    
}