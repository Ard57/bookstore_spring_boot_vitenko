package com.vitenko.bookstore.service;

import com.vitenko.bookstore.exception.order.OrderNotFoundException;
import com.vitenko.bookstore.service.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto create(OrderDto orderDto);

    OrderDto findById(Long id) throws OrderNotFoundException;

    List<OrderDto> findByUserEmail(String email);

    List<OrderDto> getAllOrders();

    OrderDto update(OrderDto orderDto);

    void deleteById(Long id);
}
