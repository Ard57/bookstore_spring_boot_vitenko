package com.vitenko.bookstore.web.controller.rest;

import com.vitenko.bookstore.exception.book.BookNotFoundException;
import com.vitenko.bookstore.service.CartService;
import com.vitenko.bookstore.service.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class CartRestController {
    private final CartService cartService;

    @PostMapping("/api/cart/add/{bookId}")
    public void addToCart(@PathVariable Long bookId,
                             @RequestParam(value = "amount", required = false) Integer amount,
                             HttpSession session, Model model) throws BookNotFoundException {
        OrderDto cart = (OrderDto) session.getAttribute("cart");
        if (cart == null) {
            cart = cartService.createCart();
            session.setAttribute("cart", cart);
        }

        if (amount == null) {
            amount = 1;
        }

        cartService.addItem(cart, bookId, amount);
    }

    @GetMapping("/api/cart/size")
    public int getCartSize(HttpSession session) {
        OrderDto cart = (OrderDto) session.getAttribute("cart");
        if (cart == null) {
            return 0;
        }
        return cartService.calculateCartSize(cart);
    }
}
