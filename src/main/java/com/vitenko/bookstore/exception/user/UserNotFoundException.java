package com.vitenko.bookstore.exception.user;

public class UserNotFoundException extends UserException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
