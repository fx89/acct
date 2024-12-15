package com.desolatetimelines.acct.common.utils;

import java.util.stream.Stream;

/**
 * Provides service methods for working with {@link Stream streams}
 */
public class Streams {

    /**
     * {@link Stream#concat(Stream, Stream) Concatenates} all the streams in the
     * given varargs array
     *
     * @param streams the given varargs array
     * @param <T>     the base type for the objects in the streams in the given varargs array
     * @return a stream of all the objects in all the streams in the given varargs array
     */
    @SafeVarargs
    public static <T> Stream<T> multiConcat(Stream<T>... streams) {
        // Garbage in, garbage out
        if (streams == null) {
            return null;
        }

        // More garbage in, more garbage out
        if (streams.length == 0) {
            return Stream.empty();
        }

        // Initialize the result stream with the first stream in the varargs array
        Stream<T> resultStream = streams[0];

        // Concatenate the result stream with all the other streams in the varargs array
        for (int s = 1; s < streams.length; s++) {
            resultStream = Stream.concat(resultStream, streams[s]);
        }

        // Return the result
        return resultStream;
    }

}
