package com.polarbookshop.catalog_service.service;

import com.polarbookshop.catalog_service.domain.Book;
import com.polarbookshop.catalog_service.exception.BookAlreadyExistsException;
import com.polarbookshop.catalog_service.exception.BookNotFoundException;
import com.polarbookshop.catalog_service.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

   private final BookRepository bookRepository;

   public BookService(BookRepository bookRepository) {
       this.bookRepository = bookRepository;
   }

   public Iterable<Book> getAllBooks() {
       return bookRepository.findAll();
   }

   public Book viewBookDetails(String isbn) {
       return bookRepository.findByIsbn(isbn).orElseThrow(
               () -> new BookNotFoundException(isbn));
   }

   public Book addBookToCatalog(Book book) {
       if (bookRepository.existsByIsbn(book.isbn())) {
           throw new BookAlreadyExistsException(book.isbn());
       }
       return bookRepository.save(book);
   }

   public Book editBookDetails(String isbn, Book book) {
       final Book bookToEdit = bookRepository.findByIsbn(isbn).orElseGet(
               () -> addBookToCatalog(book));
       var bookToSave = new Book(bookToEdit.id(), isbn, book.title(), book.author(),
               book.price(), bookToEdit.version());
       return bookRepository.save(bookToSave);
   }

   public void removeBookFromCatalog(String isbn) {
       bookRepository.deleteByIsbn(isbn);
   }
}
