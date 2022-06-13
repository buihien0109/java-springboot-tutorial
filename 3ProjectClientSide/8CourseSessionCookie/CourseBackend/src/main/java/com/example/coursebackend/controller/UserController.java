package com.example.coursebackend.controller;

import com.example.coursebackend.model.User;
import com.example.coursebackend.repository.SessionRepository;
import com.example.coursebackend.request.LoginRequest;
import com.example.coursebackend.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/v1")
@AllArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;
    private final SessionRepository sessionRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = authenticationService.login(request);

        // Tạo session cho user
        // Genarate sessionId
        String sessionId = UUID.randomUUID().toString();
        sessionRepository.save(sessionId, user);

        // Tạo cookie
        ResponseCookie cookie = ResponseCookie.from("techmastercookie", sessionId)
                .maxAge(3600)
                .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        ResponseCookie cookie = ResponseCookie.from("techmastercookie", null)
                .maxAge(0)
                .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, "")
                .build();
    }

    @GetMapping("/cookies")
    public ResponseEntity<?> readAllCookies(HttpServletRequest request) {
        System.out.println("Vào đây");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String data =  Arrays.stream(cookies)
                    .filter(c -> c.getName().equals("techmastercookie"))
                    .map(Cookie::getValue).toList().toString();
            return ResponseEntity.ok(data);
        }

        return ResponseEntity.ok("No cookies");
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Xin chào");
    }
}
