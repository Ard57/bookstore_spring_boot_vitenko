package com.vitenko.bookstore.service.impl;

import com.vitenko.bookstore.data.entity.Book;
import com.vitenko.bookstore.data.repository.BookRepository;
import com.vitenko.bookstore.exception.book.BookNotFoundException;
import com.vitenko.bookstore.exception.book.IllegalBookArgumentException;
import com.vitenko.bookstore.service.BookService;
import com.vitenko.bookstore.service.dto.BookDto;
import com.vitenko.bookstore.service.mapper.DataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final DataMapper dataMapper;

    @Override
    public BookDto create(BookDto bookDto) throws IllegalBookArgumentException {
        log.debug("Creating book");
        validate(bookDto);
        Book book = bookRepository.save(dataMapper.toBook(bookDto));
        return dataMapper.toBookDto(book);
    }

    @Override
    public BookDto findById(Long id) throws BookNotFoundException {
        log.debug("Retrieving book by id");
        Book book = bookRepository.findById(id).
                orElseThrow(() -> new BookNotFoundException("Book with id " + id + " wasn't found."));
        return dataMapper.toBookDto(book);
    }

    @Override
    public BookDto findByIsbn(String isbn) throws BookNotFoundException {
        log.debug("Retrieving book by ISBN");
        Book book = bookRepository.findByIsbn(isbn).
                orElseThrow(() -> new BookNotFoundException("Book with ISBN " + isbn + " wasn't found."));
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
    public Page<BookDto> getAllBooks(Pageable page) {
        log.debug("Retrieving all books");
        return bookRepository.findAll(page)
                .map(dataMapper::toBookDto);
    }

    @Override
    public long countAll() {
        log.debug("Counting all books");
        return bookRepository.findAll().size();
    }

    @Override
    public BookDto update(BookDto bookDto) throws IllegalBookArgumentException {
        log.debug("Updating book");
        validate(bookDto);
        Book book = bookRepository.save(dataMapper.toBook(bookDto));
        return dataMapper.toBookDto(book);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Deleting book by");
        bookRepository.deleteById(id);
    }

    private void validate(BookDto bookDto) throws IllegalBookArgumentException {
        if (bookDto.getName() == null || bookDto.getName().isEmpty()) {
            throw new IllegalBookArgumentException("Book name cannot be empty");
        }
        if (bookDto.getAuthor() == null || bookDto.getAuthor().isEmpty()) {
            throw new IllegalBookArgumentException("Book author must be specified");
        }
        if (bookDto.getIsbn() == null || bookDto.getIsbn().isEmpty()) {
            throw new IllegalBookArgumentException("Book ISBN cannot be empty");
        }
        if (bookDto.getPages() == null || bookDto.getPages() < 1) {
            throw new IllegalBookArgumentException("Number of book page must be specified");
        }
        if (bookDto.getYearPublished() == null) {
            throw new IllegalBookArgumentException("Year of publication of book must be specified");
        }
        if (bookDto.getPrice() == null || (bookDto.getPrice().compareTo(BigDecimal.valueOf(1, 2)) < 0)) {
            throw new IllegalBookArgumentException("Book price must be specified");
        }
        if (bookDto.getCover() == null) {
            throw new IllegalBookArgumentException("Book cover type must be specified");
        }
    }
}
