package com.vitenko.bookstore.web.controller.view;

import com.vitenko.bookstore.exception.user.UserEmailNotUniqueException;
import com.vitenko.bookstore.exception.user.UserEmailWasNotProvidedException;
import com.vitenko.bookstore.exception.user.UserNotFoundException;
import com.vitenko.bookstore.exception.user.UserPasswordNotProvidedException;
import com.vitenko.bookstore.service.UserService;
import com.vitenko.bookstore.service.dto.UserDto;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Log4j2
@RequiredArgsConstructor
@org.springframework.stereotype.Controller("user")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping(path = "/{id}")
    public String getUser(@PathVariable Long id, Model model) throws UserNotFoundException {
        UserDto userDto = userService.findById(id);
        model.addAttribute("userDto", userDto);
        model.addAttribute("date", LocalDateTime.now().toString());
        return "user/user";
    }

    @GetMapping(path = "/all")
    public String getAllUsers() {
        return "user/users";
    }

    @GetMapping(path = "/{id}/edit")
    public String editUserForm(@PathVariable Long id, Model model) throws UserNotFoundException {
        UserDto userDto = userService.findById(id);

        model.addAttribute("userDto", userDto);

        return "user/userEditForm";
    }

    @PostMapping(path = "/{id}/edit")
    public RedirectView editUser(@PathVariable Long id,
                                 @ModelAttribute("userDto") UserDto userDto,
                                 RedirectAttributes redirectAttributes, HttpSession session) throws
            UserEmailNotUniqueException, UserPasswordNotProvidedException,
            UserEmailWasNotProvidedException, UserNotFoundException {
        UserDto oldUserDto = userService.findById(id);

        oldUserDto.setEmail(userDto.getEmail());
        oldUserDto.setFirstName(userDto.getFirstName());
        oldUserDto.setLastName(userDto.getLastName());
        oldUserDto.setRole(userDto.getRole());

        UserDto createdUserDto = userService.update(oldUserDto);

        session.setAttribute("user", createdUserDto);

        redirectAttributes.addFlashAttribute("message", "User successfully edited.");

        return new RedirectView("/users/" + createdUserDto.getId());
    }
}
