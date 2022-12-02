package com.example.basic.controller;

import com.example.basic.request.LoginRequest;
import com.example.basic.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request, HttpServletResponse httpServletResponse) {
        return authService.login(request, httpServletResponse);
    }

    @GetMapping("/logout")
    public String login(HttpSession session) {
        return authService.logout(session);
    }
}
