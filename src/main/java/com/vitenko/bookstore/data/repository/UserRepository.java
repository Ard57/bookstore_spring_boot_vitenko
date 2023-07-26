package com.vitenko.bookstore.data.repository;

import com.vitenko.bookstore.data.entity.User;
import com.vitenko.bookstore.exception.user.UserNotFoundException;

import java.util.List;

public interface UserRepository {
    User save(User entity);

    User findById(Long id) throws UserNotFoundException;

    List<User> findAll();

    boolean delete(Long id);
    User findByEmail(String email) throws UserNotFoundException;

    List<User> findByLastName(String lastName);
}
