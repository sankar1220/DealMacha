/**
 *
 */
package com.dealmacha.businessdelegate.exception;

/**
 * @author skanumuri
 *
 */
public class ResourceMergeException extends RuntimeException {
    /**
     * Constructor for ResourceMergeException
     */
    public ResourceMergeException() {
    }

    /**
     * Constructor for ResourceMergeException
     *
     * @param message
     */
    public ResourceMergeException(final String message) {
        super(message);
    }

    /**
     * Constructor for ResourceMergeException
     *
     * @param message
     * @param cause
     */
    public ResourceMergeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for ResourceMergeException
     *
     * @param cause
     */
    public ResourceMergeException(final Throwable cause) {
        super(cause);
    }
}
