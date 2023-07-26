package com.vitenko.bookstore.web.controller.impl;

import com.vitenko.bookstore.exception.order.OrderNotFoundException;
import com.vitenko.bookstore.service.OrderService;
import com.vitenko.bookstore.service.dto.OrderDto;
import com.vitenko.bookstore.service.dto.OrderItemDto;
import com.vitenko.bookstore.web.controller.Controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@org.springframework.stereotype.Controller("order")
public class OrderController implements Controller {
    private final OrderService orderService;

    @GetMapping(path = "/order/show")
    public String getOrder(@RequestParam("id") Long id, Model model) throws OrderNotFoundException {
        OrderDto orderDto = orderService.findById(id);

        model.addAttribute("orderDto", orderDto);
        model.addAttribute("date", LocalDateTime.now().toString());

        String title = "Order #" + orderDto.getId();
        model.addAttribute("title", title);

        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderItemDto orderItemDto : orderDto.getOrderItems()) {
            totalPrice = totalPrice.add(
                    orderItemDto.getPrice().multiply(
                            new BigDecimal(orderItemDto.getAmount())));
        }
        model.addAttribute("totalPrice", totalPrice);
        return "order/order";
    }

    @GetMapping(path = "/order/all")
    public String getAllOrders(Model model) {
        List<OrderDto> orderDtos = orderService.getAllOrders();

        Map<Long, Long> totalBooks = new HashMap<>();
        Map<Long, BigDecimal> totalPrices = new HashMap<>();

        for (OrderDto orderDto : orderDtos) {
            BigDecimal totalPrice = new BigDecimal(0);
            long booksAmount = 0;
            for (OrderItemDto orderItemDto : orderDto.getOrderItems()) {
                totalPrice = totalPrice.add(
                        orderItemDto.getPrice().multiply(
                                new BigDecimal(orderItemDto.getAmount())));
                booksAmount += orderItemDto.getAmount();
            }

            totalBooks.put(orderDto.getId(), booksAmount);
            totalPrices.put(orderDto.getId(), totalPrice);
        }

        model.addAttribute("tittle", "Orders");

        model.addAttribute("orderDtos", orderDtos);
        model.addAttribute("totalBooks", totalBooks);
        model.addAttribute("totalPrices", totalPrices);
        return "order/orders";
    }
}
