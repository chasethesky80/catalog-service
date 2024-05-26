package com.polarbookshop.catalogservice.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(final String isbn) {
        super(String.format("A book with ISBN %s not found", isbn));
    }
}
