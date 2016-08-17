package com.oril.exceptions;

/**
 *  If there was a problem with the connection.
 */
public class LookupException extends RuntimeException {

    public LookupException(String message) {
        super(message);
    }

}