/**
 * Generic page request object that helps reduce confusion when calling methods with lots of parameters
 */
export interface AcctPageRequest<P> {

    /**
     * Container for the query parameters
     */
    queryParameters : P

    /**
     * The page number to fetch
     */
    pageNumber : number

    /**
     * The number of records in any given page
     */
    pageSize : number

}