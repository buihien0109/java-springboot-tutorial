package com.example.basic.controller;

import com.example.basic.exception.BadRequestException;
import com.example.basic.request.LoginRequest;
import com.example.basic.request.RegisterUserRequest;
import com.example.basic.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/auth/login")
    public void login(@RequestBody LoginRequest request, HttpSession session) {
        authService.login(request, session);
    }

    @PostMapping("/auth/register")
    public String register(@RequestBody RegisterUserRequest request) {
        return authService.register(request);
    }

    @GetMapping("/auth/confirm")
    public String confirm(@RequestParam("token") String token) {
        return authService.confirm(token);
    }
}
