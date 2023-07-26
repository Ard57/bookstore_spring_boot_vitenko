package com.vitenko.bookstore.web.controller.impl;

import com.vitenko.bookstore.data.entity.User;
import com.vitenko.bookstore.exception.user.UserEmailNotUniqueException;
import com.vitenko.bookstore.exception.user.UserEmailWasNotProvidedException;
import com.vitenko.bookstore.exception.user.UserNotFoundException;
import com.vitenko.bookstore.exception.user.UserPasswordNotProvidedException;
import com.vitenko.bookstore.service.UserService;
import com.vitenko.bookstore.service.dto.UserDto;
import com.vitenko.bookstore.web.controller.Controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@org.springframework.stereotype.Controller("user")
public class UserController implements Controller {
    private final UserService userService;

    @GetMapping(path = "/user/show")
    public String getUser(@RequestParam("id") Long id, Model model) throws UserNotFoundException {
        UserDto userDto = userService.findById(id);
        model.addAttribute("userDto", userDto);
        model.addAttribute("date", LocalDateTime.now().toString());
        return "user/user";
    }

    @GetMapping(path = "/user/all")
    public String getAllUsers(Model model) {
        List<UserDto> userDtos = userService.getAllUsers();
        model.addAttribute("userDtos", userDtos);
        model.addAttribute("date", LocalDateTime.now().toString());
        return "user/users";
    }

    @GetMapping(path = "/user/edit")
    public String editUserForm(@RequestParam("id") Long id, Model model) throws UserNotFoundException {
        UserDto userDto = userService.findById(id);

        model.addAttribute("userDto", userDto);

        return "user/userEditForm";
    }

    @PostMapping(path = "/user/edit")
    public RedirectView editUser(@RequestParam("id") Long id,
                                 @RequestParam("email") String email,
                                 @RequestParam(value = "first_name", required = false) String firstName,
                                 @RequestParam(value = "last_name", required = false) String lastName,
                                 @RequestParam(value = "role") String role,
                                 RedirectAttributes redirectAttributes, HttpSession session) throws
            UserEmailNotUniqueException, UserPasswordNotProvidedException,
            UserEmailWasNotProvidedException, UserNotFoundException {
        UserDto userDto = userService.findById(id);

        userDto.setEmail(email);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setRole(User.Role.valueOf(role));

        UserDto createdUserDto = userService.update(userDto);

        session.setAttribute("user", createdUserDto);

        redirectAttributes.addFlashAttribute("message", "User successfully edited.");

        return new RedirectView("/user/show?id=" + createdUserDto.getId());
    }
}
