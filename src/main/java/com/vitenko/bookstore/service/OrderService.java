package com.vitenko.bookstore.service;

import com.vitenko.bookstore.exception.book.BookNotFoundException;
import com.vitenko.bookstore.exception.cart.CartException;
import com.vitenko.bookstore.exception.order.IllegalOrderArgumentException;
import com.vitenko.bookstore.exception.order.OrderNotFoundException;
import com.vitenko.bookstore.service.dto.OrderDto;
import com.vitenko.bookstore.service.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    OrderDto create(OrderDto orderDto) throws IllegalOrderArgumentException;

    OrderDto findById(Long id) throws OrderNotFoundException;

    List<OrderDto> findByUserEmail(String email);

    Page<OrderDto> getAllOrders(Pageable page);

    OrderDto update(OrderDto orderDto) throws IllegalOrderArgumentException;

    void deleteById(Long id);

    BigDecimal getOrderTotalPrice(OrderDto orderDto);

    int getOrderSize(OrderDto orderDto);

    OrderDto createCart();

    OrderDto addItem(OrderDto cart, Long id, Integer amount) throws BookNotFoundException;

    OrderDto setUser(OrderDto cart, UserDto userDto);

    OrderDto makeOrder(OrderDto cart) throws CartException;
}
