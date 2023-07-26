package com.vitenko.bookstore.exception.user;

public class UserEmailWasNotProvidedException extends UserException{
    public UserEmailWasNotProvidedException(String message) {
        super(message);
    }
}
