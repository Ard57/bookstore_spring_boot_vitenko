package com.vitenko.bookstore.service;

import com.vitenko.bookstore.exception.user.UserEmailNotUniqueException;
import com.vitenko.bookstore.exception.user.UserEmailWasNotProvidedException;
import com.vitenko.bookstore.exception.user.UserNotFoundException;
import com.vitenko.bookstore.exception.user.UserPasswordNotProvidedException;
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

    UserDto login(String email, String password) throws UserNotFoundException;

    void deleteById(Long id);
}
