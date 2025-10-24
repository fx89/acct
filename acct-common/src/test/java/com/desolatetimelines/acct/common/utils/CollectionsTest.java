package com.desolatetimelines.acct.common.utils;

import org.junit.jupiter.api.Test;

import java.util.*;

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

}
