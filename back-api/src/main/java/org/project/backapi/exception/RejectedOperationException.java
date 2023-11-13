package org.project.backapi.exception;

public class RejectedOperationException extends RuntimeException{
    public RejectedOperationException(String message) {
        super(message);
    }
}
