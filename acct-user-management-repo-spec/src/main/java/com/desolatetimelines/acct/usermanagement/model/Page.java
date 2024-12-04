package com.desolatetimelines.acct.usermanagement.model;

import java.util.Collection;

/**
 * @param data        the data contained in this page
 * @param numElements the number of elements on this page
 * @param maxElements the maximum allowed number of elements
 */
public record Page<T>(
    Collection<T> data,
    int numElements,
    long maxElements
) {


}
