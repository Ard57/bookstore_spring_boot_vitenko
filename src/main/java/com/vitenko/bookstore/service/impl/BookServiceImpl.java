package com.vitenko.bookstore.service.impl;

import com.vitenko.bookstore.data.entity.Book;
import com.vitenko.bookstore.data.repository.BookRepository;
import com.vitenko.bookstore.exception.book.BookNotFoundException;
import com.vitenko.bookstore.service.BookService;
import com.vitenko.bookstore.service.dto.BookDto;
import com.vitenko.bookstore.service.mapper.DataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final DataMapper dataMapper;

    @Override
    public BookDto create(BookDto bookDto) {
        log.debug("Creating book");
        Book book = bookRepository.save(dataMapper.toBook(bookDto));
        return dataMapper.toBookDto(book);
    }

    @Override
    public BookDto findById(Long id) throws BookNotFoundException {
        log.debug("Retrieving book by id");
        Book book = bookRepository.findById(id);
        if (book == null) {
            throw new RuntimeException("Book with id " + id + " wasn't found.");
        }
        return dataMapper.toBookDto(book);
    }

    @Override
    public BookDto findByIsbn(String isbn) throws BookNotFoundException {
        log.debug("Retrieving book by ISBN");
        Book book = bookRepository.findByIsbn(isbn);
        if (book == null) {
            throw new RuntimeException("Book with ISBN " + isbn + " wasn't found.");
        }
        return dataMapper.toBookDto(book);
    }

    @Override
    public List<BookDto> findByAuthor(String author) {
        log.debug("Retrieving all books of selected author");
        return bookRepository.findByAuthor(author)
                .stream()
                .map(dataMapper::toBookDto)
                .toList();
    }

    @Override
    public List<BookDto> getAllBooks() {
        log.debug("Retrieving all books");
        return bookRepository.findAll()
                .stream()
                .map(dataMapper::toBookDto)
                .toList();
    }

    @Override
    public long countAll() {
        log.debug("Counting all books");
        return bookRepository.findAll().size();
    }

    @Override
    public BookDto update(BookDto bookDto) {
        log.debug("Updating book");
        Book book = bookRepository.save(dataMapper.toBook(bookDto));
        return dataMapper.toBookDto(book);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Deleting book by");
        boolean isDeleted = bookRepository.delete(id);
        if (!isDeleted) {
            throw new RuntimeException("Couldn't delete book with id: " + id + ".");
        }
    }
}
