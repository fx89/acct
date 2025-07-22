/**
 * Contains information about the current page and the entirety of the data set
 */
export interface AcctPageInfo {
    /**
     * the number of records on the page
     */
    size : number,

    /**
     * the total number of elements in the data set
     */
    totalElements : number,

    /**
     * the total number of pages in the data set
     */
    totalPages : number,

    /**
     * the page number
     */
    number : number
}