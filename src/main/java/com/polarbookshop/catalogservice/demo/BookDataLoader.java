package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@Profile("testData")
public class BookDataLoader {
    private final BookRepository bookRepository;

    @Autowired
    public BookDataLoader(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookData() {
        bookRepository.deleteAll();
        var book1 = new Book(1L, "1234567891", "Northern Lights", "Lyra Silverstar", 9.90, Instant.now(), Instant.now(), 0);
        var book2 = new Book(2L, "1234567892", "Polar Journey", "Iorek Polarson", 12.90, Instant.now(), Instant.now(),0);
        bookRepository.saveAll(List.of(book1, book2));
    }
}
