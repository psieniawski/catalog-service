package com.polarbookshop.catalogservice.domain

class BookNotFoundException(isbn: String) : Throwable("The book with ISBN $isbn was not found.")