package com.vitenko.bookstore.exception.order;

import com.vitenko.bookstore.exception.AppException;

public class OrderNotFoundException extends AppException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
