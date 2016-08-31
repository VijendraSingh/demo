package com.sample.exception;

/**
 * CustomerExceptionClass class used to throw exception message
 * @author Vijendra Singh
 *
 */
public class CustomerExceptionClass extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * @param message exception message
     */
    public CustomerExceptionClass(final String message) {
        super(message);
    }

    /**
     * @param cause exception cause
     */
    public CustomerExceptionClass(final Throwable cause) {
        super(cause);
    }

    /**
     * @param message exception message
     * @param cause exception message
     */
    public CustomerExceptionClass(final String message, final Throwable cause) {
        super(message, cause);
    }
}
