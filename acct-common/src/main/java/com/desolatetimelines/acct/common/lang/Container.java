package com.desolatetimelines.acct.common.lang;

/**
 * Shallow container for an object of a given type.
 *
 * @param <T> The type of the contained object
 */
public class Container<T> {
    private T contained;

    /**
     * Sets the contained object
     *
     * @param contained reference to the contained object.
     */
    public synchronized void set(T contained) {
        this.contained = contained;
    }

    /**
     * Returns a reference to the contained object or null if there is no contained object.
     */
    public T get() {
        return contained;
    }

    /**
     * Clears the container of any contained object.
     */
    public void clear() {
        this.contained = null;
    }

    /**
     * Returns true if the container contains an object. Returns false if the container does not
     * contain any object.
     */
    public boolean isPresent() {
        return contained != null;
    }
}
