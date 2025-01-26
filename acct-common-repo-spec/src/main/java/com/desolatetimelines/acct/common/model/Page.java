package com.desolatetimelines.acct.common.model;

import java.util.Collection;

import static java.util.Collections.emptyList;

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

    /**
     * Returns a {@link Page page} with zero elements
     */
    public static <T> Page<T> emptyPage() {
        return new Page<>(emptyList(), 0, 0);
    }

}
