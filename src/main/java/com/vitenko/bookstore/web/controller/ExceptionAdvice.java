package com.vitenko.bookstore.web.controller;


import com.vitenko.bookstore.exception.AppException;
import com.vitenko.bookstore.exception.book.BookNotFoundException;
import com.vitenko.bookstore.exception.cart.CartException;
import com.vitenko.bookstore.exception.order.OrderNotFoundException;
import com.vitenko.bookstore.exception.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = {org.springframework.stereotype.Controller.class, RestController.class})
public class ExceptionAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getExceptionPage(Model model, Exception e) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler
    public String handleAppException(Model model, AppException e) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFoundException(Model model, UserNotFoundException e) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleBookNotFoundException(Model model, BookNotFoundException e) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleOrderNotFoundException(Model model, OrderNotFoundException e) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleCartException(Model model, CartException e) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
