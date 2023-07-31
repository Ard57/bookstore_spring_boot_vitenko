package com.vitenko.bookstore.web.controller.rest;

import com.vitenko.bookstore.exception.book.BookNotFoundException;
import com.vitenko.bookstore.service.CartService;
import com.vitenko.bookstore.service.dto.OrderDto;
import com.vitenko.bookstore.service.dto.OrderItemDto;
import com.vitenko.bookstore.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CartRestController {
    private final CartService cartService;

    @GetMapping("/api/cart")
    public List<Map<String, String>> getCart(HttpSession session) {
        OrderDto cart = (OrderDto) session.getAttribute("cart");
        if (cart == null) {
            cart = cartService.createCart();
            if (session.getAttribute("user") != null) {
                cartService.setUser(cart, (UserDto) session.getAttribute("user"));
            }
            session.setAttribute("cart", cart);
        }

        List<Map<String, String>> mapList = new ArrayList<>();
        for (OrderItemDto orderItemDto : cart.getOrderItems()) {
            Map<String, String> orderItemMap = new HashMap<>();
            orderItemMap.put("bookId", orderItemDto.getBook().getId().toString());
            orderItemMap.put("bookName", orderItemDto.getBook().getName());
            orderItemMap.put("bookAuthor", orderItemDto.getBook().getAuthor());
            orderItemMap.put("bookIsbn", orderItemDto.getBook().getIsbn());
            orderItemMap.put("bookPages", orderItemDto.getBook().getPages().toString());
            orderItemMap.put("bookYearPublished", orderItemDto.getBook().getYearPublished().toString());
            orderItemMap.put("bookCover", orderItemDto.getBook().getCover().toString());
            orderItemMap.put("bookPrice", orderItemDto.getBook().getPrice().toString());
            orderItemMap.put("amount", orderItemDto.getAmount().toString());
            orderItemMap.put("subTotal", orderItemDto.getBook().getPrice().
                    multiply(new BigDecimal(orderItemDto.getAmount()))
                    .toString());

            mapList.add(orderItemMap);
        }

        return mapList;
    }

    @PostMapping("/api/cart/add/{bookId}")
    public void addToCart(@PathVariable Long bookId,
                          @RequestParam(value = "amount", required = false) Integer amount,
                          HttpSession session) throws BookNotFoundException {
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
        int cartSize = cartService.calculateCartSize(cart);
        session.setAttribute("cartSize", cartSize);
        return cartSize;
    }

    @GetMapping("/api/cart/totalprice")
    public String getCartTotalPrice(HttpSession session) {
        OrderDto cart = (OrderDto) session.getAttribute("cart");
        if (cart == null) {
            return "0";
        }
        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderItemDto orderItemDto : cart.getOrderItems()) {
            totalPrice = totalPrice.add(
                    orderItemDto.getPrice().multiply(
                            new BigDecimal(orderItemDto.getAmount())));
        }
        return totalPrice.toString();
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
