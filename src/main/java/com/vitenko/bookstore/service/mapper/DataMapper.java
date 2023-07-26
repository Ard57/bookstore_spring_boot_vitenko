package com.vitenko.bookstore.service.mapper;

import com.vitenko.bookstore.data.entity.Book;
import com.vitenko.bookstore.data.entity.Order;
import com.vitenko.bookstore.data.entity.OrderItem;
import com.vitenko.bookstore.data.entity.User;
import com.vitenko.bookstore.service.dto.BookDto;
import com.vitenko.bookstore.service.dto.OrderDto;
import com.vitenko.bookstore.service.dto.OrderItemDto;
import com.vitenko.bookstore.service.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataMapper{
    public User toUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    public BookDto toBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setAuthor(book.getAuthor());
        bookDto.setId(book.getId());
        bookDto.setCover(book.getCover());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setName(book.getName());
        bookDto.setPages(book.getPages());
        bookDto.setPrice(book.getPrice());
        bookDto.setYearPublished(book.getYearPublished());
        return bookDto;
    }

    public Book toBook(BookDto bookDto) {
        Book book = new Book();
        book.setAuthor(bookDto.getAuthor());
        book.setId(bookDto.getId());
        book.setCover(bookDto.getCover());
        book.setIsbn(bookDto.getIsbn());
        book.setName(bookDto.getName());
        book.setPages(bookDto.getPages());
        book.setPrice(bookDto.getPrice());
        book.setYearPublished(bookDto.getYearPublished());
        return book;
    }

    private OrderItemDto toOrderItemDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setBook(toBookDto(orderItem.getBook()));
        orderItemDto.setAmount(orderItem.getAmount());
        orderItemDto.setPrice(orderItem.getPrice());
        return orderItemDto;
    }

    private OrderItem toOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setBook(toBook(orderItemDto.getBook()));
        orderItem.setAmount(orderItemDto.getAmount());
        orderItem.setPrice(orderItemDto.getPrice());
        return orderItem;
    }

    public OrderDto toOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUser(toUserDto(order.getUser()));
        orderDto.setStatus(order.getStatus());

        List<OrderItemDto> orderItems = new ArrayList<>();
        for (OrderItem orderItem : order.getOrderItems()) {
            OrderItemDto orderItemDto = toOrderItemDto(orderItem);
            orderItemDto.setOrder(orderDto);
            orderItems.add(orderItemDto);
        }
        orderDto.setOrderItems(orderItems);

        return orderDto;
    }

    public Order toOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setUser(toUser(orderDto.getUser()));
        order.setStatus(orderDto.getStatus());

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDto orderItemDto : orderDto.getOrderItems()) {
            OrderItem orderItem = toOrderItem(orderItemDto);
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);

        return order;
    }
}
