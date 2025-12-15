package com.desolatetimelines.acct.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Collections.emptySet;
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
     * Given two sets of elements of distinct types, the minuend and the subtrahend,
     * computes a sub-set of elements from the minuend set that do not appear in the
     * subtrahend. Comparison is done on the keys extracted using the referenced key
     * extractor functions.
     *
     * @param minuend                       A set of elements from which the elements
     *                                      found in the subtrahend are to be removed
     * @param subtrahend                    A set of elements that are to be removed
     *                                      from the minuend, if found there.
     * @param minuendElementKeyExtractor    A function that extracts the key from an
     *                                      element found in the minuend set, for the
     *                                      purpose of comparison with elements from
     *                                      the subtrahend set.
     * @param subtrahendElementKeyExtractor A function that extracts the key from an
     *                                      element found in the subtrahend set, for
     *                                      the purpose of comparison with elements
     *                                      from the minuend set.
     * @param <M>                           The type of element found in the minuend
     *                                      set.
     * @param <S>                           The type of element found in the subtrahend
     *                                      set. It does not have to be the same as
     *                                      the type of element found in the minuend set.
     * @param <K>                           The type of the keys used for comparison
     *                                      between elements of the minuend set and those
     *                                      of the subtrahend set. This must be the same
     *                                      type for both sets.
     * @return an {@link java.util.Collections#unmodifiableSet(Set) unmodifiable} set of
     * elements that exist in the minuend set and do not exist in the subtrahend set.
     */
    public static <M, S, K> Set<M> minus(
        Set<M> minuend,
        Set<S> subtrahend,
        Function<M, K> minuendElementKeyExtractor,
        Function<S, K> subtrahendElementKeyExtractor
    ) {
        if (minuend == null || minuend.isEmpty()) {
            return emptySet();
        }

        if (subtrahend == null || subtrahend.isEmpty()) {
            return java.util.Collections.unmodifiableSet(minuend);
        }

        return
            minuend.stream()
                .filter(
                    me -> subtrahend.stream().noneMatch(se ->
                        Objects.equals(
                            minuendElementKeyExtractor.apply(me),
                            subtrahendElementKeyExtractor.apply(se)
                        )
                    )
                )
                .collect(Collectors.toSet());
    }

    /**
     * Given two sets of elements, set A and set B, returns a set that contains
     * the elements found in set A that also exists in set B. Comparison is done
     * using the keys extracted by the referenced key extractor functions.
     *
     * @param setA                    One of the two sets.
     * @param setB                    The other set. Element type may be different
     *                                from that of the other set.
     * @param setAElementKeyExtractor Key extractor for the element type of set A.
     * @param setBElementKeyExtractor Key extractor for the element type of set B.
     * @param <T>                     The type of element found in set A.
     * @param <U>                     The type of element found in set B. May be
     *                                different from the type of element found in
     *                                set A.
     * @param <K>                     The type of the comparison key. Must be the
     *                                same for both sets.
     * @return An {@link java.util.Collections#unmodifiableSet(Set) unmodifiable}
     * set of elements that are found in both sets. The element type is that of
     * the first set.
     */
    public static <T, U, K> Set<T> intersect(
        Set<T> setA,
        Set<U> setB,
        Function<T, K> setAElementKeyExtractor,
        Function<U, K> setBElementKeyExtractor
    ) {
        if (setA == null || setA.isEmpty() || setB == null || setB.isEmpty()) {
            return emptySet();
        }

        return
            setA.stream()
                .filter(
                    sAe -> setB.stream().anyMatch(sBe ->
                        Objects.equals(
                            setAElementKeyExtractor.apply(sAe),
                            setBElementKeyExtractor.apply(sBe)
                        )
                    )
                )
                .collect(Collectors.toSet());
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
