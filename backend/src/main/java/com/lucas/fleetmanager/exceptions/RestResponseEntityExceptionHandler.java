package com.lucas.fleetmanager.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.concurrent.ExecutionException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ExecutionException.class, InterruptedException.class})
    protected ResponseEntity<Object> handleExternalCallException(RuntimeException ex, WebRequest request) {
        String bodyError = "Error calling external service: ".concat(ex.getMessage());
        return handleExceptionInternal(ex, bodyError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
