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