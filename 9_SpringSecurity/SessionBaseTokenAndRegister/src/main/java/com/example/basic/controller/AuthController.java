package com.example.basic.controller;

import com.example.basic.exception.NotFoundException;
import com.example.basic.request.LoginRequest;
import com.example.basic.request.RegisterUserRequest;
import com.example.basic.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest request, HttpSession session) {
        authService.login(request, session);
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
