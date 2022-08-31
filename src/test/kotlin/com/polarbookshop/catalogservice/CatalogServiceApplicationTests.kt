package com.polarbookshop.catalogservice

import com.polarbookshop.catalogservice.domain.Book
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
class CatalogServiceApplicationTests(@Autowired val webTestClient: WebTestClient) {

    @Test
    fun contextLoads() {
    }

    @Test
    fun whenPostRequestThenBookCreated() {
        val expectedBook = Book.of("1231231231", "Title", "Author", 9.90)
        webTestClient
            .post()
            .uri("/books")
            .bodyValue(expectedBook)
            .exchange()
            .expectStatus().isCreated
            .expectBody(Book::class.java).value { actualBook ->
                assertThat(actualBook).isNotNull
                assertThat(actualBook.isbn).isEqualTo(expectedBook.isbn)
            }
    }
}
