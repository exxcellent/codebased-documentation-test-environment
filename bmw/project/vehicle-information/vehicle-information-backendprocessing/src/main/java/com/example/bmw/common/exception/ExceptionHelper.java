package com.example.bmw.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import com.google.common.base.Throwables;

/**
 * Helper class that provides basic functionality for exception-handling.
 *
 * @author Jonathan Knam, MaibornWolff GmbH
 */
public class ExceptionHelper {

    /**
     * Default Constructor
     */
    private ExceptionHelper() {
    }


    /**
     * Checks if the causal chain of the given exception contains a throwable of the given class.
     */
    public static boolean causalChainContainsClass(final Exception ex, final Class<?> clazzOfCause) {
        return Throwables.getCausalChain(ex)
                .stream()
                .anyMatch(clazzOfCause::isInstance);
    }


    /**
     * Extracts an exception of a given class from within the causual chain of an exception.
     */
    public static <T> T extractException(final Exception ex, final Class<T> exceptionClass) {
        for (final Throwable innerThrowable : Throwables.getCausalChain(ex)) {
            if (exceptionClass.isInstance(innerThrowable)) {
                return (T) innerThrowable;
            }
        }
        return null;
    }


    /**
     * Returns the complete stack-trace for type Throwable as string.
     *
     * @param t Throwable
     * @return String containing the stack-trace
     */
    public static String toString(final Throwable t) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

}
