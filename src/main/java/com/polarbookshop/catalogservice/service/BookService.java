package com.polarbookshop.catalogservice.service;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.exception.BookAlreadyExistsException;
import com.polarbookshop.catalogservice.exception.BookNotFoundException;
import com.polarbookshop.catalogservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(final String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(final Book book) {
        final String isbn = book.isbn();
        if (bookRepository.existsByIsbn(isbn)) {
            throw new BookAlreadyExistsException(isbn);
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(final String isbn) {
        if (!bookRepository.existsByIsbn(isbn)) {
            throw new BookNotFoundException(isbn);
        }
    }

    public Book editBookDetails(final String isbn, final Book book){
        return bookRepository.findByIsbn(isbn).map(existingBook -> {
            var bookToSave = new Book(existingBook.id(), existingBook.isbn(),
                    book.title(), book.author(), book.price(), existingBook.createdDate(),
                    existingBook.lastModifiedDate(),
                    existingBook.version());
            return bookRepository.save(bookToSave);
          }).orElseGet(() -> addBookToCatalog(book));
    }
}
