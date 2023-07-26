package com.vitenko.bookstore.web.controller.impl;

import com.vitenko.bookstore.web.controller.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class NavigationController implements Controller {
    @GetMapping(path = "/home")
    public String getHomePage() {
        return "home";
    }
}
