package com.vitenko.bookstore.data.repository;

import com.vitenko.bookstore.data.entity.Order;
import com.vitenko.bookstore.exception.order.OrderNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserEmail(String email);
}
