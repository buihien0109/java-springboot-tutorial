package com.example.basicbackend.controller;

import com.example.basicbackend.model.UserDto;
import com.example.basicbackend.request.LoginRequest;
import com.example.basicbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        UserDto userDto = userService.login(request);
        return ResponseEntity.ok(userDto);
    }
}