package com.vitenko.bookstore.exception.user;

public class UserEmailNotUniqueException extends UserException{
    public UserEmailNotUniqueException(String message) {
        super(message);
    }
}
