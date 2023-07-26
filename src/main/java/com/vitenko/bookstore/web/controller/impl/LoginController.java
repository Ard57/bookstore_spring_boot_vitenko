package com.vitenko.bookstore.web.controller.impl;

import com.vitenko.bookstore.exception.user.UserEmailNotUniqueException;
import com.vitenko.bookstore.exception.user.UserEmailWasNotProvidedException;
import com.vitenko.bookstore.exception.user.UserNotFoundException;
import com.vitenko.bookstore.exception.user.UserPasswordNotProvidedException;
import com.vitenko.bookstore.service.CartService;
import com.vitenko.bookstore.service.UserService;
import com.vitenko.bookstore.service.dto.OrderDto;
import com.vitenko.bookstore.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Log4j2
@RequiredArgsConstructor
@org.springframework.stereotype.Controller("login")
public class LoginController {
    private final UserService userService;
    private final CartService cartService;

    @GetMapping(path = "/register")
    public String registrationForm() {
        return "user/userCreateForm";
    }

    @PostMapping(path = "/register")
    public RedirectView addUser(@RequestParam("email") String email,
                                @RequestParam("password") String password,
                                RedirectAttributes redirectAttributes,
                                HttpSession session) throws
            UserEmailNotUniqueException, UserPasswordNotProvidedException, UserEmailWasNotProvidedException {
        UserDto userDto = new UserDto();

        userDto.setEmail(email);
        userDto.setPassword(password);
        UserDto createdUserDto = userService.create(userDto);

        handleSessionAttributes(session, createdUserDto);
        session.setAttribute("user", createdUserDto);

        redirectAttributes.addFlashAttribute("message", "You are successfully registered!");

        return new RedirectView("/user/show?id=" + createdUserDto.getId());
    }

    @GetMapping(path = "/login")
    public String loginForm(@RequestParam(name = "message", required = false) String message, Model model) {
        model.addAttribute("message", message);
        return "user/userLoginForm";
    }

    @PostMapping(path = "/login")
    public RedirectView login(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              RedirectAttributes redirectAttributes, HttpSession session) throws
            UserNotFoundException {
        UserDto userDto = userService.login(email, password);

        handleSessionAttributes(session, userDto);
        session.setAttribute("user", userDto);

        StringBuilder message = new StringBuilder();
        message.append("Welcome, ");
        if (userDto.getFirstName() != null && !userDto.getFirstName().isBlank()
                && userDto.getLastName() != null && !userDto.getLastName().isBlank()) {
            message.append(userDto.getFirstName()).append(" ")
                    .append(userDto.getLastName()).append("!");
        } else {
            message.append(userDto.getEmail()).append("!");
        }

        redirectAttributes.addFlashAttribute("message", message.toString());

        return new RedirectView("/user/show?id=" + userDto.getId());
    }

    @GetMapping(path = "/logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/home");
    }

    private void handleSessionAttributes(HttpSession session, UserDto userDto) {
        OrderDto cart = (OrderDto) session.getAttribute("cart");

        if (session.getAttribute("user") != null &&
                !session.getAttribute("user").equals(userDto)) {
            session.removeAttribute("user");
            session.removeAttribute("cart");
            session.removeAttribute("cartSize");
        } else if (cart != null){
            cartService.setUser(cart, userDto);
        }
    }
}
