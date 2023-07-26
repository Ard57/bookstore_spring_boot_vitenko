package com.vitenko.bookstore.service.impl;

import com.vitenko.bookstore.data.entity.Order;
import com.vitenko.bookstore.data.repository.BookRepository;
import com.vitenko.bookstore.data.repository.OrderRepository;
import com.vitenko.bookstore.exception.book.BookNotFoundException;
import com.vitenko.bookstore.service.CartService;
import com.vitenko.bookstore.service.dto.BookDto;
import com.vitenko.bookstore.service.dto.OrderDto;
import com.vitenko.bookstore.service.dto.OrderItemDto;
import com.vitenko.bookstore.service.dto.UserDto;
import com.vitenko.bookstore.service.mapper.DataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Log4j2
@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final DataMapper dataMapper;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    @Override
    public OrderDto createCart() {
        OrderDto cart = new OrderDto();
        cart.setOrderItems(new ArrayList<>());
        cart.setStatus(Order.Status.CART);
        return cart;
    }

    @Override
    public OrderDto addItem(OrderDto cart, Long id, Integer amount) throws BookNotFoundException {
        BookDto bookDto = dataMapper.toBookDto(bookRepository.findById(id));
        OrderItemDto orderItemDto = new OrderItemDto();
        for (OrderItemDto item : cart.getOrderItems()) {
            if (item.getBook().equals(bookDto)) {
                orderItemDto = item;
                orderItemDto.setAmount(item.getAmount() + amount);
                if (orderItemDto.getAmount() <= 0) {
                    cart.getOrderItems().remove(orderItemDto);
                }
                return cart;
            }
        }
        orderItemDto.setOrder(cart);
        orderItemDto.setBook(bookDto);
        orderItemDto.setAmount(amount);
        orderItemDto.setPrice(bookDto.getPrice());

        cart.getOrderItems().add(orderItemDto);

        return cart;
    }

    @Override
    public OrderDto setUser(OrderDto cart, UserDto userDto) {
        cart.setUser(userDto);
        return cart;
    }

    @Override
    public OrderDto makeOrder(OrderDto cart) {
        if (cart != null) {
            int cartSize = calculateCartSize(cart);

            if (cart.getUser() != null && cartSize > 0) {
                cart.setStatus(Order.Status.PROCESSING);
                Order order = orderRepository.save(dataMapper.toOrder(cart));
                return dataMapper.toOrderDto(order);
            }
        }
        throw new RuntimeException("Unable to make an order");
    }

    @Override
    public int calculateCartSize(OrderDto cart) {
        int cartSize = 0;
        for (OrderItemDto orderItemDto : cart.getOrderItems()) {
            cartSize += orderItemDto.getAmount();
        }
        return cartSize;
    }
}
