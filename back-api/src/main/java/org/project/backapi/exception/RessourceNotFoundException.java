package org.project.backapi.exception;

public class RessourceNotFoundException extends RuntimeException{

    public RessourceNotFoundException(String message){
        super(message);
    }

    public RessourceNotFoundException(String comment, String id, Long commentId) {
    }
}
