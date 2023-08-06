package com.vitenko.bookstore.service;

import com.vitenko.bookstore.exception.book.BookNotFoundException;
import com.vitenko.bookstore.exception.book.IllegalBookArgumentException;
import com.vitenko.bookstore.service.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    BookDto create(BookDto book) throws IllegalBookArgumentException;

    BookDto findById(Long id) throws BookNotFoundException;

    BookDto findByIsbn(String isbn) throws BookNotFoundException;

    List<BookDto> findByAuthor(String author);

    Page<BookDto> getAllBooks(Pageable page);

    long countAll();

    BookDto update(BookDto book) throws IllegalBookArgumentException;

    void deleteById(Long id);
}
