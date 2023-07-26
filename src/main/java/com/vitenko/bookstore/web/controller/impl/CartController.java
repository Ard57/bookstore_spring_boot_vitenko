package com.vitenko.bookstore.web.controller.impl;

import com.vitenko.bookstore.exception.book.BookNotFoundException;
import com.vitenko.bookstore.service.CartService;
import com.vitenko.bookstore.service.dto.OrderDto;
import com.vitenko.bookstore.service.dto.OrderItemDto;
import com.vitenko.bookstore.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Log4j2
@RequiredArgsConstructor
@org.springframework.stereotype.Controller("cart")
public class CartController {
    private final CartService cartService;

    @GetMapping(path = "/cart")
    public String getCart(Model model, HttpSession session) {
        OrderDto cart = (OrderDto) session.getAttribute("cart");
        if (cart == null) {
            cart = cartService.createCart();
            if (session.getAttribute("user") != null) {
                cartService.setUser(cart, (UserDto) session.getAttribute("user"));
            }
            session.setAttribute("cart", cart);
        }


        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderItemDto orderItemDto : cart.getOrderItems()) {
            totalPrice = totalPrice.add(
                    orderItemDto.getPrice().multiply(
                            new BigDecimal(orderItemDto.getAmount())));
        }
        model.addAttribute("totalPrice", totalPrice);

        int cartSize = cartService.calculateCartSize(cart);
        session.setAttribute("cartSize", cartSize);

        if (session.getAttribute("user") == null) {
            model.addAttribute("purchasePossible", false);
            model.addAttribute("purchaseHelp", "Please, log in to make an order");
        } else if (cartSize < 1) {
            model.addAttribute("purchasePossible", false);
            model.addAttribute("purchaseHelp", "Please, make sure your cart isn't empty to make an order");
        } else {
            model.addAttribute("purchasePossible", true);
            model.addAttribute("purchaseHelp", "");
        }

        model.addAttribute("title", "Cart");
        model.addAttribute("orderDto", cart);
        return "order/cart";
    }

    @RequestMapping(path = "/cart/add")
    public RedirectView addToCart(@RequestParam("book_id") Long bookId,
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

        cart = cartService.addItem(cart, bookId, amount);

        model.addAttribute("orderDto", cart);

        return new RedirectView("/cart");
    }

    @PostMapping(path = "/cart/purchase")
    public RedirectView purchase(HttpSession session, RedirectAttributes redirectAttributes) {
        OrderDto orderDto = cartService.makeOrder((OrderDto) session.getAttribute("cart"));

        session.removeAttribute("cart");
        session.removeAttribute("cartSize");

        redirectAttributes.addFlashAttribute("message",
                "Your order has been successfully placed");

        return new RedirectView("/home");
    }
}
