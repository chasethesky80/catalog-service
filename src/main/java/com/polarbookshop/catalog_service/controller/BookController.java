package com.polarbookshop.catalog_service.controller;

import com.polarbookshop.catalog_service.domain.Book;
import com.polarbookshop.catalog_service.service.BookService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public Iterable<Book> getBooks() {
        return null;
    }
}
