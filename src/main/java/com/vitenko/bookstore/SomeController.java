package com.vitenko.bookstore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SomeController {
    @GetMapping(path = "/some")
    @ResponseBody
    public String some() {
        return "Some response";
    }
}
