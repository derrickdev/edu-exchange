package org.project.backapi.exception;

public class RessourceAlreadyExistException extends RuntimeException{
    public RessourceAlreadyExistException(String message){
        super(message);
    }
}