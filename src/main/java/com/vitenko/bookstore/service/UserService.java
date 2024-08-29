package com.vitenko.bookstore.service;

import com.fasterxml.jackson.datatype.jdk8.WrappedIOException;
import com.vitenko.bookstore.exception.user.*;
import com.vitenko.bookstore.service.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto) throws UserException;

    UserDto findById(Long id) throws UserNotFoundException;

    UserDto findByEmail(String email) throws UserNotFoundException;

    List<UserDto> findByLastName(String lastName);

    Page<UserDto> getAllUsers(Pageable page);

    long countAll();

    UserDto update(UserDto userDto) throws UserException;

    UserDto login(String email, String password) throws UserException;

    void deleteById(Long id);
}
