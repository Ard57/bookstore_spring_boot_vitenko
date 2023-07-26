package com.vitenko.bookstore.exception.order;

import com.vitenko.bookstore.exception.AppException;

public class OrderException extends AppException {
    public OrderException(String message) {
        super(message);
    }
}
