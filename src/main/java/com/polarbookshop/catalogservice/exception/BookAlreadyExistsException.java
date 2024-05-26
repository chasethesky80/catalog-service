package com.polarbookshop.catalogservice.exception;

public class BookAlreadyExistsException extends RuntimeException {

    public BookAlreadyExistsException(final String isbn) {
        super(String.format("A book with ISBN %s already exists", isbn));
    }
}
