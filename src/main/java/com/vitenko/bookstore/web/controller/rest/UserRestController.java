package com.vitenko.bookstore.web.controller.rest;

import com.vitenko.bookstore.exception.user.UserException;
import com.vitenko.bookstore.exception.user.UserNotFoundException;
import com.vitenko.bookstore.service.UserService;
import com.vitenko.bookstore.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping("/api/users/{id}")
    public UserDto getUser(@PathVariable Long id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @GetMapping("/api/users/all")
    public Page<UserDto> getAllUsers(Pageable page) {
        return userService.getAllUsers(page);
    }

    @PostMapping("/api/users/create")
    public UserDto createUser(@ModelAttribute UserDto userDto) throws UserException {
        return userService.create(userDto);
    }

    @PutMapping("/api/users/{id}")
    public UserDto editUser(@PathVariable Long id, @ModelAttribute UserDto userDto) throws UserException {
        userDto.setId(id);
        return userService.update(userDto);
    }

    @DeleteMapping("/api/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
