package com.desolatetimelines.acct.common.ws.model;

import java.util.Collection;

/**
 * Defines a page of elements of a given data type
 *
 * @param data a collection of the elements
 * @param page page information
 * @param <T>  the given data type
 */
public record AcctPage<T>(

    Collection<T> data,

    AcctPageInfo page

) {
}
