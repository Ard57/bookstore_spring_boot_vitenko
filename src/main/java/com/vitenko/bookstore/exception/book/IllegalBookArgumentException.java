package com.vitenko.bookstore.exception.book;

public class IllegalBookArgumentException extends BookException {
    public IllegalBookArgumentException(String message) {
        super(message);
    }
}
