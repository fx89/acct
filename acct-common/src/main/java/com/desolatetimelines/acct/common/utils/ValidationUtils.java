package com.desolatetimelines.acct.common.utils;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Provides utilities that help with validation
 */
public abstract class ValidationUtils {

    /**
     * Throws the {@link Throwable} supplied by the given {@code throwableSupplier} if the given {@code item}
     * reference is null.
     *
     * @param item              The given item reference
     * @param throwableSupplier The given throwableSupplier
     * @param <T>               The type of the referenced item
     * @param <E>               The type of the supplied throwable
     */
    public static <T, E extends Throwable> void throwIfNull(T item, Supplier<E> throwableSupplier) throws E {
        if (item == null) {
            throw throwableSupplier.get();
        }
    }

    /**
     * Throws the {@link Throwable} supplied by the given {@code throwableSupplier} if the referenced {@code collection}
     * is null or empty.
     *
     * @param collection        The referenced collection
     * @param throwableSupplier The given throwableSupplier
     * @param <T>               The type of element contained by the referenced collection
     * @param <E>               The type of the supplied throwable
     */
    public static <T, E extends Throwable> void throwIfNullOrEmpty(
        Collection<T> collection,
        Supplier<E> throwableSupplier
    ) throws E {
        if (collection == null || collection.isEmpty()) {
            throw throwableSupplier.get();
        }
    }

    /**
     * Throws the {@link Throwable} supplied by the given {@code throwableSupplier} if the referenced
     * {@code map} is null or empty.
     *
     * @param map               The referenced map
     * @param throwableSupplier The given throwableSupplier
     * @param <K>               The type of the key element contained by the referenced map
     * @param <V>               The type of the value element contained by the referenced map
     * @param <E>               The type of the supplied throwable
     */
    public static <K, V, E extends Throwable> void throwIfNullOrEmpty(
        Map<K, V> map,
        Supplier<E> throwableSupplier
    ) throws E {
        if (map == null || map.isEmpty()) {
            throw throwableSupplier.get();
        }
    }

}
