package com.example.basic.controller;

import com.example.basic.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class WebController {

    private final UserService userService;

    @GetMapping("/")
    public String getHome() {
        return "/theme/index";
    }

    @GetMapping("/shop")
    public String getShop() {
        return "/theme/shop";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "/theme/about";
    }

    @GetMapping("/contact")
    public String getContact() {
        return "/theme/contact";
    }

    @GetMapping("/variable")
    public String getVariable(Model model) {
        model.addAttribute("user", userService.getUserById(1));
        model.addAttribute("otherUser", userService.getUserById(2));
        model.addAttribute("users", userService.getUsers());

        model.addAttribute("id", 3);
        model.addAttribute("page", 3);
        model.addAttribute("limit", 10);
        model.addAttribute("lang", "en");

        return "/variable/index";
    }

    @GetMapping("/condition")
    public String getCondition(Model model) {
        model.addAttribute("age", 17);
        model.addAttribute("day", 5);
        return "/condition/index";
    }

    @GetMapping("/class")
    public String getClass(Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("user", userService.getUserById(1));
        model.addAttribute("isAdmin", true);
        return "/class/index";
    }

    @GetMapping("/loop")
    public String getLoop(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "/loop/index";
    }
}
