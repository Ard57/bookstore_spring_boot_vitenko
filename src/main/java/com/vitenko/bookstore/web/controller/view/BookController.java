package com.vitenko.bookstore.web.controller.view;

import com.vitenko.bookstore.exception.book.BookNotFoundException;
import com.vitenko.bookstore.service.BookService;
import com.vitenko.bookstore.service.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@org.springframework.stereotype.Controller("book")
public class BookController {
    private final BookService bookService;

    @GetMapping(path = "/book/{id}")
    public String getBook(@PathVariable Long id, Model model) throws BookNotFoundException {
        BookDto bookDto = bookService.findById(id);

        model.addAttribute("bookDto", bookDto);
        model.addAttribute("date", LocalDateTime.now().toString());

        String title;
        if (bookDto.getName() != null && !bookDto.getName().isBlank()) {
            title = bookDto.getName();
            if (bookDto.getYearPublished() != null) {
                title += " " + bookDto.getYearPublished();
            }
        } else if (bookDto.getIsbn() != null && !bookDto.getIsbn().isBlank()) {
            title = bookDto.getIsbn();
        } else {
            title = "Book";
        }
        model.addAttribute("title", title);
        return "book/book";
    }

    @GetMapping(path = "/book/all")
    public String getAllBooks(Model model) {
        List<BookDto> bookDtos = bookService.getAllBooks();

        model.addAttribute("bookDtos", bookDtos);
        return "book/books";
    }
}