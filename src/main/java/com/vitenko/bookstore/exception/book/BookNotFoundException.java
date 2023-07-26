package com.vitenko.bookstore.exception.book;

public class BookNotFoundException extends BookException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
