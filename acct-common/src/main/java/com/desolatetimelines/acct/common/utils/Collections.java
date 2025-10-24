package com.desolatetimelines.acct.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import static java.util.Objects.requireNonNull;

/**
 * Provides utilities for working with collections
 */
public abstract class Collections {

    /**
     * Concatenates two collections of the same element type into one single collection. If both collections
     * guarantee read order, then the resulted collection will keep the read order as well. Elements from the
     * first collection will precede those from the second collection in the resulted collection. This method
     * only works if the source collection type supports a constructor that takes the initial capacity as its
     * only parameter.
     *
     * @param collectionA The first collection
     * @param collectionB The second collection
     * @param <T>         The element type of the two collections
     * @param <K>         The type of collection
     * @return A collection of the same type as the source collections, containing elements from both source
     * collection
     * @throws RuntimeException when the result collection type cannot be instantiated via reflection
     */
    public static <T, K extends Collection<T>> K concat(K collectionA, K collectionB) {
        // Make sure both collections are given
        requireNonNull(collectionA, "Null reference to collection A");
        requireNonNull(collectionB, "Null reference to collection B");

        // Create a new collection of the same type as the source collections and set the initial capacity
        // to the combined sizes of the two source collections
        final K result = newCollection(collectionA, collectionA.size() + collectionB.size());

        // Add all elements from the first source collection
        result.addAll(collectionA);

        // Add all elements from the second source collection
        result.addAll(collectionB);

        // Return a reference to the resulted collection
        return result;
    }

    /**
     * Creates a new empty collection of the data type of the referenced template and of the given initial
     * capacity.
     *
     * @param template        The referenced template
     * @param initialCapacity The given initial capacity
     * @param <T>             The element type of the given template
     * @param <K>             The type of the given template
     * @throws RuntimeException when the collection type cannot be instantiated via reflection
     */
    @SuppressWarnings("unchecked")
    private static <T, K extends Collection<T>> K newCollection(K template, int initialCapacity) {
        try {
            return (K) template.getClass().getConstructor(int.class).newInstance(initialCapacity);
        }
        // If any exception occurs in the reflection process, then throw a runtime exception
        catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Unable to create new collection: " + e.getMessage(), e);
        }
    }

}
