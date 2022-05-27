package com.example.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @GetMapping("/shop")
    public String getShop() {
        return "shop";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }

    @GetMapping("/contact")
    public String getContact() {
        return "contact";
    }
}
