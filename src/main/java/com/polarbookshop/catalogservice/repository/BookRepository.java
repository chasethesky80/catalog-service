package com.polarbookshop.catalogservice.repository;

import com.polarbookshop.catalogservice.domain.Book;

import java.util.Optional;

public interface BookRepository {
    Iterable<Book> findAll();
    Optional<Book> findByIsbn(final String isbn);
    boolean existsByIsbn(final String isbn);
    Book save(final Book book);
    void deleteByIsbn(final String isbn);
}
