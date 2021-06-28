package com.nus.lighthouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @RequestMapping(value="/login.go")
    public String home() {
        return("Login");
    }
}
