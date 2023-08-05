package com.vitenko.bookstore.service;

import com.fasterxml.jackson.datatype.jdk8.WrappedIOException;
import com.vitenko.bookstore.exception.user.*;
import com.vitenko.bookstore.service.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto) throws
            UserEmailNotUniqueException, UserPasswordNotProvidedException, UserEmailWasNotProvidedException;

    UserDto findById(Long id) throws UserNotFoundException;

    UserDto findByEmail(String email) throws UserNotFoundException;

    List<UserDto> findByLastName(String lastName);

    List<UserDto> getAllUsers();

    long countAll();

    UserDto update(UserDto userDto) throws
            UserEmailNotUniqueException, UserPasswordNotProvidedException, UserEmailWasNotProvidedException;

    UserDto login(String email, String password) throws WrongLoginInfoException;

    void deleteById(Long id);
}
