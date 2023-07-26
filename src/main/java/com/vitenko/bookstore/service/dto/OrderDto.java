package com.vitenko.bookstore.service.dto;

import com.vitenko.bookstore.data.entity.Order.Status;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private UserDto user;
    private Status status;
    private List<OrderItemDto> orderItems;
}
