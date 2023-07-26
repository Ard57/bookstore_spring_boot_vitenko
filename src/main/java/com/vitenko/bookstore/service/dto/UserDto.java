package com.vitenko.bookstore.service.dto;

import com.vitenko.bookstore.data.entity.User.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
}
