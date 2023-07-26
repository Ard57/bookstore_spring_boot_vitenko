package com.vitenko.bookstore.service;

import com.vitenko.bookstore.exception.book.BookNotFoundException;
import com.vitenko.bookstore.service.dto.OrderDto;
import com.vitenko.bookstore.service.dto.UserDto;

public interface CartService {
    OrderDto createCart();

    OrderDto addItem(OrderDto cart, Long id, Integer amount) throws BookNotFoundException;

    OrderDto setUser(OrderDto cart, UserDto userDto);

    OrderDto makeOrder(OrderDto cart);

    int calculateCartSize(OrderDto cart);
}
