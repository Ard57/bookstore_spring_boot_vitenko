package com.vitenko.bookstore.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString(exclude = "order")
public class OrderItemDto {
    private Long id;
    @JsonIgnore
    private OrderDto order;
    private BookDto book;
    private BigDecimal price;
    private Integer amount;
}
