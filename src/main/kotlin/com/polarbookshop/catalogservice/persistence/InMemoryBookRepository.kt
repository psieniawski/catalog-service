package com.polarbookshop.catalogservice.persistence

import com.polarbookshop.catalogservice.domain.Book
import com.polarbookshop.catalogservice.domain.BookRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class InMemoryBookRepository : BookRepository {
    private companion object {
        val books = ConcurrentHashMap<String, Book>()
    }

    override fun findAll() = books.values

    override fun findByIsbn(isbn: String) = books[isbn]

    override fun existsByIsbn(isbn: String) = books.containsKey(isbn)

    override fun save(book: Book): Book {
        books[book.isbn] = book
        return book
    }

    override fun deleteByIsbn(isbn: String) {
        books.remove(isbn)
    }

}