package com.vitenko.bookstore.web.controller.rest;

import com.vitenko.bookstore.exception.book.BookNotFoundException;
import com.vitenko.bookstore.service.BookService;
import com.vitenko.bookstore.service.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookRestController {
    private final BookService bookService;

    @GetMapping("/api/books/{id}")
    public BookDto getBook(@PathVariable Long id) throws BookNotFoundException {
        return bookService.findById(id);
    }

    @GetMapping("/api/books/all")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }
}
