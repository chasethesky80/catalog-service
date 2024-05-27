package com.polarbookshop.catalogservice.exception.handler;

import com.polarbookshop.catalogservice.exception.BookAlreadyExistsException;
import com.polarbookshop.catalogservice.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class BookControllerAdvice {

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String bookNotFoundHandler(final BookNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String bookAlreadyExistsHandler(final BookAlreadyExistsException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationException(final MethodArgumentNotValidException ex){
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().stream().forEach(error -> {
            final String fieldName = ((FieldError)error).getField();
            final String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
