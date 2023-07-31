package com.vitenko.bookstore.web.controller.view;

import com.vitenko.bookstore.exception.cart.CartException;
import com.vitenko.bookstore.service.CartService;
import com.vitenko.bookstore.service.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Log4j2
@RequiredArgsConstructor
@org.springframework.stereotype.Controller("cart")
public class CartController {
    private final CartService cartService;

    @GetMapping(path = "/cart")
    public String getCart(Model model) {
        model.addAttribute("title", "Cart");
        return "order/cart";
    }

    @PostMapping(path = "/cart/purchase")
    public RedirectView purchase(HttpSession session, RedirectAttributes redirectAttributes) throws CartException {
        OrderDto orderDto = cartService.makeOrder((OrderDto) session.getAttribute("cart"));

        session.removeAttribute("cart");
        session.removeAttribute("cartSize");

        redirectAttributes.addFlashAttribute("message",
                "Your order has been successfully placed");

        return new RedirectView("/home");
    }
}
