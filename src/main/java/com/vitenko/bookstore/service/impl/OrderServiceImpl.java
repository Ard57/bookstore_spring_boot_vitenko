package com.vitenko.bookstore.service.impl;

import com.vitenko.bookstore.data.entity.Order;
import com.vitenko.bookstore.data.repository.OrderRepository;
import com.vitenko.bookstore.exception.order.IllegalOrderArgumentException;
import com.vitenko.bookstore.exception.order.OrderNotFoundException;
import com.vitenko.bookstore.service.OrderService;
import com.vitenko.bookstore.service.dto.OrderDto;
import com.vitenko.bookstore.service.dto.OrderItemDto;
import com.vitenko.bookstore.service.mapper.DataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final DataMapper dataMapper;
    @Override
    public OrderDto create(OrderDto orderDto) throws IllegalOrderArgumentException {
        log.debug("Creating order");
        orderDto.setStatus(Order.Status.PROCESSING);
        validate(orderDto);
        Order order = orderRepository.save(dataMapper.toOrder(orderDto));
        return dataMapper.toOrderDto(order);
    }

    @Override
    public OrderDto findById(Long id) throws OrderNotFoundException {
        log.debug("Retrieving order by ID");
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new RuntimeException("Order with id " + id + " wasn't found.");
        }
        return dataMapper.toOrderDto(order);
    }

    @Override
    public List<OrderDto> findByUserEmail(String email) {
        log.debug("Retrieving all orders with matching last name");
        return orderRepository.findByUserEmail(email)
                .stream()
                .map(dataMapper::toOrderDto)
                .toList();
    }

    @Override
    public List<OrderDto> getAllOrders() {
        log.debug("Retrieving all orders");
        return orderRepository.findAll()
                .stream()
                .map(dataMapper::toOrderDto)
                .toList();
    }

    @Override
    public OrderDto update(OrderDto orderDto) throws IllegalOrderArgumentException {
        log.debug("Updating order");
        validate(orderDto);
        Order order = orderRepository.save(dataMapper.toOrder(orderDto));
        return dataMapper.toOrderDto(order);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Deleting order by id");
        boolean isDeleted = orderRepository.delete(id);
        if (!isDeleted) {
            throw new RuntimeException("Couldn't delete order with id: " + id + ".");
        }
    }

    private void validate(OrderDto orderDto) throws IllegalOrderArgumentException {
        if (orderDto.getUser() == null) {
            throw new IllegalOrderArgumentException("Customer for order must be specified.");
        }
        if (orderDto.getStatus() == null) {
            throw new IllegalOrderArgumentException("Order must have correct status.");
        }
        if (orderDto.getOrderItems() == null || orderDto.getOrderItems().isEmpty()) {
            throw new IllegalOrderArgumentException("List of order items must be not empty.");
        }
        for (OrderItemDto item : orderDto.getOrderItems()) {
            if (item == null) {
                throw new IllegalOrderArgumentException("Order cannot contain empty items.");
            }
            if (item.getBook() == null) {
                throw new IllegalOrderArgumentException("Order item must have specified book");
            }
            if (item.getPrice() == null || (item.getPrice().compareTo(new BigDecimal("0.01")) <= 0)) {
                throw new IllegalOrderArgumentException("Order item price must be specified");
            }
            if (item.getAmount() == null || item.getAmount() < 1) {
                throw new IllegalOrderArgumentException("Order item amount must be specified");
            }
        }
    }
}