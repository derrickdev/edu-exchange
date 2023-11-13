package org.project.backapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = RessourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ErrorResponse handleRessourceNotFoundException(RessourceNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = RequestNotAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody
    ErrorResponse handleRequestNotAuthorizedException(RequestNotAuthorizedException ex) {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(value = RessourceAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody
    ErrorResponse handleRessourceAlreadyExistException(RessourceAlreadyExistException ex) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }


    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorResponse handleBadRequestException(BadRequestException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = RejectedOperationException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public @ResponseBody
    ErrorResponse handleBadRequestException(RejectedOperationException ex) {
        return new ErrorResponse(HttpStatus.ALREADY_REPORTED.value(), ex.getMessage());
    }

    @ExceptionHandler(value = ForbiddenAccessOperation.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public @ResponseBody
    ErrorResponse handleBadRequestException(ForbiddenAccessOperation ex) {
        return new ErrorResponse(HttpStatus.FORBIDDEN.value(), ex.getMessage());
    }

}