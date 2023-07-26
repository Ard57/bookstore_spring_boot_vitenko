package com.vitenko.bookstore.service.dto;

import com.vitenko.bookstore.data.entity.Book;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {
    private Long id;
    private String name;
    private String author;
    private String isbn;
    private Integer pages;
    private Integer yearPublished;
    private BigDecimal price;
    private Book.Cover cover;
}
