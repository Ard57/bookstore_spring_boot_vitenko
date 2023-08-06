package com.vitenko.bookstore.data.repository;

import com.vitenko.bookstore.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLastName(String lastName);

    Optional<User> findByEmail(String email);
}
