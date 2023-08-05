package com.vitenko.bookstore.exception.cart;

import com.vitenko.bookstore.exception.AppException;

public class CartException extends AppException{
    public CartException(String message) {
        super(message);
    }
}
