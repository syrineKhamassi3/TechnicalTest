package com.demo.OfferTechnicalTest.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseEntity<?> constraintViolationException(
            HttpMessageNotReadableException ex, WebRequest request) {
        CustomizedResponseError customizedResponseError = new CustomizedResponseError(ex.getMessage());
        return new ResponseEntity<>(customizedResponseError, HttpStatus.BAD_REQUEST);
    }

}
