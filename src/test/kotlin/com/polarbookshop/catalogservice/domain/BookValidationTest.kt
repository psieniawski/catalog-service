package com.polarbookshop.catalogservice.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import javax.validation.Validation

internal class BookValidationTest {
    private val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun whenAllFieldsCorrectTheValidationSucceds() {
        val book = Book.of("1234567890", "Author", "Title", 1.1)
        val constraintViolations = validator.validate(book)
        assertThat(constraintViolations).isEmpty()
    }

    @Test
    fun whenIsbnDefinedButIncorrectThenValidationFails() {
        val book = Book.of("a234567890", "Title", "Author", 9.90)
        val violations = validator.validate(book)
        assertThat(violations).hasSize(1)
        assertThat(violations.iterator().next().getMessage())
            .isEqualTo("The ISBN format must be valid.")
    }
}