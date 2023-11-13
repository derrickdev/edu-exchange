package org.project.backapi.exception;

public class ForbiddenAccessOperation extends RuntimeException{
    public ForbiddenAccessOperation(String message) {
        super(message);
    }
}
