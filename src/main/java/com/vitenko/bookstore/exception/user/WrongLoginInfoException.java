package com.vitenko.bookstore.exception.user;

public class WrongLoginInfoException extends UserException{
    public WrongLoginInfoException(String message) {
        super(message);
    }
}
