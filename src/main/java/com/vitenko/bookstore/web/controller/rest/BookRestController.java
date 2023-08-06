package com.vitenko.bookstore.web.controller.rest;

import com.vitenko.bookstore.exception.book.BookNotFoundException;
import com.vitenko.bookstore.exception.book.IllegalBookArgumentException;
import com.vitenko.bookstore.service.BookService;
import com.vitenko.bookstore.service.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable Long id) throws BookNotFoundException {
        return bookService.findById(id);
    }

    @GetMapping("/all")
    public Page<BookDto> getAllBooks(Pageable page) {
        return bookService.getAllBooks(page);
    }

    @PostMapping("")
    public BookDto createBook(@ModelAttribute BookDto bookDto)
            throws IllegalBookArgumentException {
        return bookService.create(bookDto);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id, @ModelAttribute BookDto bookDto)
            throws IllegalBookArgumentException {
        bookDto.setId(id);
        return bookService.update(bookDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
