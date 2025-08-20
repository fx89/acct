import { AcctPageInfo } from "./page-info";

/**
 * Defines a page of elements of a given data type
 */
export interface AcctPage<T> {
    /**
     * a collection of the elements
     */
    data : T[],

    /**
     * page information
     */
    page : AcctPageInfo
}

/**
 * Returns an empty page of elements of the given data type
 */
export function emptyPage<T>() : AcctPage<T> {
    return {
        page: {
            number: 0,
            size: 0,
            totalElements: 0,
            totalPages: 0
          },
          data: []
    }
}