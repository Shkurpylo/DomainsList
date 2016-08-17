package com.oril.exceptions;

/**
 * If service is not available.
 */

public class ServiceUnavailableException extends Exception {

    public ServiceUnavailableException(String message) {
        super(message);
    }

}