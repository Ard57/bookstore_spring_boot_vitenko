package com.vitenko.bookstore.web.controller.rest;

import com.vitenko.bookstore.exception.order.OrderNotFoundException;
import com.vitenko.bookstore.service.OrderService;
import com.vitenko.bookstore.service.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping(path = "")
    public OrderDto createOrder(@ModelAttribute OrderDto orderDto) {
        return orderService.create(orderDto);
    }

    @PutMapping(path = "/{id}")
    public OrderDto updateOrder(@PathVariable Long id, @ModelAttribute OrderDto orderDto) {
        orderDto.setId(id);
        return orderService.update(orderDto);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
    }
}
