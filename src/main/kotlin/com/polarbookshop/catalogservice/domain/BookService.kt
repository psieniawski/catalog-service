package com.polarbookshop.catalogservice.domain

import org.springframework.stereotype.Service

@Service
class BookService(val bookRepo: BookRepository) {

    fun viewBookList() = bookRepo.findAll()
    fun viewBookDetails(isbn: String) = bookRepo.findByIsbn(isbn) ?: throw BookNotFoundException("isbn")
    fun addBookToCatalog(book: Book) =
        if (bookRepo.existsByIsbn(book.isbn)) throw BookExistsException(book.isbn) else bookRepo.save(book)

    fun removeBookFromCatalog(isbn: String) = bookRepo.deleteByIsbn(isbn)
    fun updateBookDetails(isbn: String, book: Book) = bookRepo.findByIsbn(isbn).merge(book).also { bookRepo.save(it) }


    private fun Book?.merge(book: Book) =
        this?.copy(author = book.author, title = book.title, price = book.price, version = book.version) ?: book
}