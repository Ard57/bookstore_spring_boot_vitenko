package com.vitenko.bookstore.web.controller.view;

import com.vitenko.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequiredArgsConstructor
@org.springframework.stereotype.Controller("order")
@RequestMapping("/orders")
public class OrderController {

    @GetMapping(path = "/{id}")
    public String getOrder(@PathVariable Long id) {
        return "order/order";
    }

    @GetMapping(path = "/all")
    public String getAllOrders(Model model) {
        model.addAttribute("tittle", "Orders");
        return "order/orders";
    }
}
