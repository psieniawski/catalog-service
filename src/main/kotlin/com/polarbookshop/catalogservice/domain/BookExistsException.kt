package com.polarbookshop.catalogservice.domain

class BookExistsException(isbn: String) : Throwable("A book with ISBN $isbn already exists.")