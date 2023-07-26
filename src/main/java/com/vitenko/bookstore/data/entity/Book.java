package com.vitenko.bookstore.data.entity;

import javax.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "author", length = 255)
    private String author;

    @Column(name = "isbn", length = 25)
    private String isbn;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "year_published")
    private Integer yearPublished;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "cover")
    @Enumerated(EnumType.STRING)
    private Cover cover;

    public enum Cover {HARD, SOFT, SPECIAL}
}
