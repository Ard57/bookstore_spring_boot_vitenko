package com.vitenko.bookstore.web.controller.rest;

import com.vitenko.bookstore.exception.book.BookNotFoundException;
import com.vitenko.bookstore.service.OrderService;
import com.vitenko.bookstore.service.dto.OrderDto;
import com.vitenko.bookstore.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CartRestController {
    private final OrderService orderService;

    @GetMapping("/api/cart")
    public OrderDto getCart(HttpSession session) {
        OrderDto cart = (OrderDto) session.getAttribute("cart");
        if (cart == null) {
            cart = orderService.createCart();
            if (session.getAttribute("user") != null) {
                orderService.setUser(cart, (UserDto) session.getAttribute("user"));
            }
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @PostMapping("/api/cart/add/{bookId}")
    public void addToCart(@PathVariable Long bookId,
                          @RequestParam(value = "amount", required = false) Integer amount,
                          HttpSession session) throws BookNotFoundException {
        OrderDto cart = (OrderDto) session.getAttribute("cart");
        if (cart == null) {
            cart = orderService.createCart();
            session.setAttribute("cart", cart);
        }

        if (amount == null) {
            amount = 1;
        }

        orderService.addItem(cart, bookId, amount);
    }

    @GetMapping("/api/cart/size")
    public int getCartSize(HttpSession session) {
        OrderDto cart = (OrderDto) session.getAttribute("cart");
        if (cart == null) {
            return 0;
        }
        int cartSize = orderService.getOrderSize(cart);
        session.setAttribute("cartSize", cartSize);
        return cartSize;
    }

    @GetMapping("/api/cart/totalprice")
    public BigDecimal getCartTotalPrice(HttpSession session) {
        OrderDto cart = (OrderDto) session.getAttribute("cart");
        if (cart == null) {
            return new BigDecimal("0");
        }
        return orderService.getOrderTotalPrice(cart);
    }

    @GetMapping(path = "/api/cart/validate")
    public Map<String, String> validate(HttpSession session) {

        int cartSize = (int) session.getAttribute("cartSize");

        Map<String, String> response = new HashMap<>();

        if (session.getAttribute("user") == null) {
            response.put("purchasePossible", "disabled");
            response.put("purchaseHelp", "Please, log in to make an order");
        } else if (cartSize < 1) {
            response.put("purchasePossible", "disabled");
            response.put("purchaseHelp", "Please, make sure your cart isn't empty to make an order");
        } else {
            response.put("purchasePossible", "enabled");
            response.put("purchaseHelp", "");
        }
        return response;
    }
}
