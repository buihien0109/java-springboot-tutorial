package com.example.registeruser.controller;

import com.example.registeruser.request.LoginRequest;
import com.example.registeruser.request.RegisterUserRequest;
import com.example.registeruser.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request, HttpSession session) {
        return authService.login(request, session);
    }

    @GetMapping("/logout")
    public String login(HttpSession session) {
        return authService.logout(session);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterUserRequest request) {
        return authService.register(request);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return authService.confirmToken(token);
    }
}
