package com.vitenko.bookstore.service;

import com.vitenko.bookstore.exception.book.BookNotFoundException;
import com.vitenko.bookstore.service.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto create(BookDto book);

    BookDto findById(Long id) throws BookNotFoundException;

    BookDto findByIsbn(String isbn) throws BookNotFoundException;

    List<BookDto> findByAuthor(String author);

    List<BookDto> getAllBooks();

    long countAll();

    BookDto update(BookDto book);

    void deleteById(Long id);
}
