package org.project.backapi.exception;

public class RequestNotAuthorizedException extends  RuntimeException {
    public RequestNotAuthorizedException(String message){
        super(message);
    }
}
