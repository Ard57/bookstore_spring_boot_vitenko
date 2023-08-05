package com.vitenko.bookstore.web.controller.view;

import com.vitenko.bookstore.exception.book.BookNotFoundException;
import com.vitenko.bookstore.exception.book.IllegalBookArgumentException;
import com.vitenko.bookstore.service.BookService;
import com.vitenko.bookstore.service.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@org.springframework.stereotype.Controller("book")
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping(path = "/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        model.addAttribute("title", "Book " + id);
        return "book/book";
    }

    @GetMapping(path = "/all")
    public String getAllBooks() {
        return "book/books";
    }

    @GetMapping(path = "/{id}/edit")
    public String getBookEditForm(@PathVariable Long id, Model model) throws BookNotFoundException {
        BookDto bookDto = bookService.findById(id);
        model.addAttribute("bookDto", bookDto);
        return "book/bookEditForm";
    }

    @PostMapping(path = "/{id}/edit")
    public RedirectView editBook(@PathVariable Long id,
                                 @ModelAttribute("bookDto") BookDto bookDto,
                                 RedirectAttributes redirectAttributes)
            throws BookNotFoundException, IllegalBookArgumentException {
        bookService.findById(id);
        bookDto.setId(id);
        BookDto updatedBookDto = bookService.update(bookDto);

        redirectAttributes.addFlashAttribute("message", "Book successfully edited.");

        return new RedirectView("/book/" + updatedBookDto.getId());
    }

    @GetMapping(path = "/create")
    public String getBookCreateForm() {
        return "book/bookCreateForm";
    }

    @PostMapping(path = "/create")
    public RedirectView createBook(@ModelAttribute("bookDto") BookDto bookDto,
            RedirectAttributes redirectAttributes) throws IllegalBookArgumentException {
        BookDto createdBookDto = bookService.create(bookDto);

        redirectAttributes.addFlashAttribute("message", "Book successfully added.");

        return new RedirectView("/book/" + createdBookDto.getId());
    }
}