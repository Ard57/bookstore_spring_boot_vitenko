package com.vitenko.bookstore.exception.order;

import com.vitenko.bookstore.exception.AppException;

public class IllegalOrderArgumentException extends AppException {
    public IllegalOrderArgumentException(String message) {
        super(message);
    }
}
