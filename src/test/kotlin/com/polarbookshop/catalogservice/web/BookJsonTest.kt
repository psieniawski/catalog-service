package com.polarbookshop.catalogservice.web

import com.polarbookshop.catalogservice.domain.Book
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester

@JsonTest
class BookJsonTest(@Autowired val json: JacksonTester<Book>) {

    @Test
    fun testSerialize() {
        val book = Book.of("1234567890", "Author", "Title", 9.90)
        val jsonContent = json.write(book)
        assertThat(jsonContent).extractingJsonPathNumberValue("@.id").isEqualTo(book.id)
        assertThat(jsonContent).extractingJsonPathNumberValue("@.version").isEqualTo(book.version)
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn)
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.title)
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.author)
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(book.price)
    }

    @Test
    fun testDeserialize() {
        val content = """
            {
            "isbn": "1234567890",
            "title": "Title",
            "author": "Author",
            "price": 9.90
            }
            """.trimIndent()
        assertThat(json.parse(content))
            .usingRecursiveComparison()
            .isEqualTo(Book.of("1234567890", "Author", "Title", 9.90))
    }
}