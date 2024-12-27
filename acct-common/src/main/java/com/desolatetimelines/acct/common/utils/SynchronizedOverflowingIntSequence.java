package com.desolatetimelines.acct.common.utils;

/**
 * Provides integers from a sequence with the given increment and bounds in a synchronized fashion.
 * The is is an overflowing sequence, which means that when the upper bound has been reached the
 * sequence returns to the low bound.<br />
 * <br />
 * The following parameters apply: <ul>
 * <li>The lower bound - This is the first number coming out of the sequence. The sequence rewinds to this number when it overflows</li>
 * <li>The upper bound - The maximum number that can come out of the sequence. The sequence includes this number.</li>
 * <li>The increment - Cannot be negative. Cannot be zero. Cannot be larger than (upper bound - lower bound)</li>
 * </ul>
 */
public class SynchronizedOverflowingIntSequence {

    private final int lowerBound;

    private final int upperBound;

    private final int increment;

    private final boolean allowOverflow;

    private int current;


    /**
     * Constructor for the sequence
     *
     * @param lowerBound The minimum possible number that can be returned by the sequence
     * @param upperBound The maximum possible number that can be returned by the sequence
     * @param increment  A positive integer representing the amount by which to increment the value of the sequence
     */
    public SynchronizedOverflowingIntSequence(int lowerBound, int upperBound, int increment, boolean allowOverflow) {
        // Make sure the bounds are set correctly
        if (lowerBound >= upperBound) {
            throw new IllegalArgumentException("The lower bound must be lower than the upper bound");
        }

        // Make sure the increment is set correctly
        if (increment < 1 || upperBound - lowerBound < increment) {
            throw new IllegalArgumentException(
                "The increment must be greater than zero and smaller than the range of the sequence"
            );
        }

        // Assign the sequence parameters
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.increment = increment;
        this.allowOverflow = allowOverflow;

        // Reset the current value of the sequence
        current = lowerBound - increment;
    }

    /**
     * Increments the current value of the sequence and returns the resulting number
     */
    public synchronized int incrementAndGet() {
        // Increment the current value of the sequence
        current += increment;

        // If the current value of the sequence is over the upper bound then
        // either reset it or throw an exception, depending on weather or not
        // overflowing is allowed
        if (current > upperBound) {
            if (allowOverflow) {
                current = lowerBound;
            } else {
                throw new IllegalStateException("Sequence overflow");
            }
        }

        // In any case, return the current value of the sequence
        return current;
    }

}
