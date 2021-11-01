package com.demo.OfferTechnicalTest.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ManagementException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> userNotFoundException(
            UserNotFoundException ex, WebRequest request) {
        CustomizedResponseError customizedResponseError =
                new CustomizedResponseError(ex.getMessage());
        return new ResponseEntity<>(customizedResponseError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotAllowedUserException.class)
    @ResponseBody
    public ResponseEntity<?> notAllowedUserException(
            NotAllowedUserException ex, WebRequest request) {
        CustomizedResponseError customizedResponseError =
                new CustomizedResponseError(ex.getMessage());
        return new ResponseEntity<>(customizedResponseError, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
        CustomizedResponseError customizedResponseError = new CustomizedResponseError(e.getConstraintViolations().stream().map(err -> err.getMessageTemplate()).collect(Collectors.joining(", ")));
        return new ResponseEntity<>(customizedResponseError, HttpStatus.BAD_REQUEST);
    }

}
