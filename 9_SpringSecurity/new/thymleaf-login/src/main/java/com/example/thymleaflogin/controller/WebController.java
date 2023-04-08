package com.example.thymleaflogin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class WebController {
    @GetMapping("/")
    public String getHello(Model model) {
        model.addAttribute("name", "Bùi Hiên");
        model.addAttribute("users", List.of("Nguyễn Văn A", "Trần Văn B", "Ngô Thị C"));
        return "index";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("profile")
    public String getProfile() {
      return "profile";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("admin")
    public String getAdmin() {
        return "admin";
    }


    @PreAuthorize("hasRole('AUTHOR')")
    @GetMapping("author")
    public String getAuthor() {
        return "author";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = {"/abc", "/xyz", "/012"})
    @ResponseBody
    public String getPublicPage() {
        return "Public Page";
    }
}
