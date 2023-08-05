package com.vitenko.bookstore.service;

import com.vitenko.bookstore.exception.book.BookNotFoundException;
import com.vitenko.bookstore.exception.book.IllegalBookArgumentException;
import com.vitenko.bookstore.service.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto create(BookDto book) throws IllegalBookArgumentException;

    BookDto findById(Long id) throws BookNotFoundException;

    BookDto findByIsbn(String isbn) throws BookNotFoundException;

    List<BookDto> findByAuthor(String author);

    List<BookDto> getAllBooks();

    long countAll();

    BookDto update(BookDto book) throws IllegalBookArgumentException;

    void deleteById(Long id);
}
