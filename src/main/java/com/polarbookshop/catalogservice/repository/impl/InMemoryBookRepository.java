package com.polarbookshop.catalogservice.repository.impl;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private static final Map<String, Book> books = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return books.values();
    }

    @Override
    public Optional<Book> findByIsbn(final String isbn) {
        return books.values().stream().filter(book -> book.isbn().equalsIgnoreCase(isbn)).findFirst();
    }

    @Override
    public boolean existsByIsbn(final String isbn) {
        return books.get(isbn) != null;
    }

    @Override
    public Book save(final Book book) {
        books.put(book.isbn(), book);
        return book;
    }
}
