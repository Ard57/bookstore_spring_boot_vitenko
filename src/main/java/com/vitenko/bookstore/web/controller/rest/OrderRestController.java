package com.vitenko.bookstore.web.controller.rest;

import com.vitenko.bookstore.exception.order.OrderException;
import com.vitenko.bookstore.exception.order.OrderNotFoundException;
import com.vitenko.bookstore.service.OrderService;
import com.vitenko.bookstore.service.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderRestController {
    private final OrderService orderService;

    @GetMapping(path = "/{id}")
    public OrderDto getOrder(@PathVariable Long id) throws OrderNotFoundException {
        return orderService.findById(id);
    }

    @GetMapping(path = "/all")
    public Page<OrderDto> getAllOrders(Pageable page) {
        return orderService.getAllOrders(page);
    }

    @PostMapping(path = "")
    public OrderDto createOrder(@ModelAttribute OrderDto orderDto) throws OrderException {
        return orderService.create(orderDto);
    }

    @PutMapping(path = "/{id}")
    public OrderDto updateOrder(@PathVariable Long id, @ModelAttribute OrderDto orderDto) throws OrderException {
        orderDto.setId(id);
        return orderService.update(orderDto);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
    }

    @GetMapping("/{id}/totalprice")
    public BigDecimal getOrderTotalPrice(@PathVariable Long id) throws OrderNotFoundException {
        OrderDto orderDto = orderService.findById(id);
        return orderService.getOrderTotalPrice(orderDto);
    }
}