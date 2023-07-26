package com.vitenko.bookstore.data.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "first_name", unique = false, nullable = true, length = 255)
    private String firstName;

    @Column(name = "last_name", unique = false, nullable = true, length = 255)
    private String lastName;

    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {ADMIN, MANAGER, CUSTOMER}
}
