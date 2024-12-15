package com.desolatetimelines.acct.common.ws.model;

/**
 * Contains information about the current page and the entirety of the data set
 *
 * @param size          the number of records on the page
 * @param totalElements the total number of elements in the data set
 * @param totalPages    the total number of pages in the data set
 * @param number        the page number
 */
public record AcctPageInfo(
    int size,
    long totalElements,
    int totalPages,
    int number
) {
}
