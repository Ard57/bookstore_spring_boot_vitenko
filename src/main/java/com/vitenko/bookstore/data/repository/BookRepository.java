package com.vitenko.bookstore.data.repository;

import com.vitenko.bookstore.data.entity.Book;
import com.vitenko.bookstore.exception.book.BookNotFoundException;

import java.util.List;

public interface BookRepository{
    Book save(Book book);

    Book findById(Long id) throws BookNotFoundException;

    List<Book> findAll();

    boolean delete(Long id);
    Book findByIsbn(String isbn) throws BookNotFoundException;

    List<Book> findByAuthor(String author);
}
