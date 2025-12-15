package com.desolatetimelines.acct.common.utils;

import org.junit.jupiter.api.Test;

import java.util.*;

import static com.desolatetimelines.acct.common.utils.Collections.intersect;
import static com.desolatetimelines.acct.common.utils.Collections.minus;
import static java.util.Collections.emptySet;
import static java.util.function.Function.identity;
import static org.junit.jupiter.api.Assertions.*;

public class CollectionsTest {

    /**
     * Verifies that concat() throws a NullPointerException when either source collection is null.
     * This ensures the method performs defensive input validation and prevents undefined behavior.
     */
    @Test
    void testConcat_throwsExceptionWhenAnySourceIsNull() {
        // Prepare a valid sample collection
        List<String> validList = Arrays.asList("A", "B");

        // Expect NullPointerException when the first collection is null
        assertThrows(NullPointerException.class, () -> Collections.concat(null, validList));

        // Expect NullPointerException when the second collection is null
        assertThrows(NullPointerException.class, () -> Collections.concat(validList, null));
    }

    /**
     * Verifies that concat() correctly combines all elements from both source collections.
     * This test uses HashSet, which does not guarantee element order — therefore we only
     * check that all expected elements are present, regardless of order.
     */
    @Test
    void testConcat_containsAllElementsFromBothSources() {
        // Prepare unordered collections (HashSet does not guarantee iteration order)
        Set<String> first = new HashSet<>(Arrays.asList("A", "B"));
        Set<String> second = new HashSet<>(Arrays.asList("C", "D"));

        // Call concat
        Set<String> result = Collections.concat(first, second);

        // Verify that result contains all elements from both sets (order is not important)
        assertEquals(4, result.size());
        assertTrue(result.containsAll(Arrays.asList("C", "B", "A", "D")));
    }

    /**
     * Verifies that concat() preserves read order for ordered collection types.
     * For ArrayList and LinkedHashSet, elements should appear in insertion order,
     * with elements from the first collection preceding those from the second one.
     */
    @Test
    void testConcat_preservesReadOrderForOrderedCollections() {
        // ArrayList preserves insertion order
        List<Integer> listA = new ArrayList<>(Arrays.asList(1, 2));
        List<Integer> listB = new ArrayList<>(Arrays.asList(3, 4));

        List<Integer> concatenatedList = Collections.concat(listA, listB);
        assertEquals(Arrays.asList(1, 2, 3, 4), concatenatedList);

        // LinkedHashSet also preserves insertion order
        Set<Integer> setA = new LinkedHashSet<>(Arrays.asList(10, 20));
        Set<Integer> setB = new LinkedHashSet<>(Arrays.asList(30, 40));

        Set<Integer> concatenatedSet = Collections.concat(setA, setB);
        assertEquals(Arrays.asList(10, 20, 30, 40), new ArrayList<>(concatenatedSet));
    }

    /**
     * Verifies that concat() throws a RuntimeException when using LinkedList as the source collection type.
     * This ensures that concat() explicitly rejects unsupported collection types that lack an
     * "initial capacity" constructor or cannot be safely instantiated by reflection.
     */
    @Test
    void testConcat_throwsRuntimeExceptionForLinkedList() {
        // Prepare LinkedLists as source collections
        List<String> listA = new LinkedList<>(Arrays.asList("A", "B"));
        List<String> listB = new LinkedList<>(Arrays.asList("C", "D"));

        // Expect a RuntimeException due to unsupported collection type
        assertThrows(RuntimeException.class, () -> Collections.concat(listA, listB));
    }

    /**
     * Tests that the minus() operation does not crash when presented with null or empty parameters
     */
    @Test
    void testMinus_doesNotCrashOnNullOrEmpty() {
        assertEquals(0, minus(null, null, null, null).size());
        assertEquals(0, minus(emptySet(), null, null, null).size());
        assertEquals(0, minus(null, emptySet(), null, null).size());
        assertEquals(5, minus(Set.of("1", "2", "3", "4", "5"), null, identity(), null).size());
        assertEquals(0, minus(null, Set.of(1, 2, 3), null, identity()).size());
    }

    /**
     * Tests that the minus() operation works according to plan
     */
    @Test
    void testMinus_worksCorrectly() {
        // Create the minuend
        final Set<String> minuend = Set.of("one", "two", "three", "four", "five");

        // Create the subtrahend
        final Set<String> subtrahend = Set.of("three", "five");

        // Perform the subtraction operation
        final Set<String> result = minus(minuend, subtrahend, identity(), identity());

        // Verify that the result is correct
        assertTrue(result.containsAll(Set.of("one", "two", "four")));
    }

    /**
     * Tests that the intersect() operation does not crash when presented with null or empty parameters
     */
    @Test
    void testIntersect_doesNotCrashOnNullOrEmpty() {
        assertEquals(0, intersect(null, null, null, null).size());
        assertEquals(0, intersect(emptySet(), null, null, null).size());
        assertEquals(0, intersect(null, emptySet(), null, null).size());
        assertEquals(0, intersect(Set.of("1", "2", "3", "4", "5"), null, identity(), null).size());
        assertEquals(0, intersect(null, Set.of(1, 2, 3), null, identity()).size());
    }

    /**
     * Tests that the minus() operation works according to plan
     */
    @Test
    void testIntersect_worksCorrectly() {
        // Create the minuend
        final Set<String> setA = Set.of("one", "two", "three", "four", "five");

        // Create the subtrahend
        final Set<String> setB = Set.of("three", "five", "four", "fun");

        // Perform the intersection
        final Set<String> result = intersect(setA, setB, identity(), identity());

        // Verify that the result is correct
        assertTrue(result.containsAll(Set.of("three", "four", "five")));
    }

}
