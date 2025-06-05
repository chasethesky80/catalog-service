package com.polarbookshop.catalog_service.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String isbn) {
        super(String.format("Book with ISBN %s not found", isbn));
    }
}
