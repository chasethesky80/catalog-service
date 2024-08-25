package com.polarbookshop.catalogservice.controller;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest (
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CatalogServiceApplicationTests {

	private final WebTestClient webTestClient;

	@Autowired
	public CatalogServiceApplicationTests(final WebTestClient webTestClient) {
		this.webTestClient = webTestClient;
	}

	@Test
	void whenPostRequestThenBookCreated() {
		var expectedBook = new Book(1L, "1231231231", "Title", "Author", 9.90, null, null, 0);
		webTestClient
				.post()
				.uri("/books")
				.bodyValue(expectedBook)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class)
				.value(actualBook -> {
					assertThat(actualBook).isNotNull();
					assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());

				});
	}

}
