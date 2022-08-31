package com.polarbookshop.catalogservice.demo

import com.polarbookshop.catalogservice.domain.Book
import com.polarbookshop.catalogservice.domain.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Profile
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
@Profile("test-data")
class BookDataLoader(@Autowired val bookRepo: BookRepository) {

    @EventListener(ApplicationReadyEvent::class)
    fun loadBookTestData() {
        val book1 = Book(
            "1234567891", "Marian",
            "Życie Mariana", 9.90
        )
        val book2 = Book(
            "1234567892", "Zofia",
            "Mój dramat", 12.90
        )
        bookRepo.save(book1)
        bookRepo.save(book2)
    }
}