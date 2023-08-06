package com.vitenko.bookstore.data.repository;

import com.vitenko.bookstore.data.entity.Book;
import com.vitenko.bookstore.exception.book.BookNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByAuthor(String author);
}
