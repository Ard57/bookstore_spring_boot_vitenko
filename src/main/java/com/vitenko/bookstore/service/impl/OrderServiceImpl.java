package com.vitenko.bookstore.service.impl;

import com.vitenko.bookstore.data.entity.Order;
import com.vitenko.bookstore.data.repository.BookRepository;
import com.vitenko.bookstore.data.repository.OrderRepository;
import com.vitenko.bookstore.exception.book.BookNotFoundException;
import com.vitenko.bookstore.exception.cart.CartException;
import com.vitenko.bookstore.exception.order.IllegalOrderArgumentException;
import com.vitenko.bookstore.exception.order.OrderNotFoundException;
import com.vitenko.bookstore.service.OrderService;
import com.vitenko.bookstore.service.dto.BookDto;
import com.vitenko.bookstore.service.dto.OrderDto;
import com.vitenko.bookstore.service.dto.OrderItemDto;
import com.vitenko.bookstore.service.dto.UserDto;
import com.vitenko.bookstore.service.mapper.DataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final DataMapper dataMapper;

    @Override
    public OrderDto create(OrderDto orderDto) throws IllegalOrderArgumentException {
        log.debug("Creating order");
        orderDto.setStatus(Order.Status.PROCESSING);
        validate(orderDto);
        Order order = orderRepository.save(dataMapper.toOrder(orderDto));
        return dataMapper.toOrderDto(order);
    }

    @Override
    public OrderDto findById(Long id) throws OrderNotFoundException {
        log.debug("Retrieving order by ID");
        Order order = orderRepository.findById(id).
                orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " wasn't found."));
        return dataMapper.toOrderDto(order);
    }

    @Override
    public List<OrderDto> findByUserEmail(String email) {
        log.debug("Retrieving all orders with matching last name");
        return orderRepository.findByUserEmail(email)
                .stream()
                .map(dataMapper::toOrderDto)
                .toList();
    }

    @Override
    public List<OrderDto> getAllOrders() {
        log.debug("Retrieving all orders");
        return orderRepository.findAll()
                .stream()
                .map(dataMapper::toOrderDto)
                .toList();
    }

    @Override
    public OrderDto update(OrderDto orderDto) throws IllegalOrderArgumentException {
        log.debug("Updating order");
        validate(orderDto);
        Order order = orderRepository.save(dataMapper.toOrder(orderDto));
        return dataMapper.toOrderDto(order);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Deleting order by id");
        orderRepository.deleteById(id);
    }

    private void validate(OrderDto orderDto) throws IllegalOrderArgumentException {
        if (orderDto.getUser() == null) {
            throw new IllegalOrderArgumentException("Customer for order must be specified.");
        }
        if (orderDto.getStatus() == null) {
            throw new IllegalOrderArgumentException("Order must have correct status.");
        }
        if (orderDto.getOrderItems() == null || orderDto.getOrderItems().isEmpty()) {
            throw new IllegalOrderArgumentException("List of order items must be not empty.");
        }
        for (OrderItemDto item : orderDto.getOrderItems()) {
            if (item == null) {
                throw new IllegalOrderArgumentException("Order cannot contain empty items.");
            }
            if (item.getBook() == null) {
                throw new IllegalOrderArgumentException("Order item must have specified book");
            }
            if (item.getPrice() == null || (item.getPrice().compareTo(new BigDecimal("0.01")) <= 0)) {
                throw new IllegalOrderArgumentException("Order item price must be specified");
            }
            if (item.getAmount() == null || item.getAmount() < 1) {
                throw new IllegalOrderArgumentException("Order item amount must be specified");
            }
        }
    }

    @Override
    public int getOrderSize(OrderDto cart) {
        int cartSize = 0;
        for (OrderItemDto orderItemDto : cart.getOrderItems()) {
            cartSize += orderItemDto.getAmount();
        }
        return cartSize;
    }

    @Override
    public BigDecimal getOrderTotalPrice(OrderDto orderDto) {
        if (orderDto == null) {
            return new BigDecimal("0");
        }
        BigDecimal totalPrice = new BigDecimal(0);
        if (orderDto.getStatus() == Order.Status.CART) {
            for (OrderItemDto orderItemDto : orderDto.getOrderItems()) {
                totalPrice = totalPrice.add(
                        orderItemDto.getBook().getPrice().multiply(
                                new BigDecimal(orderItemDto.getAmount())));
            }
        } else {
            for (OrderItemDto orderItemDto : orderDto.getOrderItems()) {
                totalPrice = totalPrice.add(
                        orderItemDto.getPrice().multiply(
                                new BigDecimal(orderItemDto.getAmount())));
            }
        }
        return totalPrice;
    }

    @Override
    public OrderDto createCart() {
        OrderDto cart = new OrderDto();
        cart.setOrderItems(new ArrayList<>());
        cart.setStatus(Order.Status.CART);
        return cart;
    }

    @Override
    public OrderDto addItem(OrderDto cart, Long id, Integer amount) throws BookNotFoundException {
        BookDto bookDto = dataMapper.toBookDto(bookRepository.findById(id).
                orElseThrow(() -> new BookNotFoundException("Book with id " + id + " wasn't found")));
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
    public OrderDto makeOrder(OrderDto cart) throws CartException {
        if (cart == null) {
            throw new CartException("Unable to make an order. Cart isn't set.");
        }
        int cartSize = getOrderSize(cart);

        if (cart.getUser() == null) {
            throw new CartException("Unable to make an order. You must be logged in to make an order.");
        }
        if (cartSize < 1) {
            throw new CartException("Unable to make an order. Cart is empty");
        }
        cart.setStatus(Order.Status.PROCESSING);

        for (OrderItemDto item : cart.getOrderItems()) {
            item.setPrice(item.getBook().getPrice());
        }

        Order order = orderRepository.save(dataMapper.toOrder(cart));
        return dataMapper.toOrderDto(order);
    }
}