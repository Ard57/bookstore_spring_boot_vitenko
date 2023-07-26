package com.vitenko.bookstore.data.repository;

import com.vitenko.bookstore.data.entity.Order;
import com.vitenko.bookstore.exception.order.OrderNotFoundException;

import java.util.List;

public interface OrderRepository{
    Order save(Order entity);

    Order findById(Long id) throws OrderNotFoundException;

    List<Order> findAll();

    boolean delete(Long id);
    List<Order> findByUserEmail(String email);
}
