package com.vitenko.bookstore.web.controller.view;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class NavigationController {
    @GetMapping(path = "/home")
    public String getHomePage() {
        return "home";
    }
}
